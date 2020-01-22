package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Topic {

    @SerializedName("system_name")
    @Expose
    private String systemName;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("localized_titles")
    @Expose
    private List<Titles> localized_titles;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("localized_bodies")
    @Expose
    private List<Body> localized_bodies;
    @SerializedName("meta_keywords")
    @Expose
    private Object metaKeywords;
    @SerializedName("meta_description")
    @Expose
    private Object metaDescription;
    @SerializedName("meta_title")
    @Expose
    private Object metaTitle;
    @SerializedName("display_order")
    @Expose
    private Integer displayOrder;
    @SerializedName("published")
    @Expose
    private Boolean published;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Object getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(Object metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public Object getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(Object metaDescription) {
        this.metaDescription = metaDescription;
    }

    public Object getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(Object metaTitle) {
        this.metaTitle = metaTitle;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Titles> getLocalized_titles() {
        return localized_titles;
    }

    public void setLocalized_titles(List<Titles> localized_titles) {
        this.localized_titles = localized_titles;
    }

    public List<Body> getLocalized_bodies() {
        return localized_bodies;
    }

    public void setLocalized_bodies(List<Body> localized_bodies) {
        this.localized_bodies = localized_bodies;
    }


}
