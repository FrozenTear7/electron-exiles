import model.DataLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DataLoaderTest {

    @Test
    void loads_file_data_correctly() {
        String testFilePath = Utils.getResourcesPath() + "test.csv";
        DataLoader dataLoader = new DataLoader(testFilePath);

        Assertions.assertFalse(dataLoader.getStockData().isEmpty());
    }
}