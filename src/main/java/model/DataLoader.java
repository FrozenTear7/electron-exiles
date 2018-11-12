package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    private String csvFile;
    private int lineCounter = 0;

    public DataLoader(String csvFile) {
        this.csvFile = csvFile;
    }

    public List<DataRow> getStockData() {
        List<DataRow> dataRowList = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                if (lineCounter == 0) {
                    lineCounter++;
                } else {
                    String[] parsedLine = line.split(",");

                    if (parsedLine.length == 6)
                        dataRowList.add(new DataRow(parsedLine[0], parsedLine[1], parsedLine[2], parsedLine[3],
                                parsedLine[4], parsedLine[5]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataRowList;
    }
}
