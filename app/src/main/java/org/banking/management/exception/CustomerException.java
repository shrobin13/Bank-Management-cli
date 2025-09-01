package org.banking.management.exception;

public class CustomerException extends Exception {

    public CustomerException(String message) {
        super(message);
    }

    public CustomerException(String message, Exception ex) {
        super(message, ex);
    }

    public CustomerException(Exception ex) {
        super(ex);
    }
}
