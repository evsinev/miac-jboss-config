package ru.ecom.jbossinstaller.service.impl.file;

/**
 *
 * JBOSS_HOME/server/default
 */
public class JBossDefaultDir extends AbstractFile {
    public JBossDefaultDir() {
        super("default", new JBossServerDir());
    }
}
