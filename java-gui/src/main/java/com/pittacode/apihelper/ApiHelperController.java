package com.pittacode.apihelper;

import com.google.gson.Gson;
import com.pittacode.opendevl.JFlat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static java.lang.String.format;
import static java.net.http.HttpClient.newHttpClient;

public class ApiHelperController {

    @FXML
    public GridPane params;
    @FXML
    public TextArea errorArea;
    @FXML
    public TextArea responsesArea;

    public TextField urlField;
    public TextField apiKeyField;
    public TextField saveLocationField;
    public ChoiceBox<String> methodField;

    @FXML
    public void onAddParameterButtonClick(ActionEvent actionEvent) {
        final var rowCount = params.getRowCount();
        params.addRow(rowCount, new TextField(), new TextField());
    }

    @FXML
    public void onDownloadButtonClick() {
        try {
            errorArea.setText("");
            responsesArea.setText("");
            final String requestJson = buildRequestBodyJson();

            final String responseJson = post(requestJson);
            convertToCsvAndWriteToFile(responseJson);
        } catch (Exception e) {
            final var stacktraceWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stacktraceWriter));

            errorArea.setText(format("Error message: %s%nStacktrace: %s",
                                     e.getMessage(),
                                     stacktraceWriter));
        }
    }

    private String buildRequestBodyJson() {
        final var paramTextFields = convertParamNodesToTextFields();
        final var paramKeyValues = goupIntoTuples(paramTextFields);
        return new Gson().toJson(paramKeyValues);
    }

    private String post(String requestJson) throws IOException, InterruptedException {
        final var uri = URI.create(urlField.getText().trim());
        final var method = methodField.getValue().toUpperCase(Locale.ROOT).trim();
        final var request = HttpRequest
                .newBuilder()
                .header("Content-Type", "application/json")
                .uri(uri)
                .method(method, BodyPublishers.ofString(requestJson))
                .build();
        final var response = newHttpClient().send(request, BodyHandlers.ofString());
        responsesArea.setText(format("Status: %s%nBody: %s",
                                     response.statusCode(),
                                     response.body()));
        return response.body();
    }

    private void convertToCsvAndWriteToFile(String responseJson) throws Exception {
        new JFlat(responseJson).json2Sheet().headerSeparator(".").write2csv(saveLocationField.getText());
    }

    private List<TextField> convertParamNodesToTextFields() {
        return params.getChildren().stream().map(n -> (TextField) n).toList();
    }

    private HashMap<String, String> goupIntoTuples(List<TextField> paramTextFields) {
        final var paramKeyValues = new HashMap<String, String>();
        for (var row = 0; row < params.getRowCount(); row++) {
            final var paramName = paramTextFields.get(row * 2).getText().trim();
            if (isBlank(paramName)) {
                continue;
            }

            final var paramValue = paramTextFields.get(row * 2 + 1).getText().trim();
            paramKeyValues.put(paramName, paramValue);
        }
        return paramKeyValues;
    }

    private boolean isBlank(String paramName) {
        return paramName == null || paramName.isBlank();
    }
}