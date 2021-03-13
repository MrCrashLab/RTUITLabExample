package ru.MrCrash.RtuItLabExample.Shops;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class ShopJdbcConfig {
    @Value("${spring.datasource.urlShops}")
    private String URL;
    @Value("${spring.datasource.password}")
    private String PASSWORD;
    @Value("${spring.datasource.username}")
    private String USERNAME;

    @Bean
    public DataSource mySqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(mySqlDataSource());
    }
}
