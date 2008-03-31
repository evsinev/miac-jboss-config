package ru.ecom.jbossinstaller.service.impl.file;

/**
 * JBOSS_HOME/server/default/conf
 */
public class JBossDefaultConfDir extends AbstractFile {
    public JBossDefaultConfDir() {
        super("conf", new JBossDefaultDir());
    }
}
