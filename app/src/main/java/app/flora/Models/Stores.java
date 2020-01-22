package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Stores {

    @SerializedName("stores")
    @Expose
    private List<Store> stores = null;
    public List<Store> getStores() {
        return stores;
    }
    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

}
