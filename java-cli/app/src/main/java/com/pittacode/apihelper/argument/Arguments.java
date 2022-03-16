package com.pittacode.apihelper.argument;

import java.net.URI;
import java.util.Map;

public record Arguments(
        String method,
        URI uri,
        String apiKey,
        String saveLocation,
        Map<String, String> bodyParameters
) {
}
