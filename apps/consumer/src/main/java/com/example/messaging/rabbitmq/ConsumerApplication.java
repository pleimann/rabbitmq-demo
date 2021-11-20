package com.example.messaging.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@EnableRabbit
@SpringBootApplication
public class ConsumerApplication {
  public static final String QUEUE_NAME = "myQueue";

  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(ConsumerApplication.class, args);
  }

  @Component
  @RabbitListener(id="mikes-consumer", queues=QUEUE_NAME)
  public static class Consumer {
    @RabbitListener
    public String onUser(Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag, @Payload UserMessage userMessage) throws IOException {
      log.info(tag + ": " + userMessage.toString());

      return userMessage.userName() + " is the man!";
    }
  }
}
