package app.flora.Models;

import java.util.List;

public class OrderDelete {


    private List<OrdersBean> orders;

    public List<OrdersBean> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersBean> orders) {
        this.orders = orders;
    }

    public static class OrdersBean {
        private int store_id;
        private boolean pick_up_in_store;
        private String payment_method_system_name;
        private boolean use_reward_points;
        private String customer_currency_code;
        private int currency_rate;
        private int customer_tax_display_type_id;
        private Object vat_number;
        private int order_subtotal_incl_tax;
        private int order_subtotal_excl_tax;
        private int order_sub_total_discount_incl_tax;
        private int order_sub_total_discount_excl_tax;
        private int order_shipping_incl_tax;
        private int order_shipping_excl_tax;
        private int payment_method_additional_fee_incl_tax;
        private int payment_method_additional_fee_excl_tax;
        private String tax_rates;
        private int order_tax;
        private int order_discount;
        private Double order_total;
        private int refunded_amount;
        private Object reward_points_were_added;
        private String checkout_attribute_description;
        private int customer_language_id;
        private int affiliate_id;
        private String customer_ip;
        private Object authorization_transaction_id;
        private Object authorization_transaction_code;
        private Object authorization_transaction_result;
        private Object capture_transaction_id;
        private Object capture_transaction_result;
        private Object subscription_transaction_id;
        private Object paid_date_utc;
        private String shipping_method;
        private String shipping_rate_computation_method_system_name;
        private Object custom_values_xml;
        private boolean deleted;
        private String created_on_utc;
        private CustomerBean customer;
        private int customer_id;
        private BillingAddressBean billing_address;
        private int billing_address_id;
        private ShippingAddressBean shipping_address;
        private int shipping_address_id;
        private String order_status;
        private String payment_status;
        private String shipping_status;
        private String customer_tax_display_type;
        private int id;
        private List<OrderItemsBean> order_items;

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public boolean isPick_up_in_store() {
            return pick_up_in_store;
        }

        public void setPick_up_in_store(boolean pick_up_in_store) {
            this.pick_up_in_store = pick_up_in_store;
        }

        public String getPayment_method_system_name() {
            return payment_method_system_name;
        }

        public void setPayment_method_system_name(String payment_method_system_name) {
            this.payment_method_system_name = payment_method_system_name;
        }

        public boolean isUse_reward_points() {
            return use_reward_points;
        }

        public void setUse_reward_points(boolean use_reward_points) {
            this.use_reward_points = use_reward_points;
        }

        public String getCustomer_currency_code() {
            return customer_currency_code;
        }

        public void setCustomer_currency_code(String customer_currency_code) {
            this.customer_currency_code = customer_currency_code;
        }

        public int getCurrency_rate() {
            return currency_rate;
        }

        public void setCurrency_rate(int currency_rate) {
            this.currency_rate = currency_rate;
        }

        public int getCustomer_tax_display_type_id() {
            return customer_tax_display_type_id;
        }

        public void setCustomer_tax_display_type_id(int customer_tax_display_type_id) {
            this.customer_tax_display_type_id = customer_tax_display_type_id;
        }

        public Object getVat_number() {
            return vat_number;
        }

        public void setVat_number(Object vat_number) {
            this.vat_number = vat_number;
        }

        public int getOrder_subtotal_incl_tax() {
            return order_subtotal_incl_tax;
        }

        public void setOrder_subtotal_incl_tax(int order_subtotal_incl_tax) {
            this.order_subtotal_incl_tax = order_subtotal_incl_tax;
        }

        public int getOrder_subtotal_excl_tax() {
            return order_subtotal_excl_tax;
        }

        public void setOrder_subtotal_excl_tax(int order_subtotal_excl_tax) {
            this.order_subtotal_excl_tax = order_subtotal_excl_tax;
        }

        public int getOrder_sub_total_discount_incl_tax() {
            return order_sub_total_discount_incl_tax;
        }

        public void setOrder_sub_total_discount_incl_tax(int order_sub_total_discount_incl_tax) {
            this.order_sub_total_discount_incl_tax = order_sub_total_discount_incl_tax;
        }

        public int getOrder_sub_total_discount_excl_tax() {
            return order_sub_total_discount_excl_tax;
        }

        public void setOrder_sub_total_discount_excl_tax(int order_sub_total_discount_excl_tax) {
            this.order_sub_total_discount_excl_tax = order_sub_total_discount_excl_tax;
        }

        public int getOrder_shipping_incl_tax() {
            return order_shipping_incl_tax;
        }

        public void setOrder_shipping_incl_tax(int order_shipping_incl_tax) {
            this.order_shipping_incl_tax = order_shipping_incl_tax;
        }

        public int getOrder_shipping_excl_tax() {
            return order_shipping_excl_tax;
        }

        public void setOrder_shipping_excl_tax(int order_shipping_excl_tax) {
            this.order_shipping_excl_tax = order_shipping_excl_tax;
        }

        public int getPayment_method_additional_fee_incl_tax() {
            return payment_method_additional_fee_incl_tax;
        }

        public void setPayment_method_additional_fee_incl_tax(int payment_method_additional_fee_incl_tax) {
            this.payment_method_additional_fee_incl_tax = payment_method_additional_fee_incl_tax;
        }

        public int getPayment_method_additional_fee_excl_tax() {
            return payment_method_additional_fee_excl_tax;
        }

        public void setPayment_method_additional_fee_excl_tax(int payment_method_additional_fee_excl_tax) {
            this.payment_method_additional_fee_excl_tax = payment_method_additional_fee_excl_tax;
        }

        public String getTax_rates() {
            return tax_rates;
        }

        public void setTax_rates(String tax_rates) {
            this.tax_rates = tax_rates;
        }

        public int getOrder_tax() {
            return order_tax;
        }

        public void setOrder_tax(int order_tax) {
            this.order_tax = order_tax;
        }

        public int getOrder_discount() {
            return order_discount;
        }

        public void setOrder_discount(int order_discount) {
            this.order_discount = order_discount;
        }

        public Double getOrder_total() {
            return order_total;
        }

        public void setOrder_total(Double order_total) {
            this.order_total = order_total;
        }

        public int getRefunded_amount() {
            return refunded_amount;
        }

        public void setRefunded_amount(int refunded_amount) {
            this.refunded_amount = refunded_amount;
        }

        public Object getReward_points_were_added() {
            return reward_points_were_added;
        }

        public void setReward_points_were_added(Object reward_points_were_added) {
            this.reward_points_were_added = reward_points_were_added;
        }

        public String getCheckout_attribute_description() {
            return checkout_attribute_description;
        }

        public void setCheckout_attribute_description(String checkout_attribute_description) {
            this.checkout_attribute_description = checkout_attribute_description;
        }

        public int getCustomer_language_id() {
            return customer_language_id;
        }

        public void setCustomer_language_id(int customer_language_id) {
            this.customer_language_id = customer_language_id;
        }

        public int getAffiliate_id() {
            return affiliate_id;
        }

        public void setAffiliate_id(int affiliate_id) {
            this.affiliate_id = affiliate_id;
        }

        public String getCustomer_ip() {
            return customer_ip;
        }

        public void setCustomer_ip(String customer_ip) {
            this.customer_ip = customer_ip;
        }

        public Object getAuthorization_transaction_id() {
            return authorization_transaction_id;
        }

        public void setAuthorization_transaction_id(Object authorization_transaction_id) {
            this.authorization_transaction_id = authorization_transaction_id;
        }

        public Object getAuthorization_transaction_code() {
            return authorization_transaction_code;
        }

        public void setAuthorization_transaction_code(Object authorization_transaction_code) {
            this.authorization_transaction_code = authorization_transaction_code;
        }

        public Object getAuthorization_transaction_result() {
            return authorization_transaction_result;
        }

        public void setAuthorization_transaction_result(Object authorization_transaction_result) {
            this.authorization_transaction_result = authorization_transaction_result;
        }

        public Object getCapture_transaction_id() {
            return capture_transaction_id;
        }

        public void setCapture_transaction_id(Object capture_transaction_id) {
            this.capture_transaction_id = capture_transaction_id;
        }

        public Object getCapture_transaction_result() {
            return capture_transaction_result;
        }

        public void setCapture_transaction_result(Object capture_transaction_result) {
            this.capture_transaction_result = capture_transaction_result;
        }

        public Object getSubscription_transaction_id() {
            return subscription_transaction_id;
        }

        public void setSubscription_transaction_id(Object subscription_transaction_id) {
            this.subscription_transaction_id = subscription_transaction_id;
        }

        public Object getPaid_date_utc() {
            return paid_date_utc;
        }

        public void setPaid_date_utc(Object paid_date_utc) {
            this.paid_date_utc = paid_date_utc;
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

        public Object getCustom_values_xml() {
            return custom_values_xml;
        }

        public void setCustom_values_xml(Object custom_values_xml) {
            this.custom_values_xml = custom_values_xml;
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

        public CustomerBean getCustomer() {
            return customer;
        }

        public void setCustomer(CustomerBean customer) {
            this.customer = customer;
        }

        public int getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(int customer_id) {
            this.customer_id = customer_id;
        }

        public BillingAddressBean getBilling_address() {
            return billing_address;
        }

        public void setBilling_address(BillingAddressBean billing_address) {
            this.billing_address = billing_address;
        }

        public int getBilling_address_id() {
            return billing_address_id;
        }

        public void setBilling_address_id(int billing_address_id) {
            this.billing_address_id = billing_address_id;
        }

        public ShippingAddressBean getShipping_address() {
            return shipping_address;
        }

        public void setShipping_address(ShippingAddressBean shipping_address) {
            this.shipping_address = shipping_address;
        }

        public int getShipping_address_id() {
            return shipping_address_id;
        }

        public void setShipping_address_id(int shipping_address_id) {
            this.shipping_address_id = shipping_address_id;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getPayment_status() {
            return payment_status;
        }

        public void setPayment_status(String payment_status) {
            this.payment_status = payment_status;
        }

        public String getShipping_status() {
            return shipping_status;
        }

        public void setShipping_status(String shipping_status) {
            this.shipping_status = shipping_status;
        }

        public String getCustomer_tax_display_type() {
            return customer_tax_display_type;
        }

        public void setCustomer_tax_display_type(String customer_tax_display_type) {
            this.customer_tax_display_type = customer_tax_display_type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<OrderItemsBean> getOrder_items() {
            return order_items;
        }

        public void setOrder_items(List<OrderItemsBean> order_items) {
            this.order_items = order_items;
        }

        public static class CustomerBean {
            /**
             * customer_guid : 8c182bf1-bb6e-4fb1-ab41-23981ef50dbc
             * username : admin@hardtask.com
             * email : admin@hardtask.com
             * first_name : Mohamed
             * last_name : Abdulrahim
             * phone : null
             * language_id : 1
             * date_of_birth : null
             * gender : M
             * admin_comment : null
             * is_tax_exempt : false
             * vendor_id : 0
             * has_shopping_cart_items : false
             * active : true
             * deleted : false
             * is_system_account : false
             * system_name : null
             * last_ip_address : 37.231.31.203
             * created_on_utc : 2019-05-20T12:36:00.4281614
             * last_login_date_utc : 2020-01-15T07:33:22.7890305
             * last_activity_date_utc : 2020-01-15T08:27:38.3119485
             * registered_in_store_id : 1
             * subscribed_to_newsletter : false
             * role_ids : [1,2,3]
             * id : 1
             */

            private String customer_guid;
            private String username;
            private String email;
            private String first_name;
            private String last_name;
            private Object phone;
            private String language_id;
            private Object date_of_birth;
            private String gender;
            private Object admin_comment;
            private boolean is_tax_exempt;
            private int vendor_id;
            private boolean has_shopping_cart_items;
            private boolean active;
            private boolean deleted;
            private boolean is_system_account;
            private Object system_name;
            private String last_ip_address;
            private String created_on_utc;
            private String last_login_date_utc;
            private String last_activity_date_utc;
            private int registered_in_store_id;
            private boolean subscribed_to_newsletter;
            private int id;
            private List<Integer> role_ids;

            public String getCustomer_guid() {
                return customer_guid;
            }

            public void setCustomer_guid(String customer_guid) {
                this.customer_guid = customer_guid;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getFirst_name() {
                return first_name;
            }

            public void setFirst_name(String first_name) {
                this.first_name = first_name;
            }

            public String getLast_name() {
                return last_name;
            }

            public void setLast_name(String last_name) {
                this.last_name = last_name;
            }

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public String getLanguage_id() {
                return language_id;
            }

            public void setLanguage_id(String language_id) {
                this.language_id = language_id;
            }

            public Object getDate_of_birth() {
                return date_of_birth;
            }

            public void setDate_of_birth(Object date_of_birth) {
                this.date_of_birth = date_of_birth;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public Object getAdmin_comment() {
                return admin_comment;
            }

            public void setAdmin_comment(Object admin_comment) {
                this.admin_comment = admin_comment;
            }

            public boolean isIs_tax_exempt() {
                return is_tax_exempt;
            }

            public void setIs_tax_exempt(boolean is_tax_exempt) {
                this.is_tax_exempt = is_tax_exempt;
            }

            public int getVendor_id() {
                return vendor_id;
            }

            public void setVendor_id(int vendor_id) {
                this.vendor_id = vendor_id;
            }

            public boolean isHas_shopping_cart_items() {
                return has_shopping_cart_items;
            }

            public void setHas_shopping_cart_items(boolean has_shopping_cart_items) {
                this.has_shopping_cart_items = has_shopping_cart_items;
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

            public boolean isIs_system_account() {
                return is_system_account;
            }

            public void setIs_system_account(boolean is_system_account) {
                this.is_system_account = is_system_account;
            }

            public Object getSystem_name() {
                return system_name;
            }

            public void setSystem_name(Object system_name) {
                this.system_name = system_name;
            }

            public String getLast_ip_address() {
                return last_ip_address;
            }

            public void setLast_ip_address(String last_ip_address) {
                this.last_ip_address = last_ip_address;
            }

            public String getCreated_on_utc() {
                return created_on_utc;
            }

            public void setCreated_on_utc(String created_on_utc) {
                this.created_on_utc = created_on_utc;
            }

            public String getLast_login_date_utc() {
                return last_login_date_utc;
            }

            public void setLast_login_date_utc(String last_login_date_utc) {
                this.last_login_date_utc = last_login_date_utc;
            }

            public String getLast_activity_date_utc() {
                return last_activity_date_utc;
            }

            public void setLast_activity_date_utc(String last_activity_date_utc) {
                this.last_activity_date_utc = last_activity_date_utc;
            }

            public int getRegistered_in_store_id() {
                return registered_in_store_id;
            }

            public void setRegistered_in_store_id(int registered_in_store_id) {
                this.registered_in_store_id = registered_in_store_id;
            }

            public boolean isSubscribed_to_newsletter() {
                return subscribed_to_newsletter;
            }

            public void setSubscribed_to_newsletter(boolean subscribed_to_newsletter) {
                this.subscribed_to_newsletter = subscribed_to_newsletter;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public List<Integer> getRole_ids() {
                return role_ids;
            }

            public void setRole_ids(List<Integer> role_ids) {
                this.role_ids = role_ids;
            }
        }

        public static class BillingAddressBean {
            /**
             * first_name : Mohamed
             * last_name : Abdulrahim
             * email : admin@hardtask.com
             * company : null
             * country_id : 143
             * country : Kuwait
             * state_province_id : 76
             * city : null
             * block : 1
             * address1 : 12
             * address2 : Extra
             * zip_postal_code : null
             * phone_number : 98765432
             * fax_number : null
             * customer_attributes :
             * created_on_utc : 2019-05-20T12:36:00.4502734
             * province : Abdullah Al-Salem
             * id : 42
             */

            private String first_name;
            private String last_name;
            private String email;
            private Object company;
            private int country_id;
            private String country;
            private int state_province_id;
            private Object city;
            private String block;
            private String address1;
            private String address2;
            private Object zip_postal_code;
            private String phone_number;
            private Object fax_number;
            private String customer_attributes;
            private String created_on_utc;
            private String province;
            private int id;

            public String getFirst_name() {
                return first_name;
            }

            public void setFirst_name(String first_name) {
                this.first_name = first_name;
            }

            public String getLast_name() {
                return last_name;
            }

            public void setLast_name(String last_name) {
                this.last_name = last_name;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public Object getCompany() {
                return company;
            }

            public void setCompany(Object company) {
                this.company = company;
            }

            public int getCountry_id() {
                return country_id;
            }

            public void setCountry_id(int country_id) {
                this.country_id = country_id;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public int getState_province_id() {
                return state_province_id;
            }

            public void setState_province_id(int state_province_id) {
                this.state_province_id = state_province_id;
            }

            public Object getCity() {
                return city;
            }

            public void setCity(Object city) {
                this.city = city;
            }

            public String getBlock() {
                return block;
            }

            public void setBlock(String block) {
                this.block = block;
            }

            public String getAddress1() {
                return address1;
            }

            public void setAddress1(String address1) {
                this.address1 = address1;
            }

            public String getAddress2() {
                return address2;
            }

            public void setAddress2(String address2) {
                this.address2 = address2;
            }

            public Object getZip_postal_code() {
                return zip_postal_code;
            }

            public void setZip_postal_code(Object zip_postal_code) {
                this.zip_postal_code = zip_postal_code;
            }

            public String getPhone_number() {
                return phone_number;
            }

            public void setPhone_number(String phone_number) {
                this.phone_number = phone_number;
            }

            public Object getFax_number() {
                return fax_number;
            }

            public void setFax_number(Object fax_number) {
                this.fax_number = fax_number;
            }

            public String getCustomer_attributes() {
                return customer_attributes;
            }

            public void setCustomer_attributes(String customer_attributes) {
                this.customer_attributes = customer_attributes;
            }

            public String getCreated_on_utc() {
                return created_on_utc;
            }

            public void setCreated_on_utc(String created_on_utc) {
                this.created_on_utc = created_on_utc;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }

        public static class ShippingAddressBean {
            /**
             * first_name : Mohamed
             * last_name : Abdulrahim
             * email : admin@hardtask.com
             * company : null
             * country_id : 143
             * country : Kuwait
             * state_province_id : 76
             * city : null
             * block : 1
             * address1 : 12
             * address2 : Extra
             * zip_postal_code : null
             * phone_number : 98765432
             * fax_number : null
             * customer_attributes :
             * created_on_utc : 2019-05-20T12:36:00.4502734
             * province : Abdullah Al-Salem
             * id : 43
             */

            private String first_name;
            private String last_name;
            private String email;
            private Object company;
            private int country_id;
            private String country;
            private int state_province_id;
            private Object city;
            private String block;
            private String address1;
            private String address2;
            private Object zip_postal_code;
            private String phone_number;
            private Object fax_number;
            private String customer_attributes;
            private String created_on_utc;
            private String province;
            private int id;

            public String getFirst_name() {
                return first_name;
            }

            public void setFirst_name(String first_name) {
                this.first_name = first_name;
            }

            public String getLast_name() {
                return last_name;
            }

            public void setLast_name(String last_name) {
                this.last_name = last_name;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public Object getCompany() {
                return company;
            }

            public void setCompany(Object company) {
                this.company = company;
            }

            public int getCountry_id() {
                return country_id;
            }

            public void setCountry_id(int country_id) {
                this.country_id = country_id;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public int getState_province_id() {
                return state_province_id;
            }

            public void setState_province_id(int state_province_id) {
                this.state_province_id = state_province_id;
            }

            public Object getCity() {
                return city;
            }

            public void setCity(Object city) {
                this.city = city;
            }

            public String getBlock() {
                return block;
            }

            public void setBlock(String block) {
                this.block = block;
            }

            public String getAddress1() {
                return address1;
            }

            public void setAddress1(String address1) {
                this.address1 = address1;
            }

            public String getAddress2() {
                return address2;
            }

            public void setAddress2(String address2) {
                this.address2 = address2;
            }

            public Object getZip_postal_code() {
                return zip_postal_code;
            }

            public void setZip_postal_code(Object zip_postal_code) {
                this.zip_postal_code = zip_postal_code;
            }

            public String getPhone_number() {
                return phone_number;
            }

            public void setPhone_number(String phone_number) {
                this.phone_number = phone_number;
            }

            public Object getFax_number() {
                return fax_number;
            }

            public void setFax_number(Object fax_number) {
                this.fax_number = fax_number;
            }

            public String getCustomer_attributes() {
                return customer_attributes;
            }

            public void setCustomer_attributes(String customer_attributes) {
                this.customer_attributes = customer_attributes;
            }

            public String getCreated_on_utc() {
                return created_on_utc;
            }

            public void setCreated_on_utc(String created_on_utc) {
                this.created_on_utc = created_on_utc;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }

        public static class OrderItemsBean {

            private int quantity;
            private int unit_price_incl_tax;
            private int unit_price_excl_tax;
            private int price_incl_tax;
            private int price_excl_tax;
            private int discount_amount_incl_tax;
            private int discount_amount_excl_tax;
            private int original_product_cost;
            private String attribute_description;
            private int download_count;
            private boolean isDownload_activated;
            private int license_download_id;
            private int item_weight;
            private Object rental_start_date_utc;
            private Object rental_end_date_utc;
            private ProductBean product;
            private int product_id;
            private int id;
            private List<ProductAttributesBean> product_attributes;
            private List<?> gift_cards;

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public int getUnit_price_incl_tax() {
                return unit_price_incl_tax;
            }

            public void setUnit_price_incl_tax(int unit_price_incl_tax) {
                this.unit_price_incl_tax = unit_price_incl_tax;
            }

            public int getUnit_price_excl_tax() {
                return unit_price_excl_tax;
            }

            public void setUnit_price_excl_tax(int unit_price_excl_tax) {
                this.unit_price_excl_tax = unit_price_excl_tax;
            }

            public int getPrice_incl_tax() {
                return price_incl_tax;
            }

            public void setPrice_incl_tax(int price_incl_tax) {
                this.price_incl_tax = price_incl_tax;
            }

            public int getPrice_excl_tax() {
                return price_excl_tax;
            }

            public void setPrice_excl_tax(int price_excl_tax) {
                this.price_excl_tax = price_excl_tax;
            }

            public int getDiscount_amount_incl_tax() {
                return discount_amount_incl_tax;
            }

            public void setDiscount_amount_incl_tax(int discount_amount_incl_tax) {
                this.discount_amount_incl_tax = discount_amount_incl_tax;
            }

            public int getDiscount_amount_excl_tax() {
                return discount_amount_excl_tax;
            }

            public void setDiscount_amount_excl_tax(int discount_amount_excl_tax) {
                this.discount_amount_excl_tax = discount_amount_excl_tax;
            }

            public int getOriginal_product_cost() {
                return original_product_cost;
            }

            public void setOriginal_product_cost(int original_product_cost) {
                this.original_product_cost = original_product_cost;
            }

            public String getAttribute_description() {
                return attribute_description;
            }

            public void setAttribute_description(String attribute_description) {
                this.attribute_description = attribute_description;
            }

            public int getDownload_count() {
                return download_count;
            }

            public void setDownload_count(int download_count) {
                this.download_count = download_count;
            }

            public boolean isIsDownload_activated() {
                return isDownload_activated;
            }

            public void setIsDownload_activated(boolean isDownload_activated) {
                this.isDownload_activated = isDownload_activated;
            }

            public int getLicense_download_id() {
                return license_download_id;
            }

            public void setLicense_download_id(int license_download_id) {
                this.license_download_id = license_download_id;
            }

            public int getItem_weight() {
                return item_weight;
            }

            public void setItem_weight(int item_weight) {
                this.item_weight = item_weight;
            }

            public Object getRental_start_date_utc() {
                return rental_start_date_utc;
            }

            public void setRental_start_date_utc(Object rental_start_date_utc) {
                this.rental_start_date_utc = rental_start_date_utc;
            }

            public Object getRental_end_date_utc() {
                return rental_end_date_utc;
            }

            public void setRental_end_date_utc(Object rental_end_date_utc) {
                this.rental_end_date_utc = rental_end_date_utc;
            }

            public ProductBean getProduct() {
                return product;
            }

            public void setProduct(ProductBean product) {
                this.product = product;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public List<ProductAttributesBean> getProduct_attributes() {
                return product_attributes;
            }

            public void setProduct_attributes(List<ProductAttributesBean> product_attributes) {
                this.product_attributes = product_attributes;
            }

            public List<?> getGift_cards() {
                return gift_cards;
            }

            public void setGift_cards(List<?> gift_cards) {
                this.gift_cards = gift_cards;
            }

            public static class ProductBean {
                /**
                 * visible_individually : true
                 * name : Oryte
                 * localized_names : [{"language_id":1,"localized_name":"Oryte"}]
                 * short_description : This bouquet contain flowers and chocolates.
                 * localized_short_descriptions : [{"language_id":1,"localized_short_description":"This bouquet contain flowers and chocolates."}]
                 * full_description : &lt;link href=&#39;https://dealatcity.com/Themes/Cobone_01/Content/css/my-theme.css&#39; rel=&#39;stylesheet&#39; type=&#39;text/css&#39; /&gt;
                 * localized_full_descriptions : [{"language_id":1,"localized_full_description":"<link href='https://dealatcity.com/Themes/Cobone_01/Content/css/my-theme.css' rel='stylesheet' type='text/css' />"}]
                 * show_on_home_page : true
                 * meta_keywords : null
                 * meta_description : null
                 * meta_title : null
                 * allow_customer_reviews : true
                 * approved_rating_sum : 4
                 * not_approved_rating_sum : 0
                 * approved_total_reviews : 1
                 * not_approved_total_reviews : 0
                 * sku : null
                 * manufacturer_part_number : null
                 * gtin : null
                 * is_gift_card : false
                 * require_other_products : false
                 * automatically_add_required_products : false
                 * is_download : false
                 * unlimited_downloads : false
                 * max_number_of_downloads : 0
                 * download_expiration_days : null
                 * has_sample_download : false
                 * has_user_agreement : false
                 * is_recurring : false
                 * recurring_cycle_length : 0
                 * recurring_total_cycles : 0
                 * is_rental : false
                 * rental_price_length : 0
                 * is_ship_enabled : true
                 * is_free_shipping : false
                 * ship_separately : false
                 * additional_shipping_charge : 0
                 * is_tax_exempt : false
                 * is_telecommunications_or_broadcasting_or_electronic_services : false
                 * use_multiple_warehouses : false
                 * manage_inventory_method_id : 1
                 * stock_quantity : 9999
                 * display_stock_availability : true
                 * display_stock_quantity : false
                 * min_stock_quantity : 0
                 * sold_quantity : 1
                 * stock_availability : In stock
                 * notify_admin_for_quantity_below : 1
                 * allow_back_in_stock_subscriptions : false
                 * order_minimum_quantity : 1
                 * order_maximum_quantity : 10000
                 * allowed_quantities : null
                 * allow_adding_only_existing_attribute_combinations : false
                 * disable_buy_button : false
                 * disable_wishlist_button : false
                 * available_for_pre_order : false
                 * pre_order_availability_start_date_time_utc : null
                 * call_for_price : false
                 * price : 18
                 * formatted_price : KD18.000
                 * old_price : 0
                 * formatted_old_price : KD0.000
                 * prices_percentage : null
                 * product_cost : 0
                 * special_price : null
                 * special_price_start_date_time_utc : null
                 * special_price_end_date_time_utc : null
                 * customer_enters_price : false
                 * minimum_customer_entered_price : 0
                 * maximum_customer_entered_price : 0
                 * baseprice_enabled : false
                 * baseprice_amount : 0
                 * baseprice_base_amount : 0
                 * has_tier_prices : false
                 * has_discounts_applied : false
                 * weight : 3
                 * length : 3
                 * width : 2
                 * height : 2
                 * available_start_date_time_utc : null
                 * available_end_date_time_utc : null
                 * display_order : 0
                 * published : true
                 * deleted : false
                 * created_on_utc : 2019-05-20T12:36:06.7274716
                 * updated_on_utc : 2019-12-26T10:03:27.9789092
                 * product_type : SimpleProduct
                 * parent_grouped_product_id : 0
                 * role_ids : []
                 * discount_ids : []
                 * store_ids : []
                 * manufacturer_ids : []
                 * images : [{"id":60,"picture_id":103,"position":0,"src":"http://flora.hardtask.info/images/thumbs/0000103_oryte.jpeg","attachment":null}]
                 * attributes : [{"product_attribute_id":1,"product_attribute_name":"Delivery date","localized_names":[{"language_id":1,"localized_name":"Delivery date"}],"text_prompt":null,"is_required":true,"attribute_control_type_id":20,"display_order":0,"default_value":null,"attribute_control_type_name":"Datepicker","attribute_values":[],"id":7},{"product_attribute_id":2,"product_attribute_name":"Delivery time","localized_names":[{"language_id":1,"localized_name":"Delivery time"}],"text_prompt":null,"is_required":true,"attribute_control_type_id":2,"display_order":0,"default_value":null,"attribute_control_type_name":"RadioList","attribute_values":[{"type_id":0,"associated_product_id":0,"name":"Between 10:00am - 12:00pm","localized_names":[{"language_id":1,"localized_name":"Between 10:00am - 12:00pm"}],"color_squares_rgb":null,"image_squares_image":null,"price_adjustment":0,"price_after_adjustment":"KD18.000","weight_adjustment":0,"cost":0,"quantity":0,"is_pre_selected":false,"display_order":0,"product_image_id":null,"type":"Simple","id":19},{"type_id":0,"associated_product_id":0,"name":"Between 12:00pm - 02:00pm","localized_names":[{"language_id":1,"localized_name":"Between 12:00pm - 02:00pm"}],"color_squares_rgb":null,"image_squares_image":null,"price_adjustment":0,"price_after_adjustment":"KD18.000","weight_adjustment":0,"cost":0,"quantity":0,"is_pre_selected":false,"display_order":1,"product_image_id":null,"type":"Simple","id":20},{"type_id":0,"associated_product_id":0,"name":"Between 02:00pm - 04:00pm","localized_names":[{"language_id":1,"localized_name":"Between 02:00pm - 04:00pm"}],"color_squares_rgb":null,"image_squares_image":null,"price_adjustment":0,"price_after_adjustment":"KD18.000","weight_adjustment":0,"cost":0,"quantity":0,"is_pre_selected":false,"display_order":2,"product_image_id":null,"type":"Simple","id":21},{"type_id":0,"associated_product_id":0,"name":"Between 04:00pm - 06:00pm","localized_names":[{"language_id":1,"localized_name":"Between 04:00pm - 06:00pm"}],"color_squares_rgb":null,"image_squares_image":null,"price_adjustment":0,"price_after_adjustment":"KD18.000","weight_adjustment":0,"cost":0,"quantity":0,"is_pre_selected":false,"display_order":3,"product_image_id":null,"type":"Simple","id":22},{"type_id":0,"associated_product_id":0,"name":"Between 06:00pm - 08:00pm","localized_names":[{"language_id":1,"localized_name":"Between 06:00pm - 08:00pm"}],"color_squares_rgb":null,"image_squares_image":null,"price_adjustment":0,"price_after_adjustment":"KD18.000","weight_adjustment":0,"cost":0,"quantity":0,"is_pre_selected":false,"display_order":4,"product_image_id":null,"type":"Simple","id":23},{"type_id":0,"associated_product_id":0,"name":"Between 08:00pm - 10:00pm","localized_names":[{"language_id":1,"localized_name":"Between 08:00pm - 10:00pm"}],"color_squares_rgb":null,"image_squares_image":null,"price_adjustment":0,"price_after_adjustment":"KD18.000","weight_adjustment":0,"cost":0,"quantity":0,"is_pre_selected":false,"display_order":5,"product_image_id":null,"type":"Simple","id":24}],"id":8}]
                 * product_attribute_combinations : []
                 * product_specification_attributes : [{"id":35,"product_id":4,"attribute_type_id":0,"specification_attribute_option_id":3,"custom_value":null,"allow_filtering":true,"show_on_product_page":false,"display_order":0,"attribute_type":"Option","specification_attribute_option":{"id":3,"specification_attribute_id":1,"name":"Orchid","color_squares_rgb":null,"display_order":0}},{"id":36,"product_id":4,"attribute_type_id":0,"specification_attribute_option_id":7,"custom_value":null,"allow_filtering":true,"show_on_product_page":false,"display_order":0,"attribute_type":"Option","specification_attribute_option":{"id":7,"specification_attribute_id":1,"name":"Others","color_squares_rgb":null,"display_order":0}}]
                 * associated_product_ids : []
                 * tags : []
                 * vendor_id : 1
                 * vendor : {"name":"Tree Rose","localized_names":[{"language_id":1,"localized_name":"Tree Rose"}],"email":"tree_rose@gmail.com","phone_number":null,"description":"<p>Some description...<\/p>","localized_descriptions":[{"language_id":1,"localized_description":"<p>Some description...<\/p>"}],"picture_id":165,"coordinates":null,"meta_keywords":null,"meta_description":null,"meta_title":null,"show_on_home_page":true,"active":true,"deleted":false,"image":{"src":"http://flora.hardtask.info/images/thumbs/0000165_tree-rose.jpeg","attachment":null},"se_name":"tree-rose","id":1}
                 * se_name : oryte
                 * id : 4
                 */

                private boolean visible_individually;
                private String name;
                private String short_description;
                private String full_description;
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
                private int price;
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
                private Double weight;
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

                public int getPrice() {
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

                public Double getWeight() {
                    return weight;
                }

                public void setWeight(Double weight) {
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

                public void setLocalized_names(List<LocalizedNamesBeanX> localized_names) {
                    this.localized_names = localized_names;
                }

                public List<LocalizedShortDescriptionsBean> getLocalized_short_descriptions() {
                    return localized_short_descriptions;
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
                    /**
                     * name : Tree Rose
                     * localized_names : [{"language_id":1,"localized_name":"Tree Rose"}]
                     * email : tree_rose@gmail.com
                     * phone_number : null
                     * description : <p>Some description...</p>
                     * localized_descriptions : [{"language_id":1,"localized_description":"<p>Some description...<\/p>"}]
                     * picture_id : 165
                     * coordinates : null
                     * meta_keywords : null
                     * meta_description : null
                     * meta_title : null
                     * show_on_home_page : true
                     * active : true
                     * deleted : false
                     * image : {"src":"http://flora.hardtask.info/images/thumbs/0000165_tree-rose.jpeg","attachment":null}
                     * se_name : tree-rose
                     * id : 1
                     */

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
                     * localized_name : Oryte
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
                     * localized_short_description : This bouquet contain flowers and chocolates.
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
                    /**
                     * language_id : 1
                     * localized_full_description : <link href='https://dealatcity.com/Themes/Cobone_01/Content/css/my-theme.css' rel='stylesheet' type='text/css' />
                     */

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
                    /**
                     * id : 60
                     * picture_id : 103
                     * position : 0
                     * src : http://flora.hardtask.info/images/thumbs/0000103_oryte.jpeg
                     * attachment : null
                     */

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
                    /**
                     * product_attribute_id : 1
                     * product_attribute_name : Delivery date
                     * localized_names : [{"language_id":1,"localized_name":"Delivery date"}]
                     * text_prompt : null
                     * is_required : true
                     * attribute_control_type_id : 20
                     * display_order : 0
                     * default_value : null
                     * attribute_control_type_name : Datepicker
                     * attribute_values : []
                     * id : 7
                     */

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
                     * id : 35
                     * product_id : 4
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

            public static class ProductAttributesBean {
                /**
                 * code : 0
                 * value : 14 يناير, 2020
                 * id : 7
                 */

                private int code;
                private String value;
                private int id;

                public int getCode() {
                    return code;
                }

                public void setCode(int code) {
                    this.code = code;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }
        }
    }
}
