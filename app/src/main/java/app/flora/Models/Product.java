package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Product {



    @SerializedName("show_timer")
    @Expose
    private Boolean show_timer;
    @SerializedName("visible_individually")
    @Expose
    private Boolean visibleIndividually;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("added_to_wishlist")
    @Expose
    private boolean added_to_wishlist;
    @SerializedName("localized_names")
    @Expose
    private List<LocalizedName> localizedNames = null;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("full_description")
    @Expose
    private String fullDescription;
    @SerializedName("delivery_date_id")
    @Expose
    private String delivery_date_id;
    @SerializedName("show_on_home_page")
    @Expose
    private Boolean showOnHomePage;
    @SerializedName("allow_customer_reviews")
    @Expose
    private Boolean allowCustomerReviews;
    @SerializedName("approved_rating_sum")
    @Expose
    private Integer approvedRatingSum;
    @SerializedName("not_approved_rating_sum")
    @Expose
    private Integer notApprovedRatingSum;
    @SerializedName("approved_total_reviews")
    @Expose
    private Integer approvedTotalReviews;
    @SerializedName("not_approved_total_reviews")
    @Expose
    private Integer notApprovedTotalReviews;
    @SerializedName("require_other_products")
    @Expose
    private Boolean requireOtherProducts;
    @SerializedName("automatically_add_required_products")
    @Expose
    private Boolean automaticallyAddRequiredProducts;
    @SerializedName("is_download")
    @Expose
    private Boolean isDownload;
    @SerializedName("unlimited_downloads")
    @Expose
    private Boolean unlimitedDownloads;
    @SerializedName("max_number_of_downloads")
    @Expose
    private Integer maxNumberOfDownloads;
    @SerializedName("has_sample_download")
    @Expose
    private Boolean hasSampleDownload;
    @SerializedName("has_user_agreement")
    @Expose
    private Boolean hasUserAgreement;
    @SerializedName("is_recurring")
    @Expose
    private Boolean isRecurring;
    @SerializedName("recurring_cycle_length")
    @Expose
    private Integer recurringCycleLength;
    @SerializedName("recurring_total_cycles")
    @Expose
    private Integer recurringTotalCycles;
    @SerializedName("is_rental")
    @Expose
    private Boolean isRental;
    @SerializedName("rental_price_length")
    @Expose
    private Integer rentalPriceLength;
    @SerializedName("is_ship_enabled")
    @Expose
    private Boolean isShipEnabled;
    @SerializedName("is_free_shipping")
    @Expose
    private Boolean isFreeShipping;
    @SerializedName("ship_separately")
    @Expose
    private Boolean shipSeparately;
    @SerializedName("additional_shipping_charge")
    @Expose
    private Integer additionalShippingCharge;
    @SerializedName("is_tax_exempt")
    @Expose
    private Boolean isTaxExempt;
    @SerializedName("is_telecommunications_or_broadcasting_or_electronic_services")
    @Expose
    private Boolean isTelecommunicationsOrBroadcastingOrElectronicServices;
    @SerializedName("use_multiple_warehouses")
    @Expose
    private Boolean useMultipleWarehouses;
    @SerializedName("manage_inventory_method_id")
    @Expose
    private Integer manageInventoryMethodId;
    @SerializedName("stock_quantity")
    @Expose
    private Integer stockQuantity;
    @SerializedName("display_stock_availability")
    @Expose
    private Boolean displayStockAvailability;
    @SerializedName("display_stock_quantity")
    @Expose
    private Boolean displayStockQuantity;
    @SerializedName("min_stock_quantity")
    @Expose
    private Integer minStockQuantity;
    @SerializedName("notify_admin_for_quantity_below")
    @Expose
    private Integer notifyAdminForQuantityBelow;
    @SerializedName("allow_back_in_stock_subscriptions")
    @Expose
    private Boolean allowBackInStockSubscriptions;
    @SerializedName("order_minimum_quantity")
    @Expose
    private Integer orderMinimumQuantity;
    @SerializedName("order_maximum_quantity")
    @Expose
    private Integer orderMaximumQuantity;
    @SerializedName("allow_adding_only_existing_attribute_combinations")
    @Expose
    private Boolean allowAddingOnlyExistingAttributeCombinations;
    @SerializedName("disable_buy_button")
    @Expose
    private Boolean disableBuyButton;
    @SerializedName("disable_wishlist_button")
    @Expose
    private Boolean disableWishlistButton;
    @SerializedName("available_for_pre_order")
    @Expose
    private Boolean availableForPreOrder;
    @SerializedName("call_for_price")
    @Expose
    private Boolean callForPrice;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("old_price")
    @Expose
    private Double oldPrice;
    @SerializedName("product_cost")
    @Expose
    private Integer productCost;
    @SerializedName("customer_enters_price")
    @Expose
    private Boolean customerEntersPrice;
    @SerializedName("minimum_customer_entered_price")
    @Expose
    private Double minimumCustomerEnteredPrice;
    @SerializedName("maximum_customer_entered_price")
    @Expose
    private Double maximumCustomerEnteredPrice;
    @SerializedName("baseprice_enabled")
    @Expose
    private Boolean basepriceEnabled;
    @SerializedName("baseprice_amount")
    @Expose
    private Double basepriceAmount;
    @SerializedName("baseprice_base_amount")
    @Expose
    private Double basepriceBaseAmount;
    @SerializedName("has_tier_prices")
    @Expose
    private Boolean hasTierPrices;
    @SerializedName("has_discounts_applied")
    @Expose
    private Boolean hasDiscountsApplied;
    @SerializedName("by_weight")
    @Expose
    private Boolean by_weight;
    @SerializedName("unit_weight")
    @Expose
    private Double unit_weight;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("length")
    @Expose
    private Integer length;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("available_start_date_time_utc")
    @Expose
    private String availableStartDateTimeUtc;
    @SerializedName("available_end_date_time_utc")
    @Expose
    private String availableEndDateTimeUtc;
    @SerializedName("display_order")
    @Expose
    private Integer displayOrder;
    @SerializedName("published")
    @Expose
    private Boolean published;
    @SerializedName("deleted")
    @Expose
    private Boolean deleted;
    @SerializedName("created_on_utc")
    @Expose
    private String createdOnUtc;
    @SerializedName("updated_on_utc")
    @Expose
    private String updatedOnUtc;
    @SerializedName("product_type")
    @Expose
    private String productType;
    @SerializedName("parent_grouped_product_id")
    @Expose
    private Integer parentGroupedProductId;
    @SerializedName("role_ids")
    @Expose
    private List<Object> roleIds = null;
    @SerializedName("discount_ids")
    @Expose
    private List<Object> discountIds = null;
    @SerializedName("store_ids")
    @Expose
    private List<Object> storeIds = null;
    @SerializedName("manufacturer_ids")
    @Expose
    private List<Object> manufacturerIds = null;
    @SerializedName("images")
    @Expose
    private ArrayList<Image> images = null;
    @SerializedName("attributes")
    @Expose
    private List<Attribute> attributes = null;
    @SerializedName("product_attribute_combinations")
    @Expose
    private List<Object> productAttributeCombinations = null;
    @SerializedName("product_specification_attributes")
    @Expose
    private List<Object> productSpecificationAttributes = null;
    @SerializedName("associated_product_ids")
    @Expose
    private List<Integer> associatedProductIds = null;
    @SerializedName("tags")
    @Expose
    private ArrayList<String> tags = null;
    @SerializedName("vendor_id")
    @Expose
    private Integer vendorId;
    @SerializedName("se_name")
    @Expose
    private String seName;
    @SerializedName("id")
    @Expose
    private Integer id;

    Integer quantity;
    // it is an integer not exist in back end

    @SerializedName("is_card")
    @Expose
    private Boolean isCard;
    @SerializedName("sold_quantity")
    @Expose
    private Integer soldQuantity;
    @SerializedName("stock_availability")
    @Expose
    private String stockAvailability;
    @SerializedName("formatted_price")
    @Expose
    private String formattedPrice;
    @SerializedName("formatted_old_price")
    @Expose
    private String formattedOldPrice;
    @SerializedName("prices_percentage")
    @Expose
    private Double pricesPercentage;
    @SerializedName("vendor")
    @Expose
    private Vendor vendor;

    @SerializedName("localized_short_descriptions")
    @Expose
    private List<LocalizedShortDescription> localizedShortDescriptions = null;

    @SerializedName("localized_full_descriptions")
    @Expose
    private List<LocalizedFullDescription> localizedFullDescriptions = null;


    public boolean isAdded_to_wishlist() {
        return added_to_wishlist;
    }

    public void setAdded_to_wishlist(boolean added_to_wishlist) {
        this.added_to_wishlist = added_to_wishlist;
    }

    public String getDelivery_date_id() {
        return delivery_date_id;
    }

    public void setDelivery_date_id(String delivery_date_id) {
        this.delivery_date_id = delivery_date_id;
    }

    public boolean getAdded_to_wishlist() {
        return added_to_wishlist;
    }

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

    public List<LocalizedShortDescription> getLocalizedShortDescriptions() {
        return localizedShortDescriptions;
    }

    public void setLocalizedShortDescriptions(List<LocalizedShortDescription> localizedShortDescriptions) {
        this.localizedShortDescriptions = localizedShortDescriptions;
    }

    public List<LocalizedFullDescription> getLocalizedFullDescriptions() {
        return localizedFullDescriptions;
    }

    public void setLocalizedFullDescriptions(List<LocalizedFullDescription> localizedFullDescriptions) {
        this.localizedFullDescriptions = localizedFullDescriptions;
    }

    public Boolean getShow_timer() {
        return show_timer;
    }

    public void setShow_timer(Boolean show_timer) {
        this.show_timer = show_timer;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Boolean getDownload() {
        return isDownload;
    }

    public void setDownload(Boolean download) {
        isDownload = download;
    }

    public Boolean getRecurring() {
        return isRecurring;
    }

    public void setRecurring(Boolean recurring) {
        isRecurring = recurring;
    }

    public Boolean getRental() {
        return isRental;
    }

    public void setRental(Boolean rental) {
        isRental = rental;
    }

    public Boolean getShipEnabled() {
        return isShipEnabled;
    }

    public void setShipEnabled(Boolean shipEnabled) {
        isShipEnabled = shipEnabled;
    }

    public Boolean getFreeShipping() {
        return isFreeShipping;
    }

    public void setFreeShipping(Boolean freeShipping) {
        isFreeShipping = freeShipping;
    }

    public Boolean getTaxExempt() {
        return isTaxExempt;
    }

    public void setTaxExempt(Boolean taxExempt) {
        isTaxExempt = taxExempt;
    }

    public Boolean getTelecommunicationsOrBroadcastingOrElectronicServices() {
        return isTelecommunicationsOrBroadcastingOrElectronicServices;
    }

    public void setTelecommunicationsOrBroadcastingOrElectronicServices(Boolean telecommunicationsOrBroadcastingOrElectronicServices) {
        isTelecommunicationsOrBroadcastingOrElectronicServices = telecommunicationsOrBroadcastingOrElectronicServices;
    }

    public Boolean getCard() {
        return isCard;
    }

    public void setCard(Boolean card) {
        isCard = card;
    }

    public Integer getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(Integer soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public String getStockAvailability() {
        return stockAvailability;
    }

    public void setStockAvailability(String stockAvailability) {
        this.stockAvailability = stockAvailability;
    }

    public String getFormattedPrice() {
        return formattedPrice;
    }

    public void setFormattedPrice(String formattedPrice) {
        this.formattedPrice = formattedPrice;
    }

    public String getFormattedOldPrice() {
        return formattedOldPrice;
    }

    public void setFormattedOldPrice(String formattedOldPrice) {
        this.formattedOldPrice = formattedOldPrice;
    }

    public Double getPricesPercentage() {
        return pricesPercentage;
    }

    public void setPricesPercentage(Double pricesPercentage) {
        this.pricesPercentage = pricesPercentage;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getVisibleIndividually() {
        return visibleIndividually;
    }

    public void setVisibleIndividually(Boolean visibleIndividually) {
        this.visibleIndividually = visibleIndividually;
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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public Boolean getShowOnHomePage() {
        return showOnHomePage;
    }

    public void setShowOnHomePage(Boolean showOnHomePage) {
        this.showOnHomePage = showOnHomePage;
    }

    public Boolean getAllowCustomerReviews() {
        return allowCustomerReviews;
    }

    public void setAllowCustomerReviews(Boolean allowCustomerReviews) {
        this.allowCustomerReviews = allowCustomerReviews;
    }

    public Integer getApprovedRatingSum() {
        return approvedRatingSum;
    }

    public void setApprovedRatingSum(Integer approvedRatingSum) {
        this.approvedRatingSum = approvedRatingSum;
    }

    public Integer getNotApprovedRatingSum() {
        return notApprovedRatingSum;
    }

    public void setNotApprovedRatingSum(Integer notApprovedRatingSum) {
        this.notApprovedRatingSum = notApprovedRatingSum;
    }

    public Integer getApprovedTotalReviews() {
        return approvedTotalReviews;
    }

    public void setApprovedTotalReviews(Integer approvedTotalReviews) {
        this.approvedTotalReviews = approvedTotalReviews;
    }

    public Integer getNotApprovedTotalReviews() {
        return notApprovedTotalReviews;
    }

    public void setNotApprovedTotalReviews(Integer notApprovedTotalReviews) {
        this.notApprovedTotalReviews = notApprovedTotalReviews;
    }

    public Boolean getRequireOtherProducts() {
        return requireOtherProducts;
    }

    public void setRequireOtherProducts(Boolean requireOtherProducts) {
        this.requireOtherProducts = requireOtherProducts;
    }

    public Boolean getAutomaticallyAddRequiredProducts() {
        return automaticallyAddRequiredProducts;
    }

    public void setAutomaticallyAddRequiredProducts(Boolean automaticallyAddRequiredProducts) {
        this.automaticallyAddRequiredProducts = automaticallyAddRequiredProducts;
    }

    public Boolean getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(Boolean isDownload) {
        this.isDownload = isDownload;
    }

    public Boolean getUnlimitedDownloads() {
        return unlimitedDownloads;
    }

    public void setUnlimitedDownloads(Boolean unlimitedDownloads) {
        this.unlimitedDownloads = unlimitedDownloads;
    }

    public Integer getMaxNumberOfDownloads() {
        return maxNumberOfDownloads;
    }

    public void setMaxNumberOfDownloads(Integer maxNumberOfDownloads) {
        this.maxNumberOfDownloads = maxNumberOfDownloads;
    }

    public Boolean getHasSampleDownload() {
        return hasSampleDownload;
    }

    public void setHasSampleDownload(Boolean hasSampleDownload) {
        this.hasSampleDownload = hasSampleDownload;
    }

    public Boolean getHasUserAgreement() {
        return hasUserAgreement;
    }

    public void setHasUserAgreement(Boolean hasUserAgreement) {
        this.hasUserAgreement = hasUserAgreement;
    }

    public Boolean getIsRecurring() {
        return isRecurring;
    }

    public void setIsRecurring(Boolean isRecurring) {
        this.isRecurring = isRecurring;
    }

    public Integer getRecurringCycleLength() {
        return recurringCycleLength;
    }

    public void setRecurringCycleLength(Integer recurringCycleLength) {
        this.recurringCycleLength = recurringCycleLength;
    }

    public Integer getRecurringTotalCycles() {
        return recurringTotalCycles;
    }

    public void setRecurringTotalCycles(Integer recurringTotalCycles) {
        this.recurringTotalCycles = recurringTotalCycles;
    }

    public Boolean getIsRental() {
        return isRental;
    }

    public void setIsRental(Boolean isRental) {
        this.isRental = isRental;
    }

    public Integer getRentalPriceLength() {
        return rentalPriceLength;
    }

    public void setRentalPriceLength(Integer rentalPriceLength) {
        this.rentalPriceLength = rentalPriceLength;
    }

    public Boolean getIsShipEnabled() {
        return isShipEnabled;
    }

    public void setIsShipEnabled(Boolean isShipEnabled) {
        this.isShipEnabled = isShipEnabled;
    }

    public Boolean getIsFreeShipping() {
        return isFreeShipping;
    }

    public void setIsFreeShipping(Boolean isFreeShipping) {
        this.isFreeShipping = isFreeShipping;
    }

    public Boolean getShipSeparately() {
        return shipSeparately;
    }

    public void setShipSeparately(Boolean shipSeparately) {
        this.shipSeparately = shipSeparately;
    }

    public Integer getAdditionalShippingCharge() {
        return additionalShippingCharge;
    }

    public void setAdditionalShippingCharge(Integer additionalShippingCharge) {
        this.additionalShippingCharge = additionalShippingCharge;
    }

    public Boolean getIsTaxExempt() {
        return isTaxExempt;
    }

    public void setIsTaxExempt(Boolean isTaxExempt) {
        this.isTaxExempt = isTaxExempt;
    }

    public Boolean getIsTelecommunicationsOrBroadcastingOrElectronicServices() {
        return isTelecommunicationsOrBroadcastingOrElectronicServices;
    }

    public void setIsTelecommunicationsOrBroadcastingOrElectronicServices(Boolean isTelecommunicationsOrBroadcastingOrElectronicServices) {
        this.isTelecommunicationsOrBroadcastingOrElectronicServices = isTelecommunicationsOrBroadcastingOrElectronicServices;
    }

    public Boolean getUseMultipleWarehouses() {
        return useMultipleWarehouses;
    }

    public void setUseMultipleWarehouses(Boolean useMultipleWarehouses) {
        this.useMultipleWarehouses = useMultipleWarehouses;
    }

    public Integer getManageInventoryMethodId() {
        return manageInventoryMethodId;
    }

    public void setManageInventoryMethodId(Integer manageInventoryMethodId) {
        this.manageInventoryMethodId = manageInventoryMethodId;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Boolean getDisplayStockAvailability() {
        return displayStockAvailability;
    }

    public void setDisplayStockAvailability(Boolean displayStockAvailability) {
        this.displayStockAvailability = displayStockAvailability;
    }

    public Boolean getDisplayStockQuantity() {
        return displayStockQuantity;
    }

    public void setDisplayStockQuantity(Boolean displayStockQuantity) {
        this.displayStockQuantity = displayStockQuantity;
    }

    public Integer getMinStockQuantity() {
        return minStockQuantity;
    }

    public void setMinStockQuantity(Integer minStockQuantity) {
        this.minStockQuantity = minStockQuantity;
    }

    public Integer getNotifyAdminForQuantityBelow() {
        return notifyAdminForQuantityBelow;
    }

    public void setNotifyAdminForQuantityBelow(Integer notifyAdminForQuantityBelow) {
        this.notifyAdminForQuantityBelow = notifyAdminForQuantityBelow;
    }

    public Boolean getAllowBackInStockSubscriptions() {
        return allowBackInStockSubscriptions;
    }

    public void setAllowBackInStockSubscriptions(Boolean allowBackInStockSubscriptions) {
        this.allowBackInStockSubscriptions = allowBackInStockSubscriptions;
    }

    public Integer getOrderMinimumQuantity() {
        return orderMinimumQuantity;
    }

    public void setOrderMinimumQuantity(Integer orderMinimumQuantity) {
        this.orderMinimumQuantity = orderMinimumQuantity;
    }

    public Integer getOrderMaximumQuantity() {
        return orderMaximumQuantity;
    }

    public void setOrderMaximumQuantity(Integer orderMaximumQuantity) {
        this.orderMaximumQuantity = orderMaximumQuantity;
    }

    public Boolean getAllowAddingOnlyExistingAttributeCombinations() {
        return allowAddingOnlyExistingAttributeCombinations;
    }

    public void setAllowAddingOnlyExistingAttributeCombinations(Boolean allowAddingOnlyExistingAttributeCombinations) {
        this.allowAddingOnlyExistingAttributeCombinations = allowAddingOnlyExistingAttributeCombinations;
    }

    public Boolean getDisableBuyButton() {
        return disableBuyButton;
    }

    public void setDisableBuyButton(Boolean disableBuyButton) {
        this.disableBuyButton = disableBuyButton;
    }

    public Boolean getDisableWishlistButton() {
        return disableWishlistButton;
    }

    public void setDisableWishlistButton(Boolean disableWishlistButton) {
        this.disableWishlistButton = disableWishlistButton;
    }

    public Boolean getAvailableForPreOrder() {
        return availableForPreOrder;
    }

    public void setAvailableForPreOrder(Boolean availableForPreOrder) {
        this.availableForPreOrder = availableForPreOrder;
    }

    public Boolean getCallForPrice() {
        return callForPrice;
    }

    public void setCallForPrice(Boolean callForPrice) {
        this.callForPrice = callForPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Integer getProductCost() {
        return productCost;
    }

    public void setProductCost(Integer productCost) {
        this.productCost = productCost;
    }

    public Boolean getCustomerEntersPrice() {
        return customerEntersPrice;
    }

    public void setCustomerEntersPrice(Boolean customerEntersPrice) {
        this.customerEntersPrice = customerEntersPrice;
    }

    public Double getMinimumCustomerEnteredPrice() {
        return minimumCustomerEnteredPrice;
    }

    public void setMinimumCustomerEnteredPrice(Double minimumCustomerEnteredPrice) {
        this.minimumCustomerEnteredPrice = minimumCustomerEnteredPrice;
    }

    public Double getMaximumCustomerEnteredPrice() {
        return maximumCustomerEnteredPrice;
    }

    public void setMaximumCustomerEnteredPrice(Double maximumCustomerEnteredPrice) {
        this.maximumCustomerEnteredPrice = maximumCustomerEnteredPrice;
    }

    public Boolean getBasepriceEnabled() {
        return basepriceEnabled;
    }

    public void setBasepriceEnabled(Boolean basepriceEnabled) {
        this.basepriceEnabled = basepriceEnabled;
    }

    public Double getBasepriceAmount() {
        return basepriceAmount;
    }

    public void setBasepriceAmount(Double basepriceAmount) {
        this.basepriceAmount = basepriceAmount;
    }

    public Double getBasepriceBaseAmount() {
        return basepriceBaseAmount;
    }

    public void setBasepriceBaseAmount(Double basepriceBaseAmount) {
        this.basepriceBaseAmount = basepriceBaseAmount;
    }

    public Boolean getHasTierPrices() {
        return hasTierPrices;
    }

    public void setHasTierPrices(Boolean hasTierPrices) {
        this.hasTierPrices = hasTierPrices;
    }

    public Boolean getHasDiscountsApplied() {
        return hasDiscountsApplied;
    }

    public void setHasDiscountsApplied(Boolean hasDiscountsApplied) {
        this.hasDiscountsApplied = hasDiscountsApplied;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getAvailableStartDateTimeUtc() {
        return availableStartDateTimeUtc;
    }

    public void setAvailableStartDateTimeUtc(String availableStartDateTimeUtc) {
        this.availableStartDateTimeUtc = availableStartDateTimeUtc;
    }

    public String getAvailableEndDateTimeUtc() {
        return availableEndDateTimeUtc;
    }

    public void setAvailableEndDateTimeUtc(String availableEndDateTimeUtc) {
        this.availableEndDateTimeUtc = availableEndDateTimeUtc;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getParentGroupedProductId() {
        return parentGroupedProductId;
    }

    public void setParentGroupedProductId(Integer parentGroupedProductId) {
        this.parentGroupedProductId = parentGroupedProductId;
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

    public List<Object> getManufacturerIds() {
        return manufacturerIds;
    }

    public void setManufacturerIds(List<Object> manufacturerIds) {
        this.manufacturerIds = manufacturerIds;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Object> getProductAttributeCombinations() {
        return productAttributeCombinations;
    }

    public void setProductAttributeCombinations(List<Object> productAttributeCombinations) {
        this.productAttributeCombinations = productAttributeCombinations;
    }

    public List<Object> getProductSpecificationAttributes() {
        return productSpecificationAttributes;
    }

    public void setProductSpecificationAttributes(List<Object> productSpecificationAttributes) {
        this.productSpecificationAttributes = productSpecificationAttributes;
    }

    public List<Integer> getAssociatedProductIds() {
        return associatedProductIds;
    }

    public void setAssociatedProductIds(List<Integer> associatedProductIds) {
        this.associatedProductIds = associatedProductIds;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
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

        if(images != null){

            if(images.size()>0){

                if(images.get(0).getSrc() != null){

                    url = images.get(0).getSrc();

                }

            }

        }

        return url;

    }

    public Double getDiscountPercentage(){

        return (100- ((price/oldPrice) * 100));

    }

    public String getLocalizedName(){

        String name="";

        if(localizedNames != null){

            if(localizedNames.size()>0){

                name = localizedNames.get(0).getLocalizedName();

            }
            else
            {
                name = this.name;
            }


        }
        else
        {
            name = this.name;
        }

        return name;

    }

    public String getLocalizedShortDescription(){

        String name="";

        if(localizedShortDescriptions != null){

            if(localizedShortDescriptions.size()>0){

                name = localizedShortDescriptions.get(0).getLocalizedShortDescription();

            }
            else
            {
                name = this.name;
            }

        }
        else
        {
            name = this.name;
        }

        return name;

    }

    public String getLocalizedFullDescription(){

        String name="";

        if(localizedFullDescriptions != null){

            if(localizedFullDescriptions.size()>0){

                name = localizedFullDescriptions.get(0).getLocalizedFullDescription();

            }

        }

        return name;

    }


}
