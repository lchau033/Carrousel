package com.example.lia.carrousel.Activities.Register;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.lia.carrousel.Activities.CarrouselManagementSystem;
import com.example.lia.carrousel.Activities.MainPageActivity;
import com.example.lia.carrousel.R;
import com.example.lia.carrousel.UserManagementSystem.DBUser;
import com.example.lia.carrousel.UserManagementSystem.User;

public class RegisterAvailabilityActivity extends AppCompatActivity {

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
    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;
    private boolean sunday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_availability);

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
        nonSmocker = intent.getBooleanExtra(User.UserEntry.COLUMN_NAME_NON_SMOCKER,false);
        canCook = intent.getBooleanExtra(User.UserEntry.COLUMN_NAME_APTITUDES_CULINAIRES,false);
    }

    public void previous(View v){
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

    public void suivant(View v){
        CheckBox lundiCheckbox = (CheckBox) findViewById(R.id.lundi);
        monday = lundiCheckbox.isChecked();

        CheckBox mardiCheckbox = (CheckBox) findViewById(R.id.mardi);
        tuesday = mardiCheckbox.isChecked();

        CheckBox mercrediCheckbox = (CheckBox) findViewById(R.id.mercredi);
        wednesday = mercrediCheckbox.isChecked();

        CheckBox jeudiCheckbox = (CheckBox) findViewById(R.id.jeudi);
        thursday = jeudiCheckbox.isChecked();

        CheckBox vendrediCheckbox = (CheckBox) findViewById(R.id.vendredi);
        friday = vendrediCheckbox.isChecked();

        CheckBox samediCheckbox = (CheckBox) findViewById(R.id.samedi);
        saturday = samediCheckbox.isChecked();

        CheckBox dimancheCheckbox = (CheckBox) findViewById(R.id.dimanche);
        sunday = dimancheCheckbox.isChecked();


        DBUser dbUser = new DBUser(getBaseContext());
        User user = CarrouselManagementSystem.registerGardien(username,password,prenom,nom,age,sexe,description,
                postalCode,phoneNumber,french,english,spanish,math,sciences,languages,nonSmocker,canCook,monday,
                tuesday,wednesday,thursday,friday,saturday,sunday,dbUser);
        user.setType(User.UserEntry.GARDIEN_TYPE);
        intent = new Intent(this,MainPageActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
}
