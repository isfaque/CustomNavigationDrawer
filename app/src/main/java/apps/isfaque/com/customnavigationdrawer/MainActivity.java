package apps.isfaque.com.customnavigationdrawer;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import apps.isfaque.com.customnavigationdrawer.NavigationDrawer.NavItem;
import apps.isfaque.com.customnavigationdrawer.NavigationDrawer.NavListAdapter;
import apps.isfaque.com.customnavigationdrawer.fragments.about.AboutFragment;
import apps.isfaque.com.customnavigationdrawer.fragments.contact.ContactFragment;
import apps.isfaque.com.customnavigationdrawer.fragments.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    RelativeLayout drawerPane;
    ListView lvNav;

    List<NavItem> listNavItems;
    List<Fragment> listFragments;

    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerPane = (RelativeLayout) findViewById(R.id.drawer_pane);
        lvNav = (ListView) findViewById(R.id.nav_list);
        listNavItems = new ArrayList<NavItem>();
        listNavItems.add(new NavItem("News Tracker", "Track news from multiple sources", R.drawable.jerne_user_icon));
        listNavItems.add(new NavItem("About", "about us", R.drawable.jerne_user_icon));
        listNavItems.add(new NavItem("Contact", "contact us", R.drawable.jerne_user_icon));

        NavListAdapter navListAdapter = new NavListAdapter(getApplicationContext(), R.layout.item_nav_list, listNavItems);

        lvNav.setAdapter(navListAdapter);

        listFragments = new ArrayList<Fragment>();
        listFragments.add(new HomeFragment());
        listFragments.add(new AboutFragment());
        listFragments.add(new ContactFragment());

        // load first home fragment as default in navigation drawer:
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_content, listFragments.get(0)).commit();
        setTitle(listNavItems.get(0).getTitle());
        lvNav.setItemChecked(0, true);
        drawerLayout.closeDrawer(drawerPane);

        // set listener for navigation items:
        lvNav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // replace the fragment with the selection correspondingly:
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_content, listFragments.get(position)).commit();
                setTitle(listNavItems.get(position).getTitle());
                lvNav.setItemChecked(position, true);
                drawerLayout.closeDrawer(drawerPane);

            }
        });

        // create listener for drawer layout
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.drawer_opened, R.string.drawer_closed) {

            @Override
            public void onDrawerOpened(View drawerView) {
                // TODO Auto-generated method stub
                invalidateOptionsMenu();
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                // TODO Auto-generated method stub
                invalidateOptionsMenu();
                super.onDrawerClosed(drawerView);
            }

        };


    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
