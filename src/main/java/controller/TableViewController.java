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
    private TableColumn<DataRow, String> openCol;

    @FXML
    private TableColumn<DataRow, String> highCol;

    @FXML
    private TableColumn<DataRow, String> lowCol;

    @FXML
    private TableColumn<DataRow, String> closeCol;

    @FXML
    private TableColumn<DataRow, String> volumeCol;

    @FXML
    private Label label1;

    @FXML
    private void initialize() {
        dataRowTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        dateCol.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getDate()));
        openCol.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getOpen()));
        highCol.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getHigh()));
        lowCol.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getLow()));
        closeCol.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getClose()));
        volumeCol.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getVolume()));
    }

    public void setDataAndLabel(List<DataRow> dataRowList, String filePath) {
        this.dataRowList = dataRowList;
        dataRowTableView.setItems(FXCollections.observableArrayList(dataRowList));
        label1.setText("File opened: " + filePath);
    }
}
