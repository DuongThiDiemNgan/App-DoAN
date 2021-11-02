package com.example.leartsshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.leartsshop.R;
import com.example.leartsshop.adapter.ProductAdapter;
import com.example.leartsshop.model.Cart;
import com.example.leartsshop.model.Products;
import com.example.leartsshop.ultil.CheckConnection;
import com.example.leartsshop.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class ProductActivity extends AppCompatActivity {
    Toolbar toolbarProduct;
    ProductAdapter productAdapter;
    RecyclerView recyclerView;
    ArrayList<Products> productsArrayList;
    int idItem = 0;
    int page = 1;
    View footer_view;
    boolean isLoading = false;
    boolean limit_data = false;
//    mHandler mHandler;
    TextView cart_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        AnhXa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())) {
            ActionToolbar();
            getProductData();
           // LoadMoreData();

        }else{
            CheckConnection.ShowToast_Short(getApplicationContext(),"Check the connection again !");
            finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_cart) {
            Intent intent = new Intent(getApplicationContext(), CartActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    //private void LoadMoreData() {
//        recyclerView.setOnItemClickListener((parent, view, position, id) -> {
//            Intent intent = new Intent(getApplicationContext(),ProductDetail.class);
//            intent.putExtra("productData",productsArrayList.get(position));
//            startActivity(intent);
//        });
//        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                if(firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0 && !isLoading && !limit_data){
//                    isLoading=true;
//                    ThreadData threadData = new ThreadData();
//                    threadData.start();
//                }
//            }
//        });
 //   }
private void getProductData() {
    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Server.url_product, null, response -> {
        if(response != null){
            int id;
            int id_user;
            int id_cate;
            int id_port;
            String name_product;
            String image_product;
            String image_product_hover;
            Integer quantity_product;
            Integer price_product;
            String detail_product;
            String keyword_product;
            int status;
            String view;
            String create_at;
            String update_at;
            try {
                JSONArray jsonArray = response.getJSONArray("data");
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    id = jsonObject.getInt("product_id");
                    id_user = jsonObject.getInt("user_id");
                    id_cate = jsonObject.getInt("cate_id");
                    id_port = jsonObject.getInt("port_id");
                    name_product = jsonObject.getString("product_name");
                    image_product = jsonObject.getString("product_img");
                    image_product_hover = jsonObject.getString("product_img_hover");
                    quantity_product = jsonObject.getInt("product_quantity");
                    price_product = jsonObject.getInt("product_price");
                    detail_product = jsonObject.getString("product_description");
                    keyword_product = jsonObject.getString("product_keyword");
                    status = jsonObject.getInt("status");
                    view = jsonObject.getString("view");
                    create_at = jsonObject.getString("created_at");
                    update_at = jsonObject.getString("updated_at");
                    productsArrayList.add(new Products(id,id_user,id_cate,id_port,name_product,image_product
                            ,image_product_hover,quantity_product,price_product,detail_product,keyword_product,
                            status,view,create_at,update_at));
                    productAdapter.notifyDataSetChanged();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }, error -> CheckConnection.ShowToast_Short(getApplicationContext(),error.toString()));
    requestQueue.add(jsonObjectRequest);
}


    private void ActionToolbar() {
        setSupportActionBar(toolbarProduct);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbarProduct.setNavigationOnClickListener(v -> finish());
    }


    private void AnhXa() {
        toolbarProduct = findViewById(R.id.toolbar_product);
        recyclerView = findViewById(R.id.recyclerview_product);
        productsArrayList = new ArrayList<>();
        productAdapter = new ProductAdapter(getApplicationContext(), productsArrayList);
        recyclerView.setAdapter(productAdapter);
        LayoutInflater inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footer_view=inflater.inflate(R.layout.progressbar,null);
        cart_view = findViewById(R.id.card_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

//        mHandler = new mHandler();

    }
//    public class mHandler extends Handler{
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what){
//                case 0:
//                    listView.addFooterView(footer_view);
//                    break;
//                case 1:
//                    getProductData();
//                    isLoading = false;
//                    break;
//            }
//            super.handleMessage(msg);
//        }
//    }
//
//    public class ThreadData extends Thread{
//        @Override
//        public void run() {
//            mHandler.sendEmptyMessage(0);
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Message message = mHandler.obtainMessage(1);
//            mHandler.sendMessage(message);
//            super.run();
//        }
//    }
}