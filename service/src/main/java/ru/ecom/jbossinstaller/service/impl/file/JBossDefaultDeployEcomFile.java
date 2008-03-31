package ru.ecom.jbossinstaller.service.impl.file;

/**
 * JBOSS_HOME/server/default/deploy/ecom/FILENAME
 */
public class JBossDefaultDeployEcomFile extends AbstractFile {
    public JBossDefaultDeployEcomFile(String aName) {
        super(aName, new JBossDefaultDeployEcomDir());
    }
}
