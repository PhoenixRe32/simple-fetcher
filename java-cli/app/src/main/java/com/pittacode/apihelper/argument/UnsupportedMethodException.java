package com.pittacode.apihelper.argument;

import java.util.Set;

public final class UnsupportedMethodException extends RuntimeException {
    public UnsupportedMethodException(String method, Set<String> supporteMethods) {
        super("The supplied method %s is not supported. %s"
                      .formatted(method, supporteMethods.toString()));
    }
}
