import controller.FileLoaderController;
import controller.TableViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.DataLoader;
import model.DataRow;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.NodeMatchers;

import java.util.List;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

@ExtendWith(ApplicationExtension.class)
class FileLoaderControllerTest extends CommonControllerTest {

    private FileLoaderController fileLoaderController;
    private BorderPane root;

    @Start
    void onStart(Stage stage) throws Exception {
        root = new BorderPane();

        FXMLLoader fileLoader = new FXMLLoader(getClass().getResource("/fxmls/FileLoader.fxml"));
        root.setLeft(fileLoader.load());
        fileLoaderController = fileLoader.getController();
    }

    @Test
    void should_render_button() {
        verifyThat("#button1", NodeMatchers.isNotNull());
    }

    @Test
    void should_render_list_view() {
        verifyThat("#listView1", NodeMatchers.isNotNull());
    }

    @Test
    void should_change_table_view_label() throws Exception {
        FXMLLoader tableViewLoader = new FXMLLoader(getClass().getResource("/fxmls/TableView.fxml"));
        root.setRight(tableViewLoader.load());
        TableViewController tableViewController = tableViewLoader.getController();

        String filePath = Utils.getResourcesPath() + "test.csv";
        DataLoader dl = new DataLoader(filePath);
        List<DataRow> data = dl.getStockData();

        tableViewController.setDataAndLabel(data, filePath);

        verifyThat("#label1", hasText("test.csv"));
    }
}