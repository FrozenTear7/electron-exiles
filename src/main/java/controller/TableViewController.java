package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.DataRow;

import java.util.List;

public class TableViewController {
    private List<DataRow> dataRowList;

    @FXML
    private TableView<DataRow> dataRowTableView;

    @FXML
    private TableColumn<DataRow, String> dateCol;

    @FXML
    private TableColumn<DataRow, String> stockValueCol;

    @FXML
    private Label label1;

    @FXML
    private void initialize() {
        dataRowTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        dateCol.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getDate().toString()));
        stockValueCol.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getStockValue().toString()));
    }

    public void setDataAndLabel(List<DataRow> dataRowList, String filePath) {
        this.dataRowList = dataRowList;
        dataRowTableView.setItems(FXCollections.observableArrayList(dataRowList));
        label1.setText("File opened: " + filePath);
    }
}
