package com.example.lia.carrousel.Activities.Register;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.lia.carrousel.R;
import com.example.lia.carrousel.UserManagementSystem.User;

public class RegisterActivity extends AppCompatActivity {

    private Intent intent;
    private String username;
    private String userType;
    private String nom;
    private String prenom;
    private String password;
    private String postalCode;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.carousel);


        intent = getIntent();
        username = intent.getStringExtra(User.UserEntry.COLUMN_NAME_USERNAME);
        nom = intent.getStringExtra(User.UserEntry.COLUMN_NAME_NOM);
        prenom = intent.getStringExtra(User.UserEntry.COLUMN_NAME_PRENOM);
        password = intent.getStringExtra(User.UserEntry.COLUMN_NAME_PASSWORD);
        postalCode = intent.getStringExtra(User.UserEntry.COLUMN_NAME_POSTAL_CODE);
        phoneNumber = intent.getStringExtra(User.UserEntry.COLUMN_NAME_PHONE_NUMBER);

    }

    public void previous(View v){
        intent = new Intent(this,RegisterAddressActivity.class);
        intent.putExtra(User.UserEntry.COLUMN_NAME_USERNAME,username);
        intent.putExtra(User.UserEntry.COLUMN_NAME_PRENOM,prenom);
        intent.putExtra(User.UserEntry.COLUMN_NAME_NOM,nom);
        intent.putExtra(User.UserEntry.COLUMN_NAME_PASSWORD,password);
        intent.putExtra(User.UserEntry.COLUMN_NAME_POSTAL_CODE,postalCode);
        intent.putExtra(User.UserEntry.COLUMN_NAME_PHONE_NUMBER,phoneNumber);
        startActivity(intent);
    }

    public void suivant(View v){
        RadioGroup userTypeRadioGroup = (RadioGroup) findViewById(R.id.radioGroupUserType);
        int selectedId = userTypeRadioGroup.getCheckedRadioButtonId();

        RadioButton userTypeRadioButton = (RadioButton) findViewById(selectedId);
        if(userTypeRadioButton.getText().toString().toLowerCase().equals(User.UserEntry.GARDIEN_TYPE)){
            userType = User.UserEntry.GARDIEN_TYPE;
            intent = new Intent(this,RegisterDescriptionGardienActivity.class);
        }else{
            userType = User.UserEntry.PARENT_TYPE;
            intent = new Intent(this,RegisterDescriptionActivity.class);
        }
        intent.putExtra(User.UserEntry.COLUMN_NAME_USERNAME,username);
        intent.putExtra(User.UserEntry.COLUMN_NAME_TYPE,userType);
        intent.putExtra(User.UserEntry.COLUMN_NAME_PRENOM,prenom);
        intent.putExtra(User.UserEntry.COLUMN_NAME_NOM,nom);
        intent.putExtra(User.UserEntry.COLUMN_NAME_PASSWORD,password);
        intent.putExtra(User.UserEntry.COLUMN_NAME_POSTAL_CODE,postalCode);
        intent.putExtra(User.UserEntry.COLUMN_NAME_PHONE_NUMBER,phoneNumber);
        startActivity(intent);
    }
}
