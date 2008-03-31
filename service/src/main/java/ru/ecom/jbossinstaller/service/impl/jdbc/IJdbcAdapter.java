package ru.ecom.jbossinstaller.service.impl.jdbc;

import ru.ecom.jbossinstaller.client.service.DatasourceInfo;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Адаптер для jdbc драйвера
 */
public interface IJdbcAdapter {
    Connection getConnection(DatasourceInfo aDatasourceInfo) throws SQLException;
    String getDatasourceTemplateName() ;

}
