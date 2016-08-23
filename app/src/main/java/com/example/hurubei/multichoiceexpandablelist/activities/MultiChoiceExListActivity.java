package com.example.hurubei.multichoiceexpandablelist.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.hurubei.multichoiceexpandablelist.ChildItem;
import com.example.hurubei.multichoiceexpandablelist.GroupItem;
import com.example.hurubei.multichoiceexpandablelist.R;
import com.example.hurubei.multichoiceexpandablelist.adapters.MultiChoiceExListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 16/8/16.
 */
public class MultiChoiceExListActivity extends AppCompatActivity {
    private ExpandableListView mExListView;
    private List<GroupItem> mGroupItemList = new ArrayList<>();
    private Map<GroupItem, List<ChildItem>> mChildItemMap = new HashMap<>();
    private List<ChildItem> mSelectedItemList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_choice_exlist_activity);

        initData();
        mExListView = (ExpandableListView) findViewById(R.id.list);
        MultiChoiceExListAdapter adapter = new MultiChoiceExListAdapter(this, mGroupItemList, mChildItemMap, mSelectedItemList);
        mExListView.setGroupIndicator(null);
        mExListView.setAdapter(adapter);
        mExListView.setOnChildClickListener(adapter);
        mExListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                int count=mExListView.getCount();
                for (int i=0;i<count;i++){
                    if (i==groupPosition){
                        mExListView.expandGroup(i);
                    }else{
                        mExListView.collapseGroup(i);
                    }
                }
                return true;
            }
        });
    }

    private void initData() {
        GroupItem groupItem1 = new GroupItem("first", false);
        GroupItem groupItem2 = new GroupItem("second", false);
        GroupItem groupItem3 = new GroupItem("third", false);
        mGroupItemList.add(groupItem1);
        mGroupItemList.add(groupItem2);
        mGroupItemList.add(groupItem3);

        ChildItem childItem1 = new ChildItem("1-1", false);
        ChildItem childItem2 = new ChildItem("1-2", false);
        ChildItem childItem3 = new ChildItem("1-3", false);
        List<ChildItem> childItemList1 = new ArrayList<>();
        childItemList1.add(childItem1);
        childItemList1.add(childItem2);
        childItemList1.add(childItem3);
        ChildItem childItem4 = new ChildItem("2-1", false);
        ChildItem childItem5 = new ChildItem("2-2", false);
        List<ChildItem> childItemList2 = new ArrayList<>();
        childItemList2.add(childItem4);
        childItemList2.add(childItem5);
        ChildItem childItem6 = new ChildItem("3-1", false);
        List<ChildItem> childItemList3 = new ArrayList<>();
        childItemList3.add(childItem6);

        mChildItemMap.put(groupItem1, childItemList1);
        mChildItemMap.put(groupItem2, childItemList2);
        mChildItemMap.put(groupItem3, childItemList3);

    }
}
