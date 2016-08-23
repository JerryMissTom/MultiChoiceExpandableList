package com.example.hurubei.multichoiceexpandablelist.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.hurubei.multichoiceexpandablelist.ChildItem;
import com.example.hurubei.multichoiceexpandablelist.R;
import com.example.hurubei.multichoiceexpandablelist.adapters.AllSelectListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 16/8/18.
 */
public class AllSelectListActivity extends AppCompatActivity {
    private ListView mListView;
    private List<ChildItem> mItemList = new ArrayList<>();
    private CheckBox allSelect;
    private AllSelectListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_select_list_activity);
        mListView = (ListView) findViewById(R.id.list);
        allSelect = (CheckBox) findViewById(R.id.all_selected);
        initData();
        adapter = new AllSelectListAdapter(this, mItemList);
        mListView.setAdapter(adapter);
        allSelect.setOnClickListener(onClickListener);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mItemList.get(position).toogle();
                adapter.notifyDataSetChanged();
                boolean isAllSelect = true;

                int count = mItemList.size();
                for (int i = 0; i < count; i++) {
                    if (!mItemList.get(i).isSelected()) {
                        isAllSelect = false;
                    }
                }
                allSelect.setChecked(isAllSelect);
            }
        });
    }

    private void initData() {
        ChildItem childItem1 = new ChildItem("first", false);
        ChildItem childItem2 = new ChildItem("second", false);
        ChildItem childItem3 = new ChildItem("third", false);
        mItemList.add(childItem1);
        mItemList.add(childItem2);
        mItemList.add(childItem3);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            int id = v.getId();
            if (id == R.id.all_selected) {
                boolean isSelect = allSelect.isChecked();
                int count = mItemList.size();
                for (int i = 0; i < count; i++) {
                    mItemList.get(i).setSelected(isSelect);
                }
                adapter.notifyDataSetChanged();
            }
        }
    };
}
