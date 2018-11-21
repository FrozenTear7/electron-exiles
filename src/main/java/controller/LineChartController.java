package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.DataRow;

import java.util.ArrayList;
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
        lineChart1.getData().add(series.get(1)); //High
        lineChart1.getData().add(series.get(2)); //Low
    }


    private List<XYChart.Series<String, Number>> mapToSeries(List<DataRow> data) {
        List<XYChart.Series<String, Number>> series = new ArrayList<>();

        series.add(new XYChart.Series<>());
        series.get(0).setName("open");
        series.add(new XYChart.Series<>());
        series.get(1).setName("high");
        series.add(new XYChart.Series<>());
        series.get(2).setName("low");
        series.add(new XYChart.Series<>());
        series.get(3).setName("close");
        series.add(new XYChart.Series<>());
        series.get(4).setName("volume");

        for (DataRow row : data) {
            series.get(0).getData().add(new XYChart.Data<String, Number>(row.getDate(), Float.parseFloat(row.getOpen())));
            series.get(1).getData().add(new XYChart.Data<String, Number>(row.getDate(), Float.parseFloat(row.getHigh())));
            series.get(2).getData().add(new XYChart.Data<String, Number>(row.getDate(), Float.parseFloat(row.getLow())));
            series.get(3).getData().add(new XYChart.Data<String, Number>(row.getDate(), Float.parseFloat(row.getClose())));
            series.get(4).getData().add(new XYChart.Data<String, Number>(row.getDate(), Float.parseFloat(row.getVolume())));
        }

        return series;
    }
}