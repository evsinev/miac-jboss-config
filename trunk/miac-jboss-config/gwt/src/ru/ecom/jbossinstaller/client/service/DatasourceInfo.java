package ru.ecom.jbossinstaller.client.service;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Параметры Datasource
 */
public class DatasourceInfo implements IsSerializable {


    public DatasourceInfo() {
    }


    public DatasourceInfo(String aJdbcDriverClassname
            , String aDatabaseName
            , String aUsername
            , String aPassword
            , String aHostname
            , int aPort) {
        theJdbcDriverClassname = aJdbcDriverClassname;
        theDatabaseName = aDatabaseName;
        thePassword = aPassword;
        theUsername = aUsername;
        theHostname = aHostname;
        thePort = aPort;
    }

    /** Порт */
    public int getPort() { return thePort ; }
    public void setPort(int aPort)   { thePort = aPort ; }

    /** Хост */
    public String getHostname() { return theHostname ; }
    public void setHostname(String aHostname)   { theHostname = aHostname ; }

    /** Имя пользователя */
    public String getUsername() { return theUsername ; }
    public void setUsername(String aUsername)   { theUsername = aUsername ; }

    /** Пароль пользователя */
    public String getPassword() { return thePassword ; }
    public void setPassword(String aPassword)   { thePassword = aPassword ; }

    /** Имя базы данных */
    public String getDatabaseName() { return theDatabaseName ; }
    public void setDatabaseName(String aDatabaseName)   { theDatabaseName = aDatabaseName ; }

    /** Драйвер */
    public String getJdbcDriverClassname() { return theJdbcDriverClassname ; }
    public void setJdbcDriverClassname(String aJdbcDriverClassname)   { theJdbcDriverClassname = aJdbcDriverClassname ; }

    /** Драйвер */
    private String theJdbcDriverClassname ;
    /** Имя базы данных */
    private String theDatabaseName ;
    /** Пароль пользователя */
    private String thePassword ;
    /** Имя пользователя */
    private String theUsername ;
    /** Хост */
    private String theHostname ;
    /** Порт */
    private int thePort ;
}
