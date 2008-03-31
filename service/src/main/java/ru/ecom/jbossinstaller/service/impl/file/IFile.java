package ru.ecom.jbossinstaller.service.impl.file;

import java.io.File;

/**
 * Файл
 */
public interface IFile {

    String getName() ;

    File getFile(IFile aFile) ;
}
