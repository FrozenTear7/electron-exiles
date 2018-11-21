import controller.TableViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

@ExtendWith(ApplicationExtension.class)
class TableViewControllerTest extends CommonControllerTest {

    TableViewController tableViewController;

    @Start
    void onStart(Stage stage) throws Exception {
        BorderPane root = new BorderPane();

        FXMLLoader tableViewLoader = new FXMLLoader(getClass().getResource("/fxmls/TableView.fxml"));
        root.setCenter(tableViewLoader.load());
        tableViewController = tableViewLoader.getController();
    }

    @Test
    void should_render_table() {
        verifyThat("#dataRowTableView", NodeMatchers.isNotNull());
    }

    @Test
    void should_have_label() {
        verifyThat("#label1", NodeMatchers.isNotNull());
    }

    @Test
    void should_have_6_specific_columns() {
        verifyThat("#dateCol", NodeMatchers.isNotNull());
        verifyThat("#openCol", NodeMatchers.isNotNull());
        verifyThat("#highCol", NodeMatchers.isNotNull());
        verifyThat("#lowCol", NodeMatchers.isNotNull());
        verifyThat("#closeCol", NodeMatchers.isNotNull());
        verifyThat("#volumeCol", NodeMatchers.isNotNull());
    }
}