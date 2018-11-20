import controller.FileLoaderController;
import controller.LineChartController;
import controller.TableViewController;
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

            FXMLLoader tableViewLoader = new FXMLLoader(getClass().getResource("/fxmls/TableView.fxml"));
            root.setCenter(tableViewLoader.load());
            TableViewController tableViewController = tableViewLoader.getController();

            FXMLLoader fileLoader = new FXMLLoader(getClass().getResource("/fxmls/FileLoader.fxml"));
            root.setTop(fileLoader.load());
            FileLoaderController fileLoaderController = fileLoader.getController();
            fileLoaderController.setTableViewController(tableViewController);

            FXMLLoader lineChartLoader = new FXMLLoader(getClass().getResource("/fxmls/LineChart.fxml"));
            root.setRight(lineChartLoader.load());
            LineChartController lineChartController = lineChartLoader.getController();

            Scene scene = new Scene(root, 1200, 800);
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
