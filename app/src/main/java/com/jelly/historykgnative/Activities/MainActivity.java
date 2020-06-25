package com.jelly.historykgnative.Activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.jelly.historykgnative.Core.DatesListAdapter;
import com.jelly.historykgnative.Core.PeopleListAdapter;
import com.jelly.historykgnative.R;
import com.jelly.historykgnative.ui.about.AboutFragment;
import com.jelly.historykgnative.ui.dates.DatesFragment;
import com.jelly.historykgnative.ui.people.PeopleFragment;
import com.jelly.historykgnative.ui.test.TestFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private NavigationView navigationView;
    private AppBarConfiguration mAppBarConfiguration;
    private SearchView searchView;
    FragmentTransaction mFragmentTransaction;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_dates, R.id.nav_people, R.id.nav_test, R.id.nav_about)
                .setDrawerLayout(drawerLayout)
                .build();
        NavController navController =  getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        //NavigationUI.setupWithNavController(navigationView, navController);
    }

    @NonNull
    private NavController getNavController() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (!(fragment instanceof NavHostFragment)) {
            throw new IllegalStateException("Activity " + this
                    + " does not have a NavHostFragment");
        }
        return ((NavHostFragment) fragment).getNavController();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        SetDatesSearch();
        return true;
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        Fragment fragment = null;
        switch (item.getItemId())
        {
            case R.id.nav_dates:
                item.setChecked(true);
                fragment = new DatesFragment();
                SetDatesSearch();
                break;
            case R.id.nav_people:
                item.setChecked(true);
                fragment = new PeopleFragment();
                SetPeopleSearch();
                break;
            case R.id.nav_test:
                searchView.setVisibility(View.GONE);
                fragment = new TestFragment();
                item.setChecked(true);
                break;
            case R.id.nav_about:
                searchView.setVisibility(View.GONE);
                fragment = new AboutFragment();
                item.setChecked(true);
                break;
            default:
                break;
        }

        // update the main content by replacing fragments
        if (fragment != null)
        {
            mFragmentTransaction = getSupportFragmentManager().beginTransaction();
            mFragmentTransaction.replace(R.id.nav_host_fragment, fragment);
            mFragmentTransaction.commit();

            getSupportActionBar().setTitle(item.getTitle().toString());
            drawerLayout.closeDrawers();
        }
        return true;
    }

    private void SetDatesSearch()
    {
        searchView.setVisibility(View.VISIBLE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String queryText)
            {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                ListView datesListView = findViewById(R.id.datesListView);
                DatesListAdapter adapter = (DatesListAdapter) datesListView.getAdapter();
                adapter.getFilter().filter(newText);

                return true;
            }
        });
    }

    private void SetPeopleSearch()
    {
        searchView.setVisibility(View.VISIBLE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String queryText)
            {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                ListView peopleListView = findViewById(R.id.peopleListView);
                PeopleListAdapter adapter = (PeopleListAdapter) peopleListView.getAdapter();
                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }
}
