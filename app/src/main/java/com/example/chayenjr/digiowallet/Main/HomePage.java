package com.example.chayenjr.digiowallet.Main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.chayenjr.digiowallet.LoginRegister;
import com.example.chayenjr.digiowallet.R;

import org.json.JSONException;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar mToolbar;
    NavigationView navigationView;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    AppCompatTextView mTextviewName, mTextViewMoney;
    TextView account_name, account_citizen_id;
    View view;

    ViewPager viewPager;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        initialize();
        setToolbar();
        setDrawer();
        setViewPager();

    }

    private void setViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0 :
                        return MainHomePageFragment.newInstance();
                    case 1 :
                        return HistoryListFragment.newInstance();
                    default:
                        return null;
                }

            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position){
                    case 0 :
                        return "HOME";
                    case 1 :
                        return "HISTORY";
                    default:
                        return "";
                }
            }
        });

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void initialize() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        mTextviewName = (AppCompatTextView) findViewById(R.id.username);
        mTextViewMoney = (AppCompatTextView) findViewById(R.id.user_money);
        view = navigationView.getHeaderView(0);

        account_name = (TextView)view.findViewById(R.id.account_name);
        account_citizen_id = (TextView)view.findViewById(R.id.account_citizen_id);
        Log.d("show", LoginRegister.account_info.getF_NAME());
        try {
            setAccountInfo();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setAccountInfo() throws JSONException {
        account_name.setText(LoginRegister.account_info.getF_NAME() + " "  + LoginRegister.account_info.getL_NAME());
        account_citizen_id.setText(LoginRegister.account_info.getCard_id().substring(0, 4) + "*****"
                                    + LoginRegister.account_info.getCard_id()
                                        .substring(LoginRegister.account_info.getCard_id().length() - 4, LoginRegister.account_info.getCard_id().length()));
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        mToolbar.setTitle("");
    }
    private void setDrawer() {
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.openDrawer, R.string.closeDrawer){
            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.profile) {

            // Handle the camera action
        }else if (id == R.id.setting) {


        }else if (id == R.id.source_of_fund) {

        }else if (id == R.id.privacy) {

        }else if (id == R.id.about) {

        }else if (id == R.id.logout) {

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}
