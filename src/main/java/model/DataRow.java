package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataRow {
    private String date;
    private float open;
    private float high;
    private float low;
    private float close;
    private int volume;
}
