
#  Producer-consumer/Test project
* Test task for a vacancy Java developer
    * Сreated 20 January 2021

##Task condition
* Реализовать 2 сервиса:

1) Содержит эндпоинт POST /producer?callTimes=${var}
2) Содержит эндпоинты: а) POST /consumer, б) GET /consumer

* Описание API:

1) POST /producer?callTimes=${var}

Реализация: По вызову эндпоинта сделать ${var} запросов к эндпоинту POST /consumer другого сервиса

2) POST /consumer

   Реализация: Вывод в консоль ip, с которого пришел запрос. Запись статистики в jmx о том кто(ip) и сколько раз вызвал данный метод

3) GET /consumer

   Реализация: Возвращает информацию из jmx по статистике вызова POST /consumer

* Требования к выполненному тестовому заданию:
1) выполненное тестовое задание должно быть размещено на gitlab
2) приложение должно запускаться в докере (требуется написать Dockerfile и docker-compose.yml)
3) тесты запускаются пайпланом на gitlab
4) статистика по вызову доступна как по HTTP (GET /consumer), так и по jmx (jconsole)


## Technologies
* Multi-module maven project 
* Version Control System - _Git_
* Technologies:
    * _Java 11_
    * _Log4j2_
    * _Spring boot 2_
    * _Spring 5_
    * _Docker_
    * _Docker-compose_
* Unit and Integration application tests
* Using _GitHub Actions_ to Build a Project

## Getting started

### _for the project start it is required:_
* Git
* Docker
* Docker-compose

### _add your HOST settings_

* producer-spring-boot-module/.../application.properties
```
# Docker consumerHOST
consumer.url=http://YOUR_HOST:8081/consumer
```

* consumer-spring-boot-module/.../application.properties
```
# Docker jmxHOST
jmx.url=YOUR_HOST:9000
```

### _authentication_
* you need to use basic authentication for /producer endpoint
 ```
username=admin
password=admin
```

### _commands:_
```
$ git clone -b develop https://github.com/YauhenBelanovich/producer-consumer.git
$ cd producer-consumer
$ docker-compose up -d
```

## Authors

* **Yauheni Belanovich** - [YauhenBelanovich](https://github.com/YauhenBelanovich)