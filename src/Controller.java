import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller {

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPasseord;
    @FXML private Label lbl3;// label using for erorrs 
    @FXML private Button btn;
    @FXML private AnchorPane loginArea;
    JFXTextField jfxTextField;

    // public String Id;
    // String Uname = txtPasseord;

    Parent rootParent;
    Stage stage;
    Scene scene;

    @FXML void Login(ActionEvent event) throws IOException {// LogIn method 
        DB_Connection DBConnection = new DB_Connection();
        DashBoardController dashboard;

        try{
         
         /* the selectInfo method in class DB_Connection send true or false  **/
        if (DBConnection.selectInfo(txtUsername.getText(), txtPasseord.getText())) {
            //the first four lines using to send the id for the next UI to identitfy the user
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DashBoardScene.fxml"));//getting dashboard path using fxmlloader 
            rootParent = loader.load();//load the fxml UI
            dashboard = loader.getController(); // initial the dashboardController using the fxmlLoader methods
            dashboard.setId(DBConnection.selectInfoID(txtUsername.getText(), txtPasseord.getText()));

            //the second four lines using to load the dashboard UI 
            // first get the info of this stage then cast it from event to Node then cast it to stage to 
            this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); 
            scene = new Scene(rootParent);//then loading the new scene
            stage.setScene(scene);
            stage.show();

            // switchScenes.switchToDashboard(event);

        } else {
            lbl3.setText("UserName or password are incorrect");
        }
    }catch(SQLTimeoutException e){//Printing time out erorr msg

        lbl3.setText("Connection Timeout");
        e.printStackTrace();

    }catch(SQLException ex){//For all erorrs

        lbl3.setText("Can't Reach the Server");
        ex.printStackTrace();

    }
        // dashboard.Id = Id;

    }
    @FXML
    void initialize() {
        jfxTextField = new JFXTextField();
        jfxTextField.setPromptText("UserName");
        jfxTextField.setLabelFloat(true);
        // loginArea.getChildren().addAll(jfxTextField);
    }


}
