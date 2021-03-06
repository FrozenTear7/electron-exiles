package model;

import exceptions.LoadException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class JsonDataLoader implements IDataLoader {
    public JsonDataLoader() {
    }

    @Override
    public DataRowList getStockData(String csvFile) throws LoadException {
        DataRowList dataRowList = new DataRowList();

        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray;

        try {
            jsonArray = (JSONArray) jsonParser.parse(new FileReader(csvFile));
        } catch (IOException e) {
            throw new LoadException("File not found!");
        } catch (ParseException e) {
            throw new LoadException("Wrong JSON file syntax");
        }

        Iterator<JSONObject> iterator = jsonArray.iterator();

        while (iterator.hasNext()) {
            JSONObject nextObject = iterator.next();

            if (!nextObject.containsKey("Date") || !nextObject.containsKey("High")) {
                throw new LoadException("Rows must contain Date and High keys!");
            }

            dataRowList.addRow(new DataRow(nextObject.get("Date").toString(), (nextObject.get("High").toString())));
        }

        return dataRowList;
    }
}
