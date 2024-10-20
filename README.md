# Bank Application Demo

Bank Application Demo is a microservice-based banking application that allows users to manage their accounts, store money on deposits with interest, and transfer funds between users. It also features currency exchange rates from an external API with caching, and a notification service for user transactions.

## Features

- **Currency Rates**: Fetches exchange rates for a specific date. Rates are first checked in a local Redis cache; if not found, the data is retrieved from an external API and stored in Redis for future requests.
- **Personal Account**: Users can deposit and withdraw money from their accounts.
- **Interest-bearing Deposits**: Store money on deposit with interest.
- **Money Transfers**: Transfer funds between users using phone numbers.
- **Notifications**: A notification service that uses Kafka to receive messages from the personal account service and stores them in MongoDB.
- **Swagger Documentation**: API documentation is provided via Swagger.

## Tech Stack

- **Java & Spring Boot**
- **Gradle** for project build
- **PostgreSQL** with **Flyway** for database migrations
- **Redis** for caching exchange rates
- **Kafka** for messaging between services
- **MongoDB** for storing notifications
- **pgAdmin** for managing PostgreSQL
- **Redis Commander** for managing Redis data
- **Zookeeper** for Kafka coordination
- **Mongo-Express** for managing MongoDB

## Prerequisites

- Docker and Docker Compose
- JDK 21+
- Gradle

## Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/MekaHuK/bank-application-demo.git
   cd bank-application-demo
   ```

2. Start PostgreSQL and Kafka containers:

   ```bash
   docker-compose up --build postgres kafka
   ```

3. Build the project using Gradle:

   ```bash
   ./gradlew build
   ```

4. Stop the containers and run the entire project:

   ```bash
   docker-compose down
   docker-compose up --build
   ```

5. Access the following services:
   - Rates Service Swagger UI: `http://localhost:8081/swagger-ui/`
   - Personal Service Swagger UI: `http://localhost:8083/swagger-ui/`
   - pgAdmin: `http://localhost:5050/`
   - Redis Commander: `http://localhost:8089/`
   - Mongo-Express: `http://localhost:8088/`

## Configuration

Configuration files for the application are located in the `src/main/resources` directory. Adjust `application.properties` as needed to match your local environment.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Author

Developed by [Vadim Kornyushenko](https://github.com/MekaHuK).

