package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.DataRow;

import java.util.List;

public class LineChartController {

    @FXML
    private CategoryAxis xAxis = new CategoryAxis();

    @FXML
    private NumberAxis yAxis = new NumberAxis();

    @FXML
    private LineChart<String, Number> lineChart1 = new LineChart<>(xAxis, yAxis);

    @FXML
    private void initialize() {
        xAxis.setLabel("Date");
        yAxis.setLabel("Value");

        lineChart1.setTitle("Data chart uwu");
        lineChart1.setCreateSymbols(false);
    }

    public void setData(List<DataRow> data) {
        XYChart.Series<String, Number> series = mapToSeries(data);
        lineChart1.getData().clear();
        lineChart1.getData().add(series);
    }


    private XYChart.Series<String, Number> mapToSeries(List<DataRow> data) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("uuga buga");

        for (DataRow row : data) {
            series.getData().add(new XYChart.Data<String, Number>(row.getDate(), Float.parseFloat(row.getHigh())));
        }

        return series;
    }
}