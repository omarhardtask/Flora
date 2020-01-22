package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ShoppingCarts {

    @SerializedName("shopping_carts")
    @Expose
    private ArrayList<ShoppingCart> shoppingCarts = null;

    public ArrayList<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(ArrayList<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

}
