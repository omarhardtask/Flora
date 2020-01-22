package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocalizedFullDescription {
    @SerializedName("language_id")
    @Expose
    private Integer languageId;
    @SerializedName("localized_full_description")
    @Expose
    private String localizedFullDescription;

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public String getLocalizedFullDescription() {
        return localizedFullDescription;
    }

    public void setLocalizedFullDescription(String localizedFullDescription) {
        this.localizedFullDescription = localizedFullDescription;
    }
}
