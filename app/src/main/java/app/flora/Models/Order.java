package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Order {

    @SerializedName("shipping_address_id")
    @Expose
    private Integer shipping_address_id;
    @SerializedName("shipping_method")
    @Expose
    private String shipping_method;
    @SerializedName("shipping_rate_computation_method_system_name")
    @Expose
    private String shipping_rate_computation_method_system_name;
    @SerializedName("billing_address_id")
    @Expose
    private Integer billingAddressId;
    @SerializedName("payment_by")
    @Expose
    private Integer payment_by;
    @SerializedName("use_reward_points")
    @Expose
    private String use_reward_points;

    @SerializedName("used_reward_points")
    @Expose
    private String used_reward_points;

    @SerializedName("store_id")
    @Expose
    private Integer storeId;
    @SerializedName("pick_up_in_store")
    @Expose
    private Boolean pickUpInStore;
    @SerializedName("payment_method_system_name")
    @Expose
    private String paymentMethodSystemName;
    @SerializedName("customer_currency_code")
    @Expose
    private String customerCurrencyCode;
    @SerializedName("currency_rate")
    @Expose
    private Integer currencyRate;
    @SerializedName("customer_tax_display_type_id")
    @Expose
    private Integer customerTaxDisplayTypeId;
    @SerializedName("vat_number")
    @Expose
    private Object vatNumber;
    @SerializedName("order_subtotal_incl_tax")
    @Expose
    private Double orderSubtotalInclTax;
    @SerializedName("order_subtotal_excl_tax")
    @Expose
    private Double orderSubtotalExclTax;
    @SerializedName("order_sub_total_discount_incl_tax")
    @Expose
    private Double orderSubTotalDiscountInclTax;
    @SerializedName("order_sub_total_discount_excl_tax")
    @Expose
    private Double orderSubTotalDiscountExclTax;
    @SerializedName("order_shipping_incl_tax")
    @Expose
    private Double orderShippingInclTax;
    @SerializedName("order_shipping_excl_tax")
    @Expose
    private Double orderShippingExclTax;
    @SerializedName("payment_method_additional_fee_incl_tax")
    @Expose
    private Double paymentMethodAdditionalFeeInclTax;
    @SerializedName("payment_method_additional_fee_excl_tax")
    @Expose
    private Double paymentMethodAdditionalFeeExclTax;
    @SerializedName("tax_rates")
    @Expose
    private String taxRates;
    @SerializedName("order_tax")
    @Expose
    private Double orderTax;
    @SerializedName("order_discount")
    @Expose
    private Double orderDiscount;
    @SerializedName("order_total")
    @Expose
    private Double orderTotal;
    @SerializedName("refunded_amount")
    @Expose
    private Double refundedAmount;
    @SerializedName("reward_points_were_added")
    @Expose
    private Object rewardPointsWereAdded;
    @SerializedName("checkout_attribute_description")
    @Expose
    private String checkoutAttributeDescription;
    @SerializedName("customer_language_id")
    @Expose
    private Integer customerLanguageId;
    @SerializedName("affiliate_id")
    @Expose
    private Integer affiliateId;
    @SerializedName("customer_ip")
    @Expose
    private String customerIp;
    @SerializedName("authorization_transaction_id")
    @Expose
    private String authorizationTransactionId;
    @SerializedName("authorization_transaction_code")
    @Expose
    private Object authorizationTransactionCode;
    @SerializedName("authorization_transaction_result")
    @Expose
    private Object authorizationTransactionResult;
    @SerializedName("capture_transaction_id")
    @Expose
    private Object captureTransactionId;
    @SerializedName("capture_transaction_result")
    @Expose
    private Object captureTransactionResult;
    @SerializedName("subscription_transaction_id")
    @Expose
    private Object subscriptionTransactionId;
    @SerializedName("paid_date_utc")
    @Expose
    private String paidDateUtc;
    @SerializedName("custom_values_xml")
    @Expose
    private Object customValuesXml;
    @SerializedName("deleted")
    @Expose
    private Boolean deleted;
    @SerializedName("created_on_utc")
    @Expose
    private String createdOnUtc;
    @SerializedName("customer")
    @Expose
    private Customer customer;
    @SerializedName("customer_id")
    @Expose
    private Integer customerId;
    @SerializedName("billing_address")
    @Expose
    private Address billingAddress;
    @SerializedName("shipping_address")
    @Expose
    private Object shippingAddress;
    @SerializedName("order_items")
    @Expose
    private ArrayList<OrderItem> orderItems = null;
    @SerializedName("order_status")
    @Expose
    private String orderStatus;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;
    @SerializedName("shipping_status")
    @Expose
    private String shippingStatus;
    @SerializedName("customer_tax_display_type")
    @Expose
    private String customerTaxDisplayType;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getUsed_reward_points() {
        return used_reward_points;
    }

    public void setUsed_reward_points(String used_reward_points) {
        this.used_reward_points = used_reward_points;
    }

    public String getShipping_method() {
        return shipping_method;
    }

    public void setShipping_method(String shipping_method) {
        this.shipping_method = shipping_method;
    }

    public String getShipping_rate_computation_method_system_name() {
        return shipping_rate_computation_method_system_name;
    }

    public void setShipping_rate_computation_method_system_name(String shipping_rate_computation_method_system_name) {
        this.shipping_rate_computation_method_system_name = shipping_rate_computation_method_system_name;
    }

    public Integer getShipping_address_id() {
        return shipping_address_id;
    }

    public void setShipping_address_id(Integer shipping_address_id) {
        this.shipping_address_id = shipping_address_id;
    }

    public Integer getBillingAddressId() {
        return billingAddressId;
    }

    public void setBillingAddressId(Integer billingAddressId) {
        this.billingAddressId = billingAddressId;
    }

    public Integer getPayment_by() {
        return payment_by;
    }

    public void setPayment_by(Integer payment_by) {
        this.payment_by = payment_by;
    }

    public String getUse_reward_points() {
        return use_reward_points;
    }

    public void setUse_reward_points(String use_reward_points) {
        this.use_reward_points = use_reward_points;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Boolean getPickUpInStore() {
        return pickUpInStore;
    }

    public void setPickUpInStore(Boolean pickUpInStore) {
        this.pickUpInStore = pickUpInStore;
    }

    public String getPaymentMethodSystemName() {
        return paymentMethodSystemName;
    }

    public void setPaymentMethodSystemName(String paymentMethodSystemName) {
        this.paymentMethodSystemName = paymentMethodSystemName;
    }

    public String getCustomerCurrencyCode() {
        return customerCurrencyCode;
    }

    public void setCustomerCurrencyCode(String customerCurrencyCode) {
        this.customerCurrencyCode = customerCurrencyCode;
    }

    public Integer getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(Integer currencyRate) {
        this.currencyRate = currencyRate;
    }

    public Integer getCustomerTaxDisplayTypeId() {
        return customerTaxDisplayTypeId;
    }

    public void setCustomerTaxDisplayTypeId(Integer customerTaxDisplayTypeId) {
        this.customerTaxDisplayTypeId = customerTaxDisplayTypeId;
    }

    public Object getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(Object vatNumber) {
        this.vatNumber = vatNumber;
    }

    public Double getOrderSubtotalInclTax() {
        return orderSubtotalInclTax;
    }

    public void setOrderSubtotalInclTax(Double orderSubtotalInclTax) {
        this.orderSubtotalInclTax = orderSubtotalInclTax;
    }

    public Double getOrderSubtotalExclTax() {
        return orderSubtotalExclTax;
    }

    public void setOrderSubtotalExclTax(Double orderSubtotalExclTax) {
        this.orderSubtotalExclTax = orderSubtotalExclTax;
    }

    public Double getOrderSubTotalDiscountInclTax() {
        return orderSubTotalDiscountInclTax;
    }

    public void setOrderSubTotalDiscountInclTax(Double orderSubTotalDiscountInclTax) {
        this.orderSubTotalDiscountInclTax = orderSubTotalDiscountInclTax;
    }

    public Double getOrderSubTotalDiscountExclTax() {
        return orderSubTotalDiscountExclTax;
    }

    public void setOrderSubTotalDiscountExclTax(Double orderSubTotalDiscountExclTax) {
        this.orderSubTotalDiscountExclTax = orderSubTotalDiscountExclTax;
    }

    public Double getOrderShippingInclTax() {
        return orderShippingInclTax;
    }

    public void setOrderShippingInclTax(Double orderShippingInclTax) {
        this.orderShippingInclTax = orderShippingInclTax;
    }

    public Double getOrderShippingExclTax() {
        return orderShippingExclTax;
    }

    public void setOrderShippingExclTax(Double orderShippingExclTax) {
        this.orderShippingExclTax = orderShippingExclTax;
    }

    public Double getPaymentMethodAdditionalFeeInclTax() {
        return paymentMethodAdditionalFeeInclTax;
    }

    public void setPaymentMethodAdditionalFeeInclTax(Double paymentMethodAdditionalFeeInclTax) {
        this.paymentMethodAdditionalFeeInclTax = paymentMethodAdditionalFeeInclTax;
    }

    public Double getPaymentMethodAdditionalFeeExclTax() {
        return paymentMethodAdditionalFeeExclTax;
    }

    public void setPaymentMethodAdditionalFeeExclTax(Double paymentMethodAdditionalFeeExclTax) {
        this.paymentMethodAdditionalFeeExclTax = paymentMethodAdditionalFeeExclTax;
    }

    public String getTaxRates() {
        return taxRates;
    }

    public void setTaxRates(String taxRates) {
        this.taxRates = taxRates;
    }

    public Double getOrderTax() {
        return orderTax;
    }

    public void setOrderTax(Double orderTax) {
        this.orderTax = orderTax;
    }

    public Double getOrderDiscount() {
        return orderDiscount;
    }

    public void setOrderDiscount(Double orderDiscount) {
        this.orderDiscount = orderDiscount;
    }

    public Double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Double getRefundedAmount() {
        return refundedAmount;
    }

    public void setRefundedAmount(Double refundedAmount) {
        this.refundedAmount = refundedAmount;
    }

    public Object getRewardPointsWereAdded() {
        return rewardPointsWereAdded;
    }

    public void setRewardPointsWereAdded(Object rewardPointsWereAdded) {
        this.rewardPointsWereAdded = rewardPointsWereAdded;
    }

    public String getCheckoutAttributeDescription() {
        return checkoutAttributeDescription;
    }

    public void setCheckoutAttributeDescription(String checkoutAttributeDescription) {
        this.checkoutAttributeDescription = checkoutAttributeDescription;
    }

    public Integer getCustomerLanguageId() {
        return customerLanguageId;
    }

    public void setCustomerLanguageId(Integer customerLanguageId) {
        this.customerLanguageId = customerLanguageId;
    }

    public Integer getAffiliateId() {
        return affiliateId;
    }

    public void setAffiliateId(Integer affiliateId) {
        this.affiliateId = affiliateId;
    }

    public String getCustomerIp() {
        return customerIp;
    }

    public void setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
    }

    public String getAuthorizationTransactionId() {
        return authorizationTransactionId;
    }

    public void setAuthorizationTransactionId(String authorizationTransactionId) {
        this.authorizationTransactionId = authorizationTransactionId;
    }

    public Object getAuthorizationTransactionCode() {
        return authorizationTransactionCode;
    }

    public void setAuthorizationTransactionCode(Object authorizationTransactionCode) {
        this.authorizationTransactionCode = authorizationTransactionCode;
    }

    public Object getAuthorizationTransactionResult() {
        return authorizationTransactionResult;
    }

    public void setAuthorizationTransactionResult(Object authorizationTransactionResult) {
        this.authorizationTransactionResult = authorizationTransactionResult;
    }

    public Object getCaptureTransactionId() {
        return captureTransactionId;
    }

    public void setCaptureTransactionId(Object captureTransactionId) {
        this.captureTransactionId = captureTransactionId;
    }

    public Object getCaptureTransactionResult() {
        return captureTransactionResult;
    }

    public void setCaptureTransactionResult(Object captureTransactionResult) {
        this.captureTransactionResult = captureTransactionResult;
    }

    public Object getSubscriptionTransactionId() {
        return subscriptionTransactionId;
    }

    public void setSubscriptionTransactionId(Object subscriptionTransactionId) {
        this.subscriptionTransactionId = subscriptionTransactionId;
    }

    public String getPaidDateUtc() {
        return paidDateUtc;
    }

    public void setPaidDateUtc(String paidDateUtc) {
        this.paidDateUtc = paidDateUtc;
    }

    public Object getCustomValuesXml() {
        return customValuesXml;
    }

    public void setCustomValuesXml(Object customValuesXml) {
        this.customValuesXml = customValuesXml;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Object getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Object shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public String getCustomerTaxDisplayType() {
        return customerTaxDisplayType;
    }

    public void setCustomerTaxDisplayType(String customerTaxDisplayType) {
        this.customerTaxDisplayType = customerTaxDisplayType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
