package com.example.lia.carrousel.Activities;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lia.carrousel.DemandeManagementSystem.DBDemande;
import com.example.lia.carrousel.R;
import com.example.lia.carrousel.UserManagementSystem.DBUser;
import com.example.lia.carrousel.UserManagementSystem.User;

import java.util.ArrayList;
import java.util.Map;

public class DemandeGardienActivity extends AppCompatActivity {

    private User demandUser;
    private User user;
    private Intent intent;
    private String cameFrom;
    private ArrayList<Map.Entry<String,Object>> columnList;

    private ListView listView;
    private ArrayList<Map.Entry<String, String>> potential;
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
        setContentView(R.layout.activity_demande_gardien);

        intent = getIntent();
        demandUser = (User) intent.getSerializableExtra("demandUser");
        user = (User) intent.getSerializableExtra("user");
        cameFrom = intent.getStringExtra("cameFrom");
        columnList = (ArrayList<Map.Entry<String,Object>>) intent.getSerializableExtra("columnList");

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

        mAdapter = new MyAdapter(TITLES, ICONS, user.getUsername(), user.getUsername(), R.mipmap.carousel);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);// Setting the layout Manager


        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.app_name, R.string.app_name) {

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
                    @Override
                    public void onItemClick(View view, int position) {
                        selectItem(position);
                    }
                })
        );


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.carousel);
        setTitle(demandUser.getUsername());

        TextView username = (TextView) findViewById(R.id.username);
        username.setText(demandUser.getUsername());

        TextView description = (TextView) findViewById(R.id.description);
        description.setText(demandUser.getDescription());

        TextView age = (TextView) findViewById(R.id.years);
        age.setText(getResources().getString(R.string.ge)+" "+demandUser.getAge());

        TextView sexe = (TextView) findViewById(R.id.gender);
        sexe.setText( getResources().getString(R.string.sexe)+" "+demandUser.getSexe());

        TextView academicalCapacities = (TextView) findViewById(R.id.academicalCapacities);
        boolean[] faculties = demandUser.getFaculties();
        if(faculties[User.HELP_WITH_MATH] || faculties[User.HELP_WITH_LANGUAGE_CLASSES] || faculties[User.HELP_WITH_SCIENCE]) {
            academicalCapacities.setText(academicalCapacities.getText().toString()+ (faculties[User.HELP_WITH_MATH] ? " Math" : ""));
            academicalCapacities.setText(academicalCapacities.getText().toString()+ (faculties[User.HELP_WITH_MATH] && faculties[User.HELP_WITH_LANGUAGE_CLASSES] ? ", Langues" :
                faculties[User.HELP_WITH_LANGUAGE_CLASSES]? " Langues":""));
            academicalCapacities.setText(academicalCapacities.getText().toString()+ ((faculties[User.HELP_WITH_MATH] || faculties[User.HELP_WITH_LANGUAGE_CLASSES]) &&
                    faculties[User.HELP_WITH_SCIENCE] ? ", Sciences" :
                    faculties[User.HELP_WITH_SCIENCE]? " Sciences":""));
        }

        TextView languesParlees = (TextView) findViewById(R.id.langueParlees);
        if(faculties[User.SPEAKS_ENGLISH] || faculties[User.SPEAKS_SPANISH] || faculties[User.SPEAKS_FRENCH]){
            languesParlees.setText(languesParlees.getText().toString() + (faculties[User.SPEAKS_FRENCH] ? " Français": ""));
            languesParlees.setText(languesParlees.getText().toString() + (faculties[User.SPEAKS_FRENCH] &&
                faculties[User.SPEAKS_ENGLISH] ? ", Anglais": faculties[User.SPEAKS_ENGLISH] ? " Anglais": "" ));
            languesParlees.setText(languesParlees.getText().toString() + ((faculties[User.SPEAKS_ENGLISH] ||
                faculties[User.SPEAKS_FRENCH]) && faculties[User.SPEAKS_SPANISH] ? ", Espagnol" :
                faculties[User.SPEAKS_SPANISH] ? " Espagnol": ""));
        }

        TextView qualites = (TextView) findViewById(R.id.qualites);
        qualites.setText((faculties[User.NON_SMOCKER] && faculties[User.APTITUDES_CULINAIRES] ?
                "Non-fumeur et peut cuisiner" : faculties[User.NON_SMOCKER] ? "Non-fumeur" :
                faculties[User.APTITUDES_CULINAIRES] ? "Peut cuisiner" : ""));

        TextView disponibilites = (TextView) findViewById(R.id.disponibilites);
        disponibilites.setText(disponibilites.getText().toString() + (faculties[User.AVAILABLE_MONDAY] ?
            " Lundi": ""));
        disponibilites.setText(disponibilites.getText().toString() + (faculties[User.AVAILABLE_MONDAY] &&
            faculties[User.AVAILABLE_TUESDAY] ? ", Mardi": faculties[User.AVAILABLE_TUESDAY] ? " Mardi" : "" ));
        disponibilites.setText(disponibilites.getText().toString() + ((faculties[User.AVAILABLE_MONDAY] ||
            faculties[User.AVAILABLE_TUESDAY]) && faculties[User.AVAILABLE_WEDNESDAY] ? ", Mercredi" :
            faculties[User.AVAILABLE_WEDNESDAY] ? " Mercredi" : ""));
        disponibilites.setText(disponibilites.getText().toString() + ((faculties[User.AVAILABLE_MONDAY] ||
                faculties[User.AVAILABLE_TUESDAY] || faculties[User.AVAILABLE_WEDNESDAY]) && faculties[User.AVAILABLE_THURSDAY] ?
                ", Jeudi" : faculties[User.AVAILABLE_THURSDAY] ? " Jeudi" : ""));
        disponibilites.setText(disponibilites.getText().toString() + ((faculties[User.AVAILABLE_MONDAY] ||
                faculties[User.AVAILABLE_TUESDAY] || faculties[User.AVAILABLE_WEDNESDAY] || faculties[User.AVAILABLE_THURSDAY]) &&
                faculties[User.AVAILABLE_FRIDAY] ? ", Vendredi" : faculties[User.AVAILABLE_FRIDAY] ? " Vendredi" : ""));
        disponibilites.setText(disponibilites.getText().toString() + ((faculties[User.AVAILABLE_MONDAY] ||
                faculties[User.AVAILABLE_TUESDAY] || faculties[User.AVAILABLE_WEDNESDAY] || faculties[User.AVAILABLE_THURSDAY] ||
                faculties[User.AVAILABLE_FRIDAY]) && faculties[User.AVAILABLE_SATURDAY] ? ", Samedi"
                : faculties[User.AVAILABLE_SATURDAY] ? " Samedi" : ""));
        disponibilites.setText(disponibilites.getText().toString() + ((faculties[User.AVAILABLE_MONDAY] ||
                faculties[User.AVAILABLE_TUESDAY] || faculties[User.AVAILABLE_WEDNESDAY] || faculties[User.AVAILABLE_THURSDAY] ||
                faculties[User.AVAILABLE_FRIDAY] || faculties[User.AVAILABLE_SATURDAY]) && faculties[User.AVAILABLE_SUNDAY]
                ? ", Dimanche" : faculties[User.AVAILABLE_SUNDAY] ? " Dimanche" : ""));
    }

    public void faireDemande(View v){
        DBDemande dbDemande = new DBDemande(this.getBaseContext());
        CarrouselManagementSystem.createDemandToUser(demandUser,user,dbDemande);
        if(cameFrom.equals("MainPage")){
            intent = new Intent(this,MainPageActivity.class);
        }else{
            intent = new Intent(this,ResearchResultsActivity.class);
            intent.putExtra("columnList", columnList);
        }
        intent.putExtra("user",user);
        startActivity(intent);
    }

    private void selectItem(int position){
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
                //startActivity(intent);
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
}
