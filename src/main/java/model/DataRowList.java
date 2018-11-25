package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
public class DataRowList {
    private List<DataRow> dataRowList = new ArrayList<>();

    public void addRow(DataRow dataRow) {
        dataRowList.add(dataRow);
    }

    public void sortListByDate() {
        Collections.sort(dataRowList, new Comparator<DataRow>() {
            @Override
            public int compare(DataRow lhs, DataRow rhs) {
                return lhs.getDate().before(rhs.getDate()) ? -1 : lhs.getDate().after(rhs.getDate()) ? 1 : 0;
            }
        });
    }
}
