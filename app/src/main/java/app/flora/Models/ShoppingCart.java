package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShoppingCart {

    @SerializedName("sub_total")
    @Expose
    private Double sub_total;
    @SerializedName("formatted_sub_total")
    @Expose
    private String formattedSubTotal;
    @SerializedName("formatted_item_sub_total")
    @Expose
    private String formatted_item_sub_total;
    @SerializedName("product_attributes")
    @Expose
    private List<ProductAttribute> productAttributes = null;
    @SerializedName("customer_entered_price")
    @Expose
    private Double customerEnteredPrice;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("rental_start_date_utc")
    @Expose
    private Object rentalStartDateUtc;
    @SerializedName("rental_end_date_utc")
    @Expose
    private Object rentalEndDateUtc;
    @SerializedName("created_on_utc")
    @Expose
    private String createdOnUtc;
    @SerializedName("updated_on_utc")
    @Expose
    private String updatedOnUtc;
    @SerializedName("shopping_cart_type")
    @Expose
    private String shoppingCartType;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("product")
    @Expose
    private Product product;
    @SerializedName("customer_id")
    @Expose
    private Integer customerId;
    @SerializedName("customer")
    @Expose
    private Customer customer;
    @SerializedName("id")
    @Expose
    private Integer id;



    public String getFormattedSubTotal() {
        return formattedSubTotal;
    }

    public void setFormattedSubTotal(String formattedSubTotal) {
        this.formattedSubTotal = formattedSubTotal;
    }

    public Double getSub_total() {
        return sub_total;
    }

    public void setSub_total(Double sub_total) {
        this.sub_total = sub_total;
    }

    public List<ProductAttribute> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(List<ProductAttribute> productAttributes) {
        this.productAttributes = productAttributes;
    }

    public Double getCustomerEnteredPrice() {
        return customerEnteredPrice;
    }

    public void setCustomerEnteredPrice(Double customerEnteredPrice) {
        this.customerEnteredPrice = customerEnteredPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Object getRentalStartDateUtc() {
        return rentalStartDateUtc;
    }

    public void setRentalStartDateUtc(Object rentalStartDateUtc) {
        this.rentalStartDateUtc = rentalStartDateUtc;
    }

    public Object getRentalEndDateUtc() {
        return rentalEndDateUtc;
    }

    public void setRentalEndDateUtc(Object rentalEndDateUtc) {
        this.rentalEndDateUtc = rentalEndDateUtc;
    }

    public String getCreatedOnUtc() {
        return createdOnUtc;
    }

    public void setCreatedOnUtc(String createdOnUtc) {
        this.createdOnUtc = createdOnUtc;
    }

    public String getUpdatedOnUtc() {
        return updatedOnUtc;
    }

    public String getFormatted_item_sub_total() {
        return formatted_item_sub_total;
    }

    public void setFormatted_item_sub_total(String formatted_item_sub_total) {
        this.formatted_item_sub_total = formatted_item_sub_total;
    }

    public void setUpdatedOnUtc(String updatedOnUtc) {
        this.updatedOnUtc = updatedOnUtc;
    }

    public String getShoppingCartType() {
        return shoppingCartType;
    }

    public void setShoppingCartType(String shoppingCartType) {
        this.shoppingCartType = shoppingCartType;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
