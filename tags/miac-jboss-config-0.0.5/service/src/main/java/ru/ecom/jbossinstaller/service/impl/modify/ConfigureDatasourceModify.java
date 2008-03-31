package ru.ecom.jbossinstaller.service.impl.modify;

import ru.ecom.jbossinstaller.service.impl.file.IFile;
import ru.ecom.jbossinstaller.service.impl.file.JBossDefaultDeployEcomFile;
import ru.ecom.jbossinstaller.service.impl.jdbc.IJdbcAdapter;
import ru.ecom.jbossinstaller.service.IMainModel;
import ru.ecom.jbossinstaller.client.service.DatasourceInfo;

import java.util.Map;
import java.io.*;

/**
 * Настройка datasource
 */
public class ConfigureDatasourceModify implements IModify {

    public ConfigureDatasourceModify(Map<String, IJdbcAdapter> aJdbcAdapterHash) {
        theJdbcAdapterHash = aJdbcAdapterHash;
    }

    public IFile[] getAffectedFiles() {
        return new IFile[]{new JBossDefaultDeployEcomFile("riams-ds.xml")};
    }

    public CanApplyResult canApply(IMainModel aMainModel) {
        return new CanApplyResult();
    }

    public void apply(IMainModel aModel) {
        DatasourceInfo info = aModel.getDatasourceInfo();
        String driver = info.getJdbcDriverClassname();
        if (!theJdbcAdapterHash.containsKey(driver)) {
            throw new IllegalStateException("The " + driver + " is not supported yet");
        }
        IJdbcAdapter adapter = theJdbcAdapterHash.get(driver);
        String template = adapter.getDatasourceTemplateName();
        InputStream in = getClass().getResourceAsStream(template);
        if(in==null) throw new IllegalStateException("The "+template+" was not found") ;
        try {
            LineNumberReader lin = new LineNumberReader(new InputStreamReader(in, "utf-8"));
            try {
                File file = aModel.getFile(getAffectedFiles()[0]) ;
                file.getParentFile().mkdirs() ;
                PrintWriter out = new PrintWriter(new OutputStreamWriter
                        (new FileOutputStream(file), "utf-8")) ;
                try {
                    String line ;
                    while ( (line=lin.readLine())!=null) {
                        out.println(replace(line, info)) ;
                    }
                } finally {
                    out.close() ;
                }
            } finally {
                lin.close();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    private String replace(String aLine, DatasourceInfo info) {
        String ret = aLine ;
        ret = ret.replace("$HOSTNAME$", info.getHostname()) ;
        ret = ret.replace("$PORT$", info.getPort()+"") ;
        ret = ret.replace("$DATABASE$", info.getDatabaseName()) ;
        ret = ret.replace("$USERNAME$", info.getUsername()) ;
        ret = ret.replace("$PASSWORD$", info.getPassword()) ;
        return ret ;
    }

    public String getName() {
        return "datasource";
    }

    public String getDescription() {
        return "Настройка datasource";
    }

    private final Map<String, IJdbcAdapter> theJdbcAdapterHash;
}
