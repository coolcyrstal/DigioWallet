package com.example.chayenjr.digiowallet.Main;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.chayenjr.digiowallet.LoginRegister;
import com.example.chayenjr.digiowallet.Main.SourceOfFund.SourceOfFundActivity;
import com.example.chayenjr.digiowallet.Main.manager.AccountDetails;
import com.example.chayenjr.digiowallet.Main.view.TransactionDetails;
import com.example.chayenjr.digiowallet.R;
import com.example.chayenjr.digiowallet.Service.HttpService;
import com.example.chayenjr.digiowallet.Tranfer.TranferActivity;
import com.example.chayenjr.digiowallet.Tranfer.TransferFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, TransferFragment.OnFragmentInteractionListener, MainHomePageFragment.TransferListener {
    Toolbar mToolbar;
    NavigationView navigationView;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    static TextView account_name;
    static TextView account_citizen_id;
    static TextView username;
    public static TextView usermoney;
    AppCompatTextView mTextViewName, mTextViewMoney, mTitle;
    View view;
    public static TransactionDetails transactionDetails;

    ViewPager viewPager;
    private TabLayout tabLayout;
    public static AccountDetails accountDetails;
    public static String account_details;
    RelativeLayout mViewInformation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        initialize();
        call_account();
        call_Transaction();
        setToolbar();
        setDrawer();
//        setViewPager();

    }

    private void setViewPager() {
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0 :
                        return MainHomePageFragment.newInstance();
                    case 1 :
//                        Log.d("test", ""+ HomePage.transactionDetails.getCustomer());
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

        tabLayout.setupWithViewPager(viewPager);
    }

    private void initialize() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        mTextViewName = (AppCompatTextView) findViewById(R.id.username);
        mTextViewMoney = (AppCompatTextView) findViewById(R.id.user_money);
        mViewInformation = (RelativeLayout) findViewById(R.id.information_account);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTitle = (AppCompatTextView) findViewById(R.id.title);
        view = navigationView.getHeaderView(0);

        account_name = (TextView)view.findViewById(R.id.account_name);
        account_citizen_id = (TextView)view.findViewById(R.id.account_citizen_id);
        username = (TextView)findViewById(R.id.username);
        usermoney = (TextView)findViewById(R.id.user_money);


    }


    private static void setAccountInfo(){
        account_name.setText(LoginRegister.account_info.getF_NAME() + " "  + LoginRegister.account_info.getL_NAME());
        account_citizen_id.setText(LoginRegister.account_info.getCard_id().substring(0, 4) + "*****"
                                    + LoginRegister.account_info.getCard_id()
                                        .substring(LoginRegister.account_info.getCard_id().length() - 4,
                                                LoginRegister.account_info.getCard_id().length()));
        username.setText(LoginRegister.account_info.getF_NAME());
        usermoney.setText(account_details);
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        mToolbar.setTitle("");
    }

    protected void goneView(){
        mViewInformation.setVisibility(View.GONE);
        viewPager.setVisibility(View.GONE);
        tabLayout.setVisibility(View.GONE);
        mTitle.setText("Transfer");
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
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

    public static void call_account(){
        Call<HttpService.HttpBinResponse> call_getAccount = HttpService.service.postWithFormJson_getAccount(
                LoginRegister.check_token, LoginRegister.account_info.getCard_id(),
                HttpService.RegisterContact.getVersions(), HttpService.RegisterContact.getNonce());

        call_getAccount.enqueue(new Callback<HttpService.HttpBinResponse>() {
            @Override
            public void onResponse(Call<HttpService.HttpBinResponse> call, Response<HttpService.HttpBinResponse> response) {
                if(response.body().getSuccess()){
                    accountDetails = response.body().getAccountDetails();
                    if(response.body().getDefault_account().equals(" ")){
                        account_details = accountDetails.getAccounts().get(0).getAvaliable_balance();
                    }else{
                        int i = 0;
                        while(!response.body().getDefault_account()
                                .equals(accountDetails.getAccounts().get(i).getNumber())){
                            i++;
                        }
                        account_details = accountDetails.getAccounts().get(i).getAvaliable_balance();
                    }
                } else{
                    account_details = "0.00";
                }
                setAccountInfo();
                Log.d("admin", "success");
            }

            @Override
            public void onFailure(Call<HttpService.HttpBinResponse> call, Throwable t) {
                Log.d("Fail", "fail");
            }
        });
    }

    private void call_Transaction(){
        Call<HttpService.HttpBinResponse> call_getTransactions = HttpService.service.postWithFormJson_getTransactions(
                LoginRegister.check_token, LoginRegister.text_mobileNum.getText().toString(),
                HttpService.RegisterContact.getVersions(), HttpService.RegisterContact.getNonce(),
                "", "", "", "", "");

        call_getTransactions.enqueue(new Callback<HttpService.HttpBinResponse>() {
            @Override
            public void onResponse(Call<HttpService.HttpBinResponse> call, Response<HttpService.HttpBinResponse> response) {
                if(response.body().getSuccess()){
                    transactionDetails = response.body().getTransactionDetails();
                    setViewPager();
                }
            }

            @Override
            public void onFailure(Call<HttpService.HttpBinResponse> call, Throwable t) {
                Log.d("Fail", "fail");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout(){
        Call<HttpService.HttpBinResponse> call_logout = HttpService.service.postWithFormJson_logout(
                LoginRegister.check_token,
                HttpService.RegisterContact.getVersions(), HttpService.RegisterContact.getNonce());

        call_logout.enqueue(new Callback<HttpService.HttpBinResponse>() {
            @Override
            public void onResponse(Call<HttpService.HttpBinResponse> call, Response<HttpService.HttpBinResponse> response) {
                HomePage.this.finish();
                Log.d("logout", "success");
            }

            @Override
            public void onFailure(Call<HttpService.HttpBinResponse> call, Throwable t) {
                Log.d("Fail", "fail");
            }
        });
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
//        Toast.makeText(HomePage.this, "" + id + " " + R.id.source_of_fund, Toast.LENGTH_SHORT).show();

        if (id == R.id.profile) {

            // Handle the camera action
        }else if (id == R.id.setting) {

        }else if (id == R.id.source_of_fund) {
            goSourceOfFund();
        }else if (id == R.id.privacy) {

        }else if (id == R.id.about) {

        }else if (id == R.id.logout) {
            logout();
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onTransferClickListener() {
        Intent intent = new Intent(HomePage.this, TranferActivity.class);
        startActivity(intent);
    }

    private void goSourceOfFund(){
        Intent intent = new Intent(HomePage.this, SourceOfFundActivity.class);
        startActivity(intent);
    }
}
