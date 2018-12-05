import exceptions.LoadException;
import model.DataRowList;
import model.DogeStockDataLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class DogeStockDataLoaderTest {

    private static String testFilePath;
    private static String wrongTestFilePath;

    @BeforeAll
    static void setUp() {
        testFilePath = Utils.getResourcesPath() + "doge.csv";
        wrongTestFilePath = Utils.getResourcesPath() + "dogeasdadadadaa.jpg";
    }

    @Test
    void returns_rows_list_for_correct_file() throws LoadException {
        DogeStockDataLoader dl = new DogeStockDataLoader(testFilePath);
        DataRowList dataRowList = dl.getStockData();
        assertTrue(dataRowList.size() >= 1);
    }

    @Test
    void throws_error_for_wrong_file() {
        assertThrows(LoadException.class, () -> {
            DogeStockDataLoader dl = new DogeStockDataLoader(wrongTestFilePath);
            DataRowList dataRowList = dl.getStockData();
        });
    }
}