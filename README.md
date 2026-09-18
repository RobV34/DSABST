[BST-TreeApp-README.md](https://github.com/user-attachments/files/32393975/BST-TreeApp-README.md)
Balanced Binary Tree Program

Spingboot/Maven/Java/Thymeleaf/Postgres

Input numbers and display the binary tree structure and root. 
Show previous trees and store inputs and structures in database. 
# Binary Search Tree / AVL Tree App

A Java Spring Boot web application that builds a balanced tree from user-entered numbers, displays the resulting structure, and stores previous trees in PostgreSQL.

The project combines data-structure logic with a traditional Spring MVC application, Thymeleaf views, JPA persistence, JSON serialization, and automated tests.

## Features

- Enter a sequence of integers through a web interface
- Build an AVL tree from the supplied values
- Display the tree root and formatted tree structure
- Serialize the tree structure as JSON
- Store input values and generated tree structures in PostgreSQL
- View previously generated trees
- Handle empty, single-value, duplicate, and balanced inputs
- Automated Spring Boot tests for core tree behavior

## Technologies

- Java 17
- Spring Boot 3
- Spring MVC
- Spring Data JPA
- Thymeleaf
- PostgreSQL
- Maven
- JUnit 5 / AssertJ
- Jackson

## Application Flow

```text
Enter Numbers
     ↓
POST /processNumbers
     ↓
BSTService
     ↓
AVL Tree Construction
     ↓
JSON Tree Structure
     ↓
PostgreSQL Persistence
     ↓
Result View
```

## Main Routes

```text
GET   /enterNumbers
POST  /processNumbers
GET   /previousTrees
```

`/enterNumbers` displays the input form.

`/processNumbers` builds the tree, stores the result, and displays the input numbers, root value, and formatted JSON tree structure.

`/previousTrees` retrieves saved tree records from PostgreSQL.

## Persistence

Generated trees are stored as `TreeRecord` entities. Each record preserves the original input numbers and the serialized tree structure so previously generated trees can be retrieved later.

## Testing

The project includes Spring Boot tests covering:

- Processing a normal sequence of numbers
- Retrieving previously stored trees
- Empty input
- JSON tree structure
- Single-element input
- Duplicate values
- Balanced tree input

## Local Setup

Requirements:

- Java 17
- PostgreSQL
- Maven, or the included Maven Wrapper

Clone the repository:

```bash
git clone https://github.com/RobV34/DSABST.git
cd DSABST
```

Create the PostgreSQL database/table using the included `bst.sql` file and configure the application's PostgreSQL connection for your local environment.

Run the application with:

```bash
./mvnw spring-boot:run
```

Then open:

```text
http://localhost:8080/enterNumbers
```

Run the test suite with:

```bash
./mvnw test
```

## What This Project Demonstrates

This project demonstrates Java and Spring Boot development alongside data structures and algorithms. It connects AVL tree construction with MVC controllers, service-layer logic, JSON serialization, JPA/PostgreSQL persistence, Thymeleaf rendering, and automated testing.

---

**Rob Vatcher**  
Software Development — Keyin College


Configured for postgres. 

See attached bst.sql for db and table creation.

Clone and follow path below.

http://localhost:8080/enterNumbers 
