package com.example.chayenjr.digiowallet.Tranfer;

import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chayenjr.digiowallet.LoginRegister;
import com.example.chayenjr.digiowallet.Main.MainHomePageFragment;
import com.example.chayenjr.digiowallet.R;

public class TranferActivity extends AppCompatActivity implements TransferFragment.OnFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener,
        TransferFragment.BtnListener,
        TransferInfoFragment.OnFragmentListener,
        PinConfirmTransfer.pinCodeListener {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private View view;
    private TextView account_name;
    private TextView account_citizen_id;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private String accountNumber = null;
    private String amount = null;
    private String note = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tranfer);

        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, TransferFragment.newInstance("", ""), "TransferFragment")
                    .commit();
        initInstance();
    }

    private void initInstance() {
        initToolbar();

    }

    /**************
     * Init Toolbar
     **************/

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        view = navigationView.getHeaderView(0);
        account_name = (TextView) view.findViewById(R.id.account_name);
        account_citizen_id = (TextView) view.findViewById(R.id.account_citizen_id);
        account_name.setText(LoginRegister.account_info.getF_NAME() + " " + LoginRegister.account_info.getL_NAME());
        account_citizen_id.setText(LoginRegister.account_info.getCard_id().substring(0, 4) + "*****"
                + LoginRegister.account_info.getCard_id()
                .substring(LoginRegister.account_info.getCard_id().length() - 4,
                        LoginRegister.account_info.getCard_id().length()));

        setSupportActionBar(mToolbar);
        mToolbar.setTitle("Transfer");

        setDrawer();
    }

    private void setDrawer() {
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.openDrawer, R.string.closeDrawer) {
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
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.profile) {

            // Handle the camera action
        } else if (id == R.id.setting) {


        } else if (id == R.id.source_of_fund) {

        } else if (id == R.id.privacy) {

        } else if (id == R.id.about) {

        } else if (id == R.id.logout) {

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return false;
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

    /***************
     * Function
     ***************/

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void mBankAccountBtnListener() {
        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.contentContainer);

        if (fragment instanceof TransferInfoFragment == false)
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, TransferInfoFragment.newInstance("", ""), "TransferInfoFragment")
                    .addToBackStack(null)
                    .commit();
    }

    @Override
    public void mDigioWalletBtnListener() {

    }

    @Override
    public void mCitizenIdBtnListener() {

    }

    @Override
    public void setOnClickButtonNext() {
        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.contentContainer);

        if (fragment instanceof TransferInfoFragment) {
            TransferInfoFragment transferInfoFragment = (TransferInfoFragment) fragment;
            accountNumber = transferInfoFragment.getAccountNumber();
            amount = transferInfoFragment.getCreditAmount();

            if (transferInfoFragment.getNoteDescription() != null)
                note = transferInfoFragment.getNoteDescription();
            else note = "";

        }

        if (accountNumber != "" && amount != "") {

            if (fragment instanceof PinConfirmTransfer == false)
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer, PinConfirmTransfer.newInstance())
                        .addToBackStack(null)
                        .commit();

        } else {
            Toast.makeText(TranferActivity.this, "Please Input Text", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void pinCodeSuccess() {
        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.contentContainer);

        if (fragment instanceof ConfirmTransferFragment == false) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, ConfirmTransferFragment.newInstance(accountNumber, amount, note))
                    .commit();
        }
    }
}
