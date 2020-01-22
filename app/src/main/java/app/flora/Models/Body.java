package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Body {

    @SerializedName("language_id")
    @Expose
    private String language_id;
    @SerializedName("localized_body")
    @Expose
    private String localized_body;

    public String getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(String language_id) {
        this.language_id = language_id;
    }

    public String getLocalized_body() {
        return localized_body;
    }

    public void setLocalized_body(String localized_body) {
        this.localized_body = localized_body;
    }
}
