/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokobangunan;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Kiara Yasmin
 */
public class Product extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ProductView.fxml"));

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Product");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
