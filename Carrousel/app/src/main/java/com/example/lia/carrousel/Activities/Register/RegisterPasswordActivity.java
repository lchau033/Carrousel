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

public class RegisterPasswordActivity extends AppCompatActivity {
    private Intent intent;
    private String username;
    private String nom;
    private String prenom;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_password);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.carousel);


        intent = getIntent();
        username = intent.getStringExtra(User.UserEntry.COLUMN_NAME_USERNAME);
        nom = intent.getStringExtra(User.UserEntry.COLUMN_NAME_NOM);
        prenom = intent.getStringExtra(User.UserEntry.COLUMN_NAME_PRENOM);
    }

    public void previous(View v){
        intent = new Intent(this,RegisterNamesActivity.class);
        intent.putExtra(User.UserEntry.COLUMN_NAME_USERNAME,username);
        intent.putExtra(User.UserEntry.COLUMN_NAME_PRENOM,prenom);
        intent.putExtra(User.UserEntry.COLUMN_NAME_NOM,nom);
        startActivity(intent);
    }

    public void suivant(View v){
        EditText passwordEditText = (EditText) findViewById(R.id.password);
        EditText confirmPasswordEditText = (EditText) findViewById(R.id.confirmPassword);
        if(passwordEditText.getText().toString().equals("") || confirmPasswordEditText.getText().toString().equals("")){
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
        }else if(!passwordEditText.getText().toString().equals(confirmPasswordEditText.getText().toString())){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alerte")
                    .setMessage("Les mots de passe ne correspondent pas!")
                    .setCancelable(false)
                    .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }else{
            password = passwordEditText.getText().toString();
            intent = new Intent(this,RegisterAddressActivity.class);
            intent.putExtra(User.UserEntry.COLUMN_NAME_USERNAME,username);
            intent.putExtra(User.UserEntry.COLUMN_NAME_PRENOM,prenom);
            intent.putExtra(User.UserEntry.COLUMN_NAME_NOM,nom);
            intent.putExtra(User.UserEntry.COLUMN_NAME_PASSWORD,password);
            startActivity(intent);
        }
    }
}
