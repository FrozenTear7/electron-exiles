package model;

import exceptions.LoadException;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

@Data
public class DataRow {
    private Date date;
    private Float stockValue;

    public DataRow(String date, String stockValue) throws LoadException {
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MM dd, yyyy");

        try {
            String regexSimpleDateFormat1 = "\\d{4}-\\d{2}-\\d{2}";
            String regexSimpleDateFormat2 = "\\d{2} \\d{2}, \\d{4}";

            Pattern patternSimpleDateFormat1 = Pattern.compile(regexSimpleDateFormat1);
            Pattern patternSimpleDateFormat2 = Pattern.compile(regexSimpleDateFormat2);

            if (patternSimpleDateFormat1.matcher(date).find()) {
                this.date = simpleDateFormat1.parse(date);
            } else if (patternSimpleDateFormat2.matcher(date).find()) {
                this.date = simpleDateFormat2.parse(date);
            } else {
                throw new LoadException("Wrong date format");
            }

            this.stockValue = Float.parseFloat(stockValue);

            if (this.stockValue <= 0) {
                throw new LoadException("Float value must be above 0");
            }
        } catch (NumberFormatException | ParseException e) {
            throw new LoadException("Couldn't parse data");
        }
    }
}
    