package com.example.leartsshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.leartsshop.R;
import com.example.leartsshop.ultil.CheckConnection;
import com.example.leartsshop.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CustomerInfo extends AppCompatActivity {
    EditText edtCustomerName,edtEmail,edtPhoneNumber;
    Button btnConfirm,btnBack;
    SharedPreferences userPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_info);
        AnhXa();
        btnBack.setOnClickListener(v -> finish());
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            EventButton();
        }else{
            CheckConnection.ShowToast_Short(getApplicationContext(),"Kiểm tra kết nối");
        }
    }

    private void EventButton() {
        btnConfirm.setOnClickListener(v -> {

            final String note = edtCustomerName.getText().toString().trim();
            final String phone = edtPhoneNumber.getText().toString().trim();
            final String address = edtEmail.getText().toString().trim();

            if(note.length()>0 && address.length()>0 && phone.length()>0) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.url_order, order_id -> {
                    Log.d("ma don hang",order_id);
                    if(order_id != null) {
                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                        StringRequest request = new StringRequest(Request.Method.POST, Server.url_order_detail, response -> {
                            if(response != "0") {
                                MainActivity.arrCart.clear();
                                CheckConnection.ShowToast_Short(getApplicationContext(),"Đặt hàng thành công");
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                CheckConnection.ShowToast_Short(getApplicationContext(),"Mời bạn tiếp tục mua sắm");
                            }else{
                                CheckConnection.ShowToast_Short(getApplicationContext(),"Dữ liệu giỏ hàng bị lỗi");
                            }
                        }, error -> {

                        }) {
                            @Override
                            protected Map<String, String> getParams() {
                                JSONArray jsonArray = new JSONArray();
                                for (int i = 0; i < MainActivity.arrCart.size(); i++) {
                                    JSONObject jsonObject = new JSONObject();
                                    try {
                                        jsonObject.put("order_id", order_id);
                                        jsonObject.put("product_id", MainActivity.arrCart.get(i).getProductId());
                                        jsonObject.put("price", MainActivity.arrCart.get(i).getPrice());
                                        jsonObject.put("quantity", MainActivity.arrCart.get(i).getQuantity());
                                        jsonObject.put("amount", MainActivity.arrCart.get(i).getAmount());

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    jsonArray.put(jsonObject);
                                }
                                HashMap<String, String> hashMap = new HashMap<String, String>();
                                hashMap.put("json", jsonArray.toString());
                                return hashMap;
                            }
                        };
                        queue.add(request);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        int user_id = userPref.getInt("id",0);
                        HashMap<String,String> hashMap= new HashMap<String,String>();
                        hashMap.put("note",note);
                        hashMap.put("address",address);
                        hashMap.put("phone",phone);
                        hashMap.put("user_id", String.valueOf(user_id));

                        return hashMap;
                    }
                };
                requestQueue.add(stringRequest);
            }else{
                CheckConnection.ShowToast_Short(getApplicationContext(),"Kiem Tra Lai Du Lieu");
            }
        });
    }

    private void AnhXa() {
        edtCustomerName = findViewById(R.id.edittext_customer_name);
        edtEmail  =findViewById(R.id.edittext_email);
        edtPhoneNumber = findViewById(R.id.edittext_phone_number);
        btnConfirm = findViewById(R.id.button_confirm);
        btnBack = findViewById(R.id.button_back);
        userPref = getApplicationContext().getSharedPreferences("user",getApplicationContext().MODE_PRIVATE);    }

}