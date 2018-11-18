package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import model.DataLoader;
import model.DataRow;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class FileLoaderController {
    private TableViewController tableViewController;

    @FXML
    private Button button1;

    @FXML
    private ListView listView1;

    @FXML
    private void initialize() {
        button1.setOnAction(event -> {
            FileChooser fc = new FileChooser();
            fc.setInitialDirectory(new File(Paths.get("").toAbsolutePath().toString()));
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("CSV", "*.csv")
            );

            File file = fc.showOpenDialog(null);

            if (file != null){
                String filePath = file.getAbsolutePath();
                DataLoader dl = new DataLoader(filePath);
                List<DataRow> data = dl.getStockData();
                tableViewController.setData(data);
                tableViewController.setLabel1Text("File opened: " + filePath);

                ObservableList listView1Items = listView1.getItems();

                if (!listView1Items.contains(filePath)) listView1Items.add(filePath);
            }
        });

        listView1.setOnMouseClicked(event -> {
            if (listView1.getSelectionModel().getSelectedItem() != null && event.getClickCount() == 2){
                String filePath = (String) listView1.getSelectionModel().getSelectedItem();
                DataLoader dl = new DataLoader(filePath);
                List<DataRow> data = dl.getStockData();
                tableViewController.setData(data);
                tableViewController.setLabel1Text("File opened: " + filePath);
            }
        });

    }

    public void setTableViewController(TableViewController tableViewController){
        this.tableViewController = tableViewController;
    }

}
