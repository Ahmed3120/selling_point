import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TableEditDialogController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    Label headerLbl;
    
    @FXML
    TextField emailTF;

    @FXML
    TextField fNameTF;

    @FXML
    TextField lNameTF;

    @FXML
    TextField passwordTF;

    @FXML
    DialogPane tableEditDia;

    @FXML
    TextField uNameTF;
    int choseAnum;
    String id;

    DB_Connection dbConnection = new DB_Connection();

    @FXML
    void initialize() {
        delUser();
        // switch (choseAnum) {
        //     case 1: ;break;
        //     case 2:delUser(); ;break;
        //     case 3: ;break;

        //     default:
        //         break;
        // }

    }
    public void setHeaderLbl(String headerLbl) {
    }

    public void setTableEditDia(DialogPane tableEditDia) {
        this.tableEditDia = tableEditDia;
    }

    public void setData(UsersTable uTable, int num){
        this.choseAnum = num;
        this.uNameTF.setText(uTable.getTname());
        this.lNameTF.setText(uTable.getTlastName());
        this.emailTF.setText(uTable.getTemail());
        this.uNameTF.setText(uTable.getTUname());
        id = uTable.getTid();
        if (num == 1) {
            passwordTF.setDisable(false);
        }

    }

    private void delUser(){
        headerLbl.setText("هل حفا تريد حذف البيانات");
        Button okBtn = (Button)tableEditDia.lookupButton(ButtonType.YES);
        okBtn.addEventFilter(ActionEvent.ACTION, event -> {
            try {
                dbConnection.deleteUser(Integer.parseInt(id));
            
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

}
