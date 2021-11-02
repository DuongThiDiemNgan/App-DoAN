package com.example.leartsshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leartsshop.R;
import com.example.leartsshop.activity.ProductDetail;
import com.example.leartsshop.model.Products;
import com.example.leartsshop.ultil.Server;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ItemHolder> {
    Context context;
    ArrayList<Products> productsArrayList;

    public ProductAdapter(Context context, ArrayList<Products> productsArrayList) {
        this.context = context;
        this.productsArrayList = productsArrayList;
    }

    @Override
    public ItemHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,null);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder( ItemHolder holder, int position) {
        Products product = productsArrayList.get(position);
        holder.Name.setText(product.getName_product());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.Price.setText("Giá "+ decimalFormat.format(product.getPrice_product())+" VND");
        Picasso.with(context).load(Server.localhost+"/image/product/"+product.getImage_product())
                .error(R.drawable.error)
                .into(holder.Img);
        holder.Detail.setEllipsize(TextUtils.TruncateAt.END);
        holder.Detail.setText(product.getDetail_product());
    }

    @Override
    public int getItemCount() { return productsArrayList.size(); }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView Img;
        public TextView Name,Price, Detail;
        public ItemHolder( View itemView) {
            super(itemView);
            Img = (ImageView) itemView.findViewById(R.id.imageview_item);
            Name = (TextView) itemView.findViewById(R.id.textview_item_name);
            Price = (TextView) itemView.findViewById(R.id.textview_item_price);
            Detail = itemView.findViewById(R.id.textview_item_detail);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, ProductDetail.class);
                intent.putExtra("productData", productsArrayList.get(getAdapterPosition()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            });
        }
    }
}
