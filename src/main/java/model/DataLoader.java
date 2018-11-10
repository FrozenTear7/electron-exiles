package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    private List<DataRow> dataRowList = new ArrayList<>();
    private String csvFile;
    private BufferedReader br = null;
    private String line = "";
    private String cvsSplitBy = ",";
    private int lineCounter = 0;

    public DataLoader(String csvFile) {
        this.csvFile = csvFile;
    }

    public void readData() {
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                if (lineCounter == 0) {
                    lineCounter++;
                } else {
                    String[] parsedLine = line.split(cvsSplitBy);

                    if (parsedLine.length == 6)
                        dataRowList.add(new DataRow(parsedLine[0], Float.valueOf(parsedLine[1]),
                                Float.valueOf(parsedLine[2]), Float.valueOf(parsedLine[3]), Float.valueOf(parsedLine[4]),
                                Integer.valueOf(parsedLine[5])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            for (DataRow datarow : dataRowList) {
                System.out.println(datarow.getLow());
            }
        }
    }
}
