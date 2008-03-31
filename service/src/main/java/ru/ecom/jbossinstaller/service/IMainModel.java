package ru.ecom.jbossinstaller.service;

import ru.ecom.jbossinstaller.service.impl.file.IFile;
import ru.ecom.jbossinstaller.client.service.DatasourceInfo;

import java.io.File;

/**
 * Модель для хранения значений
 */
public interface IMainModel {

    File getFile(IFile aFile);

    DatasourceInfo getDatasourceInfo() ;
}
