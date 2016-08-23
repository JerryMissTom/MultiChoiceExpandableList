package com.example.hurubei.multichoiceexpandablelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.hurubei.multichoiceexpandablelist.activities.AllSelectListActivity;
import com.example.hurubei.multichoiceexpandablelist.activities.LongClickMultiChoiceListActivity;
import com.example.hurubei.multichoiceexpandablelist.activities.MultiChoiceExpandableListActivity;
import com.example.hurubei.multichoiceexpandablelist.activities.MultiChoiceExListActivity;
import com.example.hurubei.multichoiceexpandablelist.activities.AllSelectExListActivity;

public class MainActivity extends AppCompatActivity {
    private ListView mList;
    private Sample[] mSamples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = (ListView) findViewById(R.id.activity_list);

        mSamples = new Sample[]{new Sample("MultiChoiceExpandableListActivity", MultiChoiceExpandableListActivity.class),
                new Sample("MultiChoiceExListActivity", MultiChoiceExListActivity.class),
                new Sample("AllSelectExListActivity", AllSelectExListActivity.class),
                new Sample("AllSelectListAciticy", AllSelectListActivity.class),
                new Sample("LongClickMultiChoiceListActivity", LongClickMultiChoiceListActivity.class)};

        ListAdapter adapter = new ArrayAdapter<Sample>(this, android.R.layout.simple_list_item_1, android.R.id.text1, mSamples);
        mList.setAdapter(adapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, mSamples[position].activityClass);
                startActivity(intent);
            }
        });

    }


    private class Sample {
        private String title;
        private Class<? extends AppCompatActivity> activityClass;

        public Sample(String title, Class<? extends AppCompatActivity> activityClass) {
            this.title = title;
            this.activityClass = activityClass;
        }

        @Override
        public String toString() {
            return title;
        }
    }

}
