package controller;

import exceptions.LoadException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.AppleStockDataLoader;
import model.DataRowList;
import model.DogeStockDataLoader;

import java.io.File;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class FileLoaderController {
    private TableViewController tableViewController;
    private LineChartController lineChartController;

    @FXML
    private Button button1;

    @FXML
    private ListView listView1;

    @FXML
    private Text errorInfo;

    @FXML
    private void initialize() {
        handleButtonClick();
        handleHistoryClick();
        listView1.getItems().add("C:\\#wojtek\\TO@\\electron-exiles\\src\\main\\resources\\aapl_us_d_2016.csv");
    }

    public void setTableViewController(TableViewController tableViewController) {
        this.tableViewController = tableViewController;
    }

    public void setLineChartController(LineChartController lineChartController) {
        this.lineChartController = lineChartController;
    }

    private DataRowList getDataFromLoader(String filePath) throws LoadException {
        String dogeRegex = ".*doge\\.csv";
        String appleRegex = ".*aapl.*\\.csv";

        Pattern dogePattern = Pattern.compile(dogeRegex);
        Pattern applePattern = Pattern.compile(appleRegex);

        if (dogePattern.matcher(filePath).find()) {
            DogeStockDataLoader dl = new DogeStockDataLoader(filePath);
            return dl.getStockData();
        } else if (applePattern.matcher(filePath).find()) {
            AppleStockDataLoader dl = new AppleStockDataLoader(filePath);
            return dl.getStockData();
        }

        errorInfo.setText("Stock data not recognized!");
        errorInfo.setFill(Color.RED);
        return null;
    }

    private void handleButtonClick() {
        button1.setOnAction(event -> {
            FileChooser fc = new FileChooser();
            fc.setInitialDirectory(new File(Paths.get("").toAbsolutePath().toString()));
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("CSV", "*.csv")
            );

            File file = fc.showOpenDialog(null);

            if (file != null) {
                String filePath = file.getAbsolutePath();
                DataRowList dataRowList = new DataRowList();

                try {
                    dataRowList = getDataFromLoader(filePath);
                } catch (LoadException e) {
                    errorInfo.setText(e.getMessage());
                    errorInfo.setFill(Color.RED);
                }

                if (dataRowList != null) {
                    tableViewController.setDataAndLabel(dataRowList.getDataRowList(), filePath);
                    lineChartController.setData(dataRowList.getDataRowList());
                    errorInfo.setText("");
                }

                ObservableList listView1Items = listView1.getItems();
                if (!listView1Items.contains(filePath)) listView1Items.add(filePath);
            }
        });
    }

    private void handleHistoryClick() {
        listView1.setOnMouseClicked(event -> {
            if (listView1.getSelectionModel().getSelectedItem() != null && event.getClickCount() == 2) {
                String filePath = (String) listView1.getSelectionModel().getSelectedItem();

                DataRowList dataRowList = new DataRowList();

                try {
                    dataRowList = getDataFromLoader(filePath);
                } catch (LoadException e) {
                    errorInfo.setText(e.getMessage());
                    errorInfo.setFill(Color.RED);
                }

                if (dataRowList != null) {
                    tableViewController.setDataAndLabel(dataRowList.getDataRowList(), filePath);
                    lineChartController.setData(dataRowList.getDataRowList());
                    errorInfo.setText("");
                }

            }
        });
    }
}
