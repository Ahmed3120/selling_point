public class Order {
    private String foodName;
    private String foodPrice;
    private String foodimg;

    Order(String foodName, String foodPrice, String foodimg){
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodimg = foodimg;
 
    }

    public String getFoodName() {
        return foodName;
    }
    public String getFoodPrice() {
        return foodPrice;
    }
    public String getFoodimg() {
        return foodimg;
    }



}
