package com.pittacode.apihelper.argument;

public final class SpaceInFileLocationException extends RuntimeException {
    public SpaceInFileLocationException(String location) {
        super("There is a space in the save location at position %d. [%s]"
                      .formatted(location.indexOf(" "), location));
    }
}
