package ru.ecom.jbossinstaller.service.impl.modify;

import junit.framework.TestCase;
import ru.ecom.jbossinstaller.service.impl.MainModelImpl;
import ru.ecom.jbossinstaller.service.impl.jdbc.IJdbcAdapter;
import ru.ecom.jbossinstaller.service.impl.jdbc.PostgresJdbcAdapter;
import ru.ecom.jbossinstaller.service.impl.jdbc.CacheJdbcAdapter;
import ru.ecom.jbossinstaller.client.service.DatasourceInfo;

import java.io.File;
import java.util.HashMap;

/**
 *
 */
public class ConfigureDatasourceModifyTest extends TestCase {
    public void test() {
        DatasourceInfo info = new DatasourceInfo("com.intersys.jdbc.CacheDriver", "riams", "postgres", "charpa", "localhost", 5432);
        MainModelImpl model = new MainModelImpl(new File("jboss_test"), info);

        HashMap<String, IJdbcAdapter> adapterHash = new HashMap<String, IJdbcAdapter>(2) ;
        adapterHash.put("org.postgresql.Driver", new PostgresJdbcAdapter()) ;
        adapterHash.put("com.intersys.jdbc.CacheDriver", new CacheJdbcAdapter()) ;

        ConfigureDatasourceModify modify = new ConfigureDatasourceModify(adapterHash);
        modify.apply(model);
    }
}
