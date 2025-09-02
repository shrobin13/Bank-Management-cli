package org.banking.management.exception;

public class AccountantException extends Exception{

    public AccountantException(String message) {
        super(message);
    }

    public AccountantException(String message, Exception ex) {
        super(message, ex);
    }

    public AccountantException(Exception ex) {
        super(ex);
    }
}
