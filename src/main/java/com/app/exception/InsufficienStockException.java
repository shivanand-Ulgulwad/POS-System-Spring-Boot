package com.app.exception;

public class InsufficienStockException extends RuntimeException {
    public InsufficienStockException(String msg) {
        super(msg);
    }
}
