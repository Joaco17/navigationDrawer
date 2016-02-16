package com.joaquin.proyecto_completo;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements Fragment1.OnFragmentInteractionListener, Fragment2.OnFragmentInteractionListener{
    /**
     * Instancia del drawer
     */
    private DrawerLayout drawerLayout;

    /**
     * Titulo inicial del drawer
     */
    private String drawerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(android.R.drawable.ic_menu_manage);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        Menu menu = null;
        if (navigationView != null) {
            menu = navigationView.getMenu();
        }
        if (menu != null) {
            menu.getItem(0).setChecked(true);
            Fragment fragment = null;
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            // Crear nuevo fragmento
            String title = menu.getItem(0).getTitle().toString();
            fragment = new Fragment1();
            fragmentTransaction
                    .replace(R.id.main_content, fragment)
                    .addToBackStack(null)
                    .commit();
            drawerLayout.closeDrawers(); // Cerrar drawer

            setTitle(title); // Setear título actual

        }

        drawerTitle = getResources().getString(R.string.home_item);

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers(); // Cerrar drawer
        }else{
            if(getSupportFragmentManager().getBackStackEntryCount() > 0){
                getFragmentManager().popBackStack();
            }else{
                super.onBackPressed();
            }
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    Fragment fragment = null;
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    String title = null;

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.nav_home:
                                // Marcar item presionado
                                menuItem.setChecked(true);
                                // Crear nuevo fragmento
                                title = menuItem.getTitle().toString();
                                fragment = new Fragment1();
                                break;
                            case R.id.nav_productos:
                                // Marcar item presionado
                                menuItem.setChecked(true);
                                // Crear nuevo fragmento
                                title = menuItem.getTitle().toString();
                                fragment = new Fragment2();
                                break;
                        }
                        fragmentTransaction
                                .replace(R.id.main_content, fragment)
                                .addToBackStack(null)
                                .commit();
                        drawerLayout.closeDrawers(); // Cerrar drawer

                        setTitle(title); // Setear título actual

                        return true;
                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            getMenuInflater().inflate(R.menu.nav_menu, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}