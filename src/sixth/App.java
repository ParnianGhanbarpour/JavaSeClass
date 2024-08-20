package sixth;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("product Manager");
        Scene scene =
                new Scene(FXMLLoader
                        .load(getClass().getResource("view/main.fxml")));
        primaryStage.setScene(scene);


        primaryStage.show();
    }
}
