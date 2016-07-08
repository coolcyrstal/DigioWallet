package com.example.chayenjr.digiowallet;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chayenjr.digiowallet.Main.HomePage;
import com.example.chayenjr.digiowallet.Service.HttpService;
import com.example.chayenjr.digiowallet.register.RegisterPage;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoginRegister extends AppCompatActivity {

    private static final boolean AUTO_HIDE = true;
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    EditText text_mobileNum;
    EditText text_PIN;
    String check_token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_register);

        TextView create_account = (TextView)findViewById(R.id.create_new_account);
        Button login_button = (Button)findViewById(R.id.login_button);
        text_mobileNum = (EditText)findViewById(R.id.textMobileNum);
        text_PIN = (EditText)findViewById(R.id.textPIN);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpService.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        HttpService.service = retrofit.create(HttpService.HttpBinService.class);

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginRegister.this, RegisterPage.class);
                startActivity(intent);
            }
        });
        mVisible = true;

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text_mobileNum.getText().toString().equals("") || text_PIN.getText().toString().equals("")){
                    checkLoginInfo(LoginRegister.this, "Mobile num or password incorrect", "Please type again.", "OK");
                } else{
                    checkLoginOnDB();
                }
            }
        });
    }

    private void checkLoginOnDB(){
        Call<HttpService.HttpBinResponse> call_login = HttpService.service.postWithFormJson_login(
                text_mobileNum.getText().toString(), text_PIN.getText().toString(),
                HttpService.RegisterContact.getVersions(), HttpService.RegisterContact.getNonce());

        call_login.enqueue(new Callback<HttpService.HttpBinResponse>() {
            @Override
            public void onResponse(Call<HttpService.HttpBinResponse> call, Response<HttpService.HttpBinResponse> response) {
                if(response.body().getSuccess()){
                    check_token = response.body().getToken();
                    Intent intent = new Intent(LoginRegister.this, HomePage.class);
                    startActivity(intent);
                } else{
                    checkLoginInfo(LoginRegister.this, "Mobile num or password incorrect", "Please type again.", "OK");
                }
            }

            @Override
            public void onFailure(Call<HttpService.HttpBinResponse> call, Throwable t) {
                checkLoginInfo(LoginRegister.this, "Mobile num or password incorrect", "Please type again.", "OK");
            }
        });
    }

    private AlertDialog checkLoginInfo(final AppCompatActivity act, CharSequence title,
                                         CharSequence message, CharSequence buttonYes){
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title).setMessage(message).setPositiveButton(buttonYes, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        return downloadDialog.show();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        delayedHide(100);
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mVisible = false;

//        Button dummy = (Button)findViewById(R.id.dummy_button);
//        dummy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                show();
//            }
//        });
        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
