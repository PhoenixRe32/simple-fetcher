package com.pittacode.apihelper.converter;

import com.pittacode.apihelper.domain.ApiKey;
import picocli.CommandLine;

public class ApiKeyConverter implements CommandLine.ITypeConverter<ApiKey> {
    @Override
    public ApiKey convert(String value) {
        return new ApiKey(value);
    }
}
