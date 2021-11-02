package com.example.leartsshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leartsshop.R;
import com.example.leartsshop.model.Portfolio;
import com.example.leartsshop.ultil.Server;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PortfolioAdapter extends BaseAdapter {
    ArrayList<Portfolio> portfolioArrayList;
    Context context;

    public PortfolioAdapter(ArrayList<Portfolio> portfolioArrayList, Context context) {
        this.portfolioArrayList = portfolioArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return portfolioArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return portfolioArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView txt_name_port;
        ImageView img_port;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder viewHolder = null;
       if(viewHolder == null){
           viewHolder = new ViewHolder();
           LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView = inflater.inflate(R.layout.item_categories,null);
           viewHolder.txt_name_port = convertView.findViewById(R.id.textview_cate);
           viewHolder.img_port =  convertView.findViewById(R.id.imageview_cate);
           convertView.setTag(viewHolder);
       }
       else {
           viewHolder = (ViewHolder) convertView.getTag();
       }
       Portfolio portfolio = (Portfolio) getItem(position);
       viewHolder.txt_name_port.setText(portfolio.getName_port());
        Picasso.with(context).load(Server.localhost+"/image/portfolio/"+portfolio.getImg_Port())
                .error(R.drawable.error)
                .into(viewHolder.img_port);
        return convertView;
    }
}
