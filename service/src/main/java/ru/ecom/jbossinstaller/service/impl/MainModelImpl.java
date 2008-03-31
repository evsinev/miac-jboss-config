package ru.ecom.jbossinstaller.service.impl;

import ru.ecom.jbossinstaller.service.IMainModel;
import ru.ecom.jbossinstaller.service.impl.file.IFile;
import ru.ecom.jbossinstaller.service.impl.file.JBossDir;
import ru.ecom.jbossinstaller.client.service.DatasourceInfo;

import java.io.File;

/**
 *
 */
public class MainModelImpl implements IMainModel {

    public MainModelImpl(File aJbossDir, DatasourceInfo aDatasourceInfo) {
        theJBossDir = new JBossDir(aJbossDir);
        theDatasourceInfo = aDatasourceInfo ;
    }

    public File getFile(IFile aFile) {
        File ret = aFile.getFile(theJBossDir) ;
        if(ret==null) throw new IllegalStateException("File "+aFile.getName()+" "+aFile.getClass()+" was not found") ;
        return ret ;
    }


    public DatasourceInfo getDatasourceInfo() {
        return theDatasourceInfo;
    }

    private final DatasourceInfo theDatasourceInfo ;
    private final JBossDir theJBossDir ;
}
