package ru.ecom.jbossinstaller.service.impl.modify;

import ru.ecom.jbossinstaller.service.impl.file.IFile;
import ru.ecom.jbossinstaller.service.impl.file.JBossDefaultDeployFile;

/**
 * Копирование ejb3-interceptors-aop.xml
 */
public class CopyEjb3InterceptorsAopModify extends CopyFilesModify {
    public CopyEjb3InterceptorsAopModify() {
        super("ejb3-interceptors-aop.xml"
                , new IFile[] {new JBossDefaultDeployFile("ejb3-interceptors-aop.xml")}
                , "Копирование ejb3-interceptors-aop.xml"
                , "ejb3-interceptors-aop");
    }
}
