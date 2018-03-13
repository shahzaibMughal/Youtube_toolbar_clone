package com.example.shahzaib.youtube_toolbar_clone;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    MenuItem ic_profile,ic_video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.drawable.ic_youtube);

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP)
        {
            toolbar.setElevation(6f);
        }

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);

        ic_profile = menu.findItem(R.id.ic_profile);
        ic_video = menu.findItem(R.id.ic_video);


        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.searchBtn).getActionView();
        searchView.setMaxWidth(10000);

        // current activity should the searchable activity (it is defined in manifest that activity is searchable or not)
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default




        MenuItemCompat.setOnActionExpandListener(menu.findItem(R.id.searchBtn), new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                ic_profile.setVisible(false);
                ic_video.setVisible(false);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                ic_profile.setVisible(true);
                ic_video.setVisible(true);
                return true;
            }
        });

        return true;
    }




    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.searchBtn)
        {
            Toast.makeText(this, "Search button Clicked", Toast.LENGTH_SHORT).show();
            ic_profile.setVisible(false);
            ic_video.setVisible(false);
        }


        return true;
    }*/










}
