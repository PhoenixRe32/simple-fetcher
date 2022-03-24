package com.pittacode.apihelper;

import com.pittacode.apihelper.fetcher.StatisticsFetcher;
import com.pittacode.apihelper.file.FileWriter;
import picocli.CommandLine;

public final class Runner {
    public static void main(String[] args) {
        final var statisticsFetcher = new StatisticsFetcher();
        final var fileSaver = new FileWriter();
        final var apiHelper = new ApiHelper(statisticsFetcher, fileSaver);
        final var apiHelperCommand = new ApiHelperCommand(apiHelper);

        System.exit(new CommandLine(apiHelperCommand).execute(args));
    }
}
