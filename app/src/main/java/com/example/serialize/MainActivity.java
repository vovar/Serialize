package com.example.serialize;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    Profile userProfile;
    EditText etFirstName, etSecondName;
    Button btn1;
    SharedPreferences sPref;

    private void saveText() {
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        Gson gson = new Gson();
        String jsonString = gson.toJson(userProfile);
        editor.putString("Profile", jsonString);
        editor.commit();
        Toast.makeText(MainActivity.this,"Saved", Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        userProfile = new Profile();
        etFirstName = findViewById(R.id.editText1);
        etSecondName = findViewById(R.id.editText2);
        btn1 = findViewById(R.id.button1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userProfile.setfirstName(etFirstName.getText().toString());
                userProfile.setlastName(etSecondName.getText().toString());

                Intent intent = new Intent(MainActivity.this, ProfileView.class);
                intent.putExtra("profile_key", userProfile);
                startActivity(intent);

                String str = userProfile.toString();

//                Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });





    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }
}
