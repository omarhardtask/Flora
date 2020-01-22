package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Attribute implements Comparable<Attribute> {


    @SerializedName("attribute_control_type_id")
    @Expose
    private Integer attribute_control_type_id;
    @SerializedName("attribute_control_type_name")
    @Expose
    private String attribute_control_type_name;
    @SerializedName("default_value")
    @Expose
    private String default_value;
    @SerializedName("display_order")
    @Expose
    private Integer display_order;
    @SerializedName("attribute_values")
    @Expose
    private List<AttributeValue> attribute_values = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("is_required")
    @Expose
    private Boolean is_required;
    @SerializedName("product_attribute_name")
    @Expose
    private String product_attribute_name;
    @SerializedName("product_attribute_id")
    @Expose
    private Integer product_attribute_id;
    @SerializedName("text_prompt")
    @Expose
    private String text_prompt;
    @SerializedName("localized_names")
    @Expose
    private List<LocalizedName> localized_names;

    public Integer getAttribute_control_type_id() {
        return attribute_control_type_id;
    }

    public void setAttribute_control_type_id(Integer attribute_control_type_id) {
        this.attribute_control_type_id = attribute_control_type_id;
    }

    public String getAttribute_control_type_name() {
        return attribute_control_type_name;
    }

    public void setAttribute_control_type_name(String attribute_control_type_name) {
        this.attribute_control_type_name = attribute_control_type_name;
    }

    public String getDefault_value() {
        return default_value;
    }

    public void setDefault_value(String default_value) {
        this.default_value = default_value;
    }

    public Integer getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(Integer display_order) {
        this.display_order = display_order;
    }

    public List<AttributeValue> getAttribute_values() {
        return attribute_values;
    }

    public void setAttribute_values(List<AttributeValue> attribute_values) {
        this.attribute_values = attribute_values;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIs_required() {
        return is_required;
    }

    public void setIs_required(Boolean is_required) {
        this.is_required = is_required;
    }

    public String getProduct_attribute_name() {
        return product_attribute_name;
    }

    public void setProduct_attribute_name(String product_attribute_name) {
        this.product_attribute_name = product_attribute_name;
    }

    public Integer getProduct_attribute_id() {
        return product_attribute_id;
    }

    public void setProduct_attribute_id(Integer product_attribute_id) {
        this.product_attribute_id = product_attribute_id;
    }

    public String getText_prompt() {
        return text_prompt;
    }

    public void setText_prompt(String text_prompt) {
        this.text_prompt = text_prompt;
    }

    public List<LocalizedName> getLocalized_names() {
        return localized_names;
    }

    public void setLocalized_names(List<LocalizedName> localized_names) {
        this.localized_names = localized_names;
    }

    @Override
    public int compareTo(Attribute o) {
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
