package ru.ecom.jbossinstaller.service.impl.jdbc;

import ru.ecom.jbossinstaller.client.service.DatasourceInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;

/**
 * Адаптер для postgres
 */
public class PostgresJdbcAdapter implements IJdbcAdapter {

    public Connection getConnection(DatasourceInfo aInfo) throws SQLException {
        String url =
                "jdbc:postgresql://"
                        + aInfo.getHostname()
                        + ":"
                        + aInfo.getPort()
                        + "/"
                        + aInfo.getDatabaseName();
        return DriverManager.getConnection(url, aInfo.getUsername(), aInfo.getPassword());
    }

    public String getDatasourceTemplateName() {
        return "/postgres-ds-template.xml" ;
    }
}
