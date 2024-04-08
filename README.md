# forecasting-app
A simple Java Spring Boot Application which uses OpenWeather API to serve weather forecast information via a REST API

# Maven Spring Boot Web Project

This is a simple guide to building and running a Maven Spring Boot web project.

## Prerequisites

- Java Development Kit (JDK) installed on your system
- Apache Maven installed on your system
- Basic knowledge of Spring Boot framework

## Getting Started

1. Clone the repository:

    ```bash
    git clone <repository-url>
    ```

2. Navigate to the project directory:

    ```bash
    cd <project-directory>
    ```

3. Build the project using Maven:

    ```bash
    mvn clean install
    ```

4. Run the application:

    ```bash
    mvn spring-boot:run
    ```


## Project Structure

- `src/main/java`: Contains Java source code
- `src/main/resources`: Contains application properties and configuration files
- `src/test`: Contains test cases

## Configuration

- `application.properties`: Contains application-specific properties such as server port, api key, etc.
- `pom.xml`: Contains Maven project configuration including dependencies and plugins.

## Development

- To add new features or modify existing ones, edit the Java source files in `src/main/java`.
- Configuration changes can be made in `application.properties` and `pom.xml`.
- Make sure to write appropriate test cases in `src/test` for any new functionality.

## Deployment

- For deployment, you can create a standalone executable JAR file using:

    ```bash
    mvn clean package
    ```

  This will create an executable JAR file in the `target` directory. You can then deploy this JAR file to your server.

## Contributing

- If you'd like to contribute to this project, feel free to fork the repository and submit a pull request. We welcome contributions!



