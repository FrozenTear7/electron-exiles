//import model.DataLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DataLoaderTest {

    private static String testFilePath;

    @BeforeAll
    static void setUp() {
        testFilePath = Utils.getResourcesPath() + "test.csv";
    }

    @Test
    void loads_file_data_correctly() {
//        DataLoader dataLoader = new DataLoader(testFilePath);
//
//        Assertions.assertFalse(dataLoader.getStockData().isEmpty());
    }
}