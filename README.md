Lotto Ticketing and Payout Machine
---
The Lotto Ticketing and Payout Machine is a system that allows 
users to purchase different types of lotto tickets 
and manage their transactions. The machine operates in-memory
and does not require a database.


Technologies used
----------------

- Java 11
- spring-boot
- maven 
- This services uses : H2 Memory Database
- MySQL - a test as requested 
- install postman

How to run this service
----------------------

``````
$ mvn clean install
$ mvn spring-boot:run
``````

- URL for the console: http://localhost:8083/h2-ui

To test the service
-----------------
Please use the postman collection 


file named: lotto-machine-test.postman_collection.json

To improve:

- Error handling and optimise some calculations
