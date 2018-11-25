package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.DataRow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javafx.scene.text.Font.font;

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
        xAxis.setTickLabelFont(font("Comic Sans MS"));
        yAxis.setLabel("Value");
        yAxis.setAutoRanging(true);
        yAxis.setTickLabelFont(font("Comic Sans MS"));

        lineChart1.setTitle("stock values");
        lineChart1.setCreateSymbols(false);
    }

    public void setData(List<DataRow> data) {
        List<XYChart.Series<String, Number>> series = mapToSeries(data);
        lineChart1.getData().clear();
        lineChart1.getData().add(series.get(0));
    }


    private List<XYChart.Series<String, Number>> mapToSeries(List<DataRow> data) {
        List<XYChart.Series<String, Number>> series = new ArrayList<>();

        series.add(new XYChart.Series<>());
        series.get(0).setName("value");

        for (DataRow row : data) {
            series.get(0).getData().add(new XYChart.Data<String, Number>(row.getDate().toString(), row.getStockValue()));
        }

        return series;
    }
}