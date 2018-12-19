import exceptions.LoadException;
import model.DataRow;
import model.DataRowList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DataRowListTest {

    private static DataRow firstDataRow;
    private static DataRow secondDataRow;
    private static DataRow thirdDataRow;

    @BeforeAll
    static void setUp() throws LoadException {
        final String firstDate = "1974-09-07";
        final String secondDate = "1984-09-07";
        final String thirdDate = "1994-09-07";
        final String stockValue = "0.1";

        firstDataRow = new DataRow(firstDate, stockValue);
        secondDataRow = new DataRow(secondDate, stockValue);
        thirdDataRow = new DataRow(thirdDate, stockValue);
    }

    @Test
    void sorts_its_rows_properly() {
        // place in wrong order
        DataRowList dl = new DataRowList();
        dl.addRow(thirdDataRow);
        dl.addRow(firstDataRow);
        dl.addRow(secondDataRow);

        List<DataRow> sortedRows = dl.getDataRowList();

        assertEquals(firstDataRow, sortedRows.get(0));
        assertEquals(secondDataRow, sortedRows.get(1));
        assertEquals(thirdDataRow, sortedRows.get(2));
    }
}
