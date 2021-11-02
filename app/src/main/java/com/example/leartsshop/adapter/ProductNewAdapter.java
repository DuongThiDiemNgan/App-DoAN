package com.example.leartsshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.leartsshop.R;
import com.example.leartsshop.activity.ProductDetail;
import com.example.leartsshop.model.Products;
import com.example.leartsshop.ultil.Server;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductNewAdapter extends RecyclerView.Adapter<ProductNewAdapter.ItemHolder>{
    Context context;
    ArrayList<Products> arrProduct;

    public ProductNewAdapter(Context context, ArrayList<Products> arrProduct) {
        this.context = context;
        this.arrProduct = arrProduct;
    }

    @Override
    public ItemHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_product,null);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ProductNewAdapter.ItemHolder holder, int position) {
        Products product = arrProduct.get(position);
        holder.ProductName.setText(product.getName_product());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.ProductPrice.setText("Giá "+ decimalFormat.format(product.getPrice_product())+"VND");
        Picasso.with(context).load(Server.localhost+"/image/product/"+product.getImage_product())
                .error(R.drawable.error)
                .into(holder.ProductImage);
    }

    @Override
    public int getItemCount() {
        return arrProduct.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView ProductImage;
        public TextView ProductName,ProductPrice;
        public ItemHolder( View itemView) {
            super(itemView);
            ProductImage = (ImageView) itemView.findViewById(R.id.image_view_product);
            ProductName = (TextView) itemView.findViewById(R.id.textview_product_name);
            ProductPrice = (TextView) itemView.findViewById(R.id.textview_product_price);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, ProductDetail.class);
                intent.putExtra("productData",arrProduct.get(getAdapterPosition()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            });
        }
    }
}
