package ru.ecom.jbossinstaller.service;

import junit.framework.TestCase;
import ru.ecom.jbossinstaller.client.service.DatasourceInfo;
import ru.ecom.jbossinstaller.client.service.ConfigException;

import java.io.IOException;

/**
 * 
 */
public class ConfigServiceImplTest extends TestCase {

    public void testPostgresConnection() throws ConfigException {
        DatasourceInfo info = new DatasourceInfo();
        info.setJdbcDriverClassname("org.postgresql.Driver");
        info.setHostname("localhost");
        info.setPort(5432);
        info.setUsername("postgres");
        info.setPassword("charpa");
        info.setDatabaseName("riams");
             
        ConfigServiceImpl service = new ConfigServiceImpl();
        service.testConnection(info);
    }

    public void testTryKnownConnections() {
        ConfigServiceImpl service = new ConfigServiceImpl();
        assertNotNull(service.tryKnownConnections()) ;
    }

    public void testFindJbossHomeDir() throws IOException {
//        System.getProperties().store(System.out, "test");
//        System.out.println("System.getProperty(\"username\") = " + System.getProperty("os.username"));
        ConfigServiceImpl service = new ConfigServiceImpl();
        String dir = service.findJbossHomeDir() ;
        System.out.println("dir = " + dir);
        assertNotNull(dir);

    }
}
