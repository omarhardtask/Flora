package app.flora.Models;

import java.util.ArrayList;
import java.util.List;

public class FeaturedShopsModel {

    private List<VendorsBean> vendors;

    public List<VendorsBean> getVendors() {
        return vendors;
    }

    public void setVendors(List<VendorsBean> vendors) {
        this.vendors = vendors;
    }

    public static class VendorsBean {

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

        public String getLocalizedName() {

            String name = "";

            if (localized_names != null) {

                if (localized_names.size() > 0) {

                    name = localized_names.get(0).getLocalized_name();

                }

            }

            return name;

        }

        public void setLocalizedName(String name) {

            LocalizedNamesBean
                    localizedName = new LocalizedNamesBean();

            localizedName.setLocalized_name(name);

            if (localized_names == null) {

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
}
