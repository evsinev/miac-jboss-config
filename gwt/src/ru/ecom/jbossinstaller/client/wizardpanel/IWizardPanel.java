package ru.ecom.jbossinstaller.client.wizardpanel;

import com.google.gwt.user.client.ui.Panel;
import ru.ecom.jbossinstaller.client.service.ConfigModel;
import ru.ecom.jbossinstaller.client.service.IConfigServiceAsync;
import ru.ecom.jbossinstaller.client.GwtInjection;

/**
 * Wizard Panel
 */
public interface IWizardPanel {
    /**
     * Name
     */
    String getName() ;

    /**
     * Validate
     */
    void save(ConfigModel aModel) ;

    /**
     * Load
     */
    void load(ConfigModel aModel) ;

    void setService(IConfigServiceAsync aService) ;
    /**
     * Panel
     */
    Panel getPanel() ;
}
