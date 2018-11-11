import controller.MainPanelController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.DataLoader;
import java.io.IOException;
import javafx.scene.image.Image;

public class App extends Application {
    private String csvFile = "D:/Download/aapl_us_d.csv";
//    private String csvFile = "/resources/aapl_us_d.csv";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Electron Exiles");
        primaryStage.getIcons().add(new Image("/Oguras.jpg"));

        try {
            BorderPane root = new BorderPane();
            FXMLLoader listLoader = new FXMLLoader(getClass().getResource("/MainPanel.fxml"));
            root.setCenter(listLoader.load());
            MainPanelController mainPanelController = listLoader.getController();

            DataLoader dataLoader = new DataLoader(csvFile);
            mainPanelController.initModel(dataLoader.readData());

            Scene scene = new Scene(root, 800, 600);
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
