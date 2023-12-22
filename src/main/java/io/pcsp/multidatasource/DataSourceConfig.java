package io.pcsp.multidatasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * DataSourceConfig is a configuration class that sets up multiple data sources for a Spring Boot application,
 * diverging from Spring Boot's default auto-configuration which only configures a single data source.
 *
 * In typical Spring Boot applications, a single data source is automatically configured based on
 * spring.datasource.* properties. However, this class explicitly defines two separate data sources
 * (firstdbDataSource and seconddbDataSource) each with its own properties, accommodating the need
 * for connecting to multiple databases.
 *
 * Additionally, it configures JdbcTemplate beans for each data source, which Spring Boot's auto-configuration
 * does not provide by default for multiple data sources. This is crucial for enabling easy database operations
 * for both the primary and secondary databases within the application.
 *
 * @Primary annotations are used to denote the primary data source and JdbcTemplate, guiding Spring Boot
 * to use them as defaults for any auto-configured components that rely on DataSource or JdbcTemplate.
 *
 * @author Matt L
 * @date 12/22/2023
 */

@Configuration
public class DataSourceConfig {

//    ******************** PRIMARY DATASOURCE ********************
    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.firstdb")
    public DataSourceProperties firstdbDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource firstdbDataSource() {
        return firstdbDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Autowired
    @Primary
    @Bean(name = "firstdbJdbcTemplate")
    public JdbcTemplate firstdbJdbcTemplate(@Qualifier("firstdbDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }



//    ********************* SECOND DATASOURCE *********************
    @Bean
    @ConfigurationProperties(prefix="spring.datasource.seconddb")
    public DataSourceProperties seconddbDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource seconddbDataSource() {
        return seconddbDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Autowired
    @Bean(name = "seconddbJdbcTemplate")
    public JdbcTemplate seconddbJdbcTemplate(@Qualifier("seconddbDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }



}
