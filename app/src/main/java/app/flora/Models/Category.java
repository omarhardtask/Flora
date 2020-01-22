package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Category {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("localized_names")
    @Expose
    private List<LocalizedName> localizedNames = null;
    @SerializedName("category_template_id")
    @Expose
    private Integer categoryTemplateId;
    @SerializedName("parent_category_id")
    @Expose
    private Integer parentCategoryId;
    @SerializedName("page_size")
    @Expose
    private Integer pageSize;
    @SerializedName("show_on_home_page")
    @Expose
    private Boolean showOnHomePage;
    @SerializedName("include_in_top_menu")
    @Expose
    private Boolean includeInTopMenu;
    @SerializedName("published")
    @Expose
    private Boolean published;
    @SerializedName("deleted")
    @Expose
    private Boolean deleted;
    @SerializedName("display_order")
    @Expose
    private Integer displayOrder;
    @SerializedName("created_on_utc")
    @Expose
    private String createdOnUtc;
    @SerializedName("updated_on_utc")
    @Expose
    private String updatedOnUtc;
    @SerializedName("role_ids")
    @Expose
    private List<Object> roleIds = null;
    @SerializedName("discount_ids")
    @Expose
    private List<Object> discountIds = null;
    @SerializedName("store_ids")
    @Expose
    private List<Object> storeIds = null;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("se_name")
    @Expose
    private String seName;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("has_sub_categories")
    @Expose
    private Boolean hasSubCategories;
    @SerializedName("top_products")
    @Expose
    private ArrayList<Product> topProducts;
    @SerializedName("localized_descriptions")
    @Expose
    private List<LocalizedDescription> localizedDescriptions = null;

    boolean IsSelected;

    public List<LocalizedDescription> getLocalizedDescriptions() {
        return localizedDescriptions;
    }

    public void setLocalizedDescriptions(List<LocalizedDescription> localizedDescriptions) {
        this.localizedDescriptions = localizedDescriptions;
    }

    public Boolean getHasSubCategories() {
        return hasSubCategories;
    }

    public void setHasSubCategories(Boolean hasSubCategories) {
        this.hasSubCategories = hasSubCategories;
    }

    public ArrayList<Product> getTopProducts() {
        return topProducts;
    }

    public void setTopProducts(ArrayList<Product> topProducts) {
        this.topProducts = topProducts;
    }

    public boolean isSelected() {
        return IsSelected;
    }

    public void setSelected(boolean selected) {
        IsSelected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LocalizedName> getLocalizedNames() {
        return localizedNames;
    }

    public void setLocalizedNames(List<LocalizedName> localizedNames) {
        this.localizedNames = localizedNames;
    }

    public Integer getCategoryTemplateId() {
        return categoryTemplateId;
    }

    public void setCategoryTemplateId(Integer categoryTemplateId) {
        this.categoryTemplateId = categoryTemplateId;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getShowOnHomePage() {
        return showOnHomePage;
    }

    public void setShowOnHomePage(Boolean showOnHomePage) {
        this.showOnHomePage = showOnHomePage;
    }

    public Boolean getIncludeInTopMenu() {
        return includeInTopMenu;
    }

    public void setIncludeInTopMenu(Boolean includeInTopMenu) {
        this.includeInTopMenu = includeInTopMenu;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getCreatedOnUtc() {
        return createdOnUtc;
    }

    public void setCreatedOnUtc(String createdOnUtc) {
        this.createdOnUtc = createdOnUtc;
    }

    public String getUpdatedOnUtc() {
        return updatedOnUtc;
    }

    public void setUpdatedOnUtc(String updatedOnUtc) {
        this.updatedOnUtc = updatedOnUtc;
    }

    public List<Object> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Object> roleIds) {
        this.roleIds = roleIds;
    }

    public List<Object> getDiscountIds() {
        return discountIds;
    }

    public void setDiscountIds(List<Object> discountIds) {
        this.discountIds = discountIds;
    }

    public List<Object> getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(List<Object> storeIds) {
        this.storeIds = storeIds;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getSeName() {
        return seName;
    }

    public void setSeName(String seName) {
        this.seName = seName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoto(){
        String url="";
        if(image != null){
            if(image.getSrc() != null){
                url =  image.getSrc();
            }
        }
        return url;
    }

    public String getLocalizedName(){
        String name="";
        if(localizedNames != null){
            if(localizedNames.size()>0){
                name = localizedNames.get(0).getLocalizedName();
            }
        }
        return name;
    }
    public void setLocalizedName(String name){
        LocalizedName localizedName = new LocalizedName();
        localizedName.setLocalizedName(name);
        if(localizedNames == null){
            localizedNames = new ArrayList<>();
        }
        localizedNames.add(localizedName);

    }
}
