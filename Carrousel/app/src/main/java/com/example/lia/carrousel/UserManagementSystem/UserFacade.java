package com.example.lia.carrousel.UserManagementSystem;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.Nullable;
import android.view.Display;

import com.example.lia.carrousel.Activities.MainPageActivity;
import com.example.lia.carrousel.Activities.ResearchResultsActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Lia on 2016-06-24.
 */
public class UserFacade {

    public User getUser(String username, String password,DBUser dbUser){
        return dbUser.getUser(username,password);
    }

    public User getUser(String username,DBUser dbUser){
        return dbUser.getUser(username);
    }

    public void createParent(String username, String password, DBUser dbUser){
        dbUser.createParent(username,password);
    }

    public void createGardien(String username, String password, DBUser dbUser){
        dbUser.createGardien(username,password);
    }

    public void updateEntry(String username,String columnName,String value, DBUser dbUser){
        dbUser.updateEntry(username,columnName,value);
    }

    public void updateEntry(String username,String columnName,boolean value, DBUser dbUser){
        dbUser.updateEntry(username,columnName,value);
    }

    public void updateEntry(String username,String columnName,int value, DBUser dbUser){
        dbUser.updateEntry(username,columnName,value);
    }

    public ArrayList<User> getPotentialParents(DBUser dbUser){
        return dbUser.getPotentialParents();
    }

    public ArrayList<User> getPotentialGardiens(DBUser dbUser){
        return dbUser.getPotentialGardiens();
    }

    public ArrayList<User> getPotentialGardiens(ArrayList<Map.Entry<String,Object>> columnList, DBUser dbUser, ResearchResultsActivity activity){
        return dbUser.getPotentialGardiens(columnList, activity);
    }
}
