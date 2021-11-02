
package com.example.leartsshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.leartsshop.R;
import com.example.leartsshop.adapter.CategoriesAdapter;
import com.example.leartsshop.model.Categories;
import com.example.leartsshop.ultil.CheckConnection;
import com.example.leartsshop.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class CategoriesActivity extends AppCompatActivity {
    Toolbar toolbarCate;
    ListView listView;
    CategoriesAdapter categoriesAdapter;
    ArrayList<Categories> categoriesArrayList;
    View footer_view;
    boolean isLoading = false;
//    mHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        AnhXa();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            ActionToolbar();
            LoadMoreData();
            getCateData();
        } else {
            CheckConnection.ShowToast_Short(getApplicationContext(), "Check the connection again !");
            finish();
        }
    }

    private void getCateData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Server.url_cate, null, response -> {
            if (response != null) {
                int id;
                int id_user;
                String name_cate;
                String img_cate;
                String detail;
                String create;
                String update;
                String delete;
                int status;
                String view;
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject data = jsonArray.getJSONObject(i);
                        id = data.getInt("cate_id");
                        id_user = data.getInt("user_id");
                        name_cate = data.getString("cate_name");
                        img_cate = data.getString("cate_img");
                        detail = data.getString("cate_description");
                        create = data.getString("created_at");
                        update = data.getString("updated_at");
                        status = data.getInt("status");
                        view = data.getString("view");
                        categoriesArrayList.add(new Categories(id, id_user, name_cate, img_cate,
                                detail, create, update, status, view));
                        categoriesAdapter.notifyDataSetChanged();//update
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, error -> CheckConnection.ShowToast_Short(getApplicationContext(), error.toString()));
        requestQueue.add(jsonObjectRequest);

    }

    private void LoadMoreData() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getApplicationContext(), ProductCate.class);
            intent.putExtra("id_cate", categoriesArrayList.get(position).getId());
            startActivity(intent);
        });
    }


    private void ActionToolbar() {
        setSupportActionBar(toolbarCate);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbarCate.setNavigationOnClickListener(v -> finish());
    }

    private void AnhXa() {
        toolbarCate = findViewById(R.id.toolbar_cate);
        listView = findViewById(R.id.listview_cate);
        categoriesArrayList = new ArrayList<>();
        categoriesAdapter = new CategoriesAdapter(categoriesArrayList, getApplicationContext());
        listView.setAdapter(categoriesAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footer_view = inflater.inflate(R.layout.progressbar, null);
    }

}
