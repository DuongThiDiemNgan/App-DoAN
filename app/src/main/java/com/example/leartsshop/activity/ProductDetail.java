package com.example.leartsshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.leartsshop.R;
import com.example.leartsshop.model.Cart;
import com.example.leartsshop.model.Products;
import com.example.leartsshop.ultil.Server;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ProductDetail extends AppCompatActivity {
    Toolbar toolbar_detail;
    ImageView img_detail;
    TextView txt_name,txt_price,txt_detail, txt_values_dt;
    Button btn_order, btn_plus_dt, btn_minus_dt;

    int Id = 0;
    String Name_detail = "";
    int Price_detail = 0;
    String Img_detail = "";
    String Detail = "";
    int Id_cate = 0;
    int Quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        AnhXa();
        ActionToolBar();
        GetInformation();
        EventButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_cart:
                Intent intent = new Intent(getApplicationContext(),CartActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void EventButton() {
        btn_order.setOnClickListener(v -> {
            if(MainActivity.arrCart.size() > 0){
                int sl = Integer.parseInt((txt_values_dt.getText().toString()));
                boolean exits = false;
                for(int i = 0; i < MainActivity.arrCart.size(); i++){
                    if(MainActivity.arrCart.get(i).getProductId() == Id){
                        MainActivity.arrCart.get(i).setAmount(MainActivity.arrCart.get(i).getAmount() + sl);
                        if(MainActivity.arrCart.get(i).getAmount() >= 10){
                            MainActivity.arrCart.get(i).setAmount(10);
                        }
                        MainActivity.arrCart.get(i).setPrice(Price_detail * MainActivity.arrCart.get(i).getAmount());
                        exits = true;
                    }
                }
                if(exits == false){
                    int count =Integer.parseInt((txt_values_dt.getText().toString()));
                    long SumPrice = count*Price_detail;
                    MainActivity.arrCart.add(new Cart(Id,SumPrice,Quantity,count,Img_detail,Name_detail));
                }
            }else{
                int count = Integer.parseInt((txt_values_dt.getText().toString()));
                long SumPrice = count*Price_detail;
                MainActivity.arrCart.add(new Cart(Id,SumPrice,Quantity,count,Img_detail,Name_detail));
            }
            Intent intent = new Intent(getApplicationContext(),CartActivity.class);
            startActivity(intent);
        });
    }


    private void GetInformation() {
        Products product = (Products) getIntent().getSerializableExtra("productData");
        Id = product.getID();
        Name_detail = product.getName_product();
        Price_detail = product.getPrice_product();
        Img_detail = product.getImage_product();
        Detail = product.getDetail_product();
        Id_cate = product.getId_cate();
        Quantity = product.getQuantity_product();
        txt_name.setText(Name_detail);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txt_price.setText("Giá : "+decimalFormat.format(Price_detail)+" VND");
        txt_detail.setText(Detail);
        Picasso.with(getApplicationContext()).load(Server.localhost+"/image/product/"+ Img_detail)
                .placeholder(R.drawable.error)
                .error(R.drawable.error)
                .into(img_detail);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_detail.setNavigationOnClickListener(v -> finish());
    }

    private void AnhXa() {
        toolbar_detail = (Toolbar) findViewById(R.id.toolbar_product_detail);
        img_detail= (ImageView) findViewById(R.id.imageview_product_detail);
        txt_name= (TextView) findViewById(R.id.textview_name_product_detail);
        txt_price= (TextView) findViewById(R.id.textview_price_product_detail);
        txt_detail= (TextView) findViewById(R.id.textview_product_detail);
        btn_order= (Button) findViewById(R.id.button_oder);
        btn_minus_dt = findViewById(R.id.button_minus_detail);
        btn_plus_dt = findViewById(R.id.button_plus_detail);
        txt_values_dt = findViewById(R.id.txt_value_detail);
        btn_plus_dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int new_sl =  Integer.parseInt(txt_values_dt.getText().toString()) +1;
                if(new_sl > 9){
                    btn_plus_dt.setVisibility(View.INVISIBLE);
                    btn_minus_dt.setVisibility(View.VISIBLE);
                    txt_values_dt.setText(String.valueOf(new_sl));
                }
                else
                {
                    btn_plus_dt.setVisibility(View.VISIBLE);
                    btn_minus_dt.setVisibility(View.VISIBLE);
                    txt_values_dt.setText(String.valueOf(new_sl));
                }

            }
        });
        btn_minus_dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int new_sl =  Integer.parseInt(txt_values_dt.getText().toString()) -1;
                if(new_sl < 2){
                    btn_minus_dt.setVisibility(View.INVISIBLE);
                    btn_plus_dt.setVisibility(View.VISIBLE);
                    txt_values_dt.setText(String.valueOf(new_sl));
                }
                else
                {
                    btn_plus_dt.setVisibility(View.VISIBLE);
                    btn_minus_dt.setVisibility(View.VISIBLE);
                    txt_values_dt.setText(String.valueOf(new_sl));
                }
            }
        });
    }
}