package ru.ecom.jbossinstaller.service.impl.file;

/**
 * JBOSS_HOME/server/default/deploy/ecom
 */
public class JBossDefaultDeployEcomDir extends AbstractFile {
    public JBossDefaultDeployEcomDir() {
        super("ecom", new JBossDefaultDeployDir());
    }
}
