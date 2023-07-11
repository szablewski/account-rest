# REST API account
REST API to create an account
and currency exchange in the PLN<->USD pair.

### Technologies

* Java 17
* Spring Boot 3
* Maven 
* JUnit 5
* Hamcrest

### Guides

* POST api/v1/accounts - create account
* GET api/v1/accounts/{accountId} - get account by id
* POST api/v1/accounts/{accounts}/exchange?code=code - currency exchange, the "code" parameter allows us to exchange the account balance into any currency