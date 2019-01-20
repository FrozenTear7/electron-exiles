import exceptions.LoadException;
import model.DataRowList;
import model.JsonDataLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.PathUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class JsonDataLoaderTest {

    private static JsonDataLoader jsonDataLoader;
    private static String correctFilePath;
    private static String noDateFilePath;
    private static String noHighFilePath;
    private static String wrongSyntaxFilePath;
    private static String nonExistingFilePath;

    @BeforeAll
    static void setUp() {
        jsonDataLoader = new JsonDataLoader();

        correctFilePath = PathUtils.getResourcesPath() + "test/test.json";
        noDateFilePath = PathUtils.getResourcesPath() + "test/test_no_date.json";
        noHighFilePath = PathUtils.getResourcesPath() + "test/test_no_high.json";
        wrongSyntaxFilePath = PathUtils.getResourcesPath() + "test/test_wrong_syntax.json";
        nonExistingFilePath = PathUtils.getResourcesPath() + "test/abcd.json";
    }

    @Test
    void returns_rows_list_for_correct_file() throws LoadException {
        DataRowList dataRowList;

        dataRowList = jsonDataLoader.getStockData(correctFilePath);
        assertEquals(2, dataRowList.size());
    }

    @Test
    void throws_load_exception_for_file_with_no_date_keys() {
        assertThrows(LoadException.class, () -> jsonDataLoader.getStockData(noDateFilePath));
    }

    @Test
    void throws_load_exception_for_file_with_no_high_keys() {
        assertThrows(LoadException.class, () -> jsonDataLoader.getStockData(noHighFilePath));
    }

    @Test
    void throws_load_exception_for_while_with_wrong_syntax() {
        assertThrows(LoadException.class, () -> jsonDataLoader.getStockData(wrongSyntaxFilePath));
    }

    @Test
    void throws_load_exception_for_non_existing_file() {
        assertThrows(LoadException.class, () -> jsonDataLoader.getStockData(nonExistingFilePath));
    }
}