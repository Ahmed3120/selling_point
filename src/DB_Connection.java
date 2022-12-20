/**
 * DB_Connection
 * Class for the server connection
 * the connection on localhost port 5002 and 3306 and DB name "GramDB"
 * the DB have one table named as "Menu"
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class DB_Connection {

    private Connection connection = null;
    private Statement statement = null;
    private String Username;
    private String Password;

    DB_Connection(){
        
    }

    private Connection getconConnection() throws SQLException{// this method using to connect to the server
        try {
                //using the jdbc driver to connect
                connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/GramDB","root","");

            }catch(SQLException e){ //get all sql exceptions

                System.out.println("Connection Field");
                e.printStackTrace();

            }
        return connection;
    }

    public boolean checkUserName(String userName){// check the user name in database if it avaliable or used 
        String user = "";
        try {
            connection = getconConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select UserName from `UserInfo` where UserName = '"+userName+"'");
            if(resultSet.next())
                user = resultSet.getString("UserName");

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return(user.equals(userName));
    }
    // this method using to LogIn it's take userName and Password as String
    //and Return true if the it matched and false if there's no matching
    public boolean selectInfo(String user, String txtpassword)throws SQLException, IOException{

       
        connection = getconConnection();
        statement = connection.createStatement();
            
            String Query = "SELECT * FROM `UserInfo` WHERE UserName =" +
            "'"+user+"'"+ "AND Password = "+ "'"+txtpassword+"'"   ;

            ResultSet rs1 = statement.executeQuery(Query);
            while (rs1.next()) {        
                this.Username = rs1.getString("UserName");
                this.Password = rs1.getString("Password");
            }

            return(this.Username.equalsIgnoreCase(user) && this.Password.equals(txtpassword));
    }
    //getting the data using the id
    public String Info(String ID, String Order)throws SQLException{
        String info = "";
       
        connection = getconConnection();
        statement = connection.createStatement();
            //Quere to select any colume you want
            String Query = "SELECT" + "`"+Order+"`"+ "FROM `UserInfo` WHERE NO =" +
            "'"+ID+"'";

            ResultSet rs1 = statement.executeQuery(Query);
            while (rs1.next()) {        
               info = rs1.getString(Order);
            }
           
            return info;

    }
    
    public String selectInfoID(String user, String txtpassword)throws SQLException, IOException{

        String ID = "";
        connection = getconConnection();
        statement = connection.createStatement();
            
            String Query = "SELECT * FROM `UserInfo` WHERE UserName =" +
            "'"+user+"'"+ "AND Password = "+ "'"+txtpassword+"'"   ;
            ResultSet rs1 = statement.executeQuery(Query);
            while (rs1.next()) {        
                this.Username = rs1.getString("UserName");
                this.Password = rs1.getString("Password");
                ID = rs1.getString("NO");
            }
            if (this.Username.equals(user) && this.Password.equals(txtpassword)){
                return ID;

            }else
                return "Erorr";

    }
   // insert acction into database
    void addAccount(String name, String lastName, String userName, String email, String password) throws SQLException{
        connection = getconConnection();
        try {
            statement = connection.createStatement();
            String insertTableSQL;
            insertTableSQL=("INSERT INTO `UserInfo`"+"(`Name`, `LastName`, `UserName`, `E-mail`, `Password`)"+"VALUES"+"("+"'"+name+"','"+lastName+"','"+userName+"','"+email+"','"+password+"')");
            statement.executeUpdate(insertTableSQL);
            
        } 
        catch (SQLException ex) { 
            ex.printStackTrace();
        }//end catch
    }//end method

    //getting data from the server
    public ObservableList<UsersTable> getUserInfo()throws SQLException, IOException{

        ObservableList<UsersTable> list = FXCollections.observableArrayList();
        connection = getconConnection();
        statement = connection.createStatement();
            
            String Query = "SELECT * FROM `UserInfo` ";
            ResultSet rs1 = statement.executeQuery(Query);
            while (rs1.next()) {
                
                list.add(new UsersTable(rs1.getString("NO"), rs1.getString("Name"), rs1.getString("LastName"), 
                rs1.getString("UserName"), rs1.getString("E-mail")));
            }
            return list;
    }
    //delete user using the id
    public void deleteUser(int id) throws SQLException{
        connection = getconConnection();
        statement = connection.createStatement();
        String Query = "DELETE FROM `userinfo` WHERE `NO` = '"+id+"'";
        statement.executeUpdate(Query);
    }


}

