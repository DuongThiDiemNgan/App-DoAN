package com.example.leartsshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignIn extends AppCompatActivity {
    TextInputLayout layoutUser,layoutPassword;
    TextInputEditText txtUser,txtPassword;
    TextView signup;
    Button sign_in;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        txtUser = findViewById(R.id.user_sign_in);
        txtPassword = findViewById(R.id.password);
        layoutUser = findViewById(R.id.txtLayoutUserSignIn);
        layoutPassword = findViewById(R.id.txtLayoutPasswordSignIn);
        dialog = new ProgressDialog(getApplicationContext());
        dialog.setCancelable(false);
        signup = findViewById(R.id.txtSignUp);
        sign_in = findViewById(R.id.sign_in);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });

        sign_in.setOnClickListener(v -> {
            if (validate()){
                login();
            }
        });

        txtUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!txtUser.getText().toString().isEmpty()){
                    layoutUser.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txtPassword.getText().toString().length()>7){
                    layoutPassword.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void login() {
        dialog.setMessage("Logging in");
        //dialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, Server.url_sign_in,response -> {
            //we get response if connection success
            try {
                JSONObject object = new JSONObject(response);
                if (response != null){
                    JSONObject user = object.getJSONObject("user");
                    //make shared preference user
                    SharedPreferences userPref = getApplicationContext().getSharedPreferences("user",getApplicationContext().MODE_PRIVATE);
                    SharedPreferences.Editor editor = userPref.edit();
                    editor.putString("status", object.getString("status"));
                    editor.putString("message",object.getString("message"));
                    editor.putString("access_token",object.getString("access_token"));
                    editor.putString("token_type",object.getString("token_type"));
                    editor.putInt("id",user.getInt("id"));
                    editor.putString("lastName",user.getString("lastName"));
                    editor.putString("firstName",user.getString("firstName"));
                    editor.putString("username",user.getString("username"));
                    editor.putString("avatar",user.getString("avatar"));
                    editor.putString("gender",user.getString("gender"));
                    editor.putString("phone",user.getString("phone"));
                    editor.putString("address",user.getString("address"));
                    editor.putString("email",user.getString("email"));
                    //editor.putString("password",user.getString("password"));
                    editor.putString("level",user.getString("level"));
                    editor.putString("birthday",user.getString("birthday"));
                    editor.putString("status",user.getString("status"));
                    editor.putBoolean("is_login",true);
                    editor.putString("expires_at",object.getString("expires_at"));
                    editor.apply();
                    //if success
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            dialog.dismiss();
        },error -> {
            // error if connection not success
            error.printStackTrace();
            dialog.dismiss();
        }){

            // add parameters


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("username",txtUser.getText().toString().trim());
                map.put("password",txtPassword.getText().toString());
                return map;
            }
        };

        //add this request to requestqueue
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    private boolean validate() {
        if (txtUser.getText().toString().isEmpty()){
            layoutUser.setErrorEnabled(true);
            layoutUser.setError("UserName is Required");
            return false;
        }
        if (txtPassword.getText().toString().length()<8){
            layoutPassword.setErrorEnabled(true);
            layoutPassword.setError("Required at least 8 characters");
            return false;
        }
        return true;
    }

}