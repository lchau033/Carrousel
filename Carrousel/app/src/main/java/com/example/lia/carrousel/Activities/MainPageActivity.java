package com.example.lia.carrousel.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lia.carrousel.DemandeManagementSystem.DBDemande;
import com.example.lia.carrousel.R;
import com.example.lia.carrousel.UserManagementSystem.DBUser;
import com.example.lia.carrousel.UserManagementSystem.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainPageActivity extends AppCompatActivity {

    private Intent intent;
    private User user;
    private ListView listView;
    private ArrayList<Map.Entry<String,String>> potential;
    private FeedListAdapter listAdapter;



    String TITLES[] = {"Page principale","Recherche avancée","Voir demandes reçues","Voir demandes confirmées","Voir demandes effectuées","Changer votre profil","Déconnexion","Aide"};
    int ICONS[] = {R.drawable.ic_home,R.drawable.ic_search,R.drawable.ic_see_demands,R.drawable.ic_confirmed_demands,R.drawable.ic_sent_demands,R.drawable.ic_change_profile,R.drawable.ic_logout,R.drawable.ic_help};

    private Toolbar toolbar;                              // Declaring the Toolbar Object

    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout

    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");

        if(user.getType().equals(User.UserEntry.GARDIEN_TYPE)){
            String TITLES1[]= {"Page principale","Voir demandes reçues","Voir demandes confirmées","Voir demandes effectuées","Changer votre profil","Déconnexion","Aide"};
            TITLES = TITLES1;
            int ICONS1[] = {R.drawable.ic_home,R.drawable.ic_see_demands,R.drawable.ic_confirmed_demands,R.drawable.ic_sent_demands,R.drawable.ic_change_profile,R.drawable.ic_logout,R.drawable.ic_help};
            ICONS = ICONS1;
        }

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new MyAdapter(TITLES,ICONS,user.getUsername(),user.getUsername(),R.mipmap.carousel);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);// Setting the layout Manager


        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,toolbar,R.string.app_name,R.string.app_name){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }



        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        //alert(position);
                        selectItem(position);
                    }
                })
        );



        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.carousel);


        DBUser dbUser = new DBUser(getBaseContext());
        DBDemande dbDemande = new DBDemande(getBaseContext());
        if (user.getType().equals(User.UserEntry.GARDIEN_TYPE)){
            potential = CarrouselManagementSystem.ShowGardiensFeed(user.getUsername(),user.getType(),dbUser,dbDemande);
        }else{
            potential = CarrouselManagementSystem.ShowParentFeed(user.getUsername(),user.getType(),dbUser,dbDemande);
        }
        listView = (ListView) findViewById(R.id.listView);
        listAdapter = new FeedListAdapter(this, potential);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,long id) {

                makeADemand(position);

            }
        });


    }


    private void makeADemand(int position){
        Map.Entry<String, String> feedItem = listAdapter.getItem(position);
        DBUser dbUser1 = new DBUser(this.getBaseContext());
        User demandUser = CarrouselManagementSystem.getUser(feedItem.getKey(),dbUser1);
        //this.alert(demandUser);
        if(user.getType().equals(User.UserEntry.GARDIEN_TYPE)){
            intent = new Intent(this,DemandeActivity.class);
        }else{
            intent = new Intent(this,DemandeGardienActivity.class);
        }
        intent.putExtra("demandUser",demandUser);
        intent.putExtra("user",user);
        intent.putExtra("cameFrom", "MainPage");
        startActivity(intent);
    }


    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        if (user.getType().equals(User.UserEntry.GARDIEN_TYPE)) {
            if (position == 1) {
                intent = new Intent(this, MainPageActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            } else if (position == 2) {
                intent = new Intent(this, VoirDemandesActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            } else if (position == 3) {
                intent = new Intent(this, DemandesConfirmeesActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            } else if (position == 4) {
                intent = new Intent(this,SentDemandsActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            } else if(position == 5){
                intent = new Intent(this, ChangerProfilActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            } else if (position == 6) {
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            } else {
                intent = new Intent(this, HelpActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        } else {
            if (position == 1) {
                intent = new Intent(this, MainPageActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            } else if (position == 2) {
                intent = new Intent(this, RechercheAvanceeActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            } else if (position == 3) {
                intent = new Intent(this, VoirDemandesActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            } else if (position == 4) {
                intent = new Intent(this, DemandesConfirmeesActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            } else if (position == 5) {
                intent = new Intent(this, SentDemandsActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }else if(position == 6){
                intent = new Intent(this, ChangerProfilActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            } else if (position == 7) {
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            } else {
                intent = new Intent(this, HelpActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        }
    }


    public void alert(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alerte")
                .setMessage(position)
                .setCancelable(false)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
