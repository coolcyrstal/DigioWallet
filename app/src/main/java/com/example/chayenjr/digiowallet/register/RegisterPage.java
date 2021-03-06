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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPage extends AppCompatActivity {

    int check_register_page = 1;
    EditText textFirstName;
    EditText textLastName;
    EditText textCitizenID;
    static EditText textCreateMobileNum;
    Button nextbutton;
    static String verify_check_OTP;


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
            sendCreateAccount();
        } else{
            Toast.makeText(RegisterPage.this, OTPpage.getOTPText(), Toast.LENGTH_SHORT).show();
            verifyOTP();
        }
    }

    private void sendCreateAccount(){
        Call<HttpService.HttpBinResponse> call_createAccount = HttpService.service.postWithFormJson_createAccount(
                textFirstName.getText().toString(), textLastName.getText().toString(),
                textCitizenID.getText().toString(), textCreateMobileNum.getText().toString(), HttpService.RegisterContact.getNonce(),
                HttpService.RegisterContact.getVersions());

        // Asynchronously execute HTTP request
        call_createAccount.enqueue(new Callback<HttpService.HttpBinResponse>() {
            @Override
            public void onResponse(Call<HttpService.HttpBinResponse> call, Response<HttpService.HttpBinResponse> response) {
                createAccount(response);
            }

            @Override
            public void onFailure(Call<HttpService.HttpBinResponse> call, Throwable t) {
                Log.d("onFailure", t.getMessage());
            }
        });
    }

    private void createAccount(Response<HttpService.HttpBinResponse> response){
        if(response.body().getSuccess()){
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.register_info);
            if (fragment instanceof OTPpage == false) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.register_info, OTPpage.newInstance(), "OTP Page")
                        .addToBackStack(null)
                        .commit();
            }else Toast.makeText(RegisterPage.this, "Error", Toast.LENGTH_SHORT).show();
            check_register_page = 2;
            requestOTP();
        } else{
            checkAccountInfo(RegisterPage.this, "Your citizen_id has already use", "Please check your citizen_id and type again.", "OK");
        }
    }

    protected static void requestOTP(){
        Call<HttpService.HttpBinResponse> call_reqOTP = HttpService.service.postWithFormJson_reqOTP(
                textCreateMobileNum.getText().toString(), HttpService.RegisterContact.getVersions(),
                HttpService.RegisterContact.getNonce());

        call_reqOTP.enqueue(new Callback<HttpService.HttpBinResponse>() {
            @Override
            public void onResponse(Call<HttpService.HttpBinResponse> call, Response<HttpService.HttpBinResponse> response) {
                verify_check_OTP = response.body().getRef_OTP();
            }

            @Override
            public void onFailure(Call<HttpService.HttpBinResponse> call, Throwable t) {

            }
        });
    }

    private void verifyOTP(){
        Call<HttpService.HttpBinResponse> call_verifyOTP = HttpService.service.postWithFormJson_verifyOTP(
                verify_check_OTP, OTPpage.getOTPText(), HttpService.RegisterContact.getVersions(),
                HttpService.RegisterContact.getNonce());

        call_verifyOTP.enqueue(new Callback<HttpService.HttpBinResponse>() {
            @Override
            public void onResponse(Call<HttpService.HttpBinResponse> call, Response<HttpService.HttpBinResponse> response) {
                Toast.makeText(RegisterPage.this, ""+response.body().getSuccess(), Toast.LENGTH_SHORT).show();
                if(response.body().getSuccess()){
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.register_info);
                    if (fragment instanceof CreatePINCode == false) {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.register_info, CreatePINCode.newInstance(), "PIN code Page")
                                .addToBackStack(null)
                                .commit();
                        nextbutton.setVisibility(View.INVISIBLE);
                    }else Toast.makeText(RegisterPage.this, "Error", Toast.LENGTH_SHORT).show();
                } else{
                    checkAccountInfo(RegisterPage.this, "OTP Incorrect", "Please type otp again.", "OK");
                }
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
