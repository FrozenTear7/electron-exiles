package controller;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private void initialize() {
        dataRowTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        dateCol.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getDate()));
        openCol.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getOpen()));
        highCol.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getHigh()));
        lowCol.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getLow()));
        closeCol.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getClose()));
        volumeCol.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getVolume()));
    }

    public void setData(List<DataRow> dataRowList) {
        if (this.dataRowList != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.dataRowList = dataRowList;

        dataRowTableView.setItems(FXCollections.observableArrayList(dataRowList));
    }
}
