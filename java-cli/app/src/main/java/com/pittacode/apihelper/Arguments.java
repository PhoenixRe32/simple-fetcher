package com.pittacode.apihelper;

import com.pittacode.apihelper.domain.ApiKey;
import com.pittacode.apihelper.domain.FileLocation;
import com.pittacode.apihelper.domain.Method;

import java.net.URL;
import java.util.Map;

public record Arguments(
        Method method,
        URL url,
        ApiKey apiKey,
        Map<String, String> parameters,
        FileLocation saveLocation
) {
}
