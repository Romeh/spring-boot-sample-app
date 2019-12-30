## A spring boot 2 sample app project ![Twitter Follow](https://img.shields.io/twitter/follow/mromeh.svg?style=social)

## About

Sample app generated from my spring boot custome maven archetype : https://github.com/Romeh/spring-boot-quickstart-archtype

for more details about it , check my posts here : 
- https://mromeh.com/2017/12/04/spring-boot-integration-test-with-cucumber-and-jenkins-pipeline/
- https://mromeh.com/2017/12/04/spring-boot-with-embedded-config-server-via-spring-cloud-config/
- https://mromeh.com/2017/12/04/spring-boot-integration-test-with-cucumber-and-jenkins-pipeline/

## Technical Stack

- Java 1.8+
- Maven 3.5+
- Spring boot 2.2.2.RELEASE
- Lombok abstraction
- JPA with H2 for explanation
- Swagger 2 API documentation
- Spring retry and circuit breaker for external service call
- REST API model validation 
- Spring cloud config for external configuration on GIT REPO
- Cucumber and Spring Boot test for integration test
- Jenkins Pipeline for multi branch project
- Continuous delivery and integration standards with Sonar check and release management
- Support retry in sanity checks  

## Installation

To run locally , you need to configure the run configuration by passing :
- VM parameter: -DLOG_PATH=../log
- Set SPRING profile to LOCAL 

Test on the browser via SWAGGER
-------------------

```sh
http://localhost:8080/swagger-ui.html
```

## License

This software is licensed under the [BSD License][BSD]. For more information, read the file [LICENSE](LICENSE).

[BSD]: https://opensource.org/licenses/BSD-3-Clause
