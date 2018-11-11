package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import model.DataRow;

import java.util.List;

public class MainPanelController {
    private List<DataRow> dataRowList;

    @FXML
    private ListView<DataRow> dataRowListView;

    public void initModel(List<DataRow> dataRowList) {
        if (this.dataRowList != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.dataRowList = dataRowList;

        dataRowListView.setItems(FXCollections.observableArrayList(dataRowList));

        dataRowListView.setCellFactory(lv -> new ListCell<DataRow>() {
            @Override
            public void updateItem(DataRow dataRow, boolean empty) {
                super.updateItem(dataRow, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText("Date: " + dataRow.getDate() + " - Open: " + dataRow.getOpen() + " - High: " +
                            dataRow.getHigh() + " - Low: " + dataRow.getLow() + " - Close: " + dataRow.getClose() +
                            " - Volume: " + dataRow.getVolume());
                }
            }
        });
    }
}
