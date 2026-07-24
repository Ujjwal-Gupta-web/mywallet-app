package com.mywallet.backend.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * Builds the DataSource from a DATABASE_URL env var in URI form
 * (postgres://user:pass@host:port/db), as injected by the platform's
 * connection binding. Falls back to Spring's standard spring.datasource.*
 * auto-configuration when DATABASE_URL is not set (local development).
 */
@Configuration
@ConditionalOnProperty("database.url")
public class DataSourceConfig {

    @Bean
    public DataSource dataSource(@Value("${database.url}") String url) {
        if (url.startsWith("jdbc:")) {
            return DataSourceBuilder.create().url(url).build();
        }
        URI uri = URI.create(url);
        int port = uri.getPort() == -1 ? 5432 : uri.getPort();
        String jdbcUrl = "jdbc:postgresql://" + uri.getHost() + ":" + port + uri.getPath()
                + (uri.getQuery() != null ? "?" + uri.getQuery() : "");
        String username = null;
        String password = null;
        if (uri.getUserInfo() != null) {
            String[] userInfo = uri.getUserInfo().split(":", 2);
            username = URLDecoder.decode(userInfo[0], StandardCharsets.UTF_8);
            if (userInfo.length > 1) {
                password = URLDecoder.decode(userInfo[1], StandardCharsets.UTF_8);
            }
        }
        return DataSourceBuilder.create()
                .url(jdbcUrl)
                .username(username)
                .password(password)
                .build();
    }
}
