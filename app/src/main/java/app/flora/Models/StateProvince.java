package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StateProvince {

    @SerializedName("localized_names")
    @Expose
    List<LocalizedName> localized_names;
    @SerializedName("country_id")
    @Expose
    private Integer countryId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("abbreviation")
    @Expose
    private String abbreviation;
    @SerializedName("published")
    @Expose
    private Boolean published;
    @SerializedName("display_order")
    @Expose
    private Integer displayOrder;
    @SerializedName("id")
    @Expose
    private Integer id;

    public List<LocalizedName> getLocalized_names() {
        return localized_names;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
