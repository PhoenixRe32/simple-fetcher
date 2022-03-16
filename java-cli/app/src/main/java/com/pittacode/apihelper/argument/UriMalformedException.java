package com.pittacode.apihelper.argument;

public final class UriMalformedException extends RuntimeException {
    public UriMalformedException(Exception e) {
        super(e);
    }
}
