package ru.ecom.jbossinstaller.service.impl.modify;

import ru.ecom.jbossinstaller.service.impl.file.IFile;
import ru.ecom.jbossinstaller.service.IMainModel;

/**
 * Изменение
 */
public interface IModify {

    IFile[] getAffectedFiles();

    CanApplyResult canApply(IMainModel aMainModel) ;

    void apply(IMainModel aModel) ;

    String getName() ;

    String getDescription() ;
}
