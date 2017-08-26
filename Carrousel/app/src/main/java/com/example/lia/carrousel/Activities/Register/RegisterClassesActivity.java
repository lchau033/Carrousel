package com.example.lia.carrousel.Activities.Register;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.CheckBox;

import com.example.lia.carrousel.R;
import com.example.lia.carrousel.UserManagementSystem.User;

public class RegisterClassesActivity extends AppCompatActivity {

    private Intent intent;
    private String username;
    private String userType;
    private String nom;
    private String prenom;
    private String password;
    private String postalCode;
    private String phoneNumber;
    private String description;
    private String sexe;
    private int age;
    private boolean french;
    private boolean english;
    private boolean spanish;
    private boolean math;
    private boolean languages;
    private boolean sciences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_classes);

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
        userType = intent.getStringExtra(User.UserEntry.COLUMN_NAME_TYPE);
        description = intent.getStringExtra(User.UserEntry.COLUMN_NAME_DESCRIPTION);
        age = intent.getIntExtra(User.UserEntry.COLUMN_NAME_AGE,18);
        sexe = intent.getStringExtra(User.UserEntry.COLUMN_NAME_GENDER);
        french = intent.getBooleanExtra(User.UserEntry.COLUMN_NAME_SPEAKS_FRENCH,false);
        english = intent.getBooleanExtra(User.UserEntry.COLUMN_NAME_SPEAKS_ENGLISH,false);
        spanish = intent.getBooleanExtra(User.UserEntry.COLUMN_NAME_SPEAKS_SPANISH,false);
    }

    public void previous(View v){
        intent = new Intent(this,RegisterLanguageActivity.class);
        intent = new Intent(this,RegisterLanguageActivity.class);
        intent.putExtra(User.UserEntry.COLUMN_NAME_USERNAME,username);
        intent.putExtra(User.UserEntry.COLUMN_NAME_TYPE,userType);
        intent.putExtra(User.UserEntry.COLUMN_NAME_PRENOM,prenom);
        intent.putExtra(User.UserEntry.COLUMN_NAME_NOM,nom);
        intent.putExtra(User.UserEntry.COLUMN_NAME_PASSWORD,password);
        intent.putExtra(User.UserEntry.COLUMN_NAME_POSTAL_CODE,postalCode);
        intent.putExtra(User.UserEntry.COLUMN_NAME_PHONE_NUMBER,phoneNumber);
        intent.putExtra(User.UserEntry.COLUMN_NAME_DESCRIPTION,description);
        intent.putExtra(User.UserEntry.COLUMN_NAME_AGE,age);
        intent.putExtra(User.UserEntry.COLUMN_NAME_GENDER,sexe);
        startActivity(intent);
    }

    public void suivant(View v){
        CheckBox mathCheckbox = (CheckBox) findViewById(R.id.math);
        math = mathCheckbox.isChecked();

        CheckBox languesCheckbox = (CheckBox) findViewById(R.id.langues);
        languages = languesCheckbox.isChecked();

        CheckBox sciencesCheckbox = (CheckBox) findViewById(R.id.science);
        sciences = sciencesCheckbox.isChecked();

        intent = new Intent(this,RegisterQualitiesActivity.class);
        intent.putExtra(User.UserEntry.COLUMN_NAME_USERNAME,username);
        intent.putExtra(User.UserEntry.COLUMN_NAME_TYPE,userType);
        intent.putExtra(User.UserEntry.COLUMN_NAME_PRENOM,prenom);
        intent.putExtra(User.UserEntry.COLUMN_NAME_NOM,nom);
        intent.putExtra(User.UserEntry.COLUMN_NAME_PASSWORD,password);
        intent.putExtra(User.UserEntry.COLUMN_NAME_POSTAL_CODE,postalCode);
        intent.putExtra(User.UserEntry.COLUMN_NAME_PHONE_NUMBER,phoneNumber);
        intent.putExtra(User.UserEntry.COLUMN_NAME_DESCRIPTION,description);
        intent.putExtra(User.UserEntry.COLUMN_NAME_AGE,age);
        intent.putExtra(User.UserEntry.COLUMN_NAME_GENDER,sexe);
        intent.putExtra(User.UserEntry.COLUMN_NAME_SPEAKS_FRENCH,french);
        intent.putExtra(User.UserEntry.COLUMN_NAME_SPEAKS_ENGLISH,english);
        intent.putExtra(User.UserEntry.COLUMN_NAME_SPEAKS_SPANISH,spanish);
        intent.putExtra(User.UserEntry.COLUMN_NAME_HELP_WITH_MATH,math);
        intent.putExtra(User.UserEntry.COLUMN_NAME_HELP_WITH_LANGUAGE_CLASSES,languages);
        intent.putExtra(User.UserEntry.COLUMN_NAME_HELP_WITH_SCIENCE,sciences);
        startActivity(intent);
    }

}
