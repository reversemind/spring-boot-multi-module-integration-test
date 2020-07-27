# Integration tests in Spring Boot with multiple modules (Maven and Gradle)

!!! WARNING !!!

Java 11 only


spring-boot-maven-plugin

## Description

modules
- synth-app = main application that utilizes remote service via mock
- mock-app  = mock for remote service
- test-app  = integration tests

## How to run Multi-module integration tests with mock

```bash
mvn clean install
```

Runs in specific order:

1. mock-app will start (forked JVM) and will expose service at end-point http://localhost:9999/mock
2. synth-app will start, will use mock-app as remote service and will also expose service for integration test(test-app) http://localhost:9081/mock
3. test-app will start and run integration tests to call synth-app  
4. 


## Bash scripts to track application port statuses

ports:
- 8091 = synth-app
- 9999 = mock-app
- 9001 = default jmx port of forked JVM
- 9002 = jmx port of forked JVM

macOS specific 'netstat' call
```bash
for i in {1..10000}; do netstat -an -ptcp | grep "9081\|9999\|9001\|9002"; sleep 1; echo "\n-${i}--------"; done
```


## Maven

### Mock App

start separately
```bash
mvn clean install -pl mock-app spring-boot:run
```

## Synth App

start separately
```bash
mvn clean install -pl synth-app spring-boot:run
```


-Djava.net.preferIPv4Stack=true -Djava.net.preferIPv4Addresses=true