package app.flora.Models;

import java.util.ArrayList;
import java.util.List;

public class FeaturedProductsModel {


    private List<ProductsBean> products;

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    public static class ProductsBean {

        private boolean visible_individually;
        private String name;
        private String short_description;
        private String full_description;
        private String delivery_date_id;
        private boolean show_on_home_page;
        private Object meta_keywords;
        private Object meta_description;
        private Object meta_title;
        private boolean allow_customer_reviews;
        private int approved_rating_sum;
        private int not_approved_rating_sum;
        private int approved_total_reviews;
        private int not_approved_total_reviews;
        private Object sku;
        private Object manufacturer_part_number;
        private Object gtin;
        private boolean is_gift_card;
        private boolean require_other_products;
        private boolean automatically_add_required_products;
        private boolean is_download;
        private boolean unlimited_downloads;
        private int max_number_of_downloads;
        private Object download_expiration_days;
        private boolean has_sample_download;
        private boolean has_user_agreement;
        private boolean is_recurring;
        private int recurring_cycle_length;
        private int recurring_total_cycles;
        private boolean is_rental;
        private int rental_price_length;
        private boolean is_ship_enabled;
        private boolean is_free_shipping;
        private boolean ship_separately;
        private int additional_shipping_charge;
        private boolean is_tax_exempt;
        private boolean is_telecommunications_or_broadcasting_or_electronic_services;
        private boolean use_multiple_warehouses;
        private int manage_inventory_method_id;
        private int stock_quantity;
        private boolean display_stock_availability;
        private boolean display_stock_quantity;
        private int min_stock_quantity;
        private int sold_quantity;
        private String stock_availability;
        private int notify_admin_for_quantity_below;
        private boolean allow_back_in_stock_subscriptions;
        private int order_minimum_quantity;
        private int order_maximum_quantity;
        private Object allowed_quantities;
        private boolean allow_adding_only_existing_attribute_combinations;
        private boolean disable_buy_button;
        private boolean disable_wishlist_button;
        private boolean available_for_pre_order;
        private Object pre_order_availability_start_date_time_utc;
        private boolean call_for_price;
        private float price;
        private String formatted_price;
        private int old_price;
        private String formatted_old_price;
        private Object prices_percentage;
        private int product_cost;
        private Object special_price;
        private Object special_price_start_date_time_utc;
        private Object special_price_end_date_time_utc;
        private boolean customer_enters_price;
        private int minimum_customer_entered_price;
        private int maximum_customer_entered_price;
        private boolean baseprice_enabled;
        private int baseprice_amount;
        private int baseprice_base_amount;
        private boolean has_tier_prices;
        private boolean has_discounts_applied;
        private int weight;
        private int length;
        private int width;
        private int height;
        private Object available_start_date_time_utc;
        private Object available_end_date_time_utc;
        private int display_order;
        private boolean published;
        private boolean deleted;
        private String created_on_utc;
        private String updated_on_utc;
        private String product_type;
        private int parent_grouped_product_id;
        private int vendor_id;
        private VendorBean vendor;
        private String se_name;
        private int id;
        private List<LocalizedNamesBeanX> localized_names;
        private List<LocalizedShortDescriptionsBean> localized_short_descriptions;
        private List<LocalizedFullDescriptionsBean> localized_full_descriptions;
        private List<?> role_ids;
        private List<?> discount_ids;
        private List<?> store_ids;
        private List<?> manufacturer_ids;
        private List<ImagesBean> images;
        private List<AttributesBean> attributes;
        private List<?> product_attribute_combinations;
        private List<ProductSpecificationAttributesBean> product_specification_attributes;
        private List<?> associated_product_ids;
        private List<?> tags;


        public String getDelivery_date_id() {
            return delivery_date_id;
        }

        public void setDelivery_date_id(String delivery_date_id) {
            this.delivery_date_id = delivery_date_id;
        }

        public boolean isVisible_individually() {
            return visible_individually;
        }

        public void setVisible_individually(boolean visible_individually) {
            this.visible_individually = visible_individually;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShort_description() {
            return short_description;
        }

        public void setShort_description(String short_description) {
            this.short_description = short_description;
        }

        public String getFull_description() {
            return full_description;
        }

        public void setFull_description(String full_description) {
            this.full_description = full_description;
        }

        public boolean isShow_on_home_page() {
            return show_on_home_page;
        }

        public void setShow_on_home_page(boolean show_on_home_page) {
            this.show_on_home_page = show_on_home_page;
        }

        public Object getMeta_keywords() {
            return meta_keywords;
        }

        public void setMeta_keywords(Object meta_keywords) {
            this.meta_keywords = meta_keywords;
        }

        public Object getMeta_description() {
            return meta_description;
        }

        public void setMeta_description(Object meta_description) {
            this.meta_description = meta_description;
        }

        public Object getMeta_title() {
            return meta_title;
        }

        public void setMeta_title(Object meta_title) {
            this.meta_title = meta_title;
        }

        public boolean isAllow_customer_reviews() {
            return allow_customer_reviews;
        }

        public void setAllow_customer_reviews(boolean allow_customer_reviews) {
            this.allow_customer_reviews = allow_customer_reviews;
        }

        public int getApproved_rating_sum() {
            return approved_rating_sum;
        }

        public void setApproved_rating_sum(int approved_rating_sum) {
            this.approved_rating_sum = approved_rating_sum;
        }

        public int getNot_approved_rating_sum() {
            return not_approved_rating_sum;
        }

        public void setNot_approved_rating_sum(int not_approved_rating_sum) {
            this.not_approved_rating_sum = not_approved_rating_sum;
        }

        public int getApproved_total_reviews() {
            return approved_total_reviews;
        }

        public void setApproved_total_reviews(int approved_total_reviews) {
            this.approved_total_reviews = approved_total_reviews;
        }

        public int getNot_approved_total_reviews() {
            return not_approved_total_reviews;
        }

        public void setNot_approved_total_reviews(int not_approved_total_reviews) {
            this.not_approved_total_reviews = not_approved_total_reviews;
        }

        public Object getSku() {
            return sku;
        }

        public void setSku(Object sku) {
            this.sku = sku;
        }

        public Object getManufacturer_part_number() {
            return manufacturer_part_number;
        }

        public void setManufacturer_part_number(Object manufacturer_part_number) {
            this.manufacturer_part_number = manufacturer_part_number;
        }

        public Object getGtin() {
            return gtin;
        }

        public void setGtin(Object gtin) {
            this.gtin = gtin;
        }

        public boolean isIs_gift_card() {
            return is_gift_card;
        }

        public void setIs_gift_card(boolean is_gift_card) {
            this.is_gift_card = is_gift_card;
        }

        public boolean isRequire_other_products() {
            return require_other_products;
        }

        public void setRequire_other_products(boolean require_other_products) {
            this.require_other_products = require_other_products;
        }

        public boolean isAutomatically_add_required_products() {
            return automatically_add_required_products;
        }

        public void setAutomatically_add_required_products(boolean automatically_add_required_products) {
            this.automatically_add_required_products = automatically_add_required_products;
        }

        public boolean isIs_download() {
            return is_download;
        }

        public void setIs_download(boolean is_download) {
            this.is_download = is_download;
        }

        public boolean isUnlimited_downloads() {
            return unlimited_downloads;
        }

        public void setUnlimited_downloads(boolean unlimited_downloads) {
            this.unlimited_downloads = unlimited_downloads;
        }

        public int getMax_number_of_downloads() {
            return max_number_of_downloads;
        }

        public void setMax_number_of_downloads(int max_number_of_downloads) {
            this.max_number_of_downloads = max_number_of_downloads;
        }

        public Object getDownload_expiration_days() {
            return download_expiration_days;
        }

        public void setDownload_expiration_days(Object download_expiration_days) {
            this.download_expiration_days = download_expiration_days;
        }

        public boolean isHas_sample_download() {
            return has_sample_download;
        }

        public void setHas_sample_download(boolean has_sample_download) {
            this.has_sample_download = has_sample_download;
        }

        public boolean isHas_user_agreement() {
            return has_user_agreement;
        }

        public void setHas_user_agreement(boolean has_user_agreement) {
            this.has_user_agreement = has_user_agreement;
        }

        public boolean isIs_recurring() {
            return is_recurring;
        }

        public void setIs_recurring(boolean is_recurring) {
            this.is_recurring = is_recurring;
        }

        public int getRecurring_cycle_length() {
            return recurring_cycle_length;
        }

        public void setRecurring_cycle_length(int recurring_cycle_length) {
            this.recurring_cycle_length = recurring_cycle_length;
        }

        public int getRecurring_total_cycles() {
            return recurring_total_cycles;
        }

        public void setRecurring_total_cycles(int recurring_total_cycles) {
            this.recurring_total_cycles = recurring_total_cycles;
        }

        public boolean isIs_rental() {
            return is_rental;
        }

        public void setIs_rental(boolean is_rental) {
            this.is_rental = is_rental;
        }

        public int getRental_price_length() {
            return rental_price_length;
        }

        public void setRental_price_length(int rental_price_length) {
            this.rental_price_length = rental_price_length;
        }

        public boolean isIs_ship_enabled() {
            return is_ship_enabled;
        }

        public void setIs_ship_enabled(boolean is_ship_enabled) {
            this.is_ship_enabled = is_ship_enabled;
        }

        public boolean isIs_free_shipping() {
            return is_free_shipping;
        }

        public void setIs_free_shipping(boolean is_free_shipping) {
            this.is_free_shipping = is_free_shipping;
        }

        public boolean isShip_separately() {
            return ship_separately;
        }

        public void setShip_separately(boolean ship_separately) {
            this.ship_separately = ship_separately;
        }

        public int getAdditional_shipping_charge() {
            return additional_shipping_charge;
        }

        public void setAdditional_shipping_charge(int additional_shipping_charge) {
            this.additional_shipping_charge = additional_shipping_charge;
        }

        public boolean isIs_tax_exempt() {
            return is_tax_exempt;
        }

        public void setIs_tax_exempt(boolean is_tax_exempt) {
            this.is_tax_exempt = is_tax_exempt;
        }

        public boolean isIs_telecommunications_or_broadcasting_or_electronic_services() {
            return is_telecommunications_or_broadcasting_or_electronic_services;
        }

        public void setIs_telecommunications_or_broadcasting_or_electronic_services(boolean is_telecommunications_or_broadcasting_or_electronic_services) {
            this.is_telecommunications_or_broadcasting_or_electronic_services = is_telecommunications_or_broadcasting_or_electronic_services;
        }

        public boolean isUse_multiple_warehouses() {
            return use_multiple_warehouses;
        }

        public void setUse_multiple_warehouses(boolean use_multiple_warehouses) {
            this.use_multiple_warehouses = use_multiple_warehouses;
        }

        public int getManage_inventory_method_id() {
            return manage_inventory_method_id;
        }

        public void setManage_inventory_method_id(int manage_inventory_method_id) {
            this.manage_inventory_method_id = manage_inventory_method_id;
        }

        public int getStock_quantity() {
            return stock_quantity;
        }

        public void setStock_quantity(int stock_quantity) {
            this.stock_quantity = stock_quantity;
        }

        public boolean isDisplay_stock_availability() {
            return display_stock_availability;
        }

        public void setDisplay_stock_availability(boolean display_stock_availability) {
            this.display_stock_availability = display_stock_availability;
        }

        public boolean isDisplay_stock_quantity() {
            return display_stock_quantity;
        }

        public void setDisplay_stock_quantity(boolean display_stock_quantity) {
            this.display_stock_quantity = display_stock_quantity;
        }

        public int getMin_stock_quantity() {
            return min_stock_quantity;
        }

        public void setMin_stock_quantity(int min_stock_quantity) {
            this.min_stock_quantity = min_stock_quantity;
        }

        public int getSold_quantity() {
            return sold_quantity;
        }

        public void setSold_quantity(int sold_quantity) {
            this.sold_quantity = sold_quantity;
        }

        public String getStock_availability() {
            return stock_availability;
        }

        public void setStock_availability(String stock_availability) {
            this.stock_availability = stock_availability;
        }

        public int getNotify_admin_for_quantity_below() {
            return notify_admin_for_quantity_below;
        }

        public void setNotify_admin_for_quantity_below(int notify_admin_for_quantity_below) {
            this.notify_admin_for_quantity_below = notify_admin_for_quantity_below;
        }

        public boolean isAllow_back_in_stock_subscriptions() {
            return allow_back_in_stock_subscriptions;
        }

        public void setAllow_back_in_stock_subscriptions(boolean allow_back_in_stock_subscriptions) {
            this.allow_back_in_stock_subscriptions = allow_back_in_stock_subscriptions;
        }

        public int getOrder_minimum_quantity() {
            return order_minimum_quantity;
        }

        public void setOrder_minimum_quantity(int order_minimum_quantity) {
            this.order_minimum_quantity = order_minimum_quantity;
        }

        public int getOrder_maximum_quantity() {
            return order_maximum_quantity;
        }

        public void setOrder_maximum_quantity(int order_maximum_quantity) {
            this.order_maximum_quantity = order_maximum_quantity;
        }

        public Object getAllowed_quantities() {
            return allowed_quantities;
        }

        public void setAllowed_quantities(Object allowed_quantities) {
            this.allowed_quantities = allowed_quantities;
        }

        public boolean isAllow_adding_only_existing_attribute_combinations() {
            return allow_adding_only_existing_attribute_combinations;
        }

        public void setAllow_adding_only_existing_attribute_combinations(boolean allow_adding_only_existing_attribute_combinations) {
            this.allow_adding_only_existing_attribute_combinations = allow_adding_only_existing_attribute_combinations;
        }

        public boolean isDisable_buy_button() {
            return disable_buy_button;
        }

        public void setDisable_buy_button(boolean disable_buy_button) {
            this.disable_buy_button = disable_buy_button;
        }

        public boolean isDisable_wishlist_button() {
            return disable_wishlist_button;
        }

        public void setDisable_wishlist_button(boolean disable_wishlist_button) {
            this.disable_wishlist_button = disable_wishlist_button;
        }

        public boolean isAvailable_for_pre_order() {
            return available_for_pre_order;
        }

        public void setAvailable_for_pre_order(boolean available_for_pre_order) {
            this.available_for_pre_order = available_for_pre_order;
        }

        public Object getPre_order_availability_start_date_time_utc() {
            return pre_order_availability_start_date_time_utc;
        }

        public void setPre_order_availability_start_date_time_utc(Object pre_order_availability_start_date_time_utc) {
            this.pre_order_availability_start_date_time_utc = pre_order_availability_start_date_time_utc;
        }

        public boolean isCall_for_price() {
            return call_for_price;
        }

        public void setCall_for_price(boolean call_for_price) {
            this.call_for_price = call_for_price;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getFormatted_price() {
            return formatted_price;
        }

        public void setFormatted_price(String formatted_price) {
            this.formatted_price = formatted_price;
        }

        public int getOld_price() {
            return old_price;
        }

        public void setOld_price(int old_price) {
            this.old_price = old_price;
        }

        public String getFormatted_old_price() {
            return formatted_old_price;
        }

        public void setFormatted_old_price(String formatted_old_price) {
            this.formatted_old_price = formatted_old_price;
        }

        public Object getPrices_percentage() {
            return prices_percentage;
        }

        public void setPrices_percentage(Object prices_percentage) {
            this.prices_percentage = prices_percentage;
        }

        public int getProduct_cost() {
            return product_cost;
        }

        public void setProduct_cost(int product_cost) {
            this.product_cost = product_cost;
        }

        public Object getSpecial_price() {
            return special_price;
        }

        public void setSpecial_price(Object special_price) {
            this.special_price = special_price;
        }

        public Object getSpecial_price_start_date_time_utc() {
            return special_price_start_date_time_utc;
        }

        public void setSpecial_price_start_date_time_utc(Object special_price_start_date_time_utc) {
            this.special_price_start_date_time_utc = special_price_start_date_time_utc;
        }

        public Object getSpecial_price_end_date_time_utc() {
            return special_price_end_date_time_utc;
        }

        public void setSpecial_price_end_date_time_utc(Object special_price_end_date_time_utc) {
            this.special_price_end_date_time_utc = special_price_end_date_time_utc;
        }

        public boolean isCustomer_enters_price() {
            return customer_enters_price;
        }

        public void setCustomer_enters_price(boolean customer_enters_price) {
            this.customer_enters_price = customer_enters_price;
        }

        public int getMinimum_customer_entered_price() {
            return minimum_customer_entered_price;
        }

        public void setMinimum_customer_entered_price(int minimum_customer_entered_price) {
            this.minimum_customer_entered_price = minimum_customer_entered_price;
        }

        public int getMaximum_customer_entered_price() {
            return maximum_customer_entered_price;
        }

        public void setMaximum_customer_entered_price(int maximum_customer_entered_price) {
            this.maximum_customer_entered_price = maximum_customer_entered_price;
        }

        public boolean isBaseprice_enabled() {
            return baseprice_enabled;
        }

        public void setBaseprice_enabled(boolean baseprice_enabled) {
            this.baseprice_enabled = baseprice_enabled;
        }

        public int getBaseprice_amount() {
            return baseprice_amount;
        }

        public void setBaseprice_amount(int baseprice_amount) {
            this.baseprice_amount = baseprice_amount;
        }

        public int getBaseprice_base_amount() {
            return baseprice_base_amount;
        }

        public void setBaseprice_base_amount(int baseprice_base_amount) {
            this.baseprice_base_amount = baseprice_base_amount;
        }

        public boolean isHas_tier_prices() {
            return has_tier_prices;
        }

        public void setHas_tier_prices(boolean has_tier_prices) {
            this.has_tier_prices = has_tier_prices;
        }

        public boolean isHas_discounts_applied() {
            return has_discounts_applied;
        }

        public void setHas_discounts_applied(boolean has_discounts_applied) {
            this.has_discounts_applied = has_discounts_applied;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public Object getAvailable_start_date_time_utc() {
            return available_start_date_time_utc;
        }

        public void setAvailable_start_date_time_utc(Object available_start_date_time_utc) {
            this.available_start_date_time_utc = available_start_date_time_utc;
        }

        public Object getAvailable_end_date_time_utc() {
            return available_end_date_time_utc;
        }

        public void setAvailable_end_date_time_utc(Object available_end_date_time_utc) {
            this.available_end_date_time_utc = available_end_date_time_utc;
        }

        public int getDisplay_order() {
            return display_order;
        }

        public void setDisplay_order(int display_order) {
            this.display_order = display_order;
        }

        public boolean isPublished() {
            return published;
        }

        public void setPublished(boolean published) {
            this.published = published;
        }

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }

        public String getCreated_on_utc() {
            return created_on_utc;
        }

        public void setCreated_on_utc(String created_on_utc) {
            this.created_on_utc = created_on_utc;
        }

        public String getUpdated_on_utc() {
            return updated_on_utc;
        }

        public void setUpdated_on_utc(String updated_on_utc) {
            this.updated_on_utc = updated_on_utc;
        }

        public String getProduct_type() {
            return product_type;
        }

        public void setProduct_type(String product_type) {
            this.product_type = product_type;
        }

        public int getParent_grouped_product_id() {
            return parent_grouped_product_id;
        }

        public void setParent_grouped_product_id(int parent_grouped_product_id) {
            this.parent_grouped_product_id = parent_grouped_product_id;
        }

        public int getVendor_id() {
            return vendor_id;
        }

        public void setVendor_id(int vendor_id) {
            this.vendor_id = vendor_id;
        }

        public VendorBean getVendor() {
            return vendor;
        }

        public void setVendor(VendorBean vendor) {
            this.vendor = vendor;
        }

        public String getSe_name() {
            return se_name;
        }

        public void setSe_name(String se_name) {
            this.se_name = se_name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<LocalizedNamesBeanX> getLocalized_names() {
            return localized_names;
        }

        public String getLocalizedName(){

            String name="";

            if(localized_names != null){

                if(localized_names.size()>0){

                    name = localized_names.get(0).getLocalized_name();

                }

            }

            return name;

        }

        public void setLocalizedName(String name){

            LocalizedNamesBeanX localizedName = new LocalizedNamesBeanX();

            localizedName.setLocalized_name(name);

            if(localized_names == null){

                localized_names = new ArrayList<>();

            }

            localized_names.add(localizedName);

        }

        public void setLocalized_short_descriptions(List<LocalizedShortDescriptionsBean> localized_short_descriptions) {
            this.localized_short_descriptions = localized_short_descriptions;
        }

        public List<LocalizedFullDescriptionsBean> getLocalized_full_descriptions() {
            return localized_full_descriptions;
        }

        public void setLocalized_full_descriptions(List<LocalizedFullDescriptionsBean> localized_full_descriptions) {
            this.localized_full_descriptions = localized_full_descriptions;
        }

        public List<?> getRole_ids() {
            return role_ids;
        }

        public void setRole_ids(List<?> role_ids) {
            this.role_ids = role_ids;
        }

        public List<?> getDiscount_ids() {
            return discount_ids;
        }

        public void setDiscount_ids(List<?> discount_ids) {
            this.discount_ids = discount_ids;
        }

        public List<?> getStore_ids() {
            return store_ids;
        }

        public void setStore_ids(List<?> store_ids) {
            this.store_ids = store_ids;
        }

        public List<?> getManufacturer_ids() {
            return manufacturer_ids;
        }

        public void setManufacturer_ids(List<?> manufacturer_ids) {
            this.manufacturer_ids = manufacturer_ids;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }

        public List<AttributesBean> getAttributes() {
            return attributes;
        }

        public void setAttributes(List<AttributesBean> attributes) {
            this.attributes = attributes;
        }

        public List<?> getProduct_attribute_combinations() {
            return product_attribute_combinations;
        }

        public void setProduct_attribute_combinations(List<?> product_attribute_combinations) {
            this.product_attribute_combinations = product_attribute_combinations;
        }

        public List<ProductSpecificationAttributesBean> getProduct_specification_attributes() {
            return product_specification_attributes;
        }

        public void setProduct_specification_attributes(List<ProductSpecificationAttributesBean> product_specification_attributes) {
            this.product_specification_attributes = product_specification_attributes;
        }

        public List<?> getAssociated_product_ids() {
            return associated_product_ids;
        }

        public void setAssociated_product_ids(List<?> associated_product_ids) {
            this.associated_product_ids = associated_product_ids;
        }

        public List<?> getTags() {
            return tags;
        }

        public void setTags(List<?> tags) {
            this.tags = tags;
        }

        public static class VendorBean {

            private String name;
            private String email;
            private Object phone_number;
            private String description;
            private int picture_id;
            private Object coordinates;
            private Object meta_keywords;
            private Object meta_description;
            private Object meta_title;
            private boolean show_on_home_page;
            private boolean active;
            private boolean deleted;
            private ImageBean image;
            private String se_name;
            private int id;
            private List<LocalizedNamesBean> localized_names;
            private List<LocalizedDescriptionsBean> localized_descriptions;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public Object getPhone_number() {
                return phone_number;
            }

            public void setPhone_number(Object phone_number) {
                this.phone_number = phone_number;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getPicture_id() {
                return picture_id;
            }

            public void setPicture_id(int picture_id) {
                this.picture_id = picture_id;
            }

            public Object getCoordinates() {
                return coordinates;
            }

            public void setCoordinates(Object coordinates) {
                this.coordinates = coordinates;
            }

            public Object getMeta_keywords() {
                return meta_keywords;
            }

            public void setMeta_keywords(Object meta_keywords) {
                this.meta_keywords = meta_keywords;
            }

            public Object getMeta_description() {
                return meta_description;
            }

            public void setMeta_description(Object meta_description) {
                this.meta_description = meta_description;
            }

            public Object getMeta_title() {
                return meta_title;
            }

            public void setMeta_title(Object meta_title) {
                this.meta_title = meta_title;
            }

            public boolean isShow_on_home_page() {
                return show_on_home_page;
            }

            public void setShow_on_home_page(boolean show_on_home_page) {
                this.show_on_home_page = show_on_home_page;
            }

            public boolean isActive() {
                return active;
            }

            public void setActive(boolean active) {
                this.active = active;
            }

            public boolean isDeleted() {
                return deleted;
            }

            public void setDeleted(boolean deleted) {
                this.deleted = deleted;
            }

            public ImageBean getImage() {
                return image;
            }

            public void setImage(ImageBean image) {
                this.image = image;
            }

            public String getSe_name() {
                return se_name;
            }

            public void setSe_name(String se_name) {
                this.se_name = se_name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public List<LocalizedNamesBean> getLocalized_names() {
                return localized_names;
            }

            public void setLocalized_names(List<LocalizedNamesBean> localized_names) {
                this.localized_names = localized_names;
            }

            public List<LocalizedDescriptionsBean> getLocalized_descriptions() {
                return localized_descriptions;
            }

            public void setLocalized_descriptions(List<LocalizedDescriptionsBean> localized_descriptions) {
                this.localized_descriptions = localized_descriptions;
            }

            public static class ImageBean {
                /**
                 * src : http://flora.hardtask.info/images/thumbs/0000165_tree-rose.jpeg
                 * attachment : null
                 */

                private String src;
                private Object attachment;

                public String getSrc() {
                    return src;
                }

                public void setSrc(String src) {
                    this.src = src;
                }

                public Object getAttachment() {
                    return attachment;
                }

                public void setAttachment(Object attachment) {
                    this.attachment = attachment;
                }
            }

            public static class LocalizedNamesBean {
                /**
                 * language_id : 1
                 * localized_name : Tree Rose
                 */

                private int language_id;
                private String localized_name;

                public int getLanguage_id() {
                    return language_id;
                }

                public void setLanguage_id(int language_id) {
                    this.language_id = language_id;
                }

                public String getLocalized_name() {
                    return localized_name;
                }

                public void setLocalized_name(String localized_name) {
                    this.localized_name = localized_name;
                }
            }

            public static class LocalizedDescriptionsBean {
                /**
                 * language_id : 1
                 * localized_description : <p>Some description...</p>
                 */

                private int language_id;
                private String localized_description;

                public int getLanguage_id() {
                    return language_id;
                }

                public void setLanguage_id(int language_id) {
                    this.language_id = language_id;
                }

                public String getLocalized_description() {
                    return localized_description;
                }

                public void setLocalized_description(String localized_description) {
                    this.localized_description = localized_description;
                }
            }
        }

        public static class LocalizedNamesBeanX {
            /**
             * language_id : 1
             * localized_name : Splashed Pearls
             */

            private int language_id;
            private String localized_name;

            public int getLanguage_id() {
                return language_id;
            }

            public void setLanguage_id(int language_id) {
                this.language_id = language_id;
            }

            public String getLocalized_name() {
                return localized_name;
            }

            public void setLocalized_name(String localized_name) {
                this.localized_name = localized_name;
            }
        }

        public static class LocalizedShortDescriptionsBean {
            /**
             * language_id : 1
             * localized_short_description : A huge hand bouquet that contains a mixture of white flowers with a small Laderach chocolates box.
             */

            private int language_id;
            private String localized_short_description;

            public int getLanguage_id() {
                return language_id;
            }

            public void setLanguage_id(int language_id) {
                this.language_id = language_id;
            }

            public String getLocalized_short_description() {
                return localized_short_description;
            }

            public void setLocalized_short_description(String localized_short_description) {
                this.localized_short_description = localized_short_description;
            }
        }

        public static class LocalizedFullDescriptionsBean {

            private int language_id;
            private String localized_full_description;

            public int getLanguage_id() {
                return language_id;
            }

            public void setLanguage_id(int language_id) {
                this.language_id = language_id;
            }

            public String getLocalized_full_description() {
                return localized_full_description;
            }

            public void setLocalized_full_description(String localized_full_description) {
                this.localized_full_description = localized_full_description;
            }
        }

        public static class ImagesBean {

            private int id;
            private int picture_id;
            private int position;
            private String src;
            private Object attachment;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPicture_id() {
                return picture_id;
            }

            public void setPicture_id(int picture_id) {
                this.picture_id = picture_id;
            }

            public int getPosition() {
                return position;
            }

            public void setPosition(int position) {
                this.position = position;
            }

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }

            public Object getAttachment() {
                return attachment;
            }

            public void setAttachment(Object attachment) {
                this.attachment = attachment;
            }
        }

        public static class AttributesBean {

            private int product_attribute_id;
            private String product_attribute_name;
            private Object text_prompt;
            private boolean is_required;
            private int attribute_control_type_id;
            private int display_order;
            private Object default_value;
            private String attribute_control_type_name;
            private int id;
            private List<LocalizedNamesBeanXX> localized_names;
            private List<?> attribute_values;

            public int getProduct_attribute_id() {
                return product_attribute_id;
            }

            public void setProduct_attribute_id(int product_attribute_id) {
                this.product_attribute_id = product_attribute_id;
            }

            public String getProduct_attribute_name() {
                return product_attribute_name;
            }

            public void setProduct_attribute_name(String product_attribute_name) {
                this.product_attribute_name = product_attribute_name;
            }

            public Object getText_prompt() {
                return text_prompt;
            }

            public void setText_prompt(Object text_prompt) {
                this.text_prompt = text_prompt;
            }

            public boolean isIs_required() {
                return is_required;
            }

            public void setIs_required(boolean is_required) {
                this.is_required = is_required;
            }

            public int getAttribute_control_type_id() {
                return attribute_control_type_id;
            }

            public void setAttribute_control_type_id(int attribute_control_type_id) {
                this.attribute_control_type_id = attribute_control_type_id;
            }

            public int getDisplay_order() {
                return display_order;
            }

            public void setDisplay_order(int display_order) {
                this.display_order = display_order;
            }

            public Object getDefault_value() {
                return default_value;
            }

            public void setDefault_value(Object default_value) {
                this.default_value = default_value;
            }

            public String getAttribute_control_type_name() {
                return attribute_control_type_name;
            }

            public void setAttribute_control_type_name(String attribute_control_type_name) {
                this.attribute_control_type_name = attribute_control_type_name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public List<LocalizedNamesBeanXX> getLocalized_names() {
                return localized_names;
            }

            public void setLocalized_names(List<LocalizedNamesBeanXX> localized_names) {
                this.localized_names = localized_names;
            }

            public List<?> getAttribute_values() {
                return attribute_values;
            }

            public void setAttribute_values(List<?> attribute_values) {
                this.attribute_values = attribute_values;
            }

            public static class LocalizedNamesBeanXX {
                /**
                 * language_id : 1
                 * localized_name : Delivery date
                 */

                private int language_id;
                private String localized_name;

                public int getLanguage_id() {
                    return language_id;
                }

                public void setLanguage_id(int language_id) {
                    this.language_id = language_id;
                }

                public String getLocalized_name() {
                    return localized_name;
                }

                public void setLocalized_name(String localized_name) {
                    this.localized_name = localized_name;
                }
            }
        }

        public static class ProductSpecificationAttributesBean {
            /**
             * id : 31
             * product_id : 1
             * attribute_type_id : 0
             * specification_attribute_option_id : 3
             * custom_value : null
             * allow_filtering : true
             * show_on_product_page : false
             * display_order : 0
             * attribute_type : Option
             * specification_attribute_option : {"id":3,"specification_attribute_id":1,"name":"Orchid","color_squares_rgb":null,"display_order":0}
             */

            private int id;
            private int product_id;
            private int attribute_type_id;
            private int specification_attribute_option_id;
            private Object custom_value;
            private boolean allow_filtering;
            private boolean show_on_product_page;
            private int display_order;
            private String attribute_type;
            private SpecificationAttributeOptionBean specification_attribute_option;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public int getAttribute_type_id() {
                return attribute_type_id;
            }

            public void setAttribute_type_id(int attribute_type_id) {
                this.attribute_type_id = attribute_type_id;
            }

            public int getSpecification_attribute_option_id() {
                return specification_attribute_option_id;
            }

            public void setSpecification_attribute_option_id(int specification_attribute_option_id) {
                this.specification_attribute_option_id = specification_attribute_option_id;
            }

            public Object getCustom_value() {
                return custom_value;
            }

            public void setCustom_value(Object custom_value) {
                this.custom_value = custom_value;
            }

            public boolean isAllow_filtering() {
                return allow_filtering;
            }

            public void setAllow_filtering(boolean allow_filtering) {
                this.allow_filtering = allow_filtering;
            }

            public boolean isShow_on_product_page() {
                return show_on_product_page;
            }

            public void setShow_on_product_page(boolean show_on_product_page) {
                this.show_on_product_page = show_on_product_page;
            }

            public int getDisplay_order() {
                return display_order;
            }

            public void setDisplay_order(int display_order) {
                this.display_order = display_order;
            }

            public String getAttribute_type() {
                return attribute_type;
            }

            public void setAttribute_type(String attribute_type) {
                this.attribute_type = attribute_type;
            }

            public SpecificationAttributeOptionBean getSpecification_attribute_option() {
                return specification_attribute_option;
            }

            public void setSpecification_attribute_option(SpecificationAttributeOptionBean specification_attribute_option) {
                this.specification_attribute_option = specification_attribute_option;
            }

            public static class SpecificationAttributeOptionBean {
                /**
                 * id : 3
                 * specification_attribute_id : 1
                 * name : Orchid
                 * color_squares_rgb : null
                 * display_order : 0
                 */

                private int id;
                private int specification_attribute_id;
                private String name;
                private Object color_squares_rgb;
                private int display_order;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getSpecification_attribute_id() {
                    return specification_attribute_id;
                }

                public void setSpecification_attribute_id(int specification_attribute_id) {
                    this.specification_attribute_id = specification_attribute_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public Object getColor_squares_rgb() {
                    return color_squares_rgb;
                }

                public void setColor_squares_rgb(Object color_squares_rgb) {
                    this.color_squares_rgb = color_squares_rgb;
                }

                public int getDisplay_order() {
                    return display_order;
                }

                public void setDisplay_order(int display_order) {
                    this.display_order = display_order;
                }
            }
        }
    }
}
