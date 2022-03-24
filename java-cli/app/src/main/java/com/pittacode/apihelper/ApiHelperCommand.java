package com.pittacode.apihelper;

import com.pittacode.apihelper.converter.ApiKeyConverter;
import com.pittacode.apihelper.converter.FileLocationConverter;
import com.pittacode.apihelper.domain.ApiKey;
import com.pittacode.apihelper.domain.FileLocation;
import com.pittacode.apihelper.domain.Method;

import java.net.URL;
import java.util.Map;
import java.util.concurrent.Callable;

import static picocli.CommandLine.Command;
import static picocli.CommandLine.Model;
import static picocli.CommandLine.Option;
import static picocli.CommandLine.ParameterException;
import static picocli.CommandLine.Spec;

@Command(name = "api-helper",
        description = "Downloads the JSON from specified resourse, " +
                "stores it in a *.json file, " +
                "converts it into a TSV (tab separated value) " +
                "and stores it in a *.tsv file",
        mixinStandardHelpOptions = true)
public final class ApiHelperCommand implements Callable<Integer> {

    @Spec
    private Model.CommandSpec spec;

    public Method method;

    @Option(names = {"-m", "--method"},
            description = "http method (GET, POST...)",
            paramLabel = "METHOD",
            defaultValue = "GET")
    private void setMethod(String value) {
        final var error = "`%s` is not supported. Select from %s".formatted(value, Method.SUPPORTED);
        method = Method.fromString(value)
                       .orElseThrow(() -> new ParameterException(spec.commandLine(), error));
    }

    @Option(names = {"-u", "--url"},
            required = true,
            description = "url of resource/api endpoint",
            paramLabel = "URL")
    public URL url;

    @Option(names = {"-a", "--api-key"},
            description = "url of resource or API endpoint",
            paramLabel = "API KEY",
            converter = ApiKeyConverter.class)
    public ApiKey apiKey;

    @Option(names = {"-s", "--save-location"},
            description = "full path for a file without its extension for storing the results, defaults to same location as executable with the file name data",
            paramLabel = "FILE",
            defaultValue = "./data",
            converter = FileLocationConverter.class)
    public FileLocation saveLocation;

    public Map<String, String> parameters = Map.of();

    private final ApiHelper apiHelper;

    public ApiHelperCommand(ApiHelper apiHelper) {
        this.apiHelper = apiHelper;
    }

    @Override
    public Integer call() {
        return apiHelper.execute(new Arguments(method, url, apiKey, parameters, saveLocation));
    }
}
