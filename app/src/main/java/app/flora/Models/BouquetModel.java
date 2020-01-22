package app.flora.Models;

public class BouquetModel {

    String title;
    boolean isSelected;

    public BouquetModel(String title) {
        this.title = title;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public String getTitle() {
        return title;
    }
}
