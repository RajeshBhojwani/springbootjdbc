
# Overview
In this project, We will show how Spring boot and spring data can be used to integrate with postgresql.


## Step 1 Download the code from github
```git clone https://github.com/RajeshBhojwani/springbootjdbc.git```

## Step 2 Build the project and run Integration Test
```gradlew clean build```

## Step 3 If you want to start the application
```java -jar build/libs/springbootjdbc-0.0.1-SNAPSHOT.jar```

## Launch H2 Console
http:/localhost:8100/h2-console
Create Table in H2
```CREATE TABLE users (record_id bigint NOT NULL AUTO_INCREMENT, name varchar(100), address varchar(250), email varchar(100), PRIMARY KEY (record_id)); ```
## Go to test folder and check all the test classes to understand the Integration Test implementation.

## Create a New record - POST method
http://localhost:8100/users

## Retrieve users -GET method
http://localhost:8100/users

## Retrieve user by id
http://localhost:8100/users/1







