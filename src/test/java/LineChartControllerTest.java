import controller.LineChartController;
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
class LineChartControllerTest extends CommonControllerTest {

    private LineChartController lineChartController;
    private BorderPane root;

    @Start
    void onStart(Stage stage) throws Exception {
        root = new BorderPane();

        FXMLLoader fileLoader = new FXMLLoader(getClass().getResource("/fxmls/LineChart.fxml"));
        root.setBottom(fileLoader.load());
        lineChartController = fileLoader.getController();
    }

    @Test
    void should_render_x_axis() {
        verifyThat("#xAxis", NodeMatchers.isNotNull());
    }

    @Test
    void should_render_y_axis() {
        verifyThat("#yAxis", NodeMatchers.isNotNull());
    }

    @Test
    void should_render_line_chart() {
        verifyThat("#lineChart", NodeMatchers.isNotNull());
    }

    @Test
    void should_render_button_zoom_out() {
        verifyThat("#buttonZoomIn", NodeMatchers.isNotNull());
    }

    @Test
    void should_render_button_zoom_int() {
        verifyThat("#buttonZoomOut", NodeMatchers.isNotNull());
    }

    @Test
    void should_render_button_shift_left() {
        verifyThat("#buttonShiftRight", NodeMatchers.isNotNull());
    }

    @Test
    void should_render_shift_right() {
        verifyThat("#buttonShiftLeft", NodeMatchers.isNotNull());
    }
}