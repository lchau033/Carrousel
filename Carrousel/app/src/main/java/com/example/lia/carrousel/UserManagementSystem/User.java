package com.example.lia.carrousel.UserManagementSystem;

import android.provider.BaseColumns;

import com.example.lia.carrousel.DemandeManagementSystem.Demande;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Lia on 2016-06-24.
 */
public class User implements Serializable {

    public static final int NON_SMOCKER = 0;
    public static final int APTITUDES_CULINAIRES = 1;
    public static final int SPEAKS_ENGLISH = 2;
    public static final int SPEAKS_FRENCH = 3;
    public static final int SPEAKS_SPANISH = 4;
    public static final int HELP_WITH_MATH = 5;
    public static final int HELP_WITH_SCIENCE = 6;
    public static final int HELP_WITH_LANGUAGE_CLASSES = 7;
    public static final int PREVIOUS_EXPERIENCE = 8;
    public static final int AVAILABLE_MONDAY = 9;
    public static final int AVAILABLE_TUESDAY = 10;
    public static final int AVAILABLE_WEDNESDAY = 11;
    public static final int AVAILABLE_THURSDAY = 12;
    public static final int AVAILABLE_FRIDAY = 13;
    public static final int AVAILABLE_SATURDAY = 14;
    public static final int AVAILABLE_SUNDAY = 15;
    public static final int WOMAN = 0;
    public static final int MAN = 2;

    private Collection<Demande> demandesConfirmees;
    private Collection<Demande> demandes;
    private Collection<String> commentaires;
    private int evaluation;
    private int totalEvaluation;
    private int numEvaluations;
    private String username;
    private String postalCode;
    private String phoneNumber;
    private String nom;
    private String prenom;
    private String description;
    private int age;
    private String sexe;
    private boolean[] faculties;
    private String password;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public User(String username, String password, String type, String postalCode, String phoneNumber, String nom, String prenom, String description) {
        this.username = username;
        this.password = password;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.nom = nom;
        this.prenom = prenom;
        this.description = description;
        this.type = type;

        if (type.equals(UserEntry.GARDIEN_TYPE)) {
            this.commentaires = new ArrayList<String>();
            this.demandes = new ArrayList<Demande>();
            this.demandesConfirmees = new ArrayList<Demande>();
            this.evaluation = 0;
            this.totalEvaluation = 0;
            this.numEvaluations = 0;
            faculties = new boolean[16];
        } else{
            this.demandes = new ArrayList<Demande>();
            this.demandesConfirmees = new ArrayList<Demande>();
        }
    }

    public User(String username,String description,String type){
        this.username = username;
        this.description = description;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDemandes(Collection<Demande> demandes) {
        this.demandes = demandes;
    }

    public Collection<Demande> getDemandes() {

        return demandes;
    }

    public Collection<Demande> getDemandesConfirmees() {
        return demandesConfirmees;
    }

    public void setDemandesConfirmees(Collection<Demande> demandesConfirmees) {
        this.demandesConfirmees = demandesConfirmees;
    }

    public Collection<String> getCommentaires() {
        return commentaires;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }


    public boolean[] getFaculties() {
        return faculties;
    }

    public void setFaculties(boolean[] faculties) {
        this.faculties = faculties;
    }

    /* Inner class that defines the table contents */
    public static abstract class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "USER";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_EVALUATION = "evaluation";
        public static final String COLUMN_NAME_EVALUATION_TOTAL = "evaluationTotale";
        public static final String COLUMN_NAME_NUM_EVALUATIONS = "numEvaluations";
        public static final String COLUMN_NAME_NON_SMOCKER = "nonSmocker";
        public static final String COLUMN_NAME_APTITUDES_CULINAIRES = "culinaryAptitudes";
        public static final String COLUMN_NAME_SPEAKS_ENGLISH = "speaksEnglish";
        public static final String COLUMN_NAME_SPEAKS_FRENCH = "speaksFrench";
        public static final String COLUMN_NAME_SPEAKS_SPANISH = "speaksSpanish";
        public static final String COLUMN_NAME_HELP_WITH_MATH = "helpWithMath";
        public static final String COLUMN_NAME_HELP_WITH_SCIENCE = "helpWithScience";
        public static final String COLUMN_NAME_HELP_WITH_LANGUAGE_CLASSES = "helpWithLanguageClasses";
        public static final String COLUMN_NAME_PREVIOUS_EXPERIENCE = "previousExperience";
        public static final String COLUMN_NAME_AVAILABLE_MONDAY = "availableMonday";
        public static final String COLUMN_NAME_AVAILABLE_TUESDAY = "availableTuesday";
        public static final String COLUMN_NAME_AVAILABLE_WEDNESDAY = "availableWednesday";
        public static final String COLUMN_NAME_AVAILABLE_THURSDAY = "availableThursday";
        public static final String COLUMN_NAME_AVAILABLE_FRIDAY = "availableFriday";
        public static final String COLUMN_NAME_AVAILABLE_SATURDAY = "availableSaturday";
        public static final String COLUMN_NAME_AVAILABLE_SUNDAY = "availableSunday";
        public static final String COLUMN_NAME_GENDER = "gender";
        public static final String COLUMN_NAME_NOM = "nom";
        public static final String COLUMN_NAME_PRENOM = "prenom";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_NAME_PHONE_NUMBER = "phoneNumber";
        public static final String COLUMN_NAME_POSTAL_CODE = "postalCode";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String GARDIEN_TYPE = "gardien";
        public static final String PARENT_TYPE = "parent";
        public static final String WOMAN = "femme";
        public static final String MAN = "homme";


    }


}
