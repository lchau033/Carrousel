package com.example.lia.carrousel.DemandeManagementSystem;

import java.util.ArrayList;

/**
 * Created by Lia on 2016-06-24.
 */
public class DemandeFacade {
    public void createDemande(String usernameParent,String usernameGardien,String type,DBDemande dbDemande){
        dbDemande.createDemande(usernameGardien,usernameParent,type);
    }

    public ArrayList<Demande> getDemands(String username, String typeOfDemand, DBDemande dbDemande){
        return dbDemande.getDemands(username,typeOfDemand);
    }

    public ArrayList<Demande> getAllDemands(String username, String type, DBDemande dbDemande){
        return dbDemande.getAllDemands(username,type);
    }

    public int confirmDemand(String usernameGardien, String usernameParent, DBDemande dbDemande){
        return dbDemande.confirmDemand(usernameGardien,usernameParent);
    }

    public void refuseDemand(String usernameGardien, String usernameParent, DBDemande dbDemande){
        dbDemande.refuseDemand(usernameGardien,usernameParent);
    }

    public ArrayList<Demande> getConfirmedDemands(String username, String type, DBDemande dbDemande){
        return dbDemande.getConfirmedDemands(username,type);
    }

    public ArrayList<Demande> getMadeDemands(String username, String type, DBDemande dbDemande){
        return dbDemande.getMadeDemands(username,type);
    }
}
