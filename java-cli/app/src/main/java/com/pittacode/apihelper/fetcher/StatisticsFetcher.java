package com.pittacode.apihelper.fetcher;

import com.google.gson.Gson;
import com.pittacode.apihelper.argument.Arguments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import static java.net.http.HttpClient.newHttpClient;

public final class StatisticsFetcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsFetcher.class);

    public HttpResponse<String> execute(Arguments arguments) {
        try {
            return makeRequest(arguments);
        } catch (Exception e) {
            throw new FetcherException(e);
        }
    }

    private HttpResponse<String> makeRequest(Arguments arguments) throws IOException, InterruptedException {
        final var requestJson = new Gson().toJson(arguments.bodyParameters());
        final var request = HttpRequest
                .newBuilder()
                .header("Content-Type", "application/json")
                .header("X-API-KEY", arguments.apiKey())
                .uri(arguments.uri())
                .method(arguments.method(), BodyPublishers.ofString(requestJson))
                .build();
        LOGGER.debug("{} {} {}, {}", request.method(), request.uri(), request.headers().map(), requestJson);
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