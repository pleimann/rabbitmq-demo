docker_compose(['./docker-compose.yml', './apps/docker-compose.yml'])

dc_resource('rabbitmq', labels=['core'], trigger_mode=TRIGGER_MODE_MANUAL)
dc_resource('prometheus', labels=['core'], trigger_mode=TRIGGER_MODE_MANUAL)

for app in ['producer', 'consumer' ]:
  dc_resource(app, labels=['apps'], resource_deps=['rabbitmq'])

  docker_build('apps/' + app,
    context = os.path.join('.', 'apps', app),
    dockerfile = 'apps/Dockerfile',
    build_args = {
      'project_name': app
    },
  )
