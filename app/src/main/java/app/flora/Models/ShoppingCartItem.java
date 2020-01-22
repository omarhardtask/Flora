package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShoppingCartItem {


    @SerializedName("shopping_cart_item")
    @Expose
    private ShoppingCartItem_ shoppingCartItem;

    public ShoppingCartItem_ getShoppingCartItem() {
        return shoppingCartItem;
    }

    public void setShoppingCartItem(ShoppingCartItem_ shoppingCartItem) {
        this.shoppingCartItem = shoppingCartItem;
    }
}
