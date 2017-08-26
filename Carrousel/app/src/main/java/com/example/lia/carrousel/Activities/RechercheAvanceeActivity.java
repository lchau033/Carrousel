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
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.lia.carrousel.DemandeManagementSystem.DBDemande;
import com.example.lia.carrousel.R;
import com.example.lia.carrousel.UserManagementSystem.DBUser;
import com.example.lia.carrousel.UserManagementSystem.User;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

public class RechercheAvanceeActivity extends AppCompatActivity {

    private Intent intent;
    private User user;
    private ListView listView;
    private ArrayList<Map.Entry<String,String>> potential;
    private FeedListAdapter listAdapter;
    private ArrayList<Map.Entry<String,Object>> researchColumns;



    String TITLES[] = {"Page principale","Recherche avancée","Voir demandes reçues","Voir demandes confirmées","Voir demandes effectuées","Changer votre profil","Déconnexion","Aide"};
    int ICONS[] = {R.drawable.ic_home,R.drawable.ic_search,R.drawable.ic_see_demands,R.drawable.ic_confirmed_demands,R.drawable.ic_sent_demands,R.drawable.ic_change_profile,R.drawable.ic_logout,R.drawable.ic_help};

    private Toolbar toolbar;                              // Declaring the Toolbar Object

    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout

    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_avancee);

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
                        selectItem(position);
                    }
                })
        );



        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.carousel);

        NumberPicker age = (NumberPicker) findViewById(R.id.age);
        age.setMinValue(18);
        age.setMaxValue(80);

    }

    public void rechercher(View v){
        researchColumns = new ArrayList<Map.Entry<String, Object>>();
        CheckBox frenchCheckbox = (CheckBox) findViewById(R.id.french);
        if(frenchCheckbox.isChecked()){
            researchColumns.add(new AbstractMap.SimpleImmutableEntry<String, Object>(User.UserEntry.COLUMN_NAME_SPEAKS_FRENCH,true));
        }

        CheckBox englishCheckbox = (CheckBox) findViewById(R.id.english);
        if(englishCheckbox.isChecked()){
            researchColumns.add(new AbstractMap.SimpleImmutableEntry<String, Object>(User.UserEntry.COLUMN_NAME_SPEAKS_ENGLISH,true));
        }

        CheckBox spanishCheckbox = (CheckBox) findViewById(R.id.spanish);
        if(spanishCheckbox.isChecked()){
            researchColumns.add(new AbstractMap.SimpleImmutableEntry<String, Object>(User.UserEntry.COLUMN_NAME_SPEAKS_SPANISH,true));
        }

        CheckBox mathCheckbox = (CheckBox) findViewById(R.id.math);
        if(mathCheckbox.isChecked()){
            researchColumns.add(new AbstractMap.SimpleImmutableEntry<String, Object>(User.UserEntry.COLUMN_NAME_HELP_WITH_MATH,true));
        }

        CheckBox languesCheckbox = (CheckBox) findViewById(R.id.langue);
        if(languesCheckbox.isChecked()){
            researchColumns.add(new AbstractMap.SimpleImmutableEntry<String, Object>(User.UserEntry.COLUMN_NAME_HELP_WITH_LANGUAGE_CLASSES,true));
        }

        CheckBox sciencesCheckbox = (CheckBox) findViewById(R.id.science);
        if(sciencesCheckbox.isChecked()){
            researchColumns.add(new AbstractMap.SimpleImmutableEntry<String, Object>(User.UserEntry.COLUMN_NAME_HELP_WITH_SCIENCE,true));
        }

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupSexe);
        int selectedid = radioGroup.getCheckedRadioButtonId();
        RadioButton sexe = null;
        if(selectedid!=-1){
            sexe = (RadioButton) findViewById(selectedid);
            if(sexe.getText().toString().toLowerCase().equals(User.UserEntry.WOMAN)){
                researchColumns.add(new AbstractMap.SimpleImmutableEntry<String, Object>(User.UserEntry.COLUMN_NAME_GENDER, User.UserEntry.WOMAN));
            } else {
                researchColumns.add(new AbstractMap.SimpleImmutableEntry<String, Object>(User.UserEntry.COLUMN_NAME_GENDER, User.UserEntry.MAN));
            }
        }

        NumberPicker ageMinimum = (NumberPicker) findViewById(R.id.age);
        if(ageMinimum.getValue()!=18){
            researchColumns.add(new AbstractMap.SimpleImmutableEntry<String, Object>(User.UserEntry.COLUMN_NAME_AGE,ageMinimum.getValue()));
        }

        CheckBox lundiCheckbox = (CheckBox) findViewById(R.id.monday);
        if(lundiCheckbox.isChecked()){
            researchColumns.add(new AbstractMap.SimpleImmutableEntry<String, Object>(User.UserEntry.COLUMN_NAME_AVAILABLE_MONDAY,true));
        }

        CheckBox mardiCheckbox = (CheckBox) findViewById(R.id.tuesday);
        if(mardiCheckbox.isChecked()){
            researchColumns.add(new AbstractMap.SimpleImmutableEntry<String, Object>(User.UserEntry.COLUMN_NAME_AVAILABLE_TUESDAY,true));
        }

        CheckBox mercrediCheckbox = (CheckBox) findViewById(R.id.wednesday);
        if(mercrediCheckbox.isChecked()){
            researchColumns.add(new AbstractMap.SimpleImmutableEntry<String, Object>(User.UserEntry.COLUMN_NAME_AVAILABLE_WEDNESDAY,true));
        }

        CheckBox jeudiCheckbox = (CheckBox) findViewById(R.id.thursday);
        if(jeudiCheckbox.isChecked()){
            researchColumns.add(new AbstractMap.SimpleImmutableEntry<String, Object>(User.UserEntry.COLUMN_NAME_AVAILABLE_THURSDAY,true));
        }

        CheckBox vendrediCheckbox = (CheckBox) findViewById(R.id.friday);
        if(vendrediCheckbox.isChecked()){
            researchColumns.add(new AbstractMap.SimpleImmutableEntry<String, Object>(User.UserEntry.COLUMN_NAME_AVAILABLE_FRIDAY,true));
        }

        CheckBox samediCheckbox = (CheckBox) findViewById(R.id.saturday);
        if(samediCheckbox.isChecked()){
            researchColumns.add(new AbstractMap.SimpleImmutableEntry<String, Object>(User.UserEntry.COLUMN_NAME_AVAILABLE_SATURDAY,true));
        }

        CheckBox dimancheCheckbox = (CheckBox) findViewById(R.id.sunday);
        if(dimancheCheckbox.isChecked()){
            researchColumns.add(new AbstractMap.SimpleImmutableEntry<String, Object>(User.UserEntry.COLUMN_NAME_AVAILABLE_SUNDAY,true));
        }

        intent = new Intent(this,ResearchResultsActivity.class);
        intent.putExtra("user",user);
        intent.putExtra("columnList",researchColumns);
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
