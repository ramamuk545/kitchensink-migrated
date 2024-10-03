# Modernization Factory: Developer Candidate Challenge

The application 'kitchensink' at https://github.com/jboss-developer/jboss-eap-quickstarts/tree/8.0.x/kitchensink has been migrated to Spring Boot Application.

## Requirements
Java 21  
Spring Boot 3.3.4  
H2 Database  
JBoss EAP 8.0  

## Installation
### package war file
```sh
mvn clean package
```
Rename "kitchensink-1.0.0.war" in "/target" directory to "kitchensink.war".    
Deploy "kitchensink.war" by copying this war file to JBoss EAP 8.0 server's "/standalone/deployments" location.  

## API Endpoints
### Postman can be used for consuming below API
```sh
# Get all members
GET http://localhost:8080/kitchensink/rest/members
# Get member by Id
GET http://localhost:8080/kitchensink/rest/members/0
# Create member
POST http://localhost:8080/kitchensink/rest/members
```
## JSF Integration
### Please note that I had started to work on JSF Integration into Spring Boot for migrating "index.xhtml", to Register members and display all members in a Table. But this is work in progress and needs more time to integrate and test.
```sh
This can be done using:
1. Request Scope
2. Event Listener
3. JSF dependencies - There seems to be few options here
```
## How I migrated
```sh
# Legacy "kitchensink"
1. Reviewed maven dependencies of legacy "kitchensink" application, to understand frameworks and libraries used.
2. Reviewed README of legacy "kitchensink" application, packaged it and deployed to JBoss EAP 8.0
3. Reviewed code: Service, Controller, API endpoints, Repository, JSF view and H2 tables

# Spring Boot "kitchensink"
1. I started from DB persistance and moved forward to Service and Controller
2. JPA Entity "Member" was copied with no changes
3. I used Spring Data JPA for persistance
4. Business logic is in Service components
5. API Endpoints are in RestController
6. RestController uses Service components
```