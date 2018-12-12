package controller;

import exceptions.LoadException;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.CsvDataLoader;
import model.DataRowList;
import model.JsonDataLoader;

import java.io.File;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class FileLoaderController {
    private LineChartController lineChartController;

    @FXML
    private Button openFileButton;

    @FXML
    private ListView historyView;

    @FXML
    private Text errorInfo;

    @FXML
    private void initialize() {
        openFileButton.setOnAction(this::handleButtonClick);
        historyView.setOnMouseClicked(this::handleHistoryClick);

        // Hardcoded adding .csv to the list for easier testing
        ClassLoader classLoader = getClass().getClassLoader();

        File dogeCsv = new File(classLoader.getResource("doge.csv").getFile());
        File appleCsvMini = new File(classLoader.getResource("aapl_us_d.csv").getFile());
        File appleCsv = new File(classLoader.getResource("aapl_us_d_2016.csv").getFile());
        File goldCsv = new File(classLoader.getResource("gold.csv").getFile());
        File dogeJson = new File(classLoader.getResource("doge.json").getFile());
        File appleJson = new File(classLoader.getResource("aapl.json").getFile());

        historyView.getItems().add(dogeCsv.getAbsolutePath());
        historyView.getItems().add(appleCsvMini.getAbsolutePath());
        historyView.getItems().add(appleCsv.getAbsolutePath());
        historyView.getItems().add(goldCsv.getAbsolutePath());
        historyView.getItems().add(dogeJson.getAbsolutePath());
        historyView.getItems().add(appleJson.getAbsolutePath());
    }

    public void setLineChartController(LineChartController lineChartController) {
        this.lineChartController = lineChartController;
    }

    private DataRowList getDataFromLoader(String filePath) {
        String csvRegex = ".*\\.csv";
        String jsonRegex = ".*\\.json";
        Pattern csvPattern = Pattern.compile(csvRegex);
        Pattern jsonPattern = Pattern.compile(jsonRegex);

        DataRowList dataRowList = null;

        if (csvPattern.matcher(filePath).find()) {
            CsvDataLoader dl = new CsvDataLoader();

            try {
                dataRowList = dl.getStockData(filePath);
            } catch (LoadException e) {
                errorInfo.setText(e.getMessage());
                errorInfo.setFill(Color.RED);
            }
        } else if (jsonPattern.matcher(filePath).find()) {
            JsonDataLoader dl = new JsonDataLoader();

            try {
                dataRowList = dl.getStockData(filePath);
            } catch (LoadException e) {
                errorInfo.setText(e.getMessage());
                errorInfo.setFill(Color.RED);
            }
        } else {
            errorInfo.setText("Stock data not recognized!");
            errorInfo.setFill(Color.RED);
        }

        return dataRowList;
    }

    private File selectFile() {
        FileChooser fc = new FileChooser();

        fc.setInitialDirectory(new File(Paths.get("").toAbsolutePath().toString()));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV", "*.csv"),
                new FileChooser.ExtensionFilter("JSON", "*.json")
        );

        return fc.showOpenDialog(null);
    }

    private void updateViews(DataRowList dataRowList, String filePath) {
        lineChartController.setData(dataRowList.getDataRowList());
    }

    private void updateHistory(String filePath) {
        ObservableList historyItems = historyView.getItems();
        if (!historyItems.contains(filePath)) historyItems.add(filePath);
    }

    private void handleButtonClick(Event event) {
        File file = selectFile();

        if (file != null) {
            String filePath = file.getAbsolutePath();

            DataRowList dataRowList = getDataFromLoader(filePath);

            if (dataRowList != null) {
                updateViews(dataRowList, filePath);
                updateHistory(filePath);
            }

        }
    }

    private void handleHistoryClick(MouseEvent event) {
        if (historyView.getSelectionModel().getSelectedItem() != null && event.getClickCount() == 2) {
            String filePath = (String) historyView.getSelectionModel().getSelectedItem();

            DataRowList dataRowList = getDataFromLoader(filePath);

            if (dataRowList != null) updateViews(dataRowList, filePath);
        }
    }
}
