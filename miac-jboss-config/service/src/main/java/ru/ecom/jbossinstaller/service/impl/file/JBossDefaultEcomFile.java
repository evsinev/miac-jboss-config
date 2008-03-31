package ru.ecom.jbossinstaller.service.impl.file;

/**
 * JBOSS_HOME/server/default/ecom/FILENAME
 */
public class JBossDefaultEcomFile extends AbstractFile {
    public JBossDefaultEcomFile(String aName) {
        super(aName, new JBossDefaultEcomDir());
    }
}
