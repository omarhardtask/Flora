package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocalizedDescription {

    @SerializedName("language_id")
    @Expose
    private Integer languageId;
    @SerializedName("localized_description")
    @Expose
    private Object localizedDescription;

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public Object getLocalizedDescription() {
        return localizedDescription;
    }

    public void setLocalizedDescription(Object localizedDescription) {
        this.localizedDescription = localizedDescription;
    }
}
