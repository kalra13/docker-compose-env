package com.kalra.docker.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Configures the {@link javax.sql.DataSource} for the country-service.
 * <p>
 * Values are read from environment variables with sensible defaults:
 * <ul>
 *     <li>DB_URL (default: jdbc:mysql://localhost:3306/docker-app)</li>
 *     <li>DB_USER (default: root)</li>
 *     <li>DB_PW (default: root)</li>
 * </ul>
 */
@Slf4j
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
        log.info("Creating datasource with url: {}", url);
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}


