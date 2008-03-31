package ru.ecom.jbossinstaller.service.impl.file;

/**
 * JBOSS_HOME/server/default/lib
 */
public class JBossDefaultLibDir extends AbstractFile {
    public JBossDefaultLibDir() {
        super("lib", new JBossDefaultDir());
    }
}
