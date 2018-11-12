package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataRow {
    private String date;
    private String open;
    private String high;
    private String low;
    private String close;
    private String volume;
}
