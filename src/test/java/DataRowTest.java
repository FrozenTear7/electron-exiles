import exceptions.LoadException;
import model.DataRow;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class DataRowTest {

    private static String correctDate;
    private static String correctStockValue;

    @BeforeAll
    static void setUp() {
        correctDate = "1994-09-07";
        correctStockValue = "22.37";
    }

    @Test
    void throws_load_exception_for_wrong_date_type() {
        final String wrongDate = "1994x09x07";

        assertThrows(LoadException.class, () -> new DataRow(wrongDate, correctStockValue));
    }

    @Test
    void throws_load_exception_for_wrong_stock_value() {
        final String stockValueEqualZero = "0.0";
        final String stockValueBelowZero = "-10.30";

        assertThrows(LoadException.class, () -> new DataRow(correctDate, stockValueEqualZero));
        assertThrows(LoadException.class, () -> new DataRow(correctDate, stockValueBelowZero));
    }
}
