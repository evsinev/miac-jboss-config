package ru.ecom.jbossinstaller.service.impl.file;

/**
 * JBOSS_HOME/server/default/lib/FILENAME
 */
public class JBossDefaultLibFile extends AbstractFile {
    public JBossDefaultLibFile(String aName) {
        super(aName, new JBossDefaultLibDir());
    }
}
