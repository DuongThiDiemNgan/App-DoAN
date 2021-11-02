package com.example.leartsshop.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.leartsshop.R;
import com.example.leartsshop.adapter.CartAdapter;
import com.example.leartsshop.ultil.CheckConnection;

import java.text.DecimalFormat;

public class CartActivity extends AppCompatActivity {
    ListView listView;
    TextView txtNotify;
    static TextView txtTotalCash;
    Button btnPay,btnContinueBuy;
    Toolbar toolbar_cart;
    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        AnhXa();
        ActionToolbar();
        CheckData();
        EvenUtils();
        CatchOnItemListView();
        EventButton();
    }

    private void EventButton() {
        btnContinueBuy.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        });
        btnPay.setOnClickListener(v -> {
            if (MainActivity.arrCart.size() > 0) {
                Intent intent = new Intent(getApplicationContext(), CustomerInfo.class);
                startActivity(intent);
            }else {
                CheckConnection.ShowToast_Short(getApplicationContext(),"Giỏ hàng rỗng");
            }
        });
    }

    private void CatchOnItemListView() {
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            AlertDialog.Builder builder= new AlertDialog.Builder(CartActivity.this);
            builder.setTitle("Xác nhận xóa sản phẩm");
            builder.setMessage("Bạn có chắc muốn xóa sản phẩm này?");
            builder.setPositiveButton("Có", (dialog, which) -> {
                if(MainActivity.arrCart.size()<=0)
                {
                    txtNotify.setVisibility(View.VISIBLE);
                }
                else {
                    MainActivity.arrCart.remove(position);
                    cartAdapter.notifyDataSetChanged();
                    EvenUtils();
                    if(MainActivity.arrCart.size()<=0){
                        txtNotify.setVisibility(View.VISIBLE);
                    }
                    else{
                        txtNotify.setVisibility(View.INVISIBLE);
                        cartAdapter.notifyDataSetChanged();
                        EvenUtils();
                    }
                }
            });
            builder.setNegativeButton("Không", (dialog, which) -> {
                cartAdapter.notifyDataSetChanged();
                EvenUtils();
            });
            builder.show();
            return true;
        });
    }

    public static void EvenUtils() {
        long total_cash = 0;
        for(int i = 0; i<MainActivity.arrCart.size(); i++)
        {
            total_cash += MainActivity.arrCart.get(i).getPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTotalCash.setText(decimalFormat.format(total_cash)+" VND");
    }

    private void CheckData() {
        if(MainActivity.arrCart.size() <= 0)
        {
            cartAdapter.notifyDataSetChanged();
            txtNotify.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        }else {
            cartAdapter.notifyDataSetChanged();
            txtNotify.setVisibility(View.INVISIBLE);
            listView.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_cart.setNavigationOnClickListener(view -> finish());
    }


    private void AnhXa() {
        listView = (ListView) findViewById(R.id.listview_cart);
        txtNotify = findViewById(R.id.textview_notify);
        txtTotalCash = findViewById(R.id.textview_total_cash);
        btnPay = findViewById(R.id.button_pay_cart);
        btnContinueBuy = findViewById(R.id.button_continue_shopping);
        toolbar_cart = findViewById(R.id.toolbar_cart);
        cartAdapter = new CartAdapter(CartActivity.this,MainActivity.arrCart);
        listView.setAdapter(cartAdapter);
    }
}