# Intersystems Cache 5.x #
```
<?xml version="1.0" encoding="UTF-8"?>
<datasources>
  <no-tx-datasource>
    <jndi-name>RiamsBaseDS</jndi-name>
    <connection-url>jdbc:Cache://192.168.1.2:1972/RIAMS</connection-url>
                             <!--¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯ -->
    <driver-class>com.intersys.jdbc.CacheDriver</driver-class>
    <user-name>_system</user-name>
    <password>PASSWORD</password>
    <!-- Пул соединений -->
    <min-pool-size>5</min-pool-size>
    <max-pool-size>20</max-pool-size>
    <check-valid-connection-sql>SELECT 1+1</check-valid-connection-sql>
    <!-- 10 минут на запрос-->
    <idle-timeout-minutes>600</idle-timeout-minutes>
      <metadata>
         <type-mapping>Cache 5.2</type-mapping>
      </metadata>
  </no-tx-datasource>
</datasources>
```

# Postgresql 8.x #
```
<?xml version="1.0" encoding="UTF-8"?>

<datasources>
  <local-tx-datasource>
    <jndi-name>RiamsBaseDS</jndi-name>
    <connection-url>jdbc:postgresql://localhost:5432/riams</connection-url>
    <driver-class>org.postgresql.Driver</driver-class>
    <user-name>postgres</user-name>
    <password>PASSWORD</password>

    <min-pool-size>10</min-pool-size>
    <max-pool-size>120</max-pool-size>

    <check-valid-connection-sql>SELECT 1+1</check-valid-connection-sql>
      
    <metadata>
         <type-mapping>PostgreSQL</type-mapping>
    </metadata>
    
  </local-tx-datasource>
</datasources>
```