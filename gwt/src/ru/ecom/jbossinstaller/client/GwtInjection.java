package ru.ecom.jbossinstaller.client;

import ru.ecom.jbossinstaller.client.service.IConfigServiceAsync;
import ru.ecom.jbossinstaller.client.service.IConfigService;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 *
 */
public class GwtInjection {
    public IConfigServiceAsync getService() {
        IConfigServiceAsync service = (IConfigServiceAsync) GWT.create(IConfigService.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) service;

        String url = GWT.isScript() 
                ? GWT.getModuleBaseURL() + "configService"
                : "http://localhost:2410/configService";
        GWT.log("url = " + url, null);
        endpoint.setServiceEntryPoint(url);
        return service;
    }
}
