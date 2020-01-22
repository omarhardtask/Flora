package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StateProvinces {


    @SerializedName("state_provinces")
    @Expose
    private List<StateProvince> stateProvinces = null;

    public List<StateProvince> getStateProvinces() {
        return stateProvinces;
    }

    public void setStateProvinces(List<StateProvince> stateProvinces) {
        this.stateProvinces = stateProvinces;
    }


}
