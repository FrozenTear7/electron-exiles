package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DogeStockDataLoader extends AbstractDataLoader {
    private String delimiter = ";";
    private int columns = 7;

    public DogeStockDataLoader(String csvFile) {
        this.csvFile = csvFile;
    }

    @Override
    public DataRowList getStockData() {
        // date format not working
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");
        DataRowList dataRowList = new DataRowList();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            int lineCounter = 0;

            while ((line = br.readLine()) != null) {
                if (lineCounter == 0) {
                    lineCounter++;
                } else {
                    String[] parsedLine = line.split(delimiter);

                    if (parsedLine.length == columns)
                        dataRowList.addRow(new DataRow(simpleDateFormat.parse(parsedLine[0]), Float.parseFloat(parsedLine[2])));

                    dataRowList.sortListByDate();
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return dataRowList;
    }
}
