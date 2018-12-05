package model;

import exceptions.LoadException;

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
    public DataRowList getStockData() throws LoadException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DataRowList dataRowList = new DataRowList();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parsedLine = line.split(delimiter);

                if (parsedLine.length == columns)
                    dataRowList.addRow(new DataRow(simpleDateFormat.parse(parsedLine[0]), (Float.parseFloat(parsedLine[2]) + Float.parseFloat(parsedLine[3])) / 2));
                dataRowList.sortListByDate();
            }
        } catch (ParseException e) {
            throw new LoadException("Couldn't parse .csv data!");
        } catch (IOException e) {
            throw new LoadException("Couldn't load file!");
        }

        return dataRowList;
    }
}
