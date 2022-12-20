import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// ;.

public class App {
    public static void main(String[] args) throws Exception {
    
        DB_Connection db = new DB_Connection();
        // db.selectInfo("root", "root");
        // System.out.println(db.info.get(0));
        // System.out.println(db.Info("root", "root", "Name"));
        
        // System.out.println(db.Info("1", "LastName"));

        // db.addAccount("ahmed", "ali", "miner21", "ahmed@ali.com", "123456");
        ObservableList<UsersTable> n = FXCollections.observableArrayList();
        n.addAll(db.getUserInfo());
        
        System.out.println(n.get(1).getTlastName());
    }
}
