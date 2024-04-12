# Bank Management System

This is a Spring Boot project for a bank management system that provides RESTful APIs to manage clients, accounts, and transactions.

## Project Description

The project aims to simulate the functionality of a bank management system. It includes entities such as Client, Account, and Transaction (Movement). Each client can have multiple accounts, and each account can have multiple transactions. Clients have attributes like name, address, and phone number. Accounts have attributes like number and balance. Transactions have attributes like type (debit or credit), date, and value.

## Features

- CRUD operations for managing clients and accounts.
- Ability to register transactions for an account, with validation to prevent negative balances.
- Generation of reports specifying a date range and a client to display associated accounts with their balances and total debits and credits during the specified period.
- Chuck Norris jokes displayed at the top of the screens for advertising or entertainment purposes. Chuck Norris jokes are fetched randomly from the Chuck Norris API.

## API Endpoints

- Client Management: `/api-app/cliente`
  - GET: Retrieve all clients
  - GET/{id}: Retrieve a client by ID
  - POST: Create a new client
  - PUT: Update an existing client
  - DELETE/{id}: Delete a client by ID

- Account Management: `/api-app/cuenta`
  - GET: Retrieve all accounts
  - GET/{id}: Retrieve an account by ID
  - POST: Create a new account
  - PUT: Update an existing account
  - DELETE/{id}: Delete an account by ID

- Transaction Management: `/api-app/movimiento`
  - GET: Retrieve all transactions
  - POST: Create a new transaction
  - DELETE/{id}: Delete a transaction by ID

- Report Generation: `/api/reporte`
  - POST: Generate a report specifying a date range and a client

## Requirements

- JDK 17 or higher
- Maven
- MySQL

## Configuration

1. Clone this repository: `git clone https://github.com/djrocerk/BackBits`
2. Import the project into your favorite IDE as a Maven project.
3. Configure the MySQL database in `src/main/resources/application.properties`.
4. Run the Spring Boot application. The application will be available at `http://localhost:8080`.

## Usage

You can use tools like Postman or curl to interact with the RESTful APIs. Please refer to the API documentation or Swagger UI for detailed information on each endpoint.

## Chuck Norris Notes

Chuck Norris notes are displayed at the top of the screens as a component of advertising or entertainment. These notes are fetched randomly from the Chuck Norris API:
- [Chuck Norris API](https://api.chucknorris.io/)
- [Random Joke Endpoint](https://api.chucknorris.io/jokes/random)
