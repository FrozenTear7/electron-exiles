package model;

import java.util.List;

abstract class AbstractDataLoader {
    String csvFile;
    int lineCounter = 0;

    abstract List<DataRow> getStockData();
}