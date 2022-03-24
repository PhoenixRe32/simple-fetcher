package com.pittacode.apihelper;

import com.pittacode.apihelper.fetcher.StatisticsFetcher;
import com.pittacode.apihelper.file.FileWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpResponse;
import java.util.Optional;

public final class ApiHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiHelper.class);

    private final StatisticsFetcher statisticsFetcher;
    private final FileWriter fileSaver;

    public ApiHelper(StatisticsFetcher statisticsFetcher,
                     FileWriter fileSaver) {
        this.statisticsFetcher = statisticsFetcher;
        this.fileSaver = fileSaver;
    }

    public Integer execute(Arguments arguments) {
        final var jsonFile = "%s.json".formatted(arguments.saveLocation().value());
        final var csvFile = "%s.tsv".formatted(arguments.saveLocation().value());
        int exitCode = 0;

        try {
            Optional.of(statisticsFetcher.fetchFor(arguments))
                    .map(HttpResponse::body)
                    .map(response -> fileSaver.write(response, jsonFile));
        } catch (Exception e) {
            LOGGER.debug(e.getMessage(), e);
            LOGGER.error(e.getMessage());
            exitCode += 1;
        }

        return exitCode;
    }
}
