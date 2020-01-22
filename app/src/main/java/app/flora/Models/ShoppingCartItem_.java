package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShoppingCartItem_ {

    @SerializedName("shopping_cart_type")
    @Expose
    private String shoppingCartType;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("customer_id")
    @Expose
    private Integer customerId;

    @SerializedName("product_attributes")
    @Expose
    private List<ProductAttribute> product_attributes;

    public String getShoppingCartType() {
        return shoppingCartType;
    }

    public void setShoppingCartType(String shoppingCartType) {
        this.shoppingCartType = shoppingCartType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<ProductAttribute> getProduct_attributes() {
        return product_attributes;
    }

    public void setProduct_attributes(List<ProductAttribute> product_attributes) {
        this.product_attributes = product_attributes;
    }
}
