package com.example.lia.carrousel.Activities.Register;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.lia.carrousel.R;
import com.example.lia.carrousel.UserManagementSystem.User;

public class RegisterDescriptionGardienActivity extends AppCompatActivity {


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_description_gardien);

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

        NumberPicker ageNumberPicker = (NumberPicker) findViewById(R.id.age);
        ageNumberPicker.setMaxValue(80);
        ageNumberPicker.setMinValue(18);
    }

    public void previous(View v){
        intent = new Intent(this,RegisterActivity.class);
        intent.putExtra(User.UserEntry.COLUMN_NAME_USERNAME,username);
        intent.putExtra(User.UserEntry.COLUMN_NAME_TYPE,userType);
        intent.putExtra(User.UserEntry.COLUMN_NAME_PRENOM,prenom);
        intent.putExtra(User.UserEntry.COLUMN_NAME_NOM,nom);
        intent.putExtra(User.UserEntry.COLUMN_NAME_PASSWORD,password);
        intent.putExtra(User.UserEntry.COLUMN_NAME_POSTAL_CODE,postalCode);
        intent.putExtra(User.UserEntry.COLUMN_NAME_PHONE_NUMBER,phoneNumber);
        startActivity(intent);
    }

    public void suivant(View v){
        EditText descriptionEditText = (EditText) findViewById(R.id.description);
        RadioGroup sexeRadioGroup = (RadioGroup) findViewById(R.id.radioGroupSexe);
        int selectedId = sexeRadioGroup.getCheckedRadioButtonId();
        RadioButton radioButtonSexe = (RadioButton) findViewById(selectedId);
        sexe = radioButtonSexe.getText().toString().toLowerCase();

        NumberPicker ageNumberPicker = (NumberPicker) findViewById(R.id.age);
        age = ageNumberPicker.getValue();

        if(descriptionEditText.getText().equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alerte")
                    .setMessage("Veuillez remplir tous les champs!")
                    .setCancelable(false)
                    .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }else{
            description = descriptionEditText.getText().toString();
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
    }
}
