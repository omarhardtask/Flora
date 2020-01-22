package app.flora.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cards {


    @SerializedName("purchased_with_order_item_id")
    @Expose
    private Integer purchasedWithOrderItemId;
    @SerializedName("card_type_id")
    @Expose
    private Integer cardTypeId;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("card_coupon_code")
    @Expose
    private String cardCouponCode;
    @SerializedName("is_card_activated")
    @Expose
    private Boolean isCardActivated;
    @SerializedName("is_redeemed")
    @Expose
    private Boolean isRedeemed;
    @SerializedName("created_on_utc")
    @Expose
    private String createdOnUtc;
    @SerializedName("redeemed_on_utc")
    @Expose
    private String redeemedOnUtc;
    @SerializedName("id")
    @Expose
    private Integer id;

    public Integer getPurchasedWithOrderItemId() {
        return purchasedWithOrderItemId;
    }

    public void setPurchasedWithOrderItemId(Integer purchasedWithOrderItemId) {
        this.purchasedWithOrderItemId = purchasedWithOrderItemId;
    }

    public Integer getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(Integer cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCardCouponCode() {
        return cardCouponCode;
    }

    public void setCardCouponCode(String cardCouponCode) {
        this.cardCouponCode = cardCouponCode;
    }

    public Boolean getIsCardActivated() {
        return isCardActivated;
    }

    public void setIsCardActivated(Boolean isCardActivated) {
        this.isCardActivated = isCardActivated;
    }

    public Boolean getIsRedeemed() {
        return isRedeemed;
    }

    public void setIsRedeemed(Boolean isRedeemed) {
        this.isRedeemed = isRedeemed;
    }

    public String getCreatedOnUtc() {
        return createdOnUtc;
    }

    public void setCreatedOnUtc(String createdOnUtc) {
        this.createdOnUtc = createdOnUtc;
    }

    public String getRedeemedOnUtc() {
        return redeemedOnUtc;
    }

    public void setRedeemedOnUtc(String redeemedOnUtc) {
        this.redeemedOnUtc = redeemedOnUtc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
