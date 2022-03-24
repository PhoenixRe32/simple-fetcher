package com.pittacode.apihelper.domain;

public record FileLocation(String value) {
    @Override
    public String toString() {
        return value;
    }
}
