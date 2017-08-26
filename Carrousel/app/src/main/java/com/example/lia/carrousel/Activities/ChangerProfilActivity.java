package com.example.lia.carrousel.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.lia.carrousel.Activities.Register.RegisterDescriptionActivity;
import com.example.lia.carrousel.Activities.Register.RegisterDescriptionGardienActivity;
import com.example.lia.carrousel.DemandeManagementSystem.DBDemande;
import com.example.lia.carrousel.R;
import com.example.lia.carrousel.UserManagementSystem.DBUser;
import com.example.lia.carrousel.UserManagementSystem.User;

public class ChangerProfilActivity extends AppCompatActivity {

    private Intent intent;
    private User user;
    private ListView listView;
//    private ArrayList<Map.Entry<String,String>> potential;
//    private FeedListAdapter listAdapter;
//    private ArrayList<Map.Entry<String,Object>> researchColumns;

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
        setContentView(R.layout.activity_changer_profil);

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

        if(user.getType().equals(User.UserEntry.PARENT_TYPE)){
            RadioButton myRadioButton = (RadioButton) findViewById(R.id.radioButton9);
            myRadioButton.setVisibility(View.GONE);
            RadioButton myRadioButton1 = (RadioButton) findViewById(R.id.radioButton7);
            myRadioButton1.setVisibility(View.GONE);
            RadioButton myRadioButton2 = (RadioButton) findViewById(R.id.radioButton8);
            myRadioButton2.setVisibility(View.GONE);
            RadioButton myRadioButton3 = (RadioButton) findViewById(R.id.radioButton6);
            myRadioButton3.setVisibility(View.GONE);
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.carousel);
    }

    public void changerInfo(View v){

        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroupModify);
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (checkedRadioButtonId == -1) {
            // No item selected
        }
        else{
            if (checkedRadioButtonId == R.id.radioButton1) {
                intent= new Intent(this, ModifyNamesActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
            else if(checkedRadioButtonId == R.id.radioButton3){
                intent= new Intent(this, ModifyAddressActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
            else if(checkedRadioButtonId == R.id.radioButton4){
                intent= new Intent(this, ModifyTypeActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
            else if(checkedRadioButtonId == R.id.radioButton5) {
                if(user.getType().equals(User.UserEntry.PARENT_TYPE)){
                    intent = new Intent(this, ModifyDescriptionActivity.class);}
                else{
                    intent = new Intent(this, ModifyDescriptionGardienActivity.class);
                }
                intent.putExtra("user", user);
                startActivity(intent);
            }


//                RadioGroup userTypeRadioGroup = (RadioGroup) findViewById(R.id.radioGroupUserType);
//                int selectedId = userTypeRadioGroup.getCheckedRadioButtonId();
//
//                RadioButton userTypeRadioButton = (RadioButton) findViewById(selectedId);
//                if(userTypeRadioButton.getText().toString().toLowerCase().equals(User.UserEntry.GARDIEN_TYPE)){
//                    Intent myIntent = new Intent(this,ModifyDescriptionGardienActivity.class);
//                    myIntent.putExtra("user", user);
//                    startActivity(myIntent);
//                }else{
//                    Intent myIntent = new Intent(this,ModifyDescriptionActivity.class);
//                    myIntent.putExtra("user", user);
//                    startActivity(myIntent);
//                }

            else if(checkedRadioButtonId == R.id.radioButton6) {
                intent = new Intent(this, ModifyLaguageActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }

            else if(checkedRadioButtonId == R.id.radioButton9) {
                intent = new Intent(this, ModifyAvailabilityActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }

            else if(checkedRadioButtonId == R.id.radioButton8) {
                intent = new Intent(this, ModifyQualitiesActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }

            else if(checkedRadioButtonId == R.id.radioButton7) {
                intent = new Intent(this, ModifyClassesActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }



        }

    }



    /** Swaps fragments in the main content view */
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
}

