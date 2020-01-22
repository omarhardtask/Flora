package app.flora.Models;

import java.util.List;

public class SliderModel  {

    private List<SlidersBean> sliders;

    public List<SlidersBean> getSliders() {
        return sliders;
    }

    public void setSliders(List<SlidersBean> sliders) {
        this.sliders = sliders;
    }

    public static class SlidersBean {

        private int picture_id;
        private String text;
        private String link;
        private String alt_text;
        private ImageBean image;
        private int id;

        public int getPicture_id() {
            return picture_id;
        }

        public void setPicture_id(int picture_id) {
            this.picture_id = picture_id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getAlt_text() {
            return alt_text;
        }

        public void setAlt_text(String alt_text) {
            this.alt_text = alt_text;
        }

        public ImageBean getImage() {
            return image;
        }

        public void setImage(ImageBean image) {
            this.image = image;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public static class ImageBean {
            private String src;
            private String attachment;

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }

            public String getAttachment() {
                return attachment;
            }

            public void setAttachment(String attachment) {
                this.attachment = attachment;
            }
        }
    }
}
