package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Titles {

    @SerializedName("language_id")
    @Expose
    private String language_id;
    @SerializedName("localized_title")
    @Expose
    private String localized_title;

    public String getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(String language_id) {
        this.language_id = language_id;
    }

    public String getLocalized_title() {
        return localized_title;
    }

    public void setLocalized_title(String localized_title) {
        this.localized_title = localized_title;
    }

}
