package com.pittacode.apihelper.fetcher;

import com.google.gson.Gson;
import com.pittacode.apihelper.Arguments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;

import static java.net.http.HttpClient.newHttpClient;

public final class StatisticsFetcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsFetcher.class);

    public HttpResponse<String> fetchFor(Arguments arguments) {
        try {
            return makeRequest(arguments);
        } catch (Exception e) {
            throw new FetcherException(e);
        }
    }

    private HttpResponse<String> makeRequest(Arguments arguments) throws IOException, InterruptedException, URISyntaxException {
        final var requestBuilder = HttpRequest.newBuilder()
                                              .uri(arguments.url().toURI())
                                              .header("Content-Type", "application/json");

        if (arguments.apiKey() != null) {
            requestBuilder.header("X-API-KEY", arguments.apiKey().value());
        }

        if (arguments.parameters().isEmpty()) {
            requestBuilder.method(arguments.method().value(), BodyPublishers.noBody());
        } else {
            final var requestJson = new Gson().toJson(Map.of());
            requestBuilder.method(arguments.method().value(), BodyPublishers.ofString(requestJson));
        }

        final var request = requestBuilder.build();
        LOGGER.debug("{} {} {}, {}", request.method(), request.uri(), request.headers().map(), arguments.parameters());
        final var response = newHttpClient().send(request, BodyHandlers.ofString());
        return successful(response);
    }

    private HttpResponse<String> successful(HttpResponse<String> response) {
        LOGGER.debug("Response Status: {}, Response Body: {}",
                     response.statusCode(),
                     response.body());
        final var statusCode = response.statusCode();
        if (statusCode >= 200 && statusCode < 300) return response;

        throw new FetcherException("Request to %s failed with status %s because of: %s. Stopping flow."
                                           .formatted(response.uri(),
                                                      response.statusCode(),
                                                      response.body()));
    }
}