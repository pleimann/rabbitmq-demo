FROM maven:3-openjdk-17 AS build
WORKDIR /app/

# copy the Project Object Model file
COPY ./pom.xml ./pom.xml

# fetch all dependencies
RUN mvn dependency:go-offline -B

# copy your other files
COPY ./src ./src

# build for release
ARG project_name
RUN mvn -DskipTests=true package && mv target/$project_name-*.jar ./app.jar

RUN java -Djarmode=layertools -jar app.jar extract

ENTRYPOINT ["/bin/bash"]


FROM openjdk:17-bullseye

WORKDIR /app/
EXPOSE 8080

COPY --from=build /app/dependencies/ ./
COPY --from=build /app/snapshot-dependencies/ ./
COPY --from=build /app/spring-boot-loader/ ./
COPY --from=build /app/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]