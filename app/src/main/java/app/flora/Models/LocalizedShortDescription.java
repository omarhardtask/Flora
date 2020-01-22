package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocalizedShortDescription {

    @SerializedName("language_id")
    @Expose
    private Integer languageId;
    @SerializedName("localized_short_description")
    @Expose
    private String localizedShortDescription;

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public String getLocalizedShortDescription() {
        return localizedShortDescription;
    }

    public void setLocalizedShortDescription(String localizedShortDescription) {
        this.localizedShortDescription = localizedShortDescription;
    }

}
