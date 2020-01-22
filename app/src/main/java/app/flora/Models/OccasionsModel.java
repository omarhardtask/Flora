package app.flora.Models;

import java.util.List;

public class OccasionsModel {


    private List<ManufacturersBean> manufacturers;

    public List<ManufacturersBean> getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(List<ManufacturersBean> manufacturers) {
        this.manufacturers = manufacturers;
    }

    public static class ManufacturersBean {

        private String name;
        private Object description;
        private int manufacturer_template_id;
        private Object meta_keywords;
        private Object meta_description;
        private Object meta_title;
        private int picture_id;
        private int page_size;
        private boolean allow_customers_to_select_page_size;
        private String page_size_options;
        private Object price_ranges;
        private boolean subject_to_acl;
        private boolean limited_to_stores;
        private boolean published;
        private boolean deleted;
        private int display_order;
        private String created_on_utc;
        private String updated_on_utc;
        private ImageBean image;
        private String se_name;
        private int id;
        private List<LocalizedNamesBean> localized_names;
        private List<LocalizedDescriptionsBean> localized_descriptions;
        private List<?> role_ids;
        private List<?> discount_ids;
        private List<?> store_ids;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public int getManufacturer_template_id() {
            return manufacturer_template_id;
        }

        public void setManufacturer_template_id(int manufacturer_template_id) {
            this.manufacturer_template_id = manufacturer_template_id;
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

        public int getPicture_id() {
            return picture_id;
        }

        public void setPicture_id(int picture_id) {
            this.picture_id = picture_id;
        }

        public int getPage_size() {
            return page_size;
        }

        public void setPage_size(int page_size) {
            this.page_size = page_size;
        }

        public boolean isAllow_customers_to_select_page_size() {
            return allow_customers_to_select_page_size;
        }

        public void setAllow_customers_to_select_page_size(boolean allow_customers_to_select_page_size) {
            this.allow_customers_to_select_page_size = allow_customers_to_select_page_size;
        }

        public String getPage_size_options() {
            return page_size_options;
        }

        public void setPage_size_options(String page_size_options) {
            this.page_size_options = page_size_options;
        }

        public Object getPrice_ranges() {
            return price_ranges;
        }

        public void setPrice_ranges(Object price_ranges) {
            this.price_ranges = price_ranges;
        }

        public boolean isSubject_to_acl() {
            return subject_to_acl;
        }

        public void setSubject_to_acl(boolean subject_to_acl) {
            this.subject_to_acl = subject_to_acl;
        }

        public boolean isLimited_to_stores() {
            return limited_to_stores;
        }

        public void setLimited_to_stores(boolean limited_to_stores) {
            this.limited_to_stores = limited_to_stores;
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

        public int getDisplay_order() {
            return display_order;
        }

        public void setDisplay_order(int display_order) {
            this.display_order = display_order;
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

        public static class ImageBean {

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
             * localized_name : Birthday
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
             * localized_description : null
             */

            private int language_id;
            private Object localized_description;

            public int getLanguage_id() {
                return language_id;
            }

            public void setLanguage_id(int language_id) {
                this.language_id = language_id;
            }

            public Object getLocalized_description() {
                return localized_description;
            }

            public void setLocalized_description(Object localized_description) {
                this.localized_description = localized_description;
            }
        }
    }
}
