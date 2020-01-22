package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Country {

    @SerializedName("localized_names")
    @Expose
    List<LocalizedName> localized_names;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("allows_billing")
    @Expose
    private Boolean allowsBilling;
    @SerializedName("allows_shipping")
    @Expose
    private Boolean allowsShipping;
    @SerializedName("two_letter_iso_code")
    @Expose
    private String twoLetterIsoCode;
    @SerializedName("three_letter_iso_code")
    @Expose
    private String threeLetterIsoCode;
    @SerializedName("numeric_iso_code")
    @Expose
    private Integer numericIsoCode;
    @SerializedName("subject_to_vat")
    @Expose
    private Boolean subjectToVat;
    @SerializedName("published")
    @Expose
    private Boolean published;
    @SerializedName("display_order")
    @Expose
    private Integer displayOrder;
    @SerializedName("limited_to_stores")
    @Expose
    private Boolean limitedToStores;
    @SerializedName("id")
    @Expose
    private Integer id;

    public List<LocalizedName> getLocalizedNames() {
        return localized_names;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAllowsBilling() {
        return allowsBilling;
    }

    public void setAllowsBilling(Boolean allowsBilling) {
        this.allowsBilling = allowsBilling;
    }

    public Boolean getAllowsShipping() {
        return allowsShipping;
    }

    public void setAllowsShipping(Boolean allowsShipping) {
        this.allowsShipping = allowsShipping;
    }

    public String getTwoLetterIsoCode() {
        return twoLetterIsoCode;
    }

    public void setTwoLetterIsoCode(String twoLetterIsoCode) {
        this.twoLetterIsoCode = twoLetterIsoCode;
    }

    public String getThreeLetterIsoCode() {
        return threeLetterIsoCode;
    }

    public void setThreeLetterIsoCode(String threeLetterIsoCode) {
        this.threeLetterIsoCode = threeLetterIsoCode;
    }

    public Integer getNumericIsoCode() {
        return numericIsoCode;
    }

    public void setNumericIsoCode(Integer numericIsoCode) {
        this.numericIsoCode = numericIsoCode;
    }

    public Boolean getSubjectToVat() {
        return subjectToVat;
    }

    public void setSubjectToVat(Boolean subjectToVat) {
        this.subjectToVat = subjectToVat;
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

    public Boolean getLimitedToStores() {
        return limitedToStores;
    }

    public void setLimitedToStores(Boolean limitedToStores) {
        this.limitedToStores = limitedToStores;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
