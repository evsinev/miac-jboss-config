package ru.ecom.jbossinstaller.service.impl.file;

/**
 * JBOSS_HOME/server/default/deploy
 */
public class JBossDefaultDeployDir extends AbstractFile {
    public JBossDefaultDeployDir() {
        super("deploy", new JBossDefaultDir());
    }
}
