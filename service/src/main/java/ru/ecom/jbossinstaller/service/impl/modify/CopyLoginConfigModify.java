package ru.ecom.jbossinstaller.service.impl.modify;

import ru.ecom.jbossinstaller.service.impl.file.IFile;
import ru.ecom.jbossinstaller.service.impl.file.JBossDefaultEcomFile;
import ru.ecom.jbossinstaller.service.impl.file.JBossDefaultConfFile;

/**
 * Копирование login-config.xml
 */
public class CopyLoginConfigModify extends CopyFilesModify {
    public CopyLoginConfigModify() {
        super("login-config.xml"
                , new IFile[] {new JBossDefaultConfFile("login-config.xml")}
                , "Копирование login-config.xml"
                , "login-config");
    }
}
