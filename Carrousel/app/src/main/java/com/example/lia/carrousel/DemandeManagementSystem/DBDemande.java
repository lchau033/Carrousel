package com.example.lia.carrousel.DemandeManagementSystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lia.carrousel.UserManagementSystem.Comments;
import com.example.lia.carrousel.UserManagementSystem.ParentGardien;
import com.example.lia.carrousel.UserManagementSystem.User;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Lia on 2016-06-24.
 */
public class DBDemande extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Carrousel.db";

    public DBDemande(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final String TABLE_DEMANDE = "CREATE TABLE " + Demande.DemandeEntry.TABLE_NAME + " (" +
            Demande.DemandeEntry.COLUMN_NAME_GARDIEN + TEXT_TYPE + COMMA_SEP +
            Demande.DemandeEntry.COLUMN_NAME_PARENT + TEXT_TYPE + COMMA_SEP +
            Demande.DemandeEntry.COLUMN_NAME_DATE + TEXT_TYPE + COMMA_SEP +
            Demande.DemandeEntry.COLUMN_NAME_CONFIRMEE + " INTEGER" + COMMA_SEP+
            Demande.DemandeEntry.COLUMN_NAME_TYPE + TEXT_TYPE + COMMA_SEP +
            "FOREIGN KEY("+ Demande.DemandeEntry.COLUMN_NAME_GARDIEN + ") REFERENCES " +
            User.UserEntry.TABLE_NAME + "(" + User.UserEntry.COLUMN_NAME_USERNAME + ")" +
            "FOREIGN KEY("+ Demande.DemandeEntry.COLUMN_NAME_PARENT + ") REFERENCES " +
            User.UserEntry.TABLE_NAME + "(" + User.UserEntry.COLUMN_NAME_USERNAME + ")" +
            "PRIMARY KEY (" + Demande.DemandeEntry.COLUMN_NAME_GARDIEN + COMMA_SEP + Demande.DemandeEntry.COLUMN_NAME_PARENT + ")" + ")";

    public void createDemande(String usernameGardien, String usernameParent, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Demande.DemandeEntry.COLUMN_NAME_GARDIEN, usernameGardien);
        values.put(Demande.DemandeEntry.COLUMN_NAME_PARENT,usernameParent);
        values.put(Demande.DemandeEntry.COLUMN_NAME_TYPE, type);
        values.put(Demande.DemandeEntry.COLUMN_NAME_CONFIRMEE,0);
        values.put(Demande.DemandeEntry.COLUMN_NAME_DATE,new Date().toString());
        db.insert(Demande.DemandeEntry.TABLE_NAME, null, values);
        db.close();
    }

    public void updateEntry(String username,String columnName,String value){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columnName,value );
        db.update(User.UserEntry.TABLE_NAME, contentValues, User.UserEntry.COLUMN_NAME_USERNAME + " = ?", new String[] { username });
    }

    public void updateEntry(String username,String columnName, Boolean value){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columnName,(value ? 1 : 0));
        db.update(User.UserEntry.TABLE_NAME, contentValues, User.UserEntry.COLUMN_NAME_USERNAME + " = ?", new String[] { username });
    }

    public void updateEntry(String username,String columnName, Integer value){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columnName,value );
        db.update(User.UserEntry.TABLE_NAME, contentValues, User.UserEntry.COLUMN_NAME_USERNAME + " = ?", new String[] { username });
    }

    public int confirmDemand(String usernameGardien, String usernameParent){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Demande.DemandeEntry.COLUMN_NAME_CONFIRMEE,1);
        db.update(Demande.DemandeEntry.TABLE_NAME,contentValues, Demande.DemandeEntry.COLUMN_NAME_GARDIEN + " = ? and " +
                Demande.DemandeEntry.COLUMN_NAME_PARENT +" = ?",new String[]{usernameGardien,usernameParent});
        db = this.getReadableDatabase();
        int confirmed = 0;
        String selectQuery = "SELECT "+ Demande.DemandeEntry.COLUMN_NAME_CONFIRMEE + " FROM "+ Demande.DemandeEntry.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                cursor.getInt(0);

            } while (cursor.moveToNext());
        }

        return confirmed;
    }

    public void refuseDemand(String usernameGardien, String usernameParent){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Demande.DemandeEntry.TABLE_NAME,Demande.DemandeEntry.COLUMN_NAME_GARDIEN + " = ? and " +
                Demande.DemandeEntry.COLUMN_NAME_PARENT +" = ?",new String[]{usernameGardien,usernameParent});
    }

    public ArrayList<Demande> getConfirmedDemands(String username,String demandType){
        ArrayList<Demande> demandes = null;
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = null;
        if(demandType.equals(User.UserEntry.PARENT_TYPE)) {
            selectQuery = "SELECT " + Demande.DemandeEntry.COLUMN_NAME_GARDIEN + " FROM " +
                    Demande.DemandeEntry.TABLE_NAME + " where " + Demande.DemandeEntry.COLUMN_NAME_PARENT +
                    " ='" + username + "' and "+ Demande.DemandeEntry.COLUMN_NAME_CONFIRMEE + " =1";
        } else{
            selectQuery = "SELECT " + Demande.DemandeEntry.COLUMN_NAME_PARENT + " FROM " +
                    Demande.DemandeEntry.TABLE_NAME + " where " + Demande.DemandeEntry.COLUMN_NAME_GARDIEN +
                    " ='" + username + "' and "+ Demande.DemandeEntry.COLUMN_NAME_CONFIRMEE + " =1";
        }
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            demandes = new ArrayList<Demande>();
            do {
                if(demandType.equals(User.UserEntry.PARENT_TYPE)){
                    demandes.add(new Demande(cursor.getString(0),username));
                }else{
                    demandes.add(new Demande(username,cursor.getString(0)));
                }

            } while (cursor.moveToNext());
        }

        return demandes;
    }

    public ArrayList<Demande> getDemands(String username,String demandType){
        ArrayList<Demande> demandes = null;
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = null;
        if(demandType.equals(Demande.DemandeEntry.DEMANDE_GARDIEN_TO_PARENT)) {
            selectQuery = "SELECT " + Demande.DemandeEntry.COLUMN_NAME_GARDIEN + " FROM " +
                    Demande.DemandeEntry.TABLE_NAME + " where " + Demande.DemandeEntry.COLUMN_NAME_PARENT +
                    " ='" + username + "' and " + Demande.DemandeEntry.COLUMN_NAME_TYPE + " ='" + demandType + "' and "+
                    Demande.DemandeEntry.COLUMN_NAME_CONFIRMEE + " =0";
        } else{
            selectQuery = "SELECT " + Demande.DemandeEntry.COLUMN_NAME_PARENT + " FROM " +
                    Demande.DemandeEntry.TABLE_NAME + " where " + Demande.DemandeEntry.COLUMN_NAME_GARDIEN +
                    " ='" + username + "' and " + Demande.DemandeEntry.COLUMN_NAME_TYPE + " ='" + demandType + "' and "+
                    Demande.DemandeEntry.COLUMN_NAME_CONFIRMEE + " =0";
        }
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            demandes = new ArrayList<Demande>();
            do {
                if(demandType.equals(Demande.DemandeEntry.DEMANDE_GARDIEN_TO_PARENT)){
                    demandes.add(new Demande(cursor.getString(0),username));
                }else{
                    demandes.add(new Demande(username,cursor.getString(0)));
                }

            } while (cursor.moveToNext());
        }

        return demandes;
    }

    public ArrayList<Demande> getAllDemands(String username,String demandType){
        ArrayList<Demande> demandes = null;
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = null;
        if(demandType.equals(User.UserEntry.PARENT_TYPE)) {
            selectQuery = "SELECT " + Demande.DemandeEntry.COLUMN_NAME_GARDIEN + " FROM " +
                    Demande.DemandeEntry.TABLE_NAME + " where " + Demande.DemandeEntry.COLUMN_NAME_PARENT +
                    " ='" + username + "'";
        } else{
            selectQuery = "SELECT " + Demande.DemandeEntry.COLUMN_NAME_PARENT + " FROM " +
                    Demande.DemandeEntry.TABLE_NAME + " where " + Demande.DemandeEntry.COLUMN_NAME_GARDIEN +
                    " ='" + username + "'";
        }
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            demandes = new ArrayList<Demande>();
            do {
                if(demandType.equals(User.UserEntry.PARENT_TYPE)){
                    demandes.add(new Demande(cursor.getString(0),username));
                }else{
                    demandes.add(new Demande(username,cursor.getString(0)));
                }

            } while (cursor.moveToNext());
        }

        return demandes;
    }

    public ArrayList<Demande> getMadeDemands(String username,String demandType){
        ArrayList<Demande> demandes = null;
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = null;
        if(demandType.equals(Demande.DemandeEntry.DEMANDE_PARENT_TO_GARDIEN)) {
            selectQuery = "SELECT " + Demande.DemandeEntry.COLUMN_NAME_GARDIEN + " FROM " +
                    Demande.DemandeEntry.TABLE_NAME + " where " + Demande.DemandeEntry.COLUMN_NAME_PARENT +
                    " ='" + username + "' and " + Demande.DemandeEntry.COLUMN_NAME_CONFIRMEE + "=0";
        } else{
            selectQuery = "SELECT " + Demande.DemandeEntry.COLUMN_NAME_PARENT + " FROM " +
                    Demande.DemandeEntry.TABLE_NAME + " where " + Demande.DemandeEntry.COLUMN_NAME_GARDIEN +
                    " ='" + username + "' and " + Demande.DemandeEntry.COLUMN_NAME_CONFIRMEE + "=0";
        }
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            demandes = new ArrayList<Demande>();
            do {
                if(demandType.equals(Demande.DemandeEntry.DEMANDE_PARENT_TO_GARDIEN)){
                    demandes.add(new Demande(cursor.getString(0),username));
                }else{
                    demandes.add(new Demande(username,cursor.getString(0)));
                }

            } while (cursor.moveToNext());
        }

        return demandes;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_DEMANDE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
