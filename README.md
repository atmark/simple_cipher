# SIMPLE CIPHER APP
A simple cipher app that performs Caesar and Vigenère Cipher. Build with springboot and springboot default database H2DB, with Thymleaf for frontend

Environment should have `java` and `maven` pre installed.

Running
--------

 Clean and compile:
 
     mvn clean install
     
 On first setup this will download all the dependencies of the project.
     
 (Optional)Skipping unit tests
     
     mvn clean install -DskipTests
     
 Run with embeded server tomcat:
 
     mvn spring-boot:run
     
 Run with debug. This will start remote debugging
 
    mvn spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8001"

 Once started, the application should be available at:
 
     http://localhost:8081
