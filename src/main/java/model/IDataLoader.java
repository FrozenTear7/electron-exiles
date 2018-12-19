package model;

import exceptions.LoadException;

public interface IDataLoader {
    DataRowList getStockData(String csvFile) throws LoadException;
}
