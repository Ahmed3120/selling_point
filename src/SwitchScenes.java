import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * SwitchScenes
 */
public class SwitchScenes {
    private Parent root;
    private Stage stage;
    private Scene scene2;

    void switchToDashboard(ActionEvent event) throws IOException{
        this.root = FXMLLoader.load(getClass().getResource("DashBoardScene.fxml"));
        
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene2 = new Scene(root);
        stage.setScene(scene2);
        stage.show();
    }

    void SwitchToLoginScene(MouseEvent event) throws IOException{
        
        root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene2 = new Scene(root);
        stage.setScene(scene2);
        stage.show();
    }
    
    void SwitchToUsersTable(ActionEvent event) throws IOException{
        
        root = FXMLLoader.load(getClass().getResource("UsersTable.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene2 = new Scene(root);
        stage.setScene(scene2);
        stage.show();
    }

    void SwitchToSettings(ActionEvent event) throws IOException{
        
        root = FXMLLoader.load(getClass().getResource("UsersTable.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene2 = new Scene(root);
        stage.setScene(scene2);
        stage.show();
    }
    
   
    void addVoice(String link) throws MediaException, IOException{
        Media media = new Media(link);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        
        mediaPlayer.play();
    }
}