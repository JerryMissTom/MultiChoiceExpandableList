package com.example.hurubei.multichoiceexpandablelist.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hurubei.multichoiceexpandablelist.ChildItem;
import com.example.hurubei.multichoiceexpandablelist.R;
import com.example.hurubei.multichoiceexpandablelist.adapters.LongClickMultiChoiceListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created  on 16/8/22.
 */
public class LongClickMultiChoiceListActivity extends AppCompatActivity {

    private List<ChildItem> mItemList = new ArrayList<>();
    private ListView mListView;
    private LongClickMultiChoiceListAdapter adapter;
    private boolean isCheckBoxShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.long_click_multi_choice_list_activity);
        mListView = (ListView) findViewById(R.id.list);
        initData();
        adapter = new LongClickMultiChoiceListAdapter(this, mItemList);
        adapter.setShown(isCheckBoxShown);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isCheckBoxShown) {
                    mItemList.get(position).toogle();
                    adapter.setShown(isCheckBoxShown);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (!isCheckBoxShown) {
                    isCheckBoxShown = true;
                    adapter.setShown(isCheckBoxShown);
                    adapter.notifyDataSetChanged();
                }
                return true;
            }
        });
        mListView.setAdapter(adapter);

    }


    private void initData() {
        ChildItem childItem1 = new ChildItem("first", false);
        ChildItem childItem2 = new ChildItem("second", false);
        ChildItem childItem3 = new ChildItem("third", false);
        mItemList.add(childItem1);
        mItemList.add(childItem2);
        mItemList.add(childItem3);
    }
}
