package com.pittacode.apihelper.argument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import static java.util.Arrays.copyOfRange;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toMap;

public final class ArgumentProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArgumentProcessor.class);

    private static final Set<String> METHODS = Set.of("HEAD", "GET", "POST", "PUT", "DELETE");

    public Arguments process(String[] args) {
        validateNumberOfArguments(args);
        final var method = processMethod(args[0]);
        final var uri = processUri(args[1]);
        final var apiKey = args[2];
        final var saveLocation = processSaveLocation(args[3]);
        final var bodyParameters = convertToMap(copyOfRange(args, 4, args.length));
        return new Arguments(method, uri, apiKey, saveLocation, bodyParameters);
    }

    private String processMethod(String arg) {
        LOGGER.debug("Processing " + arg);
        return METHODS.stream()
                      .filter(m -> m.equalsIgnoreCase(arg))
                      .findFirst()
                      .orElseThrow(() -> new UnsupportedMethodException(arg, METHODS));
    }

    private URI processUri(String arg) {
        LOGGER.debug("Processing " + arg);
        try {
            return URI.create(arg);
        } catch (Exception e) {
            throw new UriMalformedException(e);
        }
    }

    private String processSaveLocation(String arg) {
        LOGGER.debug("Processing " + arg);
        if (arg.contains(" ")) throw new SpaceInFileLocationException(arg);
        return arg;
    }

    private void validateNumberOfArguments(String[] args) {
        LOGGER.debug("Processing " + Arrays.toString(args));
        if (args.length < 4) throw new MissingArgumentsException(args);
    }

    private Map<String, String> convertToMap(String[] keyValuePairs) {
        LOGGER.debug("Processing " + Arrays.toString(keyValuePairs));
        return Arrays.stream(keyValuePairs)
                     .filter(not(String::isBlank))
                     .map(argument -> argument.split("="))
                     .filter(this::isValidSyntax)
                     .collect(toMap(kv -> kv[0], kv -> kv[1]));
    }

    private boolean isValidSyntax(String[] keyValue) {
        if (keyValue.length == 2) return true;

        LOGGER.warn("%s is not correctly formatted. Ignoring it."
                            .formatted(Arrays.toString(keyValue)));
        return false;
    }
}
