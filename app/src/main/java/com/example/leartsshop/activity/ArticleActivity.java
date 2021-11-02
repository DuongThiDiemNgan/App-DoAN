package com.example.leartsshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.leartsshop.R;
import com.example.leartsshop.adapter.ArticleAdapter;
import com.example.leartsshop.adapter.CategoriesAdapter;
import com.example.leartsshop.model.Article;
import com.example.leartsshop.model.Categories;
import com.example.leartsshop.ultil.CheckConnection;
import com.example.leartsshop.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class ArticleActivity extends AppCompatActivity {
    Toolbar toolbarArticle;
    ListView listView;
    ArticleAdapter articleAdapter;
    ArrayList<Article> articleArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        AnhXa();
        ActionToolbar();
        getArticleData();
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

    private void getArticleData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Server.url_article, null, response -> {
            if(response != null){
                int id;
                int id_user;
                String name_article;
                String img_article;
                String decs_article;
                String detail_article;
                String keyword_article;
                String create_article;
                String update_article;
                int status_article;
                String view;
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject data = jsonArray.getJSONObject(i);
                        id = data.getInt("article_id");
                        id_user = data.getInt("user_id");
                        name_article = data.getString("article_name");
                        img_article = data.getString("article_img");
                        decs_article = data.getString("article_description");
                        detail_article = data.getString("article_detail");
                        keyword_article = data.getString("article_keyword");
                        create_article = data.getString("created_at");
                        update_article = data.getString("updated_at");
                        status_article = data.getInt("status");
                        view = data.getString("view");
                        articleArrayList.add(new Article(id, id_user, name_article, img_article,
                                decs_article,detail_article, keyword_article, create_article, update_article, status_article, view));
                        articleAdapter.notifyDataSetChanged();//update

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, error -> CheckConnection.ShowToast_Short(getApplicationContext(),error.toString()));
        requestQueue.add(jsonObjectRequest);

    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarArticle);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbarArticle.setNavigationOnClickListener(v -> finish());
    }

    private void AnhXa() {
        toolbarArticle = findViewById(R.id.toolbar_article);
        listView = findViewById(R.id.listview_article);
        articleArrayList = new ArrayList<>();
        articleAdapter = new ArticleAdapter(articleArrayList,getApplicationContext());
        listView.setAdapter(articleAdapter);
    }
}