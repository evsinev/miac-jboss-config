package ru.ecom.jbossinstaller.service.impl.modify;

/**
 * Прошел или не прошел
 */
public class CanApplyResult {

    /**
     * Не прошел
     * @param aCause
     */
    public CanApplyResult(String aCause) {
        theCanAppy = false;
        theCause = aCause;
    }

    /**
     * Прошел
     */
    public CanApplyResult() {
        theCanAppy = true;
        theCause = null;
    }


    public boolean canApply() {
        return theCanAppy ;
    }

    public String getCause() {
        if(theCanAppy) throw new IllegalStateException("Illegal use! canApply must be false for cause") ;
        return theCause ;
    }

    private final boolean theCanAppy ;
    private final String  theCause ;
}
