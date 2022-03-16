package com.pittacode.apihelper.fetcher;

public final class FetcherException extends RuntimeException {
    public FetcherException(Exception e) {
        super(e);
    }

    public FetcherException(String message) {
        super(message);
    }
}
