import controller.FileLoaderController;
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
class FileLoaderControllerTest extends CommonControllerTest {

    private FileLoaderController fileLoaderController;

    @Start
    void onStart(Stage stage) throws Exception {
        BorderPane root = new BorderPane();

        FXMLLoader fileLoader = new FXMLLoader(getClass().getResource("/fxmls/FileLoader.fxml"));
        root.setTop(fileLoader.load());
        fileLoaderController = fileLoader.getController();
    }

    @Test
    void should_render_button() {
        // expect:
        verifyThat("#button1", NodeMatchers.isNotNull());
    }

    @Test
    void should_render_list_view() {
        // expect:
        verifyThat("#listView1", NodeMatchers.isNotNull());
    }
}