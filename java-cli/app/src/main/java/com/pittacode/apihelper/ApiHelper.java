package com.pittacode.apihelper;

import com.pittacode.apihelper.argument.ArgumentProcessor;
import com.pittacode.apihelper.argument.Arguments;
import com.pittacode.apihelper.fetcher.StatisticsFetcher;
import com.pittacode.apihelper.file.FileWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpResponse;
import java.util.Optional;

public final class ApiHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiHelper.class);

    private final ArgumentProcessor argumentProcessor;
    private final StatisticsFetcher statisticsFetcher;
    private final FileWriter fileSaver;

    public ApiHelper(ArgumentProcessor argumentProcessor,
                     StatisticsFetcher statisticsFetcher,
                     FileWriter fileSaver) {
        this.argumentProcessor = argumentProcessor;
        this.statisticsFetcher = statisticsFetcher;
        this.fileSaver = fileSaver;
    }

    public void run(String[] args) {
        try {
            run(argumentProcessor.process(args));
        } catch (Exception e) {
            LOGGER.debug(e.getMessage(), e);
            LOGGER.error(e.getMessage());
        }
    }

    private void run(Arguments arguments) {
        final var jsonFile = "%s.json".formatted(arguments.saveLocation());
        final var csvFile = "%s.tsv".formatted(arguments.saveLocation());
        Optional.of(statisticsFetcher.fetchFor(arguments))
                .map(HttpResponse::body)
                .map(response -> fileSaver.write(response, jsonFile));
    }
}
