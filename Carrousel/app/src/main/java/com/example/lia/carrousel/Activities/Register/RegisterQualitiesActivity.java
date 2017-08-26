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

public class RegisterQualitiesActivity extends AppCompatActivity {

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
    private boolean nonSmocker;
    private boolean canCook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_qualities);

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
        math = intent.getBooleanExtra(User.UserEntry.COLUMN_NAME_HELP_WITH_MATH,false);
        sciences = intent.getBooleanExtra(User.UserEntry.COLUMN_NAME_HELP_WITH_SCIENCE,false);
        languages = intent.getBooleanExtra(User.UserEntry.COLUMN_NAME_HELP_WITH_LANGUAGE_CLASSES,false);
    }

    public void previous(View v){
        intent = new Intent(this,RegisterClassesActivity.class);
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
        startActivity(intent);
    }

    public void suivant(View v){
        CheckBox nonSmockerCheckbox = (CheckBox) findViewById(R.id.nonSmocker);
        nonSmocker = nonSmockerCheckbox.isChecked();

        CheckBox canCookCheckbox = (CheckBox) findViewById(R.id.canCook);
        canCook = canCookCheckbox.isChecked();

        intent = new Intent(this,RegisterAvailabilityActivity.class);
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
        intent.putExtra(User.UserEntry.COLUMN_NAME_NON_SMOCKER,nonSmocker);
        intent.putExtra(User.UserEntry.COLUMN_NAME_APTITUDES_CULINAIRES,canCook);
        startActivity(intent);
    }
}
