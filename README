
# SpringBoot Hilton project

This project was made to showcase development skills

## Description

Project uses spring cache, spring jpa, rest template to search geolocation data by ipAddress

### Configuration

To setup the project use MySQL in local computer or remote host, add the connection details in
/src/main/java/resources/application.properties

```
spring.datasource.url = jdbc:mysql://localhost:3306/hiltondb?createDatabaseIfNotExist=true
spring.datasource.username = root
spring.datasource.password=
spring.datasource.driver-class-name = com.mysql.jdbc.Driver

# my local db is not using a password
```

To execute project run /src/main/java/HiltonApplication.main() method in intelliJ or your preferred IDE. You should see
this finally in the logs

```
2024-07-26T08:08:00.541-05:00  INFO 1838 --- [SpringBootHilton] [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2024-07-26T08:08:00.708-05:00  WARN 1838 --- [SpringBootHilton] [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2024-07-26T08:08:00.859-05:00  INFO 1838 --- [SpringBootHilton] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
2024-07-26T08:08:00.864-05:00  INFO 1838 --- [SpringBootHilton] [           main] com.hilton.HiltonApplication             : Started HiltonApplication in 1.779 seconds (process running for 1.936)
```

You can use postman project to test endpoints or hit in any browser localhost:8080/geolocation/1.1.12.123

I also added a swagger file


