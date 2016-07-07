package com.example.chayenjr.digiowallet.register;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chayenjr.digiowallet.R;
import com.example.chayenjr.digiowallet.Service.HttpService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    EditText textCreateMobileNum;

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

        final Button nextbutton = (Button)findViewById(R.id.nextbutton);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_register_page == 1){
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.register_info);
                    if (fragment instanceof OTPpage == false) {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.register_info, OTPpage.newInstance(), "OTP Page")
                                .addToBackStack(null)
                                .commit();
                        sendToDB(v);
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
        });
    }

    private void sendToDB(View v){
        HttpService api = HttpService.getInstance();
        Log.d("send", textFirstName.getText().toString() + textLastName.getText().toString() +
                textCitizenID.getText().toString() + textCreateMobileNum.getText().toString() + ":" +
                HttpService.RegisterContact.getNonce() + ":" + HttpService.RegisterContact.getVersions());

        final Map<String, String> headers = new HashMap<String, String>();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpService.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        HttpService.service = retrofit.create(HttpService.HttpBinService.class);

        Call<HttpService.HttpBinResponse> call = HttpService.service.postWithFormJson_createAccount(textFirstName.getText().toString(), textLastName.getText().toString(),
                textCitizenID.getText().toString(), textCreateMobileNum.getText().toString(), HttpService.RegisterContact.getNonce(),
                HttpService.RegisterContact.getVersions());

//        Call<HttpService.HttpBinResponse> call = HttpService.service.postWithFormJson(textCreateMobileNum.getText().toString(), HttpService.RegisterContact.getNonce(),
//                HttpService.RegisterContact.getVersions());

        // Asynchronously execute HTTP request
        call.enqueue(new Callback<HttpService.HttpBinResponse>() {
            @Override
            public void onResponse(Call<HttpService.HttpBinResponse> call, Response<HttpService.HttpBinResponse> response) {
                Log.d("Response code: ", "" + response.body().toString());
//                Toast.makeText(RegisterPage.this, "Response code: " + response.body().toString(), Toast.LENGTH_SHORT).show();
                ((TextView)findViewById(R.id.textotp_notreceive)).setText("Response code: ");
                // isSuccess is true if response code => 200 and <= 300
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

            @Override
            public void onFailure(Call<HttpService.HttpBinResponse> call, Throwable t) {
                Log.d("onFailure", t.getMessage());
            }
        });
    }
}
