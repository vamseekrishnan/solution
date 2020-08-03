# Api to Test If give cities are connected

### Sample URL & Data to validate
http://localhost:8080/connected?origin=Boston&destination=Newark
should return ``yes``

Sample Data loaded with in application:
```
Boston,New York
Philadelphia,Newark
Newark,Boston
Trenton,Albany
```

### To Build
``mvn clean install``

### Run Spring Boot app
``mvn spring-boot:run``

### Test coverage report
``mvn test jacoco:report``

### Swagger URL 
http://localhost:8080/swagger-ui.html

##$ log file 
Log file `` connectedcities.log `` rotates under root folder. Can be overriden using application properties or runtime parameters
TODO : LogBack 

## Profiles:
TODO : dev/int/staging/prod

## Smoke suite
TODO :

## Fat Jar
TODO : Include in maven 

## deployment scripts
TODO








