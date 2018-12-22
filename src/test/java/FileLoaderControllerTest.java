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
        verifyThat("#openFileButton", NodeMatchers.isNotNull());
    }

    @Test
    void should_render_list_view() {
        verifyThat("#historyView", NodeMatchers.isNotNull());
    }

    @Test
    void should_render_error_info() {
        verifyThat("#errorInfo", NodeMatchers.isNotNull());
    }
}