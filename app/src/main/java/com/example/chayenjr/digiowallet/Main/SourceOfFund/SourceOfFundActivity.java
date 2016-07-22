package com.example.chayenjr.digiowallet.Main.SourceOfFund;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.chayenjr.digiowallet.LoginRegister;
import com.example.chayenjr.digiowallet.R;
import com.example.chayenjr.digiowallet.Service.HttpService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SourceOfFundActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        PinConfirmAddBank.pinCodeListener,
        SelectBankPage.selectBankListener{

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private View view;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    Button add_new_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_of_fund);

        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.source_of_fund_contentContainer, SourceOfFundListFragment.newInstance())
                    .commit();

        initInstance();
    }

    private void initInstance() {
        initToolbar();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_sourceOfFund);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.source_of_fund_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        view = navigationView.getHeaderView(0);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setDrawer();

        add_new_account = (Button)findViewById(R.id.action_add_account);
        add_new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn_select_type = (Button)findViewById(R.id.btn_select_type);
                openContextMenu(btn_select_type);
                registerForContextMenu(btn_select_type);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        Log.d("context menu", "active");
        menu.setHeaderTitle("Select Type");
        menu.add(0, v.getId(), 0, "Add Bank Account");
        menu.add(0, v.getId(), 0, "Add Credit Card");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Add Bank Account") {
            Fragment fragment = getSupportFragmentManager()
                    .findFragmentById(R.id.source_of_fund_contentContainer);

            if (fragment instanceof SelectBankPage == false) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.source_of_fund_contentContainer, SelectBankPage.newInstance())
                        .commit();
            }
        } else if (item.getTitle() == "Add Credit Card") {
            Toast.makeText(this, "Action 2 invoked", Toast.LENGTH_SHORT).show();
        } else {
            return false;
        }
        return true;
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
        mDrawerLayout.
                addDrawerListener(actionBarDrawerToggle);
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

    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickCreateAccBank() {
        call_checkToken();
        if(LoginRegister.check_status_login){
            Fragment fragment = getSupportFragmentManager()
                    .findFragmentById(R.id.source_of_fund_contentContainer);

            if (fragment instanceof BankAccountInfo == false) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.source_of_fund_contentContainer, BankAccountInfo.newInstance())
                        .commit();
            }
        }else{
            checkStatusOnLogin((AppCompatActivity) getApplicationContext(), "You are not login", "Your app run overtime.", "OK");
        }
    }

    @Override
    public void onClickCreateAccBankWithWebtext() {
        call_checkToken();
        if(LoginRegister.check_status_login){
            Fragment fragment = getSupportFragmentManager()
                    .findFragmentById(R.id.source_of_fund_contentContainer);

            if (fragment instanceof BankAccountInfo == false) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.source_of_fund_contentContainer, BankAccountInfo.newInstance())
                        .commit();
            }
        }else{
            checkStatusOnLogin((AppCompatActivity) getApplicationContext(), "You are not login", "Your app run overtime.", "OK");
        }
    }

    @Override
    public void pinCodeSuccessBankAcc() {
        call_checkToken();
        if(LoginRegister.check_status_login){
            Fragment fragment = getSupportFragmentManager()
                    .findFragmentById(R.id.source_of_fund_contentContainer);

            if (fragment instanceof SuccessAddBankAcc == false) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.source_of_fund_contentContainer, SuccessAddBankAcc.newInstance())
                        .commit();
            }
        }else{
            checkStatusOnLogin((AppCompatActivity) getApplicationContext(), "You are not login", "Your app run overtime.", "OK");
        }
    }

    private void call_checkToken(){
        Call<HttpService.HttpBinResponse> call_checktoken = HttpService.service.postWithFormJson_checkToken(
                LoginRegister.check_token,
                HttpService.RegisterContact.getVersions(), HttpService.RegisterContact.getNonce());

        call_checktoken.enqueue(new Callback<HttpService.HttpBinResponse>() {
            @Override
            public void onResponse(Call<HttpService.HttpBinResponse> call, Response<HttpService.HttpBinResponse> response) {
                if(response.body().getSuccess()){LoginRegister.check_status_login = true;}
                else {LoginRegister.check_status_login = false;}
            }

            @Override
            public void onFailure(Call<HttpService.HttpBinResponse> call, Throwable t) {

            }
        });
    }

    private AlertDialog checkStatusOnLogin(final AppCompatActivity act, CharSequence title,
                                           CharSequence message, CharSequence buttonYes){
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title).setMessage(message).setPositiveButton(buttonYes, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        return downloadDialog.show();
    }
}
