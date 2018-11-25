package model;

import java.io.IOException;

abstract class AbstractDataLoader {
    String csvFile;

    abstract DataRowList getStockData() throws IOException;
}