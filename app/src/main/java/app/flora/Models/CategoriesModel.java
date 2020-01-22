package app.flora.Models;

import java.util.ArrayList;
import java.util.List;

public class CategoriesModel {


    private List<CategoriesBean> categories;

    public List<CategoriesBean> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesBean> categories) {
        this.categories = categories;
    }

    public static class CategoriesBean {

        private String name;
        private Object description;
        private int category_template_id;
        private Object meta_keywords;
        private Object meta_description;
        private Object meta_title;
        private int parent_category_id;
        private int page_size;
        private String page_size_options;
        private Object price_ranges;
        private boolean show_on_home_page;
        private boolean include_in_top_menu;
        private Object has_discounts_applied;
        private boolean has_sub_categories;
        private boolean published;
        private boolean deleted;
        private int display_order;
        private String created_on_utc;
        private String updated_on_utc;
        private Object top_products;
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

        public int getCategory_template_id() {
            return category_template_id;
        }

        public void setCategory_template_id(int category_template_id) {
            this.category_template_id = category_template_id;
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

        public int getParent_category_id() {
            return parent_category_id;
        }

        public void setParent_category_id(int parent_category_id) {
            this.parent_category_id = parent_category_id;
        }

        public int getPage_size() {
            return page_size;
        }

        public void setPage_size(int page_size) {
            this.page_size = page_size;
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

        public boolean isShow_on_home_page() {
            return show_on_home_page;
        }

        public void setShow_on_home_page(boolean show_on_home_page) {
            this.show_on_home_page = show_on_home_page;
        }

        public boolean isInclude_in_top_menu() {
            return include_in_top_menu;
        }

        public void setInclude_in_top_menu(boolean include_in_top_menu) {
            this.include_in_top_menu = include_in_top_menu;
        }

        public Object getHas_discounts_applied() {
            return has_discounts_applied;
        }

        public void setHas_discounts_applied(Object has_discounts_applied) {
            this.has_discounts_applied = has_discounts_applied;
        }

        public boolean isHas_sub_categories() {
            return has_sub_categories;
        }

        public void setHas_sub_categories(boolean has_sub_categories) {
            this.has_sub_categories = has_sub_categories;
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

        public Object getTop_products() {
            return top_products;
        }

        public void setTop_products(Object top_products) {
            this.top_products = top_products;
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

            LocalizedNamesBean localizedName = new LocalizedNamesBean();

            localizedName.setLocalized_name(name);

            if(localized_names == null){

                localized_names = new ArrayList<>();

            }

            localized_names.add(localizedName);

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
            /**
             * src : http://flora.hardtask.info/images/thumbs/0000152_flowers.png
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
             * localized_name : Flowers
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
