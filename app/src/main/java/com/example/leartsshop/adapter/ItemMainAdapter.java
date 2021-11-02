package com.example.leartsshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leartsshop.R;
import com.example.leartsshop.model.ItemMain;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemMainAdapter extends BaseAdapter {
    ArrayList<ItemMain> mainArrayList;
    Context context;

    public ItemMainAdapter(ArrayList<ItemMain> mainArrayList, Context context) {
        this.mainArrayList = mainArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mainArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mainArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView txt_name_item_main;
        ImageView img_item_main;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(viewHolder == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_categories,null);
            viewHolder.txt_name_item_main = (TextView) convertView.findViewById(R.id.textview_cate);
            viewHolder.img_item_main = (ImageView) convertView.findViewById(R.id.imageview_cate);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ItemMain itemMain = (ItemMain) getItem(position);
        viewHolder.txt_name_item_main.setText(itemMain.getName());
        Picasso.with(context).load(itemMain.getImg())
                .error(R.drawable.error)
                .into(viewHolder.img_item_main);
        return convertView;
    }
}
