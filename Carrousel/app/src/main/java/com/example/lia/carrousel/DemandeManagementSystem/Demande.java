package com.example.lia.carrousel.DemandeManagementSystem;

import android.provider.BaseColumns;

import com.example.lia.carrousel.UserManagementSystem.User;

import java.util.Date;

/**
 * Created by Lia on 2016-06-24.
 */
public class Demande {
    public static abstract class DemandeEntry implements BaseColumns {
        public static final String TABLE_NAME = "DEMANDE";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_GARDIEN = "gardien";
        public static final String COLUMN_NAME_PARENT = "parent";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_CONFIRMEE = "demandeConfirmee";
        public static final String DEMANDE_GARDIEN_TO_PARENT = "gardienToParent";
        public static final String DEMANDE_PARENT_TO_GARDIEN ="parentToGardien";
    }

    private boolean confirmedDemand = false;
    private User gardien;
    private User parent;
    private Date date;
    private String gardienUsername;
    private String parentUsername;

    public Demande(User gardien, User parent) {
        this.gardien = gardien;
        this.parent = parent;
        this.confirmedDemand = false;
    }

    public Demande(String gardien, String parent){
        this.gardienUsername = gardien;
        this.parentUsername = parent;
    }

    public boolean isConfirmedDemand() {
        return confirmedDemand;
    }

    public void setConfirmedDemand(boolean confirmedDemand) {
        this.confirmedDemand = confirmedDemand;
    }

    public String getGardienUsername(){
        return this.gardienUsername;
    }

    public String getParentUsername() {
        return parentUsername;
    }

    public User getGardien() {
        return gardien;
    }

    public void setGardien(User gardien) {
        this.gardien = gardien;
    }


    public User getParent() {
        return parent;
    }

    public void setParent(User parent) {
        this.parent = parent;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }





}
