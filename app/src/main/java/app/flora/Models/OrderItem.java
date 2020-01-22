package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderItem {

    @SerializedName("by_weight")
    @Expose
    private Boolean by_weight;
    @SerializedName("unit_weight")
    @Expose
    private Double unit_weight;
    @SerializedName("cards")
    @Expose
    private List<Cards> cardsList = null;
    @SerializedName("product_attributes")
    @Expose
    private List<ProductAttribute> productAttributes = null;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("unit_price_incl_tax")
    @Expose
    private Double unitPriceInclTax;
    @SerializedName("unit_price_excl_tax")
    @Expose
    private Double unitPriceExclTax;
    @SerializedName("price_incl_tax")
    @Expose
    private Double priceInclTax;
    @SerializedName("price_excl_tax")
    @Expose
    private Double priceExclTax;
    @SerializedName("discount_amount_incl_tax")
    @Expose
    private Double discountAmountInclTax;
    @SerializedName("discount_amount_excl_tax")
    @Expose
    private Double discountAmountExclTax;
    @SerializedName("original_product_cost")
    @Expose
    private Double originalProductCost;
    @SerializedName("attribute_description")
    @Expose
    private String attributeDescription;
    @SerializedName("download_count")
    @Expose
    private Integer downloadCount;
    @SerializedName("isDownload_activated")
    @Expose
    private Boolean isDownloadActivated;
    @SerializedName("license_download_id")
    @Expose
    private Integer licenseDownloadId;
    @SerializedName("item_weight")
    @Expose
    private Integer itemWeight;
    @SerializedName("rental_start_date_utc")
    @Expose
    private Object rentalStartDateUtc;
    @SerializedName("rental_end_date_utc")
    @Expose
    private Object rentalEndDateUtc;
    @SerializedName("product")
    @Expose
    private Product product;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("id")
    @Expose
    private Integer id;


    public Boolean getBy_weight() {
        return by_weight;
    }

    public void setBy_weight(Boolean by_weight) {
        this.by_weight = by_weight;
    }

    public Double getUnit_weight() {
        return unit_weight;
    }

    public void setUnit_weight(Double unit_weight) {
        this.unit_weight = unit_weight;
    }

    public List<Cards> getCardsList() {
        return cardsList;
    }

    public void setCardsList(List<Cards> cardsList) {
        this.cardsList = cardsList;
    }

    public List<ProductAttribute> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(List<ProductAttribute> productAttributes) {
        this.productAttributes = productAttributes;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPriceInclTax() {
        return unitPriceInclTax;
    }

    public void setUnitPriceInclTax(Double unitPriceInclTax) {
        this.unitPriceInclTax = unitPriceInclTax;
    }

    public Double getUnitPriceExclTax() {
        return unitPriceExclTax;
    }

    public void setUnitPriceExclTax(Double unitPriceExclTax) {
        this.unitPriceExclTax = unitPriceExclTax;
    }

    public Double getPriceInclTax() {
        return priceInclTax;
    }

    public void setPriceInclTax(Double priceInclTax) {
        this.priceInclTax = priceInclTax;
    }

    public Double getPriceExclTax() {
        return priceExclTax;
    }

    public void setPriceExclTax(Double priceExclTax) {
        this.priceExclTax = priceExclTax;
    }

    public Double getDiscountAmountInclTax() {
        return discountAmountInclTax;
    }

    public void setDiscountAmountInclTax(Double discountAmountInclTax) {
        this.discountAmountInclTax = discountAmountInclTax;
    }

    public Double getDiscountAmountExclTax() {
        return discountAmountExclTax;
    }

    public void setDiscountAmountExclTax(Double discountAmountExclTax) {
        this.discountAmountExclTax = discountAmountExclTax;
    }

    public Double getOriginalProductCost() {
        return originalProductCost;
    }

    public void setOriginalProductCost(Double originalProductCost) {
        this.originalProductCost = originalProductCost;
    }

    public String getAttributeDescription() {
        return attributeDescription;
    }

    public void setAttributeDescription(String attributeDescription) {
        this.attributeDescription = attributeDescription;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Boolean getIsDownloadActivated() {
        return isDownloadActivated;
    }

    public void setIsDownloadActivated(Boolean isDownloadActivated) {
        this.isDownloadActivated = isDownloadActivated;
    }

    public Integer getLicenseDownloadId() {
        return licenseDownloadId;
    }

    public void setLicenseDownloadId(Integer licenseDownloadId) {
        this.licenseDownloadId = licenseDownloadId;
    }

    public Integer getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(Integer itemWeight) {
        this.itemWeight = itemWeight;
    }

    public Object getRentalStartDateUtc() {
        return rentalStartDateUtc;
    }

    public void setRentalStartDateUtc(Object rentalStartDateUtc) {
        this.rentalStartDateUtc = rentalStartDateUtc;
    }

    public Object getRentalEndDateUtc() {
        return rentalEndDateUtc;
    }

    public void setRentalEndDateUtc(Object rentalEndDateUtc) {
        this.rentalEndDateUtc = rentalEndDateUtc;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
