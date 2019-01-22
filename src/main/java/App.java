import controller.FileLoaderController;
import controller.LineChartController;
import controller.StrategyController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Electron Exiles");
        primaryStage.getIcons().add(new Image("/Oguras.jpg"));

        try {
            BorderPane root = new BorderPane();

            FXMLLoader strategyListLoader = new FXMLLoader(getClass().getResource("/fxmls/StrategyList.fxml"));
            root.setRight(strategyListLoader.load());
            StrategyController strategyListController = strategyListLoader.getController();

            FXMLLoader lineChartLoader = new FXMLLoader(getClass().getResource("/fxmls/LineChart.fxml"));
            root.setBottom(lineChartLoader.load());
            LineChartController lineChartController = lineChartLoader.getController();

            strategyListController.setLineChartController(lineChartController);

            FXMLLoader fileLoader = new FXMLLoader(getClass().getResource("/fxmls/FileLoader.fxml"));
            root.setLeft(fileLoader.load());
            FileLoaderController fileLoaderController = fileLoader.getController();
            fileLoaderController.setLineChartController(lineChartController);

            Scene scene = new Scene(root, 1400, 900);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
