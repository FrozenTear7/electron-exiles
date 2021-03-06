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
        dataRowList.sort((lhs, rhs) -> lhs.getDate().before(rhs.getDate()) ? -1 : lhs.getDate().after(rhs.getDate()) ? 1 : 0);
    }

    public int size() {
        return dataRowList.size();
    }
}
