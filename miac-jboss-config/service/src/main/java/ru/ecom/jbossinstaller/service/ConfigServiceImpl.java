package ru.ecom.jbossinstaller.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.ecom.jbossinstaller.client.service.ConfigModel;
import ru.ecom.jbossinstaller.client.service.IConfigService;
import ru.ecom.jbossinstaller.client.service.ConfigException;
import ru.ecom.jbossinstaller.client.service.DatasourceInfo;
import ru.ecom.jbossinstaller.service.impl.MainModelImpl;
import ru.ecom.jbossinstaller.service.impl.LocalConfigServiceImpl;
import ru.ecom.jbossinstaller.service.impl.jdbc.IJdbcAdapter;
import ru.ecom.jbossinstaller.service.impl.jdbc.PostgresJdbcAdapter;
import ru.ecom.jbossinstaller.service.impl.jdbc.CacheJdbcAdapter;

import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * Реализация сервиса настройки
 */
public class ConfigServiceImpl extends RemoteServiceServlet implements IConfigService {


    public ConfigServiceImpl() {
        theJdbcAdapterHash = new HashMap<String, IJdbcAdapter>(2) ;
        theJdbcAdapterHash.put("org.postgresql.Driver", new PostgresJdbcAdapter()) ;
        theJdbcAdapterHash.put("com.intersys.jdbc.CacheDriver", new CacheJdbcAdapter()) ;
    }

    /**
     * Ищем каталог jboss
     */
    public String findJbossHomeDir() {
        String[] dirs = {"/opt/jboss-4.2.2.GA"
                , "c:/opt/jboss-4.2.2.GA"
                , System.getProperty("user.home")+"/opt/jboss-4.2.2.GA"
                , System.getProperty("user.home")+"/jboss-4.2.2.GA"
        };
        for (String dir : dirs) {
            File file = new File(dir);
            if(file.exists()) return file.getAbsolutePath() ;
        }
        return dirs[0] ;
    }

    public String config(ConfigModel aModel) throws ConfigException {
        try {
            File jbossHomeDir = getJbossHomeDir(aModel.getJbossHomeDir());
            LocalConfigServiceImpl service = new LocalConfigServiceImpl(theJdbcAdapterHash);
            MainModelImpl model = new MainModelImpl(jbossHomeDir, aModel.getDataSourceInfo());
            return service.configure(model);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConfigException(e.getMessage(), e);
        }
    }

    /**
     * Каталог jboss с проверкой на существование
     * @param aJbossHomeDir
     * @return
     */
    private File getJbossHomeDir(String aJbossHomeDir) {
        File jbossHomeDir = new File(aJbossHomeDir);
        if (!jbossHomeDir.exists()) throw new IllegalStateException("Нет каталога " + aJbossHomeDir);
        return jbossHomeDir;
    }

    public DatasourceInfo findCurrentDatasource(String aJbossHomeDir) {
        // пробуем найти datasource
        File jbossHomeDir = getJbossHomeDir(aJbossHomeDir);
        File riamsDatasourceFile = new File(jbossHomeDir, "server/default/deploy/ecom/riams-ds.xml") ;
        DatasourceInfo info ;
        if(riamsDatasourceFile.exists()) {
            // TODO есть такой файл, пробуем взять из него информацию
            info = tryKnownConnections() ;
        } else {
            // перебираем все известные параметры и пробуем подключиться
            info = tryKnownConnections() ;
        }
        return info;
    }

    /**
     * Ищем первое успешное соединение с базой
     * @return
     */
    DatasourceInfo tryKnownConnections() {
        ArrayList<DatasourceInfo> list = new ArrayList<DatasourceInfo>();
        list.add(new DatasourceInfo("org.postgresql.Driver", "riams", "postgres", "charpa", "localhost", 5432)) ;
        list.add(new DatasourceInfo("com.intersys.jdbc.CacheDriver", "RIAMS", "_system"  , "sys", "192.168.1.2", 1972)) ;
        list.add(new DatasourceInfo("com.intersys.jdbc.CacheDriver", "RIAMS", "_system"  , "sys", "127.0.0.1", 1972)) ;
        list.add(new DatasourceInfo("com.intersys.jdbc.CacheDriver", "RIAMS", "_system"  , "sys", "192.168.1.1", 1972)) ;
        list.add(new DatasourceInfo("org.postgresql.Driver", "riams", "esinev"  , "charpa", "localhost", 5432)) ;
        for (DatasourceInfo info : list) {
            try {
                testConnection(info);
                return info ;
            } catch (Exception e) {
                // skip exception
                System.err.println(e) ;
            }
        }
        // second known connection for default.
        list.iterator().next();
        return list.iterator().next();
    }


    public void testConnection(DatasourceInfo aDatasourceInfo) throws ConfigException {
        if(aDatasourceInfo==null) throw new NullPointerException("aDatasourceInfo is null") ;
        if(aDatasourceInfo.getJdbcDriverClassname()==null) throw new NullPointerException("No jdbc driver name") ;
        if(!theJdbcAdapterHash.containsKey(aDatasourceInfo.getJdbcDriverClassname())) {
            throw new ConfigException("Jdbc driver "+aDatasourceInfo.getJdbcDriverClassname()+" not supported yet.") ;
        }
        try {
            Class.forName(aDatasourceInfo.getJdbcDriverClassname()) ;
            IJdbcAdapter adapter = theJdbcAdapterHash.get(aDatasourceInfo.getJdbcDriverClassname());
            Connection connection = adapter.getConnection(aDatasourceInfo) ;
            try {
                // не только проверяем соединение, но и выполняем простой запрос
                Statement stmt = connection.createStatement();
                try {
                    ResultSet rs = stmt.executeQuery("select 1+1") ;
                    try {
                        if(rs.next()) {
                            String result = rs.getString(1) ;
                            if(!"2".equals(result)) {
                                throw new ConfigException("Wrong database response for 'select 1+1':"+result ) ;
                            }
                        }
                    } finally {
                        rs.close() ;
                    }
                } finally {
                    stmt.close();
                }
            } finally {
                connection.close() ;
            }
        } catch (ClassNotFoundException e) {
            throw new ConfigException("Драйвер "+aDatasourceInfo.getJdbcDriverClassname()+" не найден: "+e.getMessage()
                    ,e) ;
        } catch (SQLException e) {
            throw new ConfigException("Ошибка подключения к базе: "+e.getMessage(),e) ;
        }
    }

    private final Map<String, IJdbcAdapter> theJdbcAdapterHash ;
}
