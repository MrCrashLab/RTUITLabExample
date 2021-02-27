package ru.MrCrash.RtuItLabExample.DAO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PurchaseDAO {
    @Value("${spring.datasource.url}")
    private final String URL = null;
    @Value("${spring.datasource.password}")
    private final String PASSWORD = null;
    @Value("${spring.datasource.username}")
    private final String USERNAME = null;

}
