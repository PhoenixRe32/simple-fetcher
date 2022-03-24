package com.pittacode.apihelper.domain;

import java.util.Optional;
import java.util.Set;

public record Method(String value) {
    public static final Method HEAD = new Method("HEAD");
    public static final Method GET = new Method("GET");
    public static final Method POST = new Method("POST");
    public static final Set<Method> SUPPORTED = Set.of(HEAD, GET, POST);

    public static Optional<Method> fromString(String method) {
        return SUPPORTED.stream()
                        .filter(m -> m.value.equalsIgnoreCase(method))
                        .findFirst();
    }

    @Override
    public String toString() {
        return value;
    }
}
