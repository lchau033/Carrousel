package com.example.lia.carrousel.Activities.Register;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;

import com.example.lia.carrousel.R;
import com.example.lia.carrousel.UserManagementSystem.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterAddressActivity extends AppCompatActivity {
    private Intent intent;
    private String username;
    private String nom;
    private String prenom;
    private String password;
    private String postalCode;
    private String phoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_address);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.carousel);


        intent = getIntent();
        username = intent.getStringExtra(User.UserEntry.COLUMN_NAME_USERNAME);
        nom = intent.getStringExtra(User.UserEntry.COLUMN_NAME_NOM);
        prenom = intent.getStringExtra(User.UserEntry.COLUMN_NAME_PRENOM);
        password = intent.getStringExtra(User.UserEntry.COLUMN_NAME_PASSWORD);
    }

    public void previous(View v){
        intent = new Intent(this,RegisterPasswordActivity.class);
        intent.putExtra(User.UserEntry.COLUMN_NAME_USERNAME,username);
        intent.putExtra(User.UserEntry.COLUMN_NAME_PRENOM,prenom);
        intent.putExtra(User.UserEntry.COLUMN_NAME_NOM,nom);
        intent.putExtra(User.UserEntry.COLUMN_NAME_PASSWORD,password);
        startActivity(intent);
    }

    public void suivant(View v){
        EditText postalCodeEditText = (EditText) findViewById(R.id.postalCode);
        EditText phoneNumberEditText = (EditText) findViewById(R.id.phoneNumber);

        if(postalCodeEditText.getText().toString().equals("") || phoneNumberEditText.getText().toString().equals("")){
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
        }else if(!isPostalCodeValid(postalCodeEditText.getText().toString())){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alerte")
                    .setMessage("Veuillez renter un code postal valide!")
                    .setCancelable(false)
                    .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }else if(!isValidMobile(phoneNumberEditText.getText().toString())){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alerte")
                    .setMessage("Veuillez rentrer un numéro de téléphone valide!")
                    .setCancelable(false)
                    .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }else{
            postalCode = postalCodeEditText.getText().toString();
            phoneNumber = phoneNumberEditText.getText().toString();
            intent = new Intent(this,RegisterActivity.class);
            intent.putExtra(User.UserEntry.COLUMN_NAME_USERNAME,username);
            intent.putExtra(User.UserEntry.COLUMN_NAME_PRENOM,prenom);
            intent.putExtra(User.UserEntry.COLUMN_NAME_NOM,nom);
            intent.putExtra(User.UserEntry.COLUMN_NAME_PASSWORD,password);
            intent.putExtra(User.UserEntry.COLUMN_NAME_POSTAL_CODE,postalCode);
            intent.putExtra(User.UserEntry.COLUMN_NAME_PHONE_NUMBER,phoneNumber);
            startActivity(intent);
        }
    }

    public static boolean isPostalCodeValid(String postalCode) {
        boolean isValid = false;

        String expression = "^[ABCEGHJKLMNPRSTVXY]{1}\\d{1}[A-Z]{1} *\\d{1}[A-Z]{1}\\d{1}$";
        CharSequence inputStr = postalCode;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    private boolean isValidMobile(String phone)
    {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }


}
