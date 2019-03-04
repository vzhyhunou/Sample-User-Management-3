## Sample multi project application for organisation chart management (Brest-Java-Course-2018)

[![Build Status](https://travis-ci.org/Brest-Java-Course-2018/Sample-User-Management.svg?branch=master)](https://travis-ci.org/Brest-Java-Course-2018/Sample-User-Management)
[![Coverage Status](https://coveralls.io/repos/github/Brest-Java-Course-2018/Sample-User-Management/badge.svg)](https://coveralls.io/github/Brest-Java-Course-2018/Sample-User-Management)

java 1.8 and Maven 3.5 required for build

You can get it at:

        http://maven.apache.org

#### Check environment configuration
        
        $ java -version
        
        $ export JAVA_HOME = ...
        
        $ mvn -version
        
#### Build project

        $ mvn clean install

There is a Maven wrapper included with the project. In order to use it substitute "mvn" command with the wrapper script:

        $ ./mvnw clean install

Windows users should use mvnw.cmd script correspondingly. Please note that calling Maven wrapper for the very first time might take a while to complete. Wrapper will be downloading Maven executable from Maven repos and storing it locally. All the subsequent calls will have no delays.
        
#### Preparing reports
      
        $ mvn site
      
        $ mvn site:stage
      
        check: ``<project>/target/stage/index.html``

#### Use embedded jetty server for REST application test

        mvn -pl rest-app/ jetty:run
        
        Once started, the REST server should be available at:
        
        http://localhost:8088
        
        Try CURL:
        
        curl -v localhost:8088/departments
        
        curl -v localhost:8088/departments/1
        
        curl -H "Content-Type: application/json" -X POST -d '{"departmentName":"xyz","description":"xyz"}' -v localhost:8088/departments
        
        curl -X "DELETE" localhost:8088/departments/7
        

#### Use embedded jetty server for WEB application test

       mvn -pl web-app/ jetty:run

       Once started, the application should be available at:

       http://localhost:8080

#### Using command line rest client

       cd client-rest/target && java -jar client-rest-1.0-SNAPSHOT.jar

#### Travis CI integration

        https://travis-ci.org/Brest-Java-Course-2018/Sample-User-Management/

