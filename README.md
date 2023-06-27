Lotto Ticketing and Payout Machine
---
The Lotto Ticketing and Payout Machine is a system that allows 
users to purchase different types of lotto tickets 
and manage their transactions. The machine operates in-memory
and does not require a database.


Technologies used
----------------

- Java
- spring-boot
- maven 
- This service uses: H2 Memory Database
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
Please use the Postman collection 


The file named: lotto-machine-test.postman_collection.json

To improve:

- Error handling and optimize some calculations
- Database Transactions: When dealing with database operations, 
  it's important to ensure the proper use of transactions 
  to maintain data integrity. Consider reviewing and verifying 
  that database operation are appropriately wrapped in transactions 
  to handle failures and rollbacks if needed.
- Unit Testing: Implement unit tests to cover different scenarios 
  and validate the correctness of the implementation. 
  This will help identify any issues and ensure the desired behavior of the Lotto Machine.
- Code organizations
