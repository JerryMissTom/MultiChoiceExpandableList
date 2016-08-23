package com.example.hurubei.multichoiceexpandablelist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.hurubei.multichoiceexpandablelist.ChildItem;
import com.example.hurubei.multichoiceexpandablelist.GroupItem;
import com.example.hurubei.multichoiceexpandablelist.R;

import java.util.List;
import java.util.Map;

/**
 * Created by hurubei on 16/7/28.
 */
public class MultiChoiceExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<GroupItem> groupItems;
    private Map<GroupItem, List<ChildItem>> childItems;
    private List<ChildItem> selectedItems;

    public MultiChoiceExpandableListAdapter(Context context, List<GroupItem> parent, Map<GroupItem, List<ChildItem>> child, List<ChildItem> selectedItems) {
        this.context = context;
        this.groupItems = parent;
        this.childItems = child;
        this.selectedItems = selectedItems;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.groupitem, null);
            groupHolder = new GroupHolder();
            groupHolder.groupTv = (TextView) convertView.findViewById(R.id.groupTv);
            groupHolder.groupBox = (CheckBox) convertView.findViewById(R.id.groupCheckBox);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        groupHolder.groupTv.setText(groupItems.get(groupPosition).getGroupTitle());
        groupHolder.groupBox.setChecked(groupItems.get(groupPosition).isSelected());
        groupHolder.groupBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupItems.get(groupPosition).toogle();
                int childCount = childItems.get(groupItems.get(groupPosition)).size();
                boolean isChecked = groupItems.get(groupPosition).isSelected();
                for (int i = 0; i < childCount; i++) {
                    childItems.get(groupItems.get(groupPosition)).get(i).setSelected(isChecked);
                    if (isChecked) {
                        if (!selectedItems.contains(childItems.get(groupItems.get(groupPosition)).get(i))) {
                            selectedItems.add(childItems.get(groupItems.get(groupPosition)).get(i));
                        }
                    } else {
                        if (selectedItems.contains(childItems.get(groupItems.get(groupPosition)).get(i))) {
                            selectedItems.remove(childItems.get(groupItems.get(groupPosition)).get(i));
                        }
                    }
                }
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public int getGroupCount() {
        return groupItems.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupItems.get(groupPosition);
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ChildHolder childHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.childitem, null);
            childHolder = new ChildHolder();
            childHolder.childTv = (TextView) convertView.findViewById(R.id.childTv);
            childHolder.childBox = (CheckBox) convertView.findViewById(R.id.childCheckbox);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }
        childHolder.childTv.setText(childItems.get(groupItems.get(groupPosition)).get(childPosition).getChildTitle());
        childHolder.childBox.setChecked(childItems.get(groupItems.get(groupPosition)).get(childPosition).isSelected());
        childHolder.childBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                childItems.get(groupItems.get(groupPosition)).get(childPosition).toogle();
                int childrenCount = childItems.get(groupItems.get(groupPosition)).size();
                boolean childrenAllIsChecked = true;
                for (int i = 0; i < childrenCount; i++) {
                    if (!childItems.get(groupItems.get(groupPosition)).get(i).isSelected())
                        childrenAllIsChecked = false;
                }
                groupItems.get(groupPosition).setSelected(childrenAllIsChecked);

                ChildItem childItem = childItems.get(groupItems.get(groupPosition)).get(childPosition);
                if (childItem.isSelected()) {
                    if (!selectedItems.contains(childItem)) {
                        selectedItems.add(childItem);
                    }
                } else {
                    if (selectedItems.contains(childItem)) {
                        selectedItems.remove(childItem);
                    }
                }
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        GroupItem groupItemKey = groupItems.get(groupPosition);
        return childItems.get(groupItemKey).size();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        GroupItem groupItemKey = groupItems.get(groupPosition);
        return childItems.get(groupItemKey).get(childPosition);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    private class GroupHolder {
        TextView groupTv;
        CheckBox groupBox;
    }

    private class ChildHolder {
        TextView childTv;
        CheckBox childBox;
    }
}
