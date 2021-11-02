package com.example.leartsshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.leartsshop.R;
import com.example.leartsshop.adapter.ProductAdapter;
import com.example.leartsshop.model.Products;
import com.example.leartsshop.ultil.CheckConnection;
import com.example.leartsshop.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;

public class ProductCate extends AppCompatActivity {
    Toolbar toolbarProduct;
    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    ArrayList<Products> productsArrayList;
    int idItem = 0;
    View footer_view;
    boolean isLoading = false;
    boolean limit_data = false;
//    mHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_cate);
        AnhXa();
        getIdCategories();
        ActionToolbar();
        getData();
    }


    private void AnhXa() {
        toolbarProduct = findViewById(R.id.toolbar_product_cate);
        recyclerView = findViewById(R.id.recyclerview_product_cate);
        productsArrayList = new ArrayList<>();
        productAdapter = new ProductAdapter(getApplicationContext(), productsArrayList);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        LayoutInflater inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footer_view=inflater.inflate(R.layout.progressbar,null);
//        mHandler = new mHandler();
    }
    private void getIdCategories() {
        idItem = getIntent().getIntExtra("id_cate", -1);
        Log.d("gia tri categories ", idItem + "");
    }
    private void ActionToolbar() {
        setSupportActionBar(toolbarProduct);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbarProduct.setNavigationOnClickListener(v -> finish());
    }

    private void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String link = Server.url_product_cate + idItem;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(link, response -> {
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
            if(response != null){
                try {
                    for(int i = 0; i < response.length(); i++){
                        JSONObject jsonObject = response.getJSONObject(i);
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
                                ,image_product_hover,quantity_product,price_product,detail_product,keyword_product,status,view,create_at,update_at));
                        productAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
         }, error -> {

        }){
            @Override
            protected Map<String, String> getParams() {
                HashMap<String,String> param= new HashMap<>();
                param.put("cate_id",String.valueOf(idItem));
                return param;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }

}