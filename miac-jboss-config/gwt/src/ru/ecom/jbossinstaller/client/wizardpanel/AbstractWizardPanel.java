package ru.ecom.jbossinstaller.client.wizardpanel;

import ru.ecom.jbossinstaller.client.service.ConfigModel;
import ru.ecom.jbossinstaller.client.service.IConfigServiceAsync;
import ru.ecom.jbossinstaller.client.GwtInjection;

/**
 * Abstraction Wizard Panel
 */
public abstract class AbstractWizardPanel implements IWizardPanel {

    
    public void save(ConfigModel aModel) {
    }


    public void load(ConfigModel aModel) {
    }


    public void setService(IConfigServiceAsync aService) {
        theService = aService ;
    }

    IConfigServiceAsync theService ;
}
