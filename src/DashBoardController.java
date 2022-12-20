import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;


public class DashBoardController {
    
    private GridPane OrderGrid;
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Label namelbl;//used to show name under the picture
    @FXML private ScrollPane scrollPane;
    @FXML private Pane checkoutPane;
    @FXML private ImageView productCart;
    @FXML private HBox headerHBox;
    @FXML private HBox settingsBtn;
    @FXML private HBox statusBtn;
    @FXML private HBox employeeBtn;
    @FXML private AnchorPane mainScreenInScrollPane;

    @FXML private Button cancelBtn;
    @FXML private Button checkOutBtn;
    @FXML private TableView<Order> checkoutTable;
    @FXML private TableColumn<Order, Integer> numberOfOrderSection;
    @FXML private TableColumn<Order, String> orderSection;
    @FXML private TableColumn<Order, String> priceSection;
    @FXML private Label totalPriceLabel;
    Listener listener;
    private String Id;//Used for sending and recive id

    List<Order> orders = new ArrayList<Order>();//used for "orders" scene to save and order info
    ObservableList<Order> obList = FXCollections.observableArrayList();//make instance of observable list t
    SwitchScenes switchScenes = new SwitchScenes();
    DB_Connection db = new DB_Connection();
    
    public void setId(String id) throws SQLException {
        //setting the info getting it by id  
        this.Id = id;
        namelbl.setText(db.Info(Id, "Name"));

        if(Id.equals("1")){//here make the superviser account 
            settingsBtn.setDisable(false);
            // statusBtn.setDisable(false);
            employeeBtn.setDisable(false);
        }

    }

    @FXML
    void BtnSignOut(MouseEvent mouseEvent) throws IOException {//call sign out scene
        switchScenes.SwitchToLoginScene(mouseEvent);//get from switch scene class
    }
    

    public void setit(String Num) throws SQLException{//get id from sign in scene and used to call other
        this.Id = Num;                                //info from server it has a security issus
        namelbl.setText(db.Info(Num, "Name")+" "+db.Info(Num, "LastName"));

    }//compeleted but must fix the security issus

    private List<Order> add(){//TODO:temperory method untill i could built the add to manu section
        Order order;          
        List<Order> list = new ArrayList<Order>();

            order = new Order("pizza", "1000", "Icons/pizza-png-1.png");
            list.add(order);

            order = new Order("Burger", "5000", "Icons/pizza-png-1.png");
            list.add(order);
            
            order = new Order("Melon", "4000", "Icons/pizza-png-1.png");
            list.add(order);

            order = new Order("Balqlaa", "3000", "Icons/pizza-png-1.png");
            list.add(order);

            order = new Order("felea", "2000", "Icons/pizza-png-1.png");
            list.add(order);


            order = new Order("Balqlaa", "3000", "Icons/pizza-png-1.png");
            list.add(order);

            order = new Order("felea", "2000", "Icons/pizza-png-1.png");
            list.add(order);
            
            
        return list;
    }


    private void showManu()throws IOException{
       //the button on the side used for show food menu
        mainScreenInScrollPane.getChildren().clear();
        orders.clear();

        //the listener used for detect the click on the food menu 
        listener = new Listener() {
            @Override 
            public void onClickListener(Order order) {
                setCheckOut(order);
                
            }
            //it's a simple a interface 
        };
        
        
        orders.addAll(add());// add all menu item to list
        int column = 0, row = 0; //track the item
        OrderGrid = new GridPane();
        
        try{
            for (int i = 0; i < orders.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Order.fxml"));//load the order scene menu to the anchor Pane

                AnchorPane anchorPane = fxmlLoader.load();//load the order scene 
                OrderController orderController = fxmlLoader.getController();// send data to order scene throw the controller to show it in menu
                orderController.setData(orders.get(i), listener);//setting the data and controller

                if (column == 4) {//make the list width 4 item in grid pane
                    column = 0;
                    row++;
                }
                this.OrderGrid.add(anchorPane, column++, row);

                //set grid width
                OrderGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                OrderGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                OrderGrid.setMaxWidth(Region.USE_COMPUTED_SIZE);

                //set grid Hight
                OrderGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                OrderGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                OrderGrid.setMaxHeight(Region.USE_COMPUTED_SIZE);
                
                //set margin
                GridPane.setMargin(anchorPane, new Insets(10, 10, 10, 10));
            
                
            }
            mainScreenInScrollPane.getChildren().add(OrderGrid);// show the order list
        
        }catch(IOException e){
            e.printStackTrace();
        }

        
    }
   
    @FXML private void Order(MouseEvent mouseEvent) throws IOException{//this method used for showing data in order section
       showManu();
    }

    @FXML 
    void initialize() throws IOException, SQLException {//this method execute when the program first initialize
        
        checkoutPane.setLayoutX(headerHBox.getLayoutX()+15);
        checkoutPane.setLayoutY(productCart.getLayoutY() + 30);
        showManu();
        
        
    }

    @FXML
    void showCheckoutTable(MouseEvent event) throws IOException{
        checkoutPane.setVisible(!checkoutPane.isVisible());// to show the check out table 
        double sum = 0;
        
        for (int i = 0; i < obList.size(); i++) {//to 
            Order p = obList.get(i);
            sum += Integer.parseInt(p.getFoodPrice());
        }
        
        totalPriceLabel.setText(String.valueOf(sum));// cast the double to integer
        
    }

    public void setCheckOut(Order order){
        String number = "1";
        priceSection.setCellValueFactory(new PropertyValueFactory<Order, String>("foodPrice"));
        orderSection.setCellValueFactory(new PropertyValueFactory<Order, String>("foodName"));
        numberOfOrderSection.setCellValueFactory(new PropertyValueFactory<Order, Integer>(number));
        
        checkoutTable.setItems(obList);
        obList.add(order);

    }

    @FXML private void showSetting(MouseEvent mouseEvent) throws Exception{
        changeShowingScene("Settings.fxml");
    }

    @FXML private void cancelOrder(ActionEvent event){
        obList.clear();
        checkoutPane.setVisible(!checkoutPane.isVisible());
    }

    @FXML private void checkoutOrder(ActionEvent event){
        

    }
    @FXML private void changeToTable(MouseEvent mouseEvent) throws Exception{
        changeShowingScene("UsersTable.fxml");
    }


    public void changeShowingScene(String path)throws Exception{
        //method to change the ui in dashboard
        URL url = getClass().getResource(path);//get the path of the ui
        mainScreenInScrollPane.getChildren().clear();//then clear the scroll pane 
        mainScreenInScrollPane.getChildren().add(FXMLLoader.load(url));//load the ui in the scroll pane
    }


}
