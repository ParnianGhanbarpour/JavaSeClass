package sixth;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("product");
        Scene scene =
                new Scene(FXMLLoader
                        .load(Objects.requireNonNull(getClass().getResource("view/product.fxml"))));
        primaryStage.setScene(scene);


        primaryStage.show();
    }
}
