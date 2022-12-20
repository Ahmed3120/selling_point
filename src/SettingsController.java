import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.sql.SQLException;
import java.sql.SQLTransientConnectionException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;



public class SettingsController {

    @FXML private TextField accountEmail;
    @FXML private TextField accountLastName;
    @FXML private TextField accountName;
    @FXML private TextField accountPassword;
    @FXML private TextField accountRePassword;
    @FXML private TextField accountUserName;
    @FXML private Button addAccountBtn;
    @FXML private ProgressIndicator addAccountProgress;
    @FXML private Button addMealBtn;
    @FXML private TextField mealImage;
    @FXML private TextField mealName;
    @FXML private TextField mealPrice;
    @FXML private Label accountErorrs;
    Scene scene;

    DB_Connection db = new DB_Connection();
    DashBoardController dashBoardController;

    @FXML private void signUp(ActionEvent actionEvent) throws InterruptedException {
        addAccountProgress.setVisible(true);
        String name = accountName.getText();
        String lastName = accountLastName.getText();
        String email = accountEmail.getText();
        String userName = accountUserName.getText();
        String password = "";
        if(accountPassword.getText().equals(accountRePassword.getText())){
            password = accountPassword.getText();
        }else{
            accountErorrs.setText("Password not mached");
        }
        if (name.isBlank())
            accountErorrs.setText("Pleas enter name");

        else if(lastName.isBlank())
            accountErorrs.setText("Pleas enter lastname");

        else if(email.isBlank() && isEmail(email))
            accountErorrs.setText("Pleas enter email in well format");
        
        else if(password.isBlank())
            accountErorrs.setText("Pleas enter password");
        else{
            try {
                db.addAccount(name, lastName, userName, email, password);

                
            } catch (SQLTransientConnectionException e) {
                accountErorrs.setText("theres a problem in server");
            }catch (SQLException e) {
                accountErorrs.setText("the username is used");
            }
                
            accountErorrs.setText("Done");
            addAccountProgress.setVisible(false);

            accountEmail.clear();
            accountLastName.clear();
            accountName.clear();
            accountPassword.clear();
            accountRePassword.clear();
            accountUserName.clear();
            accountUserName.onInputMethodTextChangedProperty();
           
            
        }

        
    }
    @FXML
    private void inpt(KeyEvent event) throws Exception{
        if(accountUserName.getText().isBlank())
            accountErorrs.setVisible(false);
        if(db.checkUserName(accountUserName.getText())){
            accountErorrs.setText("UserName is Taken");
            accountErorrs.setTextFill(javafx.scene.paint.Color.web("#ff0000"));// change text color to red
            // accountUserName.setStyle("-fx-border-color: #ff0000");
        }else{
            accountErorrs.setVisible(true);
            accountErorrs.setText("UserName is Avaliable");
            accountErorrs.setTextFill(javafx.scene.paint.Color.web("#00ff00"));// change text color to green
        }
    }

    @FXML 
    private void checkPassword(KeyEvent keyEvent){

        if(accountPassword.getText().equals(accountRePassword.getText())){
            accountRePassword.setStyle("-fx-border-color: #81b0f8");
        }else{
            accountRePassword.setStyle("-fx-border-color: #ff0000");
        }

    }


    @FXML private void addMealBtn(ActionEvent actionEvent){
        //TODO: make a add meal method 
    }

    @FXML 
    private void openUsersTable(ActionEvent actionEvent) throws Exception{
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DashBoardScene.fxml"));
        loader.load();
        dashBoardController = loader.getController();
        dashBoardController.changeShowingScene("UsersTable.fxml");

    }

    private boolean isEmail(String email){
        return Pattern.compile("\\w+@\\w+\\.com").matcher(email).matches();
    }

}
