package com.example.lia.carrousel.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.example.lia.carrousel.DemandeManagementSystem.DBDemande;
import com.example.lia.carrousel.DemandeManagementSystem.Demande;
import com.example.lia.carrousel.DemandeManagementSystem.DemandeFacade;
import com.example.lia.carrousel.UserManagementSystem.DBUser;
import com.example.lia.carrousel.UserManagementSystem.User;
import com.example.lia.carrousel.UserManagementSystem.UserFacade;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Lia on 2016-06-25.
 */
public class CarrouselManagementSystem {
    private static UserFacade uf = new UserFacade();
    private static DemandeFacade df = new DemandeFacade();

    public static User getUser(String username, String password, DBUser dbUser){
        return uf.getUser(username,password,dbUser);
    }



    public static User registerGardien(String username, String password, String prenom, String nom, int age,
                                       String sexe, String description,String postalCode, String phoneNumber,
                                       boolean french, boolean english, boolean spanish, boolean math, boolean sciences,
                                       boolean languages, boolean nonSmocker, boolean canCook, boolean monday,
                                       boolean tuesday, boolean wednesday, boolean thursday, boolean friday,
                                       boolean saturday, boolean sunday,DBUser dbUser){
        uf.createGardien(username,password,dbUser);
        uf.updateEntry(username, User.UserEntry.COLUMN_NAME_PRENOM,prenom,dbUser);
        uf.updateEntry(username, User.UserEntry.COLUMN_NAME_NOM,nom,dbUser);
        uf.updateEntry(username, User.UserEntry.COLUMN_NAME_AGE,age,dbUser);
        uf.updateEntry(username, User.UserEntry.COLUMN_NAME_GENDER,sexe,dbUser);
        uf.updateEntry(username, User.UserEntry.COLUMN_NAME_DESCRIPTION,description,dbUser);
        uf.updateEntry(username, User.UserEntry.COLUMN_NAME_POSTAL_CODE,postalCode,dbUser);
        uf.updateEntry(username, User.UserEntry.COLUMN_NAME_PHONE_NUMBER,phoneNumber,dbUser);
        uf.updateEntry(username,User.UserEntry.COLUMN_NAME_NON_SMOCKER,nonSmocker,dbUser);
        uf.updateEntry(username,User.UserEntry.COLUMN_NAME_APTITUDES_CULINAIRES,canCook,dbUser);
        uf.updateEntry(username,User.UserEntry.COLUMN_NAME_HELP_WITH_LANGUAGE_CLASSES,languages,dbUser);
        uf.updateEntry(username,User.UserEntry.COLUMN_NAME_HELP_WITH_MATH,math,dbUser);
        uf.updateEntry(username,User.UserEntry.COLUMN_NAME_HELP_WITH_SCIENCE,sciences,dbUser);
        uf.updateEntry(username,User.UserEntry.COLUMN_NAME_SPEAKS_FRENCH,french,dbUser);
        uf.updateEntry(username,User.UserEntry.COLUMN_NAME_SPEAKS_ENGLISH,english,dbUser);
        uf.updateEntry(username,User.UserEntry.COLUMN_NAME_SPEAKS_SPANISH,spanish,dbUser);
        uf.updateEntry(username,User.UserEntry.COLUMN_NAME_AVAILABLE_MONDAY,monday,dbUser);
        uf.updateEntry(username,User.UserEntry.COLUMN_NAME_AVAILABLE_TUESDAY,tuesday,dbUser);
        uf.updateEntry(username,User.UserEntry.COLUMN_NAME_AVAILABLE_WEDNESDAY,wednesday,dbUser);
        uf.updateEntry(username,User.UserEntry.COLUMN_NAME_AVAILABLE_THURSDAY,thursday,dbUser);
        uf.updateEntry(username,User.UserEntry.COLUMN_NAME_AVAILABLE_FRIDAY,friday,dbUser);
        uf.updateEntry(username,User.UserEntry.COLUMN_NAME_AVAILABLE_SATURDAY,saturday,dbUser);
        uf.updateEntry(username,User.UserEntry.COLUMN_NAME_AVAILABLE_SUNDAY,sunday,dbUser);
        User user= new User(username,password, User.UserEntry.GARDIEN_TYPE,postalCode,phoneNumber,nom,prenom,description);
        user.setAge(age);
        user.setSexe(sexe);
        boolean[] faculties = new boolean[16];
        faculties[User.SPEAKS_FRENCH] = french;
        faculties[User.SPEAKS_ENGLISH] = english;
        faculties[User.SPEAKS_SPANISH] = spanish;
        faculties[User.HELP_WITH_MATH] = math;
        faculties[User.HELP_WITH_SCIENCE] =sciences;
        faculties[User.HELP_WITH_LANGUAGE_CLASSES]=languages;
        faculties[User.NON_SMOCKER] = nonSmocker;
        faculties[User.APTITUDES_CULINAIRES] = canCook;
        faculties[User.AVAILABLE_MONDAY] = monday;
        faculties[User.AVAILABLE_TUESDAY] = tuesday;
        faculties[User.AVAILABLE_WEDNESDAY] = wednesday;
        faculties[User.AVAILABLE_THURSDAY] = thursday;
        faculties[User.AVAILABLE_FRIDAY]=friday;
        faculties[User.AVAILABLE_SATURDAY]=saturday;
        faculties[User.AVAILABLE_SUNDAY]=sunday;
        user.setFaculties(faculties);

        return user;
    }

    public static User registerParent(String username, String password, String prenom, String nom, String description,String postalCode, String phoneNumber,DBUser dbUser){
        uf.createParent(username,password,dbUser);
        uf.updateEntry(username, User.UserEntry.COLUMN_NAME_PRENOM,prenom,dbUser);
        uf.updateEntry(username, User.UserEntry.COLUMN_NAME_NOM,nom,dbUser);
        uf.updateEntry(username, User.UserEntry.COLUMN_NAME_DESCRIPTION,description,dbUser);
        uf.updateEntry(username, User.UserEntry.COLUMN_NAME_POSTAL_CODE,postalCode,dbUser);
        uf.updateEntry(username, User.UserEntry.COLUMN_NAME_PHONE_NUMBER,phoneNumber,dbUser);
        User user= new User(username,password, User.UserEntry.PARENT_TYPE,postalCode,phoneNumber,nom,prenom,description);
        return user;
    }

    public static ArrayList<Map.Entry<String,String>> ShowParentFeed(String username,String type, DBUser dbUser,DBDemande dbDemande){
        ArrayList<User> potentialGardiens = uf.getPotentialGardiens(dbUser);
        ArrayList<Demande> demandes = df.getAllDemands(username,type,dbDemande);
        ArrayList<Map.Entry<String,String>> feedItems = new ArrayList<Map.Entry<String,String>>();
        boolean add = true;
        if (potentialGardiens!=null) {
            Iterator<User> it = potentialGardiens.iterator();
            int i = 0;
            while (it.hasNext()) {
                add = true;
                User gardien = it.next();
                if(demandes!=null){
                    Iterator<Demande> it2= demandes.iterator();
                    while(it2.hasNext()){
                        Demande temp = it2.next();
                        String tempUsername = temp.getGardienUsername();
                        if(gardien.getUsername().equals(tempUsername)){
                            add = false;
                            break;
                        }
                    }
                }
                if(add) {
                    feedItems.add(new AbstractMap.SimpleImmutableEntry<String, String>(gardien.getUsername(), gardien.getDescription()));
                }
            }
        }
        return feedItems;
    }

    public static ArrayList<Map.Entry<String,String>> ShowParentFeed(String username,String type, ArrayList<Map.Entry<String,Object>> columnList,DBUser dbUser,DBDemande dbDemande, ResearchResultsActivity activity){
        ArrayList<User> potentialGardiens = uf.getPotentialGardiens(columnList,dbUser, activity);
        ArrayList<Demande> demandes = df.getAllDemands(username,type,dbDemande);
        ArrayList<Map.Entry<String,String>> feedItems = new ArrayList<Map.Entry<String,String>>();
        boolean add = true;
        if (potentialGardiens!=null) {
            Iterator<User> it = potentialGardiens.iterator();
            int i = 0;
            while (it.hasNext()) {
                add = true;
                User gardien = it.next();
                if(demandes!=null){
                    Iterator<Demande> it2= demandes.iterator();
                    while(it2.hasNext()){
                        Demande temp = it2.next();
                        String tempUsername = temp.getGardienUsername();
                        if(gardien.getUsername().equals(tempUsername)){
                            add = false;
                            break;
                        }
                    }
                }
                if(add) {
                    feedItems.add(new AbstractMap.SimpleImmutableEntry<String, String>(gardien.getUsername(), gardien.getDescription()));
                }
            }
        }
        return feedItems;
    }


    public static ArrayList<Map.Entry<String,String>> ShowGardiensFeed(String username, String type,DBUser dbUser, DBDemande dbDemande){
        ArrayList<User> potentialParents = uf.getPotentialParents(dbUser);
        ArrayList<Demande> demandes = df.getAllDemands(username,type,dbDemande);
        ArrayList<Map.Entry<String,String>> feedItems = new ArrayList<Map.Entry<String,String>>();
        boolean add = true;
        if (potentialParents!=null) {
            Iterator<User> it = potentialParents.iterator();
            int i = 0;
            while (it.hasNext()) {
                add = true;
                User parent = it.next();
                if(demandes!=null){
                    Iterator<Demande> it2= demandes.iterator();
                    while(it2.hasNext()){
                        Demande temp = it2.next();
                        String tempUsername = temp.getParentUsername();
                        if(parent.getUsername().equals(tempUsername)){
                            add = false;
                            break;
                        }
                    }
                }
                if(add) {
                    feedItems.add(new AbstractMap.SimpleImmutableEntry<String, String>(parent.getUsername(), parent.getDescription()));
                }
            }
        }
        return feedItems;
    }

    public static void createDemandToUser(User demandedUser, User user, DBDemande dbDemande){
        String type = null;
        if(user.getType().equals(User.UserEntry.GARDIEN_TYPE)){
            type = Demande.DemandeEntry.DEMANDE_GARDIEN_TO_PARENT;
            df.createDemande(demandedUser.getUsername(),user.getUsername(),type,dbDemande);
        }else{
            type = Demande.DemandeEntry.DEMANDE_PARENT_TO_GARDIEN;
            df.createDemande(user.getUsername(),demandedUser.getUsername(),type,dbDemande);
        }
    }

    public static User getUser(String username,DBUser dbUser){
        return uf.getUser(username,dbUser);
    }

    public static ArrayList<Map.Entry<String,String>> getDemands(String username,String type,DBDemande dbDemande,DBUser dbUser){
        ArrayList<Demande> demandes = df.getDemands(username,type,dbDemande);
        ArrayList<Map.Entry<String, String>> demandeDescriptions = null;
        if(demandes!=null) {
            Iterator<Demande> it = demandes.iterator();
            demandeDescriptions = new ArrayList<Map.Entry<String, String>>();
            if (type.equals(Demande.DemandeEntry.DEMANDE_GARDIEN_TO_PARENT)) {
                while (it.hasNext()) {
                    Demande demand = it.next();
                    User user = getUser(demand.getGardienUsername(), dbUser);
                    demandeDescriptions.add(new AbstractMap.SimpleImmutableEntry<String, String>(user.getUsername(), user.getDescription()));
                }
            } else {
                while (it.hasNext()) {
                    Demande demand = it.next();
                    User user = getUser(demand.getParentUsername(), dbUser);
                    demandeDescriptions.add(new AbstractMap.SimpleImmutableEntry<String, String>(user.getUsername(), user.getDescription()));
                }
            }
        }

        return demandeDescriptions;
    }

    public static ArrayList<Map.Entry<String,String>> getMadeDemands(String username,String type,DBDemande dbDemande,DBUser dbUser){
        ArrayList<Demande> demandes = df.getMadeDemands(username,type,dbDemande);
        ArrayList<Map.Entry<String, String>> demandeDescriptions = null;
        if(demandes!=null) {
            Iterator<Demande> it = demandes.iterator();
            demandeDescriptions = new ArrayList<Map.Entry<String, String>>();
            if (type.equals(Demande.DemandeEntry.DEMANDE_PARENT_TO_GARDIEN)) {
                while (it.hasNext()) {
                    Demande demand = it.next();
                    User user = getUser(demand.getGardienUsername(), dbUser);
                    demandeDescriptions.add(new AbstractMap.SimpleImmutableEntry<String, String>(user.getUsername(), user.getDescription()));
                }
            } else {
                while (it.hasNext()) {
                    Demande demand = it.next();
                    User user = getUser(demand.getParentUsername(), dbUser);
                    demandeDescriptions.add(new AbstractMap.SimpleImmutableEntry<String, String>(user.getUsername(), user.getDescription()));
                }
            }
        }

        return demandeDescriptions;
    }

    public static int confirmDemand(String usernameGardien, String usernameParent, DBDemande dbDemande){
        return df.confirmDemand(usernameGardien,usernameParent,dbDemande);
    }

    public static void refuseDemand(String usernameGardien, String usernameParent, DBDemande dbDemande){
        df.refuseDemand(usernameGardien,usernameParent,dbDemande);
    }

    public static ArrayList<Map.Entry<String,String>> getConfirmedDemands(String username, String type, DBDemande dbDemande, DBUser dbUser){
        ArrayList<Demande> demandes = df.getConfirmedDemands(username,type,dbDemande);
        ArrayList<Map.Entry<String, String>> demandeDescriptions = null;
        if(demandes!=null) {
            Iterator<Demande> it = demandes.iterator();
            demandeDescriptions = new ArrayList<Map.Entry<String, String>>();
            if (type.equals(User.UserEntry.PARENT_TYPE)) {
                while (it.hasNext()) {
                    Demande demand = it.next();
                    User user = getUser(demand.getGardienUsername(), dbUser);
                    demandeDescriptions.add(new AbstractMap.SimpleImmutableEntry<String, String>(user.getUsername(), user.getDescription()));
                }
            } else {
                while (it.hasNext()) {
                    Demande demand = it.next();
                    User user = getUser(demand.getParentUsername(), dbUser);
                    demandeDescriptions.add(new AbstractMap.SimpleImmutableEntry<String, String>(user.getUsername(), user.getDescription()));
                }
            }
        }

        return demandeDescriptions;
    }

    public static void updateInfo(String username,String columnName,String value,DBUser dbUser){
        uf.updateEntry(username,columnName ,value,dbUser);
    }

    public static void updateInfo(String username,String columnName,boolean value,DBUser dbUser){
        uf.updateEntry(username,columnName ,value,dbUser);
    }

    public static void updateInfo(String username,String columnName,int value,DBUser dbUser){
        uf.updateEntry(username,columnName ,value,dbUser);
    }
}
