# Sample multi project application for organisation chart management (Brest-Java-Course-2018)

[![Build Status](https://travis-ci.org/Brest-Java-Course-2018/Sample-User-Management.svg?branch=master)](https://travis-ci.org/Brest-Java-Course-2018/Sample-User-Management)
[![Coverage Status](https://coveralls.io/repos/github/Brest-Java-Course-2018/Sample-User-Management/badge.svg)](https://coveralls.io/github/Brest-Java-Course-2018/Sample-User-Management)

    java 1.8 and Maven 3.5 required for build

    You can get it at:

        http://maven.apache.org

    1. Check environment configuration
        
        $ java -version
        
        $ export JAVA_HOME = ...
        
        $ mvn -version
        
    2. Build project

        $ mvn clean install
        
    3. Preparing reports
      
        $ mvn site
      
        $ mvn site:stage
      
        check: ``<project>/target/stage/index.html``

    4. Use embedded jetty server for application test

        $ cd ./web-app

        $ mvn jetty:run

       Once started, the application should be available at:

       http://localhost:8080

    5. Travis CI integration

        https://travis-ci.org/Brest-Java-Course-2018/Sample-User-Management/

