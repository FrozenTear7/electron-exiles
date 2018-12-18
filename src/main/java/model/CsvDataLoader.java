package model;

import exceptions.LoadException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CsvDataLoader implements IDataLoader {
    public CsvDataLoader() {
    }

    @Override
    public DataRowList getStockData(String csvFile) throws LoadException {
        DataRowList dataRowList = new DataRowList();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line = br.readLine();
            String delimiter = getDelimiter(line);
            List<String> columns = new ArrayList<>(Arrays.asList(line.split(delimiter)));

            if (!columns.contains("Date") || !columns.contains("High")) {
                throw new LoadException("File must contain Date and High columns!");
            }

            while ((line = br.readLine()) != null) {
                List<String> parsedLine = new ArrayList<>(Arrays.asList(line.split(delimiter)));
                dataRowList.addRow(new DataRow(parsedLine.get(columns.indexOf("Date")), parsedLine.get(columns.indexOf("High"))));
            }
        } catch (IOException e) {
            throw new LoadException("Couldn't load file!");
        }

        return dataRowList;
    }

    private String getDelimiter(String line) throws LoadException {
        String delimiter;

        Pattern patternSemicolon = Pattern.compile(".*;.*");
        Pattern patternComma = Pattern.compile(".*,.*");

        if (patternSemicolon.matcher(line).find()) {
            delimiter = ";";
        } else if (patternComma.matcher(line).find()) {
            delimiter = ",";
        } else {
            throw new LoadException("Wrong delimiter. Only ',' and ';' are accepted.");
        }

        return delimiter;
    }
}