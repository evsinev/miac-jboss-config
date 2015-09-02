#настройка подключения к базе в grails

# Introduction #

Add your content here.


# Ошибки #

## No suitable driver ##
```
Cannot create JDBC driver of class 'org.hsqldb.jdbcDriver' for connect URL 'jdbc:mysql://localhost/bookshelf':
java.sql.SQLException: No suitable driver
        at java.sql.DriverManager.getDriver(DriverManager.java:243)
        at org.apache.commons.dbcp.BasicDataSource.createDataSource(BasicDataSource.java:773)
        at org.apache.commons.dbcp.BasicDataSource.getConnection(BasicDataSource.java:540)
```

Пропущена регистрация jdbc драйвера. Нужно добавить **driverClassName** в environments/development/dataSource параметр driverClassName:
```
// environment specific settings
environments {
        development {
                dataSource {
                        logSql = true
                        dbCreate = "update" // one of 'create', 'create-drop','update'
                        url = "jdbc:mysql://localhost/bookshelf"
                        username = "test"
                        password = "test"
                        driverClassName = "com.mysql.jdbc.Driver"
                }
        }
```

## java.lang.ClassNotFoundException: com.mysql.jdbc.Driver ##
```
Could not get JDBC Connection; nested exception is org.apache.commons.dbcp.SQLNestedException: Cannot load JDBC driver class 'com.mysql.jdbc.Driver':
java.lang.ClassNotFoundException: com.mysql.jdbc.Driver
        at org.codehaus.groovy.tools.RootLoader.findClass(RootLoader.java:146)
        at java.lang.ClassLoader.loadClass(ClassLoader.java:306)
        at org.codehaus.groovy.tools.RootLoader.loadClass(RootLoader.java:118)
```

Нет драйвера в classpath. Нужно положить jar файл, содержащий драйвер, в каталог ./lib