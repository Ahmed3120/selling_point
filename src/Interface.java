/**
 *  
 */

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Interface extends Application {
    @Override
    public void start(Stage stage) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gram");
        
        stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
