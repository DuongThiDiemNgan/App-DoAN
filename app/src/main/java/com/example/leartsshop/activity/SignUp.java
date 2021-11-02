package com.example.leartsshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static java.security.AccessController.getContext;

public class SignUp extends AppCompatActivity {
    TextInputLayout layoutUser,layoutPassword,layoutConfirm, layoutLastname, layoutFirstname, layoutEmail;
    TextInputEditText txtUsername,txtPassword,txtConfirm, txtLastname, txtFirstname, txtEmail;
    Button signup;
    TextView sign_in;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        AnhXa();
    }

    private void AnhXa() {
        txtUsername = findViewById(R.id.user_sign_up);
        txtPassword = findViewById(R.id.password);
        txtConfirm = findViewById(R.id.re_password);
        layoutPassword = findViewById(R.id.txtLayoutPasswordSignUp);
        layoutUser = findViewById(R.id.txtLayoutUserSignUp);
        layoutConfirm = findViewById(R.id.txtLayoutConfirmSignUp);
        txtEmail = findViewById(R.id.email_sign_up);
        txtLastname = findViewById(R.id.last_sign_up);
        txtFirstname = findViewById(R.id.first_sign_up);
        layoutEmail = findViewById(R.id.txtLayoutEmailSignUp);
        layoutFirstname = findViewById(R.id.txtLayoutFirstSignUp);
        layoutLastname = findViewById(R.id.txtLayoutLastSignUp);
        dialog = new ProgressDialog(getApplicationContext());
        dialog.setCancelable(false);
        signup = findViewById(R.id.signup);
        sign_in = findViewById(R.id.txtSignIn);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),SignIn.class);
                startActivity(intent);
            }
        });
        signup.setOnClickListener(v -> {
            if (validate()) {
                register();
            }
        });

        txtUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txtUsername.getText().toString().isEmpty()){
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

        txtConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txtConfirm.getText().toString().equals(txtPassword.getText().toString())){
                    layoutConfirm.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtFirstname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txtFirstname.getText().toString().isEmpty()){
                    layoutFirstname.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtLastname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txtLastname.getText().toString().isEmpty()){
                    layoutLastname.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txtEmail.getText().toString().isEmpty()){
                    layoutEmail.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void register() {
        dialog.setMessage("Registering");
        //dialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, Server.url_sign_up, response -> {
            //we get response if connection success
            try {
                JSONObject object = new JSONObject(response);
                if (response != null){
                    JSONObject user = object.getJSONObject("data");
                    //make shared preference user
                    SharedPreferences userPref = getApplicationContext().getSharedPreferences("data",getApplicationContext().MODE_PRIVATE);
                    SharedPreferences.Editor editor = userPref.edit();
                    editor.putInt("success", object.getInt("success"));
                    editor.putString("firstName",user.getString("firstName"));
                    editor.putString("lastName",user.getString("lastName"));
                    editor.putString("username",user.getString("username"));
                    //editor.putString("avatar",user.getString("avatar"));
                    editor.putString("gender",user.getString("gender"));
                    editor.putString("phone",user.getString("phone"));
                    editor.putString("address",user.getString("address"));
                    editor.putString("email",user.getString("email"));
                    //editor.putString("password",user.getString("password"));
                    editor.putInt("level",user.getInt("level"));
                    editor.putInt("status",user.getInt("status"));
                    editor.putInt("id",user.getInt("id"));
                    editor.putString("code", user.getString("code"));
                    editor.putString("status", object.getString("status"));
                    editor.putString("message", object.getString("message"));
                    editor.apply();
                    //if success
                    Intent intent = new Intent(getApplicationContext(),SignIn.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Register Success", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //dialog.dismiss();

        },error -> {
            // error if connection not success
            error.printStackTrace();
            //dialog.dismiss();
        }){

            // add parameters


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("firstName",txtFirstname.getText().toString().trim());
                map.put("lastName",txtLastname.getText().toString().trim());
                map.put("email",txtEmail.getText().toString().trim());
                map.put("username",txtUsername.getText().toString().trim());
                map.put("password",txtPassword.getText().toString());
                return map;
            }
        };

        //add this request to requestqueue
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    private boolean validate() {
        if (txtFirstname.getText().toString().isEmpty()){
            layoutFirstname.setErrorEnabled(true);
            layoutFirstname.setError("FirstName is Required");
            return false;
        }
        if (txtLastname.getText().toString().isEmpty()){
            layoutLastname.setErrorEnabled(true);
            layoutLastname.setError("LastName is Required");
            return false;
        }
        if (txtEmail.getText().toString().isEmpty()){
            layoutEmail.setErrorEnabled(true);
            layoutEmail.setError("Email is Required");
            return false;
        }
        if (txtUsername.getText().toString().isEmpty()){
            layoutUser.setErrorEnabled(true);
            layoutUser.setError("Username is Required");
            return false;
        }
        if (txtPassword.getText().toString().length()<8){
            layoutPassword.setErrorEnabled(true);
            layoutPassword.setError("Required at least 8 characters");
            return false;
        }
        if (!txtConfirm.getText().toString().equals(txtPassword.getText().toString())){
            layoutConfirm.setErrorEnabled(true);
            layoutConfirm.setError("Password does not match");
            return false;
        }


        return true;
    }
}