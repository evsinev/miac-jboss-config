package ru.ecom.jbossinstaller.service.impl;

import ru.ecom.jbossinstaller.service.impl.file.JBossDefaultLibFile;
import ru.ecom.jbossinstaller.client.service.DatasourceInfo;

import java.io.File;

import junit.framework.TestCase;

/**
 *
 */
public class MainModelImplTest extends TestCase {

    public void test() {
        MainModelImpl model = new MainModelImpl(new File("jboss_test"), new DatasourceInfo());
        JBossDefaultLibFile file = new JBossDefaultLibFile("msh-util-voc.jar");
        File filesystemFile = model.getFile(file) ;
        System.out.println("filesystemFile.getAbsolutePath() = " + filesystemFile.getAbsolutePath());
        System.out.println("filesystemFile.exists() = " + filesystemFile.exists());
    }
}
