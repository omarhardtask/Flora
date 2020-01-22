package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetCategories {

    @SerializedName("categories")
    @Expose
    private ArrayList<Category> categories = null;

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
}
