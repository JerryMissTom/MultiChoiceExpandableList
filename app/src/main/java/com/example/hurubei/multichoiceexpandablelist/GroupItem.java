package com.example.hurubei.multichoiceexpandablelist;

/**
 * Created by hurubei on 16/7/29.
 */
public class GroupItem {

    private String groupTitle;
    private boolean isSelected;

    public GroupItem(String title,boolean isSelected){
        this.groupTitle=title;
        this.isSelected=isSelected;

    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    public void toogle(){
        this.isSelected=!this.isSelected;
    }
}
