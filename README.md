# CustomerRewards

Customer Rewards Service
-------------------------

This service module calculates reward points for customers based on their transaction amount and its respective
 transaction dates.
 This module is built using Java 11, Spring Boot 2.7.2, H2 DB (in-memory hql),Spring MVC, Spring Data JPA, Junit 5.
 
 
 To build and run this module, the following is needed :
 1. JDK 11
 2. Apache Maven 3.5.0
 
 To build:
 Use the following command on the root folder of the project:
 > mvn clean install 
 
 To run:
 Use the following command on the root folder of the project:
 > mvn spring-boot:run
 
 
 Eclipse:
 This is maven project, in Eclipse import this project as existing maven project ,
  build and run using CustomerRewardsApplication as Spring boot application class.
 
 Log file:
 Logback.xml file present under src/main/resources can be configured. The default log location is c:/temp/
 
 External properties and logback files:
 To use external files, the following VM arguments can be used :
 -Dspring.config.location=C:\workspace-springboot\src\main\resources\application.properties 
 -Dlogback.configurationFile=C:\workspace-springboot\src\main\resources\logback.xml 
 
 To access the service:
 http://localhost:8080/store/customer/getRewardsPoints/301
 
 Code coverage stats:
 SonarQube is used to run code coverage stats.
 RewardsService - 95.5%
 RewardsController - 79.2%
 
 
