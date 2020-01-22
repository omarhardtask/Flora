package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payment {

    @SerializedName("friendly_name")
    @Expose
    private String friendly_name;
    @SerializedName("system_name")
    @Expose
    private String system_name;


    public String getFriendly_name() {
        return friendly_name;
    }

    public void setFriendly_name(String friendly_name) {
        this.friendly_name = friendly_name;
    }

    public String getSystem_name() {
        return system_name;
    }

    public void setSystem_name(String system_name) {
        this.system_name = system_name;
    }

}
