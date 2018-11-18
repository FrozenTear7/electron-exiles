import controller.TableViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

@ExtendWith(ApplicationExtension.class)
class TableViewControllerTest {

    @Start
    void onStart(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        stage.setScene(new Scene(root, 1920, 1080));
        stage.show();

        FXMLLoader tableViewLoader = new FXMLLoader(getClass().getResource("/TableView.fxml"));
        root.setCenter(tableViewLoader.load());
        TableViewController tableViewController = tableViewLoader.getController();
    }

    @Test
    void should_render_table() throws Exception {
        // expect:
        verifyThat("#dataRowTableView", NodeMatchers.isNotNull());
    }
}