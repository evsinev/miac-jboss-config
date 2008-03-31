package ru.ecom.jbossinstaller.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.ecom.jbossinstaller.client.service.ConfigException;

/**
 */
public abstract class BaseAsyncCallback implements AsyncCallback {

	public void onFailure(Throwable caught) {
		try {
			throw caught;
        } catch(ConfigException e) {
            Window.alert(e.getMessage());
        } catch (Throwable e) {
			Window.alert("ERROR: "+e);
		}
	}

}
