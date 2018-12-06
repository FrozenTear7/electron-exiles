package model;

import exceptions.LoadException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JsonDataLoader implements IDataLoader {
    public JsonDataLoader() {}

    @Override
    public DataRowList getStockData(String csvFile) throws LoadException {
        DataRowList dataRowList = new DataRowList();

        Gson gson = new Gson();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line = br.readLine();
            String delimiter = getDelimiter(line);
            int columns = line.split(delimiter).length;

            while ((line = br.readLine()) != null) {
                String[] parsedLine = line.split(delimiter);

                if (parsedLine.length == columns) {
                    dataRowList.addRow(new DataRow(parsedLine[0], parsedLine[2]));
                }

                dataRowList.sortListByDate();
            }
        } catch (IOException e) {
            throw new LoadException("Couldn't load file!");
        }

        return dataRowList;
    }
}
