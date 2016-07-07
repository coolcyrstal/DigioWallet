package com.example.chayenjr.digiowallet.register;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chayenjr.digiowallet.R;
import com.example.chayenjr.digiowallet.Service.HttpService;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterPage extends AppCompatActivity {

    int check_register_page = 1;
    EditText textFirstName;
    EditText textLastName;
    EditText textCitizenID;
    static EditText textCreateMobileNum;
    Button nextbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.register_actionbar);

        textFirstName = (EditText)findViewById(R.id.textFirstname);
        textLastName = (EditText)findViewById(R.id.textLastname);
        textCitizenID = (EditText)findViewById(R.id.textCitizenID);
        textCreateMobileNum = (EditText)findViewById(R.id.text_createMobileNum);
        nextbutton = (Button)findViewById(R.id.nextbutton);


        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textFirstName.getText().toString().equals("") || textLastName.getText().toString().equals("")
                        || textCitizenID.getText().toString().equals("") || textCreateMobileNum.getText().toString().equals("")){
                    checkAccountInfo(RegisterPage.this, "Your information not complete", "Please input all field.", "OK");
                } else{
                    goCreateAccount();
                }
            }
        });
    }

    private void goCreateAccount(){
        if(check_register_page == 1){
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.register_info);
            if (fragment instanceof OTPpage == false) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.register_info, OTPpage.newInstance(), "OTP Page")
                        .addToBackStack(null)
                        .commit();
                sendCreateAccount();
            }else Toast.makeText(RegisterPage.this, "Error", Toast.LENGTH_SHORT).show();
            check_register_page = 2;
        } else{
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.register_info);
            if (fragment instanceof CreatePINCode == false) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.register_info, CreatePINCode.newInstance(), "PIN code Page")
                        .addToBackStack(null)
                        .commit();
                nextbutton.setVisibility(View.INVISIBLE);
            }else Toast.makeText(RegisterPage.this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendCreateAccount(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpService.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        HttpService.service = retrofit.create(HttpService.HttpBinService.class);

        Call<HttpService.HttpBinResponse> call_createAccount = HttpService.service.postWithFormJson_createAccount(
                textFirstName.getText().toString(), textLastName.getText().toString(),
                textCitizenID.getText().toString(), textCreateMobileNum.getText().toString(), HttpService.RegisterContact.getNonce(),
                HttpService.RegisterContact.getVersions());

        // Asynchronously execute HTTP request
        call_createAccount.enqueue(new Callback<HttpService.HttpBinResponse>() {
            @Override
            public void onResponse(Call<HttpService.HttpBinResponse> call, Response<HttpService.HttpBinResponse> response) {
                doSomething(response);
            }

            @Override
            public void onFailure(Call<HttpService.HttpBinResponse> call, Throwable t) {
                Log.d("onFailure", t.getMessage());
            }
        });

        requestOTP();
    }

    private void doSomething(Response<HttpService.HttpBinResponse> response){
//        Toast.makeText(RegisterPage.this, "Response code: " + response.body().toString(), Toast.LENGTH_SHORT).show();
//        ((TextView)findViewById(R.id.textotp_notreceive)).setText("Success : "
//                + response.body().getSuccess() + "Error code: " + response.body().getError_code());
        if (!response.isSuccessful()) {
            // print response body if unsuccessful
            try {
                Log.d("success", response.errorBody().string());
            } catch (IOException e) {
                // do nothing
                Log.d("fail", "555");
            }
            return;
        }
        // if parsing the JSON body failed, `response.body()` returns null
        HttpService.HttpBinResponse decodedResponse = response.body();
        if (decodedResponse == null) return;
    }

    protected static void requestOTP(){
        Call<HttpService.HttpBinResponse> call_reqOTP = HttpService.service.postWithFormJson_reqOTP(
                textCreateMobileNum.getText().toString(), HttpService.RegisterContact.getVersions(),
                HttpService.RegisterContact.getNonce());

        call_reqOTP.enqueue(new Callback<HttpService.HttpBinResponse>() {
            @Override
            public void onResponse(Call<HttpService.HttpBinResponse> call, Response<HttpService.HttpBinResponse> response) {

            }

            @Override
            public void onFailure(Call<HttpService.HttpBinResponse> call, Throwable t) {

            }
        });
    }

    private AlertDialog checkAccountInfo(final AppCompatActivity act, CharSequence title,
                                                    CharSequence message, CharSequence buttonYes){
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title).setMessage(message).setPositiveButton(buttonYes, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        return downloadDialog.show();
    }
}
