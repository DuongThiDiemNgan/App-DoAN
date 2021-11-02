package com.example.leartsshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.leartsshop.R;
import com.example.leartsshop.adapter.ArticleAdapter;
import com.example.leartsshop.adapter.PortfolioAdapter;
import com.example.leartsshop.model.Categories;
import com.example.leartsshop.model.Portfolio;
import com.example.leartsshop.ultil.CheckConnection;
import com.example.leartsshop.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class PortfolioActivity extends AppCompatActivity {
    Toolbar toolbarPort;
    ListView listView;
    PortfolioAdapter portfolioAdapter;
    ArrayList<Portfolio> portfolioArrayList;
    View footer_view;
    boolean isLoading = false;
    boolean limit_data = false;
//    mHandler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
        AnhXa();
        ActionToolbar();
        getPortData();
        LoadMoreData();
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
    private void LoadMoreData() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getApplicationContext(),ProductPort.class);
            intent.putExtra("id_port",portfolioArrayList.get(position).getId());
            startActivity(intent);
        });
//        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                if(firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0 && isLoading == false &&limit_data==false){
//                    isLoading=true;
//                    ThreadData threadData = new ThreadData();
//                    threadData.start();
//                }
//            }
//        });
    }

    private void getPortData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Server.url_port, null, response -> {
            if(response != null){
                int id;
                int id_user;
                String name_port;
                String avatar_port;
                String img_port;
                String origin_port;
                String detail_port;
                String create_port;
                String update_port;
                String delete_port;
                int status_port;
                String view;
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject data = jsonArray.getJSONObject(i);
                        id = data.getInt("port_id");
                        id_user = data.getInt("user_id");
                        name_port = data.getString("port_name");
                        avatar_port = data.getString("port_avatar");
                        img_port = data.getString("port_img");
                        origin_port = data.getString("port_origin");
                        detail_port = data.getString("port_description");
                        create_port = data.getString("created_at");
                        update_port = data.getString("updated_at");
                        status_port = data.getInt("status");
                        portfolioArrayList.add(new Portfolio(id, id_user, name_port, avatar_port,
                                img_port, origin_port, detail_port, create_port, update_port, status_port));
                        portfolioAdapter.notifyDataSetChanged();//update

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, error -> CheckConnection.ShowToast_Short(getApplicationContext(),error.toString()));
        requestQueue.add(jsonObjectRequest);

    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarPort);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbarPort.setNavigationOnClickListener(v -> finish());
    }

    private void AnhXa() {
        toolbarPort = findViewById(R.id.toolbar_port);
        listView = findViewById(R.id.listview_port);
        portfolioArrayList = new ArrayList<>();
        portfolioAdapter = new PortfolioAdapter(portfolioArrayList,getApplicationContext());
        listView.setAdapter(portfolioAdapter);
        LayoutInflater inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footer_view=inflater.inflate(R.layout.progressbar,null);
//        mHandler = new mHandler();
    }
//    public class mHandler extends Handler {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what){
//                case 0:
//                    listView.addFooterView(footer_view);
//                    break;
//                case 1:
//                    getPortData();
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