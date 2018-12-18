/*
import model.DataRow;
import model.DataRowList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DataRowListTest {

    private static DataRow firstDataRow;
    private static DataRow secondDataRow;
    private static DataRow thirdDataRow;

    @BeforeAll
    static void setUp() throws ParseException {
        final Date firstDate = new SimpleDateFormat("yyyy-MM-dd").parse("1984-09-07");
        final Date secondDate = new SimpleDateFormat("yyyy-MM-dd").parse("1994-09-07");
        final Date thirdDate = new SimpleDateFormat("yyyy-MM-dd").parse("2004-09-07");
        final Float stockValue = (float) 0.0;

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

        dl.sortListByDate();

        List<DataRow> sortedRows = dl.getDataRowList();

        assertEquals(firstDataRow, sortedRows.get(0));
        assertEquals(secondDataRow, sortedRows.get(1));
        assertEquals(thirdDataRow, sortedRows.get(2));
    }
}*/
