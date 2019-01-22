package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.util.Pair;
import model.DataRow;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    private LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);

    @FXML
    private Button buttonZoomIn;

    @FXML
    private Button buttonZoomOut;

    @FXML
    private Button buttonShiftRight;

    @FXML
    private Button buttonShiftLeft;

    private List<DataRow> data;
    private int zoomLevel = 0;
    private double position = 0.5;

    private int MAX_ZOOM_LVL = 10;
    private int MIN_ZOOM_LVL = 0;
    private final int maximumNumberOfPoints = 1200;

    @FXML
    private void initialize() {
        xAxis.setLabel("Date");
        xAxis.setTickLabelFont(font("Comic Sans MS"));
        yAxis.setTickLabelGap(100);

        yAxis.setLabel("Value");
        yAxis.setAutoRanging(true);
        yAxis.setTickLabelFont(font("Comic Sans MS"));

        lineChart.setTitle("stock values");
        lineChart.setCreateSymbols(false);
        lineChart.setAnimated(false);

        handleButtonsClick();
    }

    private void handleButtonsClick() {
        buttonZoomIn.setOnAction(event -> {
            zoomLevel++;
            if (zoomLevel > MAX_ZOOM_LVL) {
                zoomLevel = MAX_ZOOM_LVL;
                return;
            }
            adjustData();
        });

        buttonZoomOut.setOnAction(event -> {
            zoomLevel--;
            if (zoomLevel < MIN_ZOOM_LVL) {
                zoomLevel = MIN_ZOOM_LVL;
                return;
            }
            position = (int) (position * Math.pow(2, zoomLevel + 1));
            if (position == 0) position = 1;
            position *= Math.pow(0.5, zoomLevel + 1);
            adjustData();
        });

        buttonShiftRight.setOnAction(event -> {
            if (position + Math.pow(0.5, zoomLevel + 1) >= 1) return;
            position += Math.pow(0.5, zoomLevel + 1);
            adjustData();
        });

        buttonShiftLeft.setOnAction(event -> {
            if (position <= Math.pow(0.5, zoomLevel + 1)) return;
            position -= Math.pow(0.5, zoomLevel + 1);
            adjustData();
        });
    }

    public void setData(List<DataRow> data) {
        this.data = data;
        MAX_ZOOM_LVL = (int) Math.floor(Math.log(data.size()) / Math.log(2));
        zoomLevel = 0;
        position = 0.5;
        List<XYChart.Series<String, Number>> series = mapToSeries(data);
        lineChart.getData().clear();
        lineChart.getData().add(series.get(0));
    }

    private void adjustData() {
        List<XYChart.Series<String, Number>> series = mapToSeries(getZoomedData());
        lineChart.getData().clear();
        lineChart.getData().add(series.get(0));
    }

    public List<DataRow> getZoomedData() {
        return new ArrayList<>(data.subList(calculateNewRanges().getKey(), calculateNewRanges().getValue()));
    }

    private Pair<Integer, Integer> calculateNewRanges() {
        int len = data.size();
        int lenZoomed = (int) (len / Math.pow(2, zoomLevel));
        int pos = (int) (this.position * len);
        int left = pos - lenZoomed / 2;
        if (left < 0) left = 0;
        int right = pos + lenZoomed / 2;
        if (right > len) right = len;
        return new Pair<>(left, right);
    }

    private List<XYChart.Series<String, Number>> mapToSeries(List<DataRow> data) {
        List<XYChart.Series<String, Number>> series = new ArrayList<>();

        series.add(new XYChart.Series<>());
        series.get(0).setName("value");

        int amountOfData = data.size();
        int omitRate = amountOfData / maximumNumberOfPoints;
        if (omitRate < 1) omitRate = 1;

        for (int i = 0; i < amountOfData; i++) {
            if(i % omitRate == 0) {
                series.get(0).getData().add(new XYChart.Data<String, Number>(getDateLabel(data.get(i).getDate()), data.get(i).getStockValue()));
            }
        }

        return series;
    }

    private String getDateLabel(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }
}