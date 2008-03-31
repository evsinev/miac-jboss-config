package ru.ecom.jbossinstaller.service.impl.modify;

import ru.ecom.jbossinstaller.service.impl.file.IFile;
import ru.ecom.jbossinstaller.service.impl.file.JBossDefaultLibFile;

/**
 * Копирование msh-util-voc.jar
 */
public class CopyMshUtilVocJarModify extends CopyFilesModify {
    public CopyMshUtilVocJarModify() {
        super("msh-util-voc.jar"
                , new IFile[] {new JBossDefaultLibFile("msh-util-voc.jar")}
                , "Копирование ecom-jboss.jar"
                , "msh-util-voc-jar");
    }
}
