package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AppleStockDataLoader extends AbstractDataLoader {
    private String delimiter = ",";
    private int columns = 6;

    public AppleStockDataLoader(String csvFile) {
        this.csvFile = csvFile;
    }

    @Override
    public DataRowList getStockData() throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dataRowList;
    }
}
