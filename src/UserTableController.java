import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXAlert;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserTableController {

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private TableColumn<UsersTable, String> TEmail;
    @FXML private TableColumn<UsersTable, String> TLastName;
    @FXML private TableColumn<UsersTable, String> TName;
    @FXML private TableColumn<UsersTable, String> TUserId;
    @FXML private TableColumn<UsersTable, String> TUserName;
    @FXML private TableView<UsersTable> TUserTable;
    
    DashBoardController dashBoardController;
    DB_Connection dbConnection = new DB_Connection();
    ObservableList<UsersTable> obList = FXCollections.observableArrayList();


    @FXML void addNewUser(ActionEvent event) {

    }

   

    @FXML
    void deleteUser(ActionEvent event) {

    }

    @FXML
    void editUser(ActionEvent event) {
        JFXAlert<String> jAlert = new JFXAlert<String>();
        jAlert.setContentText("ajkkkkkkkkkkkkkkkkkkkkkkkkkkkj");
        jAlert.showAndWait();
    }

    private void data() 
    {

        try {
            obList.addAll(dbConnection.getUserInfo());
        } catch (SQLException | IOException e) {
            
            e.printStackTrace();
        }
        // FIXME: cant take the data from select in tableView comp said problem here
        //
        this.TName.cellValueFactoryProperty().set(new PropertyValueFactory<>("Tname"));//
        this.TLastName.setCellValueFactory(new PropertyValueFactory<>("TlastName"));
        this.TEmail.setCellValueFactory(new PropertyValueFactory<>("Temail"));
        this.TUserId.setCellValueFactory(new PropertyValueFactory<>("Tid"));
        this.TUserName.setCellValueFactory(new PropertyValueFactory<>("TUname"));

        this.TUserTable.setItems(obList);
    }

    @FXML
    void initialize() {

        data();

    }

    @FXML private void DelUser(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("TableEditDialog.fxml"));
        DialogPane dialogPane = fxmlLoader.load();

        TableEditDialogController tedc = fxmlLoader.getController();
        UsersTable uTable = TUserTable.getSelectionModel().getSelectedItem();
        Dialog<ButtonType> dialog = new Dialog<>();
        
        dialog.setDialogPane(dialogPane);
        dialog.setTitle("");
        tedc.setData(uTable, 2);// number 2 for delete to set it in the dialog
        dialog.showAndWait();
    }

}





























