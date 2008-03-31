package ru.ecom.jbossinstaller.service.impl.file;

/**
 * JBOSS_HOME/server/default/ecom
 */
public class JBossDefaultEcomDir extends AbstractFile {
    public JBossDefaultEcomDir() {
        super("ecom", new JBossDefaultDir());
    }
}
