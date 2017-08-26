package com.example.lia.carrousel.UserManagementSystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lia.carrousel.Activities.MainPageActivity;
import com.example.lia.carrousel.Activities.ResearchResultsActivity;
import com.example.lia.carrousel.DemandeManagementSystem.DBDemande;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Lia on 2016-06-24.
 */
public class DBUser extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Carrousel.db";

    public DBUser(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //context.deleteDatabase(DATABASE_NAME);
    }
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String TABLE_USER =
            "CREATE TABLE " + User.UserEntry.TABLE_NAME + " (" +
                    User.UserEntry.COLUMN_NAME_USERNAME + TEXT_TYPE +" PRIMARY KEY NOT NULL" + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_TYPE + TEXT_TYPE + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_EVALUATION + " INTEGER" + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_EVALUATION_TOTAL + " INTEGER" + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_NUM_EVALUATIONS + " INTEGER" + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_NON_SMOCKER + " INTEGER" + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_APTITUDES_CULINAIRES + " BOOLEAN" + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_SPEAKS_ENGLISH + " INTEGER" + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_SPEAKS_FRENCH + " INTEGER" + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_SPEAKS_SPANISH + " INTEGER" + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_HELP_WITH_MATH + " INTEGER" + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_HELP_WITH_SCIENCE + " INTEGER" + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_HELP_WITH_LANGUAGE_CLASSES + " BOOLEAN" + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_PREVIOUS_EXPERIENCE + " INTEGER" + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_AVAILABLE_MONDAY + " INTEGER" + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_AVAILABLE_TUESDAY + " INTEGER" + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_AVAILABLE_WEDNESDAY + " INTEGER" + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_AVAILABLE_THURSDAY + " INTEGER" + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_AVAILABLE_FRIDAY + " INTEGER" + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_AVAILABLE_SATURDAY + " INTEGER" + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_AVAILABLE_SUNDAY + " INTEGER" + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_GENDER + TEXT_TYPE + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_NOM + TEXT_TYPE + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_PRENOM + TEXT_TYPE + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_PHONE_NUMBER + TEXT_TYPE + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_POSTAL_CODE + TEXT_TYPE  + COMMA_SEP +
                    User.UserEntry.COLUMN_NAME_AGE + " INTEGER" + ")";

    public void createGardien(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        if(db.isOpen()) {
            db.beginTransaction();
            ContentValues values = new ContentValues();
            values.put(User.UserEntry.COLUMN_NAME_USERNAME, username);
            values.put(User.UserEntry.COLUMN_NAME_PASSWORD, password);
            values.put(User.UserEntry.COLUMN_NAME_TYPE, User.UserEntry.GARDIEN_TYPE);
            String where = User.UserEntry.COLUMN_NAME_USERNAME + "= ?";
            String[] value = new String[1];
            value[0] = username;
            int rowsAffected = db.update(User.UserEntry.TABLE_NAME,values,where,value);
            if(rowsAffected==0){
                db.insert(User.UserEntry.TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        }
        db.close();
    }

    public void updateEntry(String username,String columnName,String value){
        SQLiteDatabase db = this.getWritableDatabase();
        if(db.isOpen()){
            db.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put(columnName,value );
            db.update(User.UserEntry.TABLE_NAME, contentValues, User.UserEntry.COLUMN_NAME_USERNAME + " = ?", new String[] { username });
            db.setTransactionSuccessful();
            db.endTransaction();
        }

    }

    public void updateEntry(String username,String columnName, Boolean value){
        SQLiteDatabase db = this.getWritableDatabase();
        if(db.isOpen()){
            db.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put(columnName,(value ? 1 :0));
            db.update(User.UserEntry.TABLE_NAME, contentValues, User.UserEntry.COLUMN_NAME_USERNAME + " = ?", new String[] { username });
            db.setTransactionSuccessful();
            db.endTransaction();
        }
    }

    public void updateEntry(String username,String columnName, Integer value){
        SQLiteDatabase db = this.getWritableDatabase();
        if(db.isOpen()){
            db.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put(columnName,value );
            db.update(User.UserEntry.TABLE_NAME, contentValues, User.UserEntry.COLUMN_NAME_USERNAME + " = ?", new String[] { username });
            db.setTransactionSuccessful();
            db.endTransaction();
        }
    }

    public void createParent(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        if(db.isOpen()) {
            db.beginTransaction();
            ContentValues values = new ContentValues();
            values.put(User.UserEntry.COLUMN_NAME_USERNAME, username);
            values.put(User.UserEntry.COLUMN_NAME_PASSWORD, password);
            values.put(User.UserEntry.COLUMN_NAME_TYPE, User.UserEntry.PARENT_TYPE);
            String where = User.UserEntry.COLUMN_NAME_USERNAME + "= ?";
            String[] value = new String[1];
            value[0] = username;
            int rowsAffected = db.update(User.UserEntry.TABLE_NAME,values,where,value);
            if(rowsAffected==0){
                db.insert(User.UserEntry.TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        }
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USER);
        db.execSQL(DBDemande.TABLE_DEMANDE);
    }

    public User getUser(String username,String password) {
        User user = null;
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + User.UserEntry.TABLE_NAME + " where " +
                User.UserEntry.COLUMN_NAME_USERNAME +" ='"+username+"'" +
                " and " + User.UserEntry.COLUMN_NAME_PASSWORD +" ='"+password+"'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                user = new User(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(26),cursor.getString(25),
                    cursor.getString(23), cursor.getString(24),cursor.getString(25));
                if(user.getType().equals(User.UserEntry.GARDIEN_TYPE)){
                    user.setAge(cursor.getInt(28));
                    user.setSexe(cursor.getString(22));
                    boolean[] faculties = new boolean[16];
                    faculties[User.NON_SMOCKER] = (cursor.getInt(6) == 1 ? true : false);
                    faculties[User.APTITUDES_CULINAIRES] = (cursor.getInt(7) == 1 ? true : false);
                    faculties[User.SPEAKS_ENGLISH] = (cursor.getInt(8) == 1 ? true : false);
                    faculties[User.SPEAKS_FRENCH] = (cursor.getInt(9) == 1 ? true : false);
                    faculties[User.SPEAKS_SPANISH] = (cursor.getInt(10) == 1 ? true : false);
                    faculties[User.HELP_WITH_MATH] = (cursor.getInt(11) == 1 ? true : false);
                    faculties[User.HELP_WITH_LANGUAGE_CLASSES] = (cursor.getInt(13) == 1 ? true : false);
                    faculties[User.HELP_WITH_SCIENCE] = (cursor.getInt(12) == 1 ? true : false);
                    faculties[User.AVAILABLE_MONDAY] = (cursor.getInt(15) == 1 ? true : false);
                    faculties[User.AVAILABLE_TUESDAY] = (cursor.getInt(16) == 1 ? true : false);
                    faculties[User.AVAILABLE_WEDNESDAY] = (cursor.getInt(17) == 1 ? true : false);
                    faculties[User.AVAILABLE_THURSDAY] = (cursor.getInt(18) == 1 ? true : false);
                    faculties[User.AVAILABLE_FRIDAY] = (cursor.getInt(19) == 1 ? true : false);
                    faculties[User.AVAILABLE_SATURDAY] = (cursor.getInt(20) == 1 ? true : false);
                    faculties[User.AVAILABLE_SUNDAY] = (cursor.getInt(21) == 1 ? true : false);

                }

            } while (cursor.moveToNext());
        }
        return user;
    }

    public User getUser(String username) {
        User user = null;
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + User.UserEntry.TABLE_NAME + " where " +
                User.UserEntry.COLUMN_NAME_USERNAME +" ='"+username+"'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                user = new User(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(27),cursor.getString(26),
                        cursor.getString(23), cursor.getString(24),cursor.getString(25));
                if(user.getType().equals(User.UserEntry.GARDIEN_TYPE)){
                    user.setAge(cursor.getInt(28));
                    user.setSexe(cursor.getString(22));
                    boolean[] faculties = new boolean[16];
                    faculties[User.NON_SMOCKER] = (cursor.getInt(6) == 1 ? true : false);
                    faculties[User.APTITUDES_CULINAIRES] = (cursor.getInt(7) == 1 ? true : false);
                    faculties[User.SPEAKS_ENGLISH] = (cursor.getInt(8) == 1 ? true : false);
                    faculties[User.SPEAKS_FRENCH] = (cursor.getInt(9) == 1 ? true : false);
                    faculties[User.SPEAKS_SPANISH] = (cursor.getInt(10) == 1 ? true : false);
                    faculties[User.HELP_WITH_MATH] = (cursor.getInt(11) == 1 ? true : false);
                    faculties[User.HELP_WITH_LANGUAGE_CLASSES] = (cursor.getInt(13) == 1 ? true : false);
                    faculties[User.HELP_WITH_SCIENCE] = (cursor.getInt(12) == 1 ? true : false);
                    faculties[User.AVAILABLE_MONDAY] = (cursor.getInt(15) == 1 ? true : false);
                    faculties[User.AVAILABLE_TUESDAY] = (cursor.getInt(16) == 1 ? true : false);
                    faculties[User.AVAILABLE_WEDNESDAY] = (cursor.getInt(17) == 1 ? true : false);
                    faculties[User.AVAILABLE_THURSDAY] = (cursor.getInt(18) == 1 ? true : false);
                    faculties[User.AVAILABLE_FRIDAY] = (cursor.getInt(19) == 1 ? true : false);
                    faculties[User.AVAILABLE_SATURDAY] = (cursor.getInt(20) == 1 ? true : false);
                    faculties[User.AVAILABLE_SUNDAY] = (cursor.getInt(21) == 1 ? true : false);
                    user.setFaculties(faculties);

                }

            } while (cursor.moveToNext());
        }
        return user;
    }

    public ArrayList<User> getPotentialParents(){
        ArrayList<User> users = null;
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT " + User.UserEntry.COLUMN_NAME_USERNAME + COMMA_SEP +
                User.UserEntry.COLUMN_NAME_DESCRIPTION + " FROM " + User.UserEntry.TABLE_NAME + " where " +
                User.UserEntry.COLUMN_NAME_TYPE +" ='"+ User.UserEntry.PARENT_TYPE+"'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            users = new ArrayList<User>();
            do {
                users.add(new User(cursor.getString(0),cursor.getString(1), User.UserEntry.PARENT_TYPE));

            } while (cursor.moveToNext());
        }

        return users;
    }

    public ArrayList<User> getPotentialGardiens(){
        ArrayList<User> users = null;
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT " + User.UserEntry.COLUMN_NAME_USERNAME + COMMA_SEP +
                User.UserEntry.COLUMN_NAME_DESCRIPTION + " FROM " + User.UserEntry.TABLE_NAME + " where " +
                User.UserEntry.COLUMN_NAME_TYPE +" ='"+ User.UserEntry.GARDIEN_TYPE+"'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            users = new ArrayList<User>();
            do {
                users.add(new User(cursor.getString(0),cursor.getString(1), User.UserEntry.GARDIEN_TYPE));

            } while (cursor.moveToNext());
        }

        return users;
    }


    public ArrayList<User> getPotentialGardiens(ArrayList<Map.Entry<String,Object>> columnList, ResearchResultsActivity activity){
        ArrayList<User> users = null;
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT " + User.UserEntry.COLUMN_NAME_USERNAME + COMMA_SEP +
                User.UserEntry.COLUMN_NAME_DESCRIPTION + " FROM " + User.UserEntry.TABLE_NAME + " where " +
                User.UserEntry.COLUMN_NAME_TYPE +" ='"+ User.UserEntry.GARDIEN_TYPE+"' ";
        if(columnList!=null){
            Iterator<Map.Entry<String,Object>> it = columnList.iterator();
            while(it.hasNext()){
                Map.Entry<String,Object> entry = it.next();
                String columnName = entry.getKey();
                Object value = entry.getValue();
                if(columnName.equals(User.UserEntry.COLUMN_NAME_AGE)){
                    selectQuery += "and " + columnName + ">=" + (Integer)value + " ";
                }else if(columnName.equals(User.UserEntry.COLUMN_NAME_GENDER)){
                    selectQuery += "and " + columnName + " ='"+ (String) value + "' ";
                }else{
                    selectQuery +="and " + columnName + " =" + ((Boolean) value ? 1 : 0) + " ";
                }
            }
        }
        //activity.alert(selectQuery);
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            users = new ArrayList<User>();
            do {
                users.add(new User(cursor.getString(0),cursor.getString(1), User.UserEntry.GARDIEN_TYPE));

            } while (cursor.moveToNext());
        }

        return users;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
