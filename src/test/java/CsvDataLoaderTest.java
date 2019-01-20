import exceptions.LoadException;
import model.CsvDataLoader;
import model.DataRowList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.PathUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CsvDataLoaderTest {

    private static CsvDataLoader csvDataLoader;
    private static String commaFilePath;
    private static String semicolonFilePath;
    private static String noDateFilePath;
    private static String noHighFilePath;
    private static String wrongDelimitersFilePath;
    private static String nonExistingFilePath;

    @BeforeAll
    static void setUp() {
        csvDataLoader = new CsvDataLoader();

        commaFilePath = PathUtils.getResourcesPath() + "test/test.csv";
        semicolonFilePath = PathUtils.getResourcesPath() + "test/test_semicolon.csv";
        noDateFilePath = PathUtils.getResourcesPath() + "test/test_no_date.csv";
        noHighFilePath = PathUtils.getResourcesPath() + "test/test_no_high.csv";
        wrongDelimitersFilePath = PathUtils.getResourcesPath() + "test/test_wrong_delimiters.csv";
        nonExistingFilePath = PathUtils.getResourcesPath() + "test/abcd.csv";
    }

    @Test
    void returns_rows_list_for_correct_file() throws LoadException {
        DataRowList dataRowList;

        dataRowList = csvDataLoader.getStockData(commaFilePath);
        assertEquals(2, dataRowList.size());

        dataRowList = csvDataLoader.getStockData(semicolonFilePath);
        assertEquals(2, dataRowList.size());
    }

    @Test
    void throws_load_exception_for_file_with_no_date_column() {
        assertThrows(LoadException.class, () -> csvDataLoader.getStockData(noDateFilePath));
    }

    @Test
    void throws_load_exception_for_file_with_no_high_column() {
        assertThrows(LoadException.class, () -> csvDataLoader.getStockData(noHighFilePath));
    }

    @Test
    void throws_load_exception_for_while_with_wrong_delimiter() {
        assertThrows(LoadException.class, () -> csvDataLoader.getStockData(wrongDelimitersFilePath));
    }

    @Test
    void throws_load_exception_for_non_existing_file() {
        assertThrows(LoadException.class, () -> csvDataLoader.getStockData(nonExistingFilePath));
    }
}