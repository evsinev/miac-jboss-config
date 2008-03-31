package ru.ecom.jbossinstaller.service.impl.modify;

import ru.ecom.jbossinstaller.service.impl.file.IFile;
import ru.ecom.jbossinstaller.service.impl.file.JBossDefaultLibFile;

/**
 * Копирование cashedb.jar
 */
public class CopyCasheDbJarModify extends CopyFilesModify {
    public CopyCasheDbJarModify() {
        super("CasheDB.jar"
                , new IFile[] {new JBossDefaultLibFile("CacheDB.jar")}
                , "Копирование cashedb.jar"
                , "cachedb-jar");
    }
}
