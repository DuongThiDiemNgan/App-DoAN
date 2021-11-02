package com.example.leartsshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.leartsshop.R;
import com.example.leartsshop.adapter.ItemMainAdapter;
import com.example.leartsshop.adapter.ProductNewAdapter;
import com.example.leartsshop.model.Cart;
import com.example.leartsshop.model.ItemMain;
import com.example.leartsshop.model.Products;
import com.example.leartsshop.ultil.CheckConnection;
import com.example.leartsshop.ultil.Server;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView_main;
    NavigationView navigationView;
    ListView listView_main;
    DrawerLayout drawerLayout;
    ArrayList<ItemMain> itemMainArrayList;
    ItemMainAdapter itemMainAdapter;
    ArrayList<Products> productsArrayList;
    ProductNewAdapter productNewAdapter;
    Button btn_user;
    static boolean is_login = false;
    static String username;
    public static ArrayList<Cart> arrCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            Actionbar();
            ActionViewFlipper();
            getDataNewProduct();
            CatchOnItemListView();
        }
        else
        {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Check Internet Connection");
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
        switch (item.getItemId())
        {
            case R.id.menu_cart:
                Intent intent = new Intent(getApplicationContext(),CartActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void CatchOnItemListView() {
        listView_main.setOnItemClickListener((adapterView, view, i, l) -> {
            switch (i){
                case 0:
                    if(CheckConnection.haveNetworkConnection(getApplicationContext()))
                    {
                        Intent intent = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        CheckConnection.ShowToast_Short(getApplicationContext(),"Check Your Internet Connection");

                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case 1:
                    if(CheckConnection.haveNetworkConnection(getApplicationContext()))
                    {
                        Intent intent = new Intent(MainActivity.this,ProductActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        CheckConnection.ShowToast_Short(getApplicationContext(),"Check Your Internet Connection");

                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case 2:
                    if(CheckConnection.haveNetworkConnection(getApplicationContext()))
                    {
                        Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        CheckConnection.ShowToast_Short(getApplicationContext(),"Check Your Internet Connection");

                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case 3:
                    if(CheckConnection.haveNetworkConnection(getApplicationContext()))
                    {
                        Intent intent = new Intent(MainActivity.this, ArticleActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        CheckConnection.ShowToast_Short(getApplicationContext(),"Check Your Internet Connection");

                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case 4:
                    if(CheckConnection.haveNetworkConnection(getApplicationContext()))
                    {
                        Intent intent = new Intent(MainActivity.this, PortfolioActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        CheckConnection.ShowToast_Short(getApplicationContext(),"Check Your Internet Connection");

                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
            }
        });
    }

    private void getDataNewProduct() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Server.url_new_product, null, response -> {
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
                        ,image_product_hover,quantity_product,price_product,detail_product,keyword_product,status,view,create_at,update_at));
                        productNewAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, error -> CheckConnection.ShowToast_Short(getApplicationContext(),error.toString()));
        requestQueue.add(jsonObjectRequest);
    }


    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add(Server.localhost + "/image/portfolio/20210819175234.jpg");
        mangquangcao.add(Server.localhost + "/image/portfolio/20210819175730.jpg");
        mangquangcao.add(Server.localhost + "/image/portfolio/20210819181009.png");
        mangquangcao.add(Server.localhost + "/image/portfolio/20210819175948.jpg");
        for(int i=0;i<mangquangcao.size();i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    private void Actionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));
    }

    private void AnhXa() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
        recyclerView_main = (RecyclerView) findViewById(R.id.recyclerview);
        navigationView = (NavigationView) findViewById(R.id.NavigationView_main);
        listView_main = (ListView) findViewById(R.id.listview_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        itemMainArrayList = new ArrayList<>();
        itemMainArrayList.add(0,new ItemMain(0,"Home",null));
        itemMainArrayList.add(1,new ItemMain(0,"Product",null));
        itemMainArrayList.add(2,new ItemMain(0,"Category",null));
        itemMainArrayList.add(3,new ItemMain(0,"Article",null));
        itemMainArrayList.add(4,new ItemMain(0,"Portfolio",null));
        itemMainAdapter = new ItemMainAdapter(itemMainArrayList, getApplicationContext());
        listView_main.setAdapter(itemMainAdapter);
        productsArrayList = new ArrayList<>();
        productNewAdapter = new ProductNewAdapter(getApplicationContext(), productsArrayList);
        recyclerView_main.setHasFixedSize(true);
        recyclerView_main.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView_main.setAdapter(productNewAdapter);
        btn_user =(Button)findViewById(R.id.user);
        btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserInformation.class);
                startActivity(intent);
            }
        });
        if(arrCart != null){

        }else{
            arrCart = new ArrayList<>();
        }
    }
}