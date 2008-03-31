package ru.ecom.jbossinstaller.client.service;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by IntelliJ IDEA.
 * User: esinev
 * Date: Feb 22, 2008
 * Time: 3:36:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class ConfigException extends Exception implements IsSerializable {


    public ConfigException() {
    }

    public ConfigException(String message) {
        super(message);
        theMessage = message ;
    }

    public ConfigException(String message, Throwable cause) {
        super(message, cause);
        theMessage = message ;
    }


    public String getMessage() {
        return theMessage ;
    }
    public void setMessage(String aMessage) {
        theMessage = aMessage;
    }

    private String theMessage ;
}
