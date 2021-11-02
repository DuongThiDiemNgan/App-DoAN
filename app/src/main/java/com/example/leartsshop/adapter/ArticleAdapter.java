package com.example.leartsshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leartsshop.R;
import com.example.leartsshop.model.Article;
import com.example.leartsshop.ultil.Server;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArticleAdapter extends BaseAdapter {
    ArrayList<Article> articleArrayList;
    Context context;

    public ArticleAdapter(ArrayList<Article> articleArrayList, Context context) {
        this.articleArrayList = articleArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return articleArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return articleArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView txt_name_article,txt_detail_article;
        ImageView img_article;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder viewHolder = null;
       if(viewHolder == null){
           viewHolder = new ViewHolder();
           LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView = inflater.inflate(R.layout.item_article,null);
           viewHolder.txt_name_article = convertView.findViewById(R.id.textview_item_name_article);
           viewHolder.txt_detail_article = convertView.findViewById(R.id.textview_item_detail_article);
           viewHolder.img_article = convertView.findViewById(R.id.imageview_item_article);
           convertView.setTag(viewHolder);
       }
       else {
           viewHolder = (ViewHolder) convertView.getTag();
       }
       Article article = (Article) getItem(position);
       viewHolder.txt_name_article.setText(article.getName_article());
       viewHolder.txt_detail_article.setText(article.getDetail_article());
        Picasso.with(context).load(Server.localhost+"/image/article/"+article.getImg_article())
                .error(R.drawable.error)
                .into(viewHolder.img_article);
        return convertView;
    }
}
