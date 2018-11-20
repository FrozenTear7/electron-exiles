package controller;

import java.io.File;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.DataLoader;
import model.DataRow;

import java.util.List;

public class LineChartController {

    @FXML
    private CategoryAxis xAxis = new CategoryAxis();

    @FXML
    private NumberAxis yAxis = new NumberAxis();

    @FXML
    private LineChart<String,Number> lineChart1 = new LineChart<>(xAxis, yAxis);

    @FXML
    private void initialize() {
        xAxis.setLabel("Date");
        yAxis.setLabel("Value");

        lineChart1.setTitle("Data chart uwu");
        lineChart1.setCreateSymbols(false);

        XYChart.Series<String, Number> series = prepareData();

        lineChart1.getData().add(series);
    }

    private XYChart.Series<String, Number> prepareData() {
        XYChart.Series<String, Number> result = new XYChart.Series<>();
        result.setName("uuga buga");

        String csvFile = new File("").getAbsolutePath() + "/src/main/resources/" + "aapl_us_d.csv";
        DataLoader dataLoader = new DataLoader(csvFile);
        List<DataRow> data = dataLoader.getStockData();
        for(DataRow row : data){
            result.getData().add(new XYChart.Data<String, Number>(row.getDate(), Float.parseFloat(row.getHigh())));
        }
        return result;
    }
}