package com.example.leartsshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leartsshop.R;
import com.example.leartsshop.activity.CartActivity;
import com.example.leartsshop.activity.MainActivity;
import com.example.leartsshop.model.Cart;
import com.example.leartsshop.ultil.Server;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    Context context;
    ArrayList<Cart> arrCart;

    public CartAdapter(Context context, ArrayList<Cart> arrCart) {
        this.context = context;
        this.arrCart = arrCart;
    }

    @Override
    public int getCount() {
        return arrCart.size();
    }

    @Override
    public Object getItem(int position) {
        return arrCart.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txt_cart_name, txt_cart_cost, txt_values;
        public ImageView img_cart;
        public Button btn_minus,btn_plus;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_cart,null);
            viewHolder.txt_cart_name = convertView.findViewById(R.id.textview_cart_name);
            viewHolder.txt_cart_cost = (TextView) convertView.findViewById(R.id.textview_cart_price);
            viewHolder.img_cart = (ImageView) convertView.findViewById(R.id.imageview_cart);
            viewHolder.btn_minus = (Button) convertView.findViewById(R.id.button_minus);
            viewHolder.txt_values = (TextView) convertView.findViewById(R.id.txt_value);
            viewHolder.btn_plus = (Button) convertView.findViewById(R.id.button_plus);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Cart cart = (Cart) getItem(position);
        viewHolder.txt_cart_name.setText(cart.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txt_cart_cost.setText(decimalFormat.format(cart.getPrice()) + " VND");
        Picasso.with(context).load(Server.localhost+"/image/product/"+cart.getImg())
                .error(R.drawable.error)
                .into(viewHolder.img_cart);
        viewHolder.txt_values.setText(cart.getAmount()+"");
        int sl = Integer.parseInt(viewHolder.txt_values.getText().toString());
        if(sl>=10) {
            viewHolder.btn_plus.setVisibility(View.INVISIBLE);
            viewHolder.btn_plus.setVisibility(View.VISIBLE);
        }else if(sl<=1) {
            viewHolder.btn_minus.setVisibility(View.INVISIBLE);
        }
        else if(sl>1) {
            viewHolder.btn_minus.setVisibility(View.VISIBLE);
            viewHolder.btn_plus.setVisibility(View.VISIBLE);
        }
        ViewHolder finalViewHolder = viewHolder;
        viewHolder.btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int new_sl = Integer.parseInt(finalViewHolder.txt_values.getText().toString()) +1;
                int cur_sl = (int) MainActivity.arrCart.get(position).getAmount();
                long cur_price = MainActivity.arrCart.get(position).getPrice();
                MainActivity.arrCart.get(position).setAmount(new_sl);
                long new_cash = (cur_price*new_sl)/cur_sl;
                MainActivity.arrCart.get(position).setPrice(new_cash);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txt_cart_cost.setText(decimalFormat.format(new_cash) + " VND");
                CartActivity.EvenUtils();
                if(new_sl > 9){
                    finalViewHolder.btn_plus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btn_minus.setVisibility(View.VISIBLE);
                    finalViewHolder.txt_values.setText(String.valueOf(new_sl));
                }
                else
                {
                    finalViewHolder.btn_plus.setVisibility(View.VISIBLE);
                    finalViewHolder.btn_minus.setVisibility(View.VISIBLE);
                    finalViewHolder.txt_values.setText(String.valueOf(new_sl));
                }
            }
        });
        viewHolder.btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int new_sl = Integer.parseInt(finalViewHolder.txt_values.getText().toString()) -1;
                int cur_sl = (int) MainActivity.arrCart.get(position).getAmount();
                long cur_price = MainActivity.arrCart.get(position).getPrice();
                MainActivity.arrCart.get(position).setAmount(new_sl);
                long new_cash = (cur_price*new_sl)/cur_sl;
                MainActivity.arrCart.get(position).setPrice(new_cash);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txt_cart_cost.setText(decimalFormat.format(new_cash) + " VND");
                CartActivity.EvenUtils();
                if(new_sl<2){
                    finalViewHolder.btn_minus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btn_plus.setVisibility(View.VISIBLE);
                    finalViewHolder.txt_values.setText(String.valueOf(new_sl));
                }
                else
                {
                    finalViewHolder.btn_plus.setVisibility(View.VISIBLE);
                    finalViewHolder.btn_minus.setVisibility(View.VISIBLE);
                    finalViewHolder.txt_values.setText(String.valueOf(new_sl));
                }
            }
        });

        return convertView;

    }
}
