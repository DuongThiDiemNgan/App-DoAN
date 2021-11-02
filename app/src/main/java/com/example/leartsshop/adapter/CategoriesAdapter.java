package com.example.leartsshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leartsshop.R;
import com.example.leartsshop.model.Categories;
import com.example.leartsshop.ultil.Server;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoriesAdapter extends BaseAdapter {
    ArrayList<Categories>categoriesArrayList;
    Context context;

    public CategoriesAdapter(ArrayList<Categories> categoriesArrayList, Context context) {
        this.categoriesArrayList = categoriesArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return categoriesArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoriesArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView txt_name_cate;
        ImageView img_cate;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(viewHolder == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_categories,null);
            viewHolder.txt_name_cate = (TextView) convertView.findViewById(R.id.textview_cate);
            viewHolder.img_cate = (ImageView) convertView.findViewById(R.id.imageview_cate);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Categories categories = (Categories) getItem(position);
        viewHolder.txt_name_cate.setText(categories.getName_cate());
        Picasso.with(context).load(Server.localhost+"/image/category/"+categories.getImg_cate())
                .error(R.drawable.error)
                .into(viewHolder.img_cate);
        return convertView;
    }
}
