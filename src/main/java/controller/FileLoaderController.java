package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import model.AppleStockDataLoader;
import model.DataRow;
import model.DogeStockDataLoader;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class FileLoaderController {
    private TableViewController tableViewController;
    private LineChartController lineChartController;

    @FXML
    private Button button1;

    @FXML
    private ListView listView1;

    @FXML
    private void initialize() {
        handleButtonClick();
        handleHistoryClick();
    }

    public void setTableViewController(TableViewController tableViewController) {
        this.tableViewController = tableViewController;
    }

    public void setLineChartController(LineChartController lineChartController) {
        this.lineChartController = lineChartController;
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
                DogeStockDataLoader dl = new DogeStockDataLoader(filePath);
                List<DataRow> data = dl.getStockData();
                tableViewController.setDataAndLabel(data, filePath);
                lineChartController.setData(data);

                ObservableList listView1Items = listView1.getItems();

                if (!listView1Items.contains(filePath)) listView1Items.add(filePath);
            }
        });
    }

    private void handleHistoryClick() {
        listView1.setOnMouseClicked(event -> {
            if (listView1.getSelectionModel().getSelectedItem() != null && event.getClickCount() == 2) {
                String filePath = (String) listView1.getSelectionModel().getSelectedItem();
                DogeStockDataLoader dl = new DogeStockDataLoader(filePath);
                List<DataRow> data = dl.getStockData();
                tableViewController.setDataAndLabel(data, filePath);
                lineChartController.setData(data);
            }
        });
    }
}
