/**
 * 
 * Sample Skeleton for 'Tables.fxml' Controller Class
 * 
 */

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class OrderController{
    SwitchScenes switchScenes = new SwitchScenes();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

     // URL location of the FXML file that was given to the FXMLLoader
    @FXML private URL location;
    @FXML private Label Price;
    @FXML private ImageView foodImg;
    @FXML private Label foodName;
          private Order order;
          private Listener listener;




    @FXML private void click(MouseEvent event){
       listener.onClickListener(order);
    }


    public void setData(Order order, Listener listener) {//to set data for the scene "i need drink"
        this.order = order;
        this.listener = listener;
        foodName.setText(order.getFoodName());//set text for the food Name label
        Price.setText(order.getFoodPrice());

        Image image = new Image(getClass().getResourceAsStream(order.getFoodimg()));
        foodImg.setImage(image);
    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        
    }
    

}
