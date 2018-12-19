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
        SimpleDateFormat iso8601DateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat specialDateFormat = new SimpleDateFormat("MM dd, yyyy");

        String regexIso8601DateFormat = "\\d{4}-\\d{2}-\\d{2}";
        String regexSpecialDateFormat = "\\d{2} \\d{2}, \\d{4}";

        Pattern patternIso8601DateFormat = Pattern.compile(regexIso8601DateFormat);
        Pattern patternSpecialDateFormat = Pattern.compile(regexSpecialDateFormat);

        try {
            if (patternIso8601DateFormat.matcher(date).find()) {
                this.date = iso8601DateFormat.parse(date);
            } else if (patternSpecialDateFormat.matcher(date).find()) {
                this.date = specialDateFormat.parse(date);
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
    