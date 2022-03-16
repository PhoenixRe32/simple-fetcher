package com.pittacode.apihelper.argument;

import java.util.Arrays;

public final class MissingArgumentsException extends RuntimeException {
    public MissingArgumentsException(String[] args) {
        super("You only passed %d arguments. %s".
                      formatted(args.length, Arrays.toString(args)));
    }

}
