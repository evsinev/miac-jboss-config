package ru.ecom.jbossinstaller.service.impl.modify;

import ru.ecom.jbossinstaller.service.impl.file.IFile;
import ru.ecom.jbossinstaller.service.impl.file.JBossDefaultLibFile;
import ru.ecom.jbossinstaller.service.impl.file.JBossDefaultEcomFile;

/**
 * Копирование riams.properties
 */
public class CopyRiamsPropertiesModify extends CopyFilesModify {
    public CopyRiamsPropertiesModify() {
        super("riams.properties"
                , new IFile[] {new JBossDefaultEcomFile("riams.properties")}
                , "Копирование riams.properties"
                , "ecom");
    }
}
