package ru.ecom.jbossinstaller.service.impl.file;

/**
 * JBOSS_HOME/server/default/conf/FILENAME
 */
public class JBossDefaultConfFile extends AbstractFile {
    public JBossDefaultConfFile(String aName) {
        super(aName, new JBossDefaultConfDir());
    }
}
