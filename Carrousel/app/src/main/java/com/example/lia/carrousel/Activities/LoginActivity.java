package com.example.lia.carrousel.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lia.carrousel.Activities.Register.RegisterActivity;
import com.example.lia.carrousel.Activities.Register.RegisterNamesActivity;
import com.example.lia.carrousel.R;
import com.example.lia.carrousel.UserManagementSystem.DBUser;
import com.example.lia.carrousel.UserManagementSystem.User;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{


    private String email;
    private String password;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.carousel);
    }



    public void signIn(View v){
        boolean activity = true;
        EditText emailEditText = (EditText) findViewById(R.id.email);
        EditText passwordEditText = (EditText) findViewById(R.id.password);
        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();
        DBUser dbUser = new DBUser(getApplicationContext());
        User user = CarrouselManagementSystem.getUser(email,password,dbUser);
        User user1 = CarrouselManagementSystem.getUser(email,dbUser);
        if (user!=null){
            intent = new Intent(this,MainPageActivity.class);
            intent.putExtra("user",user);
        }else if(user1!=null) {
            activity = false;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alerte")
                    .setMessage("Vous n'avez pas rentrer le bon mot de passe!")
                    .setCancelable(false)
                    .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }else{
            intent = new Intent(this, RegisterNamesActivity.class);
            intent.putExtra(User.UserEntry.COLUMN_NAME_USERNAME,email);
            intent.putExtra(User.UserEntry.COLUMN_NAME_PASSWORD,password);
        }
        if(activity) {
            startActivity(intent);
        }
    }





}

