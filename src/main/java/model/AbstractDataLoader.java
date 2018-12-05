package model;

import exceptions.LoadException;

abstract class AbstractDataLoader {
    String csvFile;

    abstract DataRowList getStockData() throws LoadException;
}