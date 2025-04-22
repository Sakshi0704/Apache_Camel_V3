# Kafka and Apache Camel Integration with Docker Compose

This project demonstrates the integration of Apache Camel with Kafka and PostgreSQL using Docker Compose. It includes two Spring Boot microservices:

1. **Producer Service**: Produces messages to Kafka topics.
2. **Consumer Service**: Consumes messages from Kafka topics.

### Prerequisites

Ensure you have the following installed on your system:
- Docker
- Docker Compose
- Java 11 or higher
- Maven

### Project Setup

The project includes a `docker-compose.yml` file that orchestrates the following services:

- **Zookeeper**: Required for Kafka to work.
- **Kafka**: A message broker.
- **PostgreSQL**: Database for storing information.
- **Producer Service**: A Spring Boot microservice that sends messages to Kafka.
- **Consumer Service**: A Spring Boot microservice that consumes messages from Kafka.

### Directory Structure

/project-root ├── /producer-service │ ├── Dockerfile │ ├── src/ │ └── pom.xml ├── /consumer-service │ ├── Dockerfile │ ├── src/ │ └── pom.xml └── docker-compose.yml

### Configuration

The following configuration is specified in the `docker-compose.yml` and `application.properties` files:

- **Kafka**: The producer and consumer communicate with Kafka, which is configured to use `autoOffsetReset=latest` to start consuming from the most recent message.
- **PostgreSQL**: A shared PostgreSQL instance is used for persistence.
- **Spring Boot**: The producer and consumer services are configured to interact with Kafka and PostgreSQL.

### 1. Docker Compose Setup

To run the project, use Docker Compose to start all the services:

```bash
docker-compose up --build

docker-compose logs -f producer-service
docker-compose logs -f consumer-service

docker-compose down


# Kafka Configuration for Producer
spring.kafka.producer.bootstrap-servers=kafka:9093
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer


# Kafka Configuration for Consumer
spring.kafka.consumer.bootstrap-servers=kafka:9093
spring.kafka.consumer.group-id=your-group-id
spring.kafka.consumer.auto-offset-reset=latest

# PostgreSQL Configuration
spring.datasource.url=jdbc:postgresql://postgres:5432/your_database
spring.datasource.username=user
spring.datasource.password=password



