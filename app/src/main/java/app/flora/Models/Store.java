package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Store {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("ssl_enabled")
    @Expose
    private Boolean sslEnabled;
    @SerializedName("secure_url")
    @Expose
    private Object secureUrl;
    @SerializedName("primary_weight_id")
    @Expose
    private String primary_weight_id;
    @SerializedName("hosts")
    @Expose
    private String hosts;
    @SerializedName("default_language_id")
    @Expose
    private Integer defaultLanguageId;
    @SerializedName("measure_weights")
    @Expose
    private List<MeasureWeight> measure_weights = null;

    @SerializedName("store_languages")
    @Expose
    private List<StoreLanguage> storeLanguages = null;
    @SerializedName("display_order")
    @Expose
    private Integer displayOrder;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("company_address")
    @Expose
    private String companyAddress;
    @SerializedName("company_phone_number")
    @Expose
    private String companyPhoneNumber;
    @SerializedName("company_vat")
    @Expose
    private Object companyVat;
    @SerializedName("primary_currency_id")
    @Expose
    private Integer primaryCurrencyId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("store_currencies")
    @Expose
    private List<StoreCurrency> storeCurrencies = null;
    @SerializedName("store_payment_methods")
    @Expose
    private List<Payment> store_payment_methods = null;

    public List<StoreCurrency> getStoreCurrencies() {
        return storeCurrencies;
    }

    public List<Payment> getStore_payment_methods() {
        return store_payment_methods;
    }

    public void setStore_payment_methods(List<Payment> store_payment_methods) {
        this.store_payment_methods = store_payment_methods;
    }

    public void setStoreCurrencies(List<StoreCurrency> storeCurrencies) {
        this.storeCurrencies = storeCurrencies;
    }

    public String getPrimary_weight_id() {
        return primary_weight_id;
    }

    public void setPrimary_weight_id(String primary_weight_id) {
        this.primary_weight_id = primary_weight_id;
    }

    public List<MeasureWeight> getMeasure_weights() {
        return measure_weights;
    }

    public void setMeasure_weights(List<MeasureWeight> measure_weights) {
        this.measure_weights = measure_weights;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getSslEnabled() {
        return sslEnabled;
    }

    public void setSslEnabled(Boolean sslEnabled) {
        this.sslEnabled = sslEnabled;
    }

    public Object getSecureUrl() {
        return secureUrl;
    }

    public void setSecureUrl(Object secureUrl) {
        this.secureUrl = secureUrl;
    }

    public String getHosts() {
        return hosts;
    }

    public void setHosts(String hosts) {
        this.hosts = hosts;
    }

    public Integer getDefaultLanguageId() {
        return defaultLanguageId;
    }

    public void setDefaultLanguageId(Integer defaultLanguageId) {
        this.defaultLanguageId = defaultLanguageId;
    }

    public List<StoreLanguage> getStoreLanguages() {
        return storeLanguages;
    }

    public void setStoreLanguages(List<StoreLanguage> storeLanguages) {
        this.storeLanguages = storeLanguages;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyPhoneNumber() {
        return companyPhoneNumber;
    }

    public void setCompanyPhoneNumber(String companyPhoneNumber) {
        this.companyPhoneNumber = companyPhoneNumber;
    }

    public Object getCompanyVat() {
        return companyVat;
    }

    public void setCompanyVat(Object companyVat) {
        this.companyVat = companyVat;
    }

    public Integer getPrimaryCurrencyId() {
        return primaryCurrencyId;
    }

    public void setPrimaryCurrencyId(Integer primaryCurrencyId) {
        this.primaryCurrencyId = primaryCurrencyId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



}
