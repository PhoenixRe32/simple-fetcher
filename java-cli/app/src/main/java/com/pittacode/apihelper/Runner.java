package com.pittacode.apihelper;

import com.pittacode.apihelper.argument.ArgumentProcessor;
import com.pittacode.apihelper.fetcher.StatisticsFetcher;
import com.pittacode.apihelper.file.FileWriter;

public final class Runner {

    private static final String INTRO = "Api helper converting JSON to CSV:-)"
            + System.lineSeparator()
            + "----------------------------"
            + System.lineSeparator() + System.lineSeparator()
            + "Command: apihelper method url apikey savelocation paramName1=paramValue1 paramName2=paramValue2 ..."
            + System.lineSeparator()
            + "Arguments:"
            + System.lineSeparator()
            + "\t* method: The type of request [GET, POST, PUT...]"
            + System.lineSeparator()
            + "\t* url: The address for the api endpoint [https://api.endpoint.to/resource]"
            + System.lineSeparator()
            + "\t* apikey: The API key provided from the service"
            + System.lineSeparator()
            + "\t* savelocation: Where to store the json and CSV. Please make sure there is no space in the path. [C:\\path\\to\\folder\\nameoffile]"
            + System.lineSeparator()
            + "\t* paramName1=paramValue1: " +
            "Eveything after this are treated as body parameters and they should be in the format of key=value"
            + System.lineSeparator() + System.lineSeparator();


    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.print(INTRO);
            return;
        }

        final var argumentProcessor = new ArgumentProcessor();
        final var statisticsFetcher = new StatisticsFetcher();
        final var fileSaver = new FileWriter();
        final var apiHelper = new ApiHelper(argumentProcessor, statisticsFetcher, fileSaver);

        apiHelper.run(args);
    }
}
