package ru.ecom.jbossinstaller.service.impl.file;

/**
 * JBOSS_HOME/server/default/deploy/FILENAME
 */
public class JBossDefaultDeployFile extends AbstractFile {
    public JBossDefaultDeployFile(String aName) {
        super(aName, new JBossDefaultDeployDir());
    }
}
