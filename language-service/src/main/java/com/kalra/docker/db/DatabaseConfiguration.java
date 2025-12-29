package com.kalra.docker.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


/**
 * Configures the {@link javax.sql.DataSource} for the language-service.
 * <p>
 * Values are read from environment variables with defaults:
 * <ul>
 *     <li>DB_URL (default: jdbc:mysql://localhost:3306/docker-app)</li>
 *     <li>DB_USER (default: root)</li>
 *     <li>DB_PW (default: root)</li>
 * </ul>
 */
@Configuration
public class DatabaseConfiguration {

    private final String username;
    private final String password;
    private final String url;

    public DatabaseConfiguration(@Value("${DB_URL:jdbc:mysql://localhost:3306/docker-app}") String url,
                                 @Value("${DB_USER:root}")String username,
                                 @Value("${DB_PW:root}")String password) {
        this.username = username;
        this.password = password;
        this.url = url;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}


