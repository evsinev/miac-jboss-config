package ru.ecom.jbossinstaller.service.impl;

import ru.ecom.jbossinstaller.service.ILocalConfigService;
import ru.ecom.jbossinstaller.service.IMainModel;
import ru.ecom.jbossinstaller.service.impl.modify.*;
import ru.ecom.jbossinstaller.service.impl.jdbc.IJdbcAdapter;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 */
public class LocalConfigServiceImpl implements ILocalConfigService {

    public LocalConfigServiceImpl(Map<String, IJdbcAdapter> aJdbcAdapterHash) {
        theModifies.add(new CopyMshUtilVocJarModify());
        theModifies.add(new CopyEcomJbossJarModify());
        theModifies.add(new CopyCasheDbJarModify());
        theModifies.add(new CopyUsersRolesModify());
        theModifies.add(new ConfigureDatasourceModify(aJdbcAdapterHash));
        theModifies.add(new CopyRiamsPropertiesModify());
        theModifies.add(new CopyLoginConfigModify());
        theModifies.add(new CopyEjb3InterceptorsAopModify());
    }

    public String configure(IMainModel aMainModel) {
        StringBuilder sb = new StringBuilder();
        for (IModify modify : theModifies) {
            sb.append(" " + modify.getName() + "  ...");
            if (modify.canApply(aMainModel).canApply()) {
                modify.apply(aMainModel);
                sb.append(" обновлен");
            } else {
                sb.append(" обновлять не надо");
            }
            sb.append("\n") ;
        }
        return sb.toString() ;
    }

    private final ArrayList<IModify> theModifies = new ArrayList<IModify>();
}
