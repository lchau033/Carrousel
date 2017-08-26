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

import com.example.lia.carrousel.Activities.CarrouselManagementSystem;
import com.example.lia.carrousel.Activities.LoginActivity;
import com.example.lia.carrousel.R;
import com.example.lia.carrousel.UserManagementSystem.DBUser;
import com.example.lia.carrousel.UserManagementSystem.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterNamesActivity extends AppCompatActivity {
    private Intent intent;
    private String username;
    private String prenom;
    private String nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_names);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.carousel);


        intent = getIntent();
        username = intent.getStringExtra(User.UserEntry.COLUMN_NAME_USERNAME);


        if(!username.equals("") && isEmailValid(username)){
            EditText emailEditText = (EditText) findViewById(R.id.email);
            emailEditText.setText(username);
        }
    }

    public void previous(View v){
        intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void suivant(View v){
        EditText prenomEditText = (EditText) findViewById(R.id.prenom);
        EditText nomEditText = (EditText) findViewById(R.id.nom);
        EditText emailEditText = (EditText) findViewById(R.id.email);

        if(prenomEditText.getText().toString().equals("") || nomEditText.getText().toString().equals("") || emailEditText.getText().toString().equals("")){
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
        } else if(!isEmailValid(emailEditText.getText().toString())) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alerte")
                    .setMessage("Votre adresse email est invalide!")
                    .setCancelable(false)
                    .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }else{
            DBUser dbUser = new DBUser(getBaseContext());
            User user1 = CarrouselManagementSystem.getUser(emailEditText.getText().toString(),dbUser);
            if(user1!=null){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Alerte")
                        .setMessage("Désolé ce nom d'utilisateur a déjà été pris!")
                        .setCancelable(false)
                        .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }else {
                prenom = prenomEditText.getText().toString();
                nom = nomEditText.getText().toString();
                username = emailEditText.getText().toString();
                intent = new Intent(this, RegisterPasswordActivity.class);
                intent.putExtra(User.UserEntry.COLUMN_NAME_USERNAME, username);
                intent.putExtra(User.UserEntry.COLUMN_NAME_PRENOM, prenom);
                intent.putExtra(User.UserEntry.COLUMN_NAME_NOM, nom);
                startActivity(intent);
            }
        }


    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}
