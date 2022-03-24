package com.pittacode.apihelper.domain;

public record ApiKey(String value) {
    @Override
    public String toString() {
        return value;
    }

}
