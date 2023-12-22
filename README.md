# Multi-DataSource Spring Boot Template

This template demonstrates how to configure a Spring Boot application to connect to two different databases simultaneously using JDBC Template.
It's ideal for projects that require interaction with multiple databases using Spring Boot.

## Setting Up
### Prerequisites

- JDK 17 or later
- Maven 3.2 or later

### Steps

1. **Clone the Repository**: 

```
git clone [your-repository-url]
cd [repository-name]
```

2. **Configure the Databases**:
- Modify `src/main/resources/application.properties` to include the connection details for your two databases.

3. **Database Schemas**
    - **Accessibility**: Verify that the databases specified in `application.properties` are accessible.
    - **Connection String Breakdown**: The connection strings in `application.properties` are structured as follows:
        - `jdbc:sqlserver://localhost:1433;databaseName=example;`
            - `sqlserver`: Specifies the database type. This template is configured for Microsoft SQL Server.
            - `localhost`: The database server address. In this template, it's set to localhost, indicating that the server is running on the same machine as the application.
            - `1433`: The port number for the SQL Server. This is the default port for Microsoft SQL Server.
            - `example`: Name of the database. In this template, the database is named "example".

### JDBC Connection String Examples and Information

Different databases use specific formats and ports for their JDBC connection strings. Below is a reference table with examples and important information for various database types:

| Database Type | Common Port | Example Connection String                                       | Notes                                                    |
|---------------|-------------|-----------------------------------------------------------------|----------------------------------------------------------|
| SQL Server    | 1433        | `jdbc:sqlserver://localhost:1433;databaseName=example;`         | Default port for SQL Server. Use for local or specific server connections. |
| MySQL         | 3306        | `jdbc:mysql://localhost:3306/example?useSSL=false`              | Default port for MySQL. `useSSL=false` can be used to avoid SSL connection in non-production environments. |
| PostgreSQL    | 5432        | `jdbc:postgresql://localhost:5432/example`                      | Default port for PostgreSQL. Widely used in various environments. |
| Oracle        | 1521        | `jdbc:oracle:thin:@localhost:1521:example`                      | Oracle uses a `SID` (`example` here), or you can use a service name. |

### Additional Notes

- **Host/IP**: Replace `localhost` with the actual server address or IP if the database is not hosted locally.
- **Database Name**: Replace `example` with the actual name of your database.
- **Port**: The port number can be different if configured differently on the database server.
- **Parameters**: Connection strings can include additional parameters for specific needs, like SSL configuration, time zone settings, etc.

### Other Connection Methods

In addition to standard host and port connections, JDBC allows for various other methods:

- **Named Instances**: For SQL Server, you can connect to a named instance. E.g., `jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=example;`
- **Integrated Security**: Used primarily in Windows environments to use Windows authentication. E.g., `jdbc:sqlserver://localhost;integratedSecurity=true;`

Understanding these components and methods will help you effectively configure and troubleshoot database connections in your Spring Boot applications.

### Additional Connection String Parameters

| Parameter            | Example                       | Why Use It                                             | Considerations                                   | Errors/Exceptions it Might Solve                |
|----------------------|-------------------------------|--------------------------------------------------------|--------------------------------------------------|-------------------------------------------------|
| `encrypt`            | `encrypt=true`                | Ensures data transfer is encrypted for security.       | May slightly impact performance. Necessary in production for security. | Solves security exceptions related to unencrypted data transfer. |
| `trustServerCertificate` | `trustServerCertificate=true` | Useful in development with self-signed certificates.   | Not recommended for production; bypasses SSL/TLS security checks. | Resolves certificate validation errors in development environments. |
| `integratedSecurity` | `integratedSecurity=true`     | Uses Windows integrated authentication.                | Only relevant in Windows-based environments.    | Addresses authentication errors when using Windows credentials. |
| `applicationName`    | `applicationName=MyApp`       | Identifies the application in SQL Server monitoring.   | Mainly for monitoring; no direct functionality impact. | Helps in diagnosing issues by distinguishing application connections in logs. |
| `connectTimeout`     | `connectTimeout=30`           | Limits how long to wait for a database connection.     | Too low a value might cause failures under normal latency. | Prevents application hang-ups due to indefinite wait times for database connections. |
| `sendTimeAsDateTime` | `sendTimeAsDateTime=false`    | Compatibility with newer SQL Server versions.          | Align with SQL Server version and date/time handling. | Solves issues with time and datetime fields in newer SQL Server versions. |

Each parameter can be tailored to specific database connectivity requirements in Spring Boot applications, offering customization and control.


4. **Edit the queries in `TestRepository.java`**:
- Modify the queries in `TestRepository.java` to match the schema of your databases.
- Ensure the table names and column names match the schema of your databases.

5. **Test the Application**:
- Build and Run the application.
- The port number can be configured in `application.properties` and is set to 80.
- Test the endpoints "/one" and "/two" to verify that the application is able to connect to both databases.

## Configuration Overview

- `DataSourceConfig.java`: Configures the two data sources and their JdbcTemplate.
- `TestRepository.java`: Demonstrates using the two JdbcTemplate instances for database operations.
- `TestController.java`: A REST controller to expose database interaction endpoints.
- `application.properties`: Contains database and Hibernate configuration properties.

## Contributing
Contributions to enhance this template are welcome!



