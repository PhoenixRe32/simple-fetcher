package com.pittacode.apihelper.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FileWriter {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileWriter.class);

    public String write(String content, String saveLocation) {
        try {
            LOGGER.debug("Writing file {}. Content: {}", saveLocation, content);
            Files.writeString(Path.of(saveLocation), content);
            return content;
        } catch (IOException e) {
            LOGGER.warn("Failed to write file {} because {} but continuing anyway", saveLocation, e.getMessage());
            return content;
        }
    }
}
