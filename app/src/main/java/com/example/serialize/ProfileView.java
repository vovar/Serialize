package com.example.serialize;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class ProfileView extends AppCompatActivity {

    TextView textView;
    SharedPreferences sPref;

    private void loadText() {
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);

        Gson gson = new Gson();
        String jsonString = sPref.getString("Profile", "");
        Profile userProfile = gson.fromJson(jsonString, Profile.class);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_veiw);

        textView = findViewById(R.id.textView);

        Profile userProfile = (Profile) getIntent().getSerializableExtra("profile_key");

//        textView.setText(userProfile.toString());

        textView.setText(userProfile.getfirstName() + " " + userProfile.getlastName());


//      userProfile.getfirstName();
//        userProfile.getlastName();
//
//        if(userProfile != null) {
//
//        }

    }
    @Override
    protected void onStart() {
        super.onStart();
        loadText();



    }

}
