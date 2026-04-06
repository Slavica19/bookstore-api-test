# **API Test Automation Project**

### **Overview**

This project contains automated API tests for Books and Authors services.
It is using:
* 	Java
* 	REST Assured
* 	TestNG
* 	Allure Reports

The test suite validates both happy paths and edge cases for CRUD operations.

### **Test Coverage:**

**Books API**
* Get all books
* Get book by ID
* Create book
* Update book
* Delete book

**Books Edge Cases**
* Non-existent ID (404)
* Invalid payload (400)
* Empty body (400)
* Invalid path parameters


**Authors API**
* Get all authors
* Get author by ID
* Create author
* Update author
* Delete author

**Authors Edge Cases**
* Non-existent ID (404)
* Invalid payload (400)
* Empty body (400)
* Invalid path parameters


### **Project Structure**

* src/main/java/client/        - API client classes (HTTP requests)
* src/main/java/model/         - Data models (POJO)
* src/main/java/com/config/    - config
* src/main/java/com/utils/     - Utility classes (specbuilder)
* src/test/java/assertions/    -response assertion
* src/test/java/factory/       -Test classes 
* src/test/java/tests          - Test classes
* src/test/resources/          - Config files 

### **How to Run Tests**
1. Clone the repository

   git clone https://github.com/Slavica19/bookstore-api-test
 
   cd bookstore-api-test

2. Run tests using Maven

   mvn clean test

3. Run specific groups

   mvn clean test -Dgroups=happy

   mvn clean test -Dgroups=edge

### **Allure Reporting:**

allure generate target/allure-results

allure serve target/allure-results

**Features used:**

•	@Epic
•	@Feature
•	@Story
•	@Severity
•	@Description
•	@Tag


### **Docker Usage**

You can run the tests inside a Docker container with Maven, JDK 21, and Allure installed.

#### **Build the Docker Image:**

docker build --no-cache -t books-authors-tests .

#### **Run the Tests in a Container:**

docker run --rm -v ${PWD}:/app books-authors-tests

--rm -> removes the container after finishing.

-v ${PWD}:/app -> keeps Allure reports on the host machine.

#### **Run the Tests with a Custom Environment Variable:**

docker run --rm -v ${PWD}:/app -e BASE_URL=https://fakerestapi.azurewebsites.net/ api-tests

-e BASE_URL -> sets the environment variable inside the container.

#### **View Allure Report:**

allure open target/allure-report



### **CI Pipeline**

The project uses GitHub Actions to automatically run tests and publish Allure reports to GitHub Pages whenever code is pushed to master.

#### **Notes:**

* Runs automatically on push to master.

* Generates Allure report and deploys to gh-pages branch.

* Maintains a dashboard with history and latest report.

* Report can be find: https://slavica19.github.io/bookstore-api-test/

### **Key Design Patterns**

**Factory Pattern**

Used for generating test data:
* BookFactory
* AuthorFactory

**Assertion Helpers**

 Reusable validations:
* BookAssertions
* AuthorAssertions

**Client Layer**

Encapsulates API calls:
* 	BooksClient
* 	AuthorsClient

**Assertions Strategy**

* SoftAssert for validating multiple fields in one test
* Custom assertion helpers for reusable validations
* Response validation:
            Status codes, 
            Response body structure,
            Data integrity,

**Technologies**

REST Assured =	API testing

TestNG	= Test framework

Allure = 	Reporting

Maven = 	Build tool

Docker=  Containerized test execution

**Notes:**

Tests are grouped by:

* 	happy → Positive scenarios
* 	edge → Negative & edge cases

   All responses are validated for: 
* 	Status codes
* 	Data correctness
* 	Schema consistency (via assertions)



