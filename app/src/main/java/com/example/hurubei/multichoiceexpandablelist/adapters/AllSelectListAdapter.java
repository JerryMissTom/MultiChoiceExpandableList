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
 * Created on 16/8/18.
 */
public class AllSelectListAdapter extends BaseAdapter {
    private Context context; 
    private List<ChildItem>  itemsList;

  public  AllSelectListAdapter(Context context, List<ChildItem> items){
      this.context=context;
      this.itemsList=items;
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder  holder=null;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.childitem, null);
            holder=new Holder();
            holder.titleTv=(TextView)convertView.findViewById(R.id.childTv);
            holder.checkBox=(CheckBox)convertView.findViewById(R.id.childCheckbox);
            convertView.setTag(holder);
        }else {
            holder=(Holder)convertView.getTag();
        }
        holder.titleTv.setText(itemsList.get(position).getChildTitle());
        holder.checkBox.setChecked(itemsList.get(position).isSelected());
        return convertView;
    }


 private static class Holder{
     TextView  titleTv;
     CheckBox  checkBox;
 }

}
