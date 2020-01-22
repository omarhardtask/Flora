package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AttributeValue  implements Comparable<AttributeValue> {


    @SerializedName("associated_product_id")
    @Expose
    private Integer associated_product_id;
    @SerializedName("color_squares_rgb")
    @Expose
    private String color_squares_rgb;
    @SerializedName("cost")
    @Expose
    private Double cost;
    @SerializedName("display_order")
    @Expose
    private Integer display_order;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("image_squares_image")
    @Expose
    private String image_squares_image;
    @SerializedName("is_pre_selected")
    @Expose
    private Boolean is_pre_selected;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price_adjustment")
    @Expose
    private Double price_adjustment;
    @SerializedName("product_image_id")
    @Expose
    private String product_image_id;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("type_id")
    @Expose
    private Integer type_id;
    @SerializedName("weight_adjustment")
    @Expose
    private String weight_adjustment;
    @SerializedName("price_after_adjustment")
    @Expose
    private String price_after_adjustment;
    @SerializedName("localized_names")
    @Expose
    private List<LocalizedName> localized_names;

    public Integer getAssociated_product_id() {
        return associated_product_id;
    }

    public void setAssociated_product_id(Integer associated_product_id) {
        this.associated_product_id = associated_product_id;
    }

    public String getColor_squares_rgb() {
        return color_squares_rgb;
    }

    public void setColor_squares_rgb(String color_squares_rgb) {
        this.color_squares_rgb = color_squares_rgb;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(Integer display_order) {
        this.display_order = display_order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage_squares_image() {
        return image_squares_image;
    }

    public void setImage_squares_image(String image_squares_image) {
        this.image_squares_image = image_squares_image;
    }

    public Boolean getIs_pre_selected() {
        return is_pre_selected;
    }

    public void setIs_pre_selected(Boolean is_pre_selected) {
        this.is_pre_selected = is_pre_selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice_adjustment() {
        return price_adjustment;
    }

    public void setPrice_adjustment(Double price_adjustment) {
        this.price_adjustment = price_adjustment;
    }

    public String getProduct_image_id() {
        return product_image_id;
    }

    public void setProduct_image_id(String product_image_id) {
        this.product_image_id = product_image_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public String getWeight_adjustment() {
        return weight_adjustment;
    }

    public void setWeight_adjustment(String weight_adjustment) {
        this.weight_adjustment = weight_adjustment;
    }

    public String getPrice_after_adjustment() {
        return price_after_adjustment;
    }

    public void setPrice_after_adjustment(String price_after_adjustment) {
        this.price_after_adjustment = price_after_adjustment;
    }

    public List<LocalizedName> getLocalized_names() {
        return localized_names;
    }

    public void setLocalized_names(List<LocalizedName> localized_names) {
        this.localized_names = localized_names;
    }

    @Override
    public int compareTo(AttributeValue o) {
        if (display_order > o.display_order) {
            return 1;
        }
        else if (display_order <  o.display_order) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
