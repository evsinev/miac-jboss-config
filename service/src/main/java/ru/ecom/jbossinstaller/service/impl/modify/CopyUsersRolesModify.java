package ru.ecom.jbossinstaller.service.impl.modify;

import ru.ecom.jbossinstaller.service.impl.file.IFile;
import ru.ecom.jbossinstaller.service.impl.file.JBossDefaultLibFile;
import ru.ecom.jbossinstaller.service.impl.file.JBossDefaultConfFile;

/**
 * Копирование users.properties и roles.properties
 */
public class CopyUsersRolesModify extends CopyFilesModify {
    public CopyUsersRolesModify() {
        super("users.properties и roles.properties"
                , new IFile[]{
                new JBossDefaultConfFile("users.properties")
                , new JBossDefaultConfFile("roles.properties")
        }
                , "Копирование users.properties и roles.properties"
                , "usersroles");
    }
}
