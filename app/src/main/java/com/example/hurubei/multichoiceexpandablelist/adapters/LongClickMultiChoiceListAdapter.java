package com.example.hurubei.multichoiceexpandablelist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.hurubei.multichoiceexpandablelist.ChildItem;
import com.example.hurubei.multichoiceexpandablelist.R;

import java.util.List;

/**
 * Created on 16/8/22.
 */
public class LongClickMultiChoiceListAdapter extends BaseAdapter {

    private Context context;
    private List<ChildItem> items;
    private boolean isShown;


    public boolean isShown() {
        return isShown;
    }

    public void setShown(boolean shown) {
        this.isShown = shown;
    }

    public LongClickMultiChoiceListAdapter(Context context, List<ChildItem> itemList) {
        this.context = context;
        this.items = itemList;

    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
            holder.tv = (TextView) convertView.findViewById(R.id.title_Tv);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.is_selected_checkbox);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.tv.setText(items.get(position).getChildTitle());
        if (isShown) {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(items.get(position).isSelected());
        } else {
            holder.checkBox.setVisibility(View.INVISIBLE);
        }
        return convertView;

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class Holder {
        TextView tv;
        CheckBox checkBox;
    }
}
