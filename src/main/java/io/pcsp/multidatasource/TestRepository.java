package io.pcsp.multidatasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TestRepository is a repository class in a Spring Boot application designed to interact with multiple databases.
 * It demonstrates the concept of using multiple {@link JdbcTemplate} instances, each configured for a different data source.
 * This allows the repository to perform database operations on distinct databases within the same application context.
 *
 * The class is annotated with @Repository, indicating that it's a Spring-managed Data Access Object (DAO).
 * It primarily provides methods (queryOne and queryTwo) to execute specific queries against two separate databases.
 *
 * Two JdbcTemplate instances, FIRST_DB_JDBC_TEMPLATE and SECOND_DB_JDBC_TEMPLATE, are injected into this repository.
 * Each JdbcTemplate is qualified with a specific bean name to distinguish between the primary and secondary data sources.
 * This injection is performed via the constructor, ensuring that the correct instances of JdbcTemplate are used for the corresponding database operations.
 *
 * Methods:
 *  - queryOne(): Executes a query on the first database (primary data source) and returns a list of String representations of the retrieved records.
 *  - queryTwo(): Executes a similar query on the second database (secondary data source) and returns the results in a similar format.
 *
 * These methods demonstrate how to perform read operations on different databases using Spring's JdbcTemplate in a multi-database configuration.
 *
 * @author Matt L
 * @date 2023-12-22
 */
@Repository
public class TestRepository {

    private final JdbcTemplate FIRST_DB_JDBC_TEMPLATE;
    private final JdbcTemplate SECOND_DB_JDBC_TEMPLATE;


    @Autowired
    public TestRepository(
            @Qualifier("firstdbJdbcTemplate") JdbcTemplate firstdbJdbcTemplate,
            @Qualifier("seconddbJdbcTemplate") JdbcTemplate seconddbJdbcTemplate) {
        this.FIRST_DB_JDBC_TEMPLATE = firstdbJdbcTemplate;
        this.SECOND_DB_JDBC_TEMPLATE = seconddbJdbcTemplate;
    }

    public List<String> queryOne() {
        String sql = "SELECT TOP 10 * FROM test_table";
        return FIRST_DB_JDBC_TEMPLATE.query(sql, (rs, rowNum) -> {
            rs.getString("id");
            rs.getString("name");
            return rs.getString("id") + ", " + rs.getString("name");
        });
    }

    public List<String> queryTwo() {
        String sql = "SELECT * FROM test_table";

        return SECOND_DB_JDBC_TEMPLATE.query(sql, (rs, rowNum) -> {
            rs.getString("id");
            rs.getString("name");
            return rs.getString("id") + ", " + rs.getString("name");
        });
    }
}
