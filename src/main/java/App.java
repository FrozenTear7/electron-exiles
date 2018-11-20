import controller.FileLoaderController;
import controller.LineChartController;
import controller.TableViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.DataLoader;

import java.io.IOException;

public class App extends Application {
    private String csvFile = Utils.getResourcesPath() + "aapl_us_d.csv";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Electron Exiles");
        primaryStage.getIcons().add(new Image("/Oguras.jpg"));

        try {
            BorderPane root = new BorderPane();

            FXMLLoader fileLoader = new FXMLLoader(getClass().getResource("/FileLoader.fxml"));
            root.setTop(fileLoader.load());
            FileLoaderController fileLoaderController = fileLoader.getController();

            FXMLLoader tableViewLoader = new FXMLLoader(getClass().getResource("/TableView.fxml"));
            root.setCenter(tableViewLoader.load());
            TableViewController tableViewController = tableViewLoader.getController();

            FXMLLoader lineChartLoader = new FXMLLoader(getClass().getResource("/LineChart.fxml"));
            root.setRight(lineChartLoader.load());
            LineChartController lineChartController = lineChartLoader.getController();

            DataLoader dataLoader = new DataLoader(csvFile);
            tableViewController.setData(dataLoader.getStockData());

            Scene scene = new Scene(root, 1920, 1080);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Lulus-pylus!!!");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
