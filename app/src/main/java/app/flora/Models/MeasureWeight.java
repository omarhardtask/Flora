package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MeasureWeight {

    @SerializedName("weight_id")
    @Expose
    private String weight_id;

    @SerializedName("weight_name")
    @Expose
    private String weight_name;

    public String getWeight_id() {
        return weight_id;
    }

    public void setWeight_id(String weight_id) {
        this.weight_id = weight_id;
    }

    public String getWeight_name() {
        return weight_name;
    }

    public void setWeight_name(String weight_name) {
        this.weight_name = weight_name;
    }



}
