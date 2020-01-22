package app.flora.Models;

public class CartModel {

    String picture , title , price ;

    public CartModel(String picture, String title, String price) {
        this.picture = picture;
        this.title = title;
        this.price = price;
    }


    public String getPicture() {
        return picture;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }
}
