package ru.ecom.jbossinstaller.service.impl.modify;

import ru.ecom.jbossinstaller.service.impl.file.IFile;
import ru.ecom.jbossinstaller.service.impl.file.JBossDefaultConfFile;
import ru.ecom.jbossinstaller.service.impl.file.JBossDefaultDeployEcomFile;

/**
 * Копирование riams-ds.xml
 */
public class CopyDataSourceModify extends CopyFilesModify {
    public CopyDataSourceModify() {
        super("riams-ds.xml"
                , new IFile[]{
                new JBossDefaultDeployEcomFile("riams-ds.xml")
        }
                , "Копирование riams-ds.xml"
                , "datasource");
    }
}
