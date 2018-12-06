package model;

import exceptions.LoadException;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class DataRow {
    private Date date;
    private Float stockValue;

    public DataRow(String date, String stockValue) throws LoadException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            this.date = simpleDateFormat.parse(date);
            this.stockValue = Float.parseFloat(stockValue);

            if (this.stockValue <= 0) {
                throw new LoadException("Float value must be above 0");
            }
        } catch (NumberFormatException | ParseException e) {
            throw new LoadException("Couldn't parse data");
        }
    }
}
    