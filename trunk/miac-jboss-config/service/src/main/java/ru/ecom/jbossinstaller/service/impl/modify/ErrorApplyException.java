package ru.ecom.jbossinstaller.service.impl.modify;

/**
 * Created by IntelliJ IDEA.
 * User: esinev
 * Date: Feb 19, 2008
 * Time: 8:42:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class ErrorApplyException extends RuntimeException {

    public ErrorApplyException(String message) {
        super(message);
    }


    public ErrorApplyException(String message, Throwable cause) {
        super(message, cause);
    }

    
}
