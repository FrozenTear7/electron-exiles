package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AppleStockDataLoader extends AbstractDataLoader {
    private String delimiter = ",";
    private int columns = 6;

    public AppleStockDataLoader(String csvFile) {
        this.csvFile = csvFile;
    }

    @Override
    public List<DataRow> getStockData() {
        List<DataRow> dataRowList = new ArrayList<>();
        String line;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                if (lineCounter == 0) {
                    lineCounter++;
                } else {
                    String[] parsedLine = line.split(delimiter);

                    if (parsedLine.length == columns)
                        dataRowList.add(new DataRow(simpleDateFormat.parse(parsedLine[0]), Float.parseFloat(parsedLine[1])));
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return dataRowList;
    }
}
