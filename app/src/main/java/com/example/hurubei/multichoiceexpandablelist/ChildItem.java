package com.example.hurubei.multichoiceexpandablelist;

/**
 * Created on 16/7/29.
 */
public class ChildItem {
    private String childTitle;
    private boolean isSelected;

    public ChildItem(String title, boolean isSelected) {
        this.childTitle = title;
        this.isSelected = isSelected;

    }

    public String getChildTitle() {
        return childTitle;
    }

    public void setChildTitle(String childTitle) {
        this.childTitle = childTitle;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void toogle() {
        this.isSelected = !this.isSelected;
    }
}
