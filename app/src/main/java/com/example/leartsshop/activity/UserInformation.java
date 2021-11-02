package com.example.leartsshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.leartsshop.R;
import com.example.leartsshop.ultil.CheckConnection;

public class UserInformation extends AppCompatActivity {
    Button btn_logout;
    TextView user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        btn_logout = (Button)findViewById(R.id.button);
        user = findViewById(R.id.username);
        user.setText(MainActivity.username.toString());
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.is_login = false;
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                CheckConnection.ShowToast_Short(getApplicationContext(),"Logout Success");
            }
        });
    }
}