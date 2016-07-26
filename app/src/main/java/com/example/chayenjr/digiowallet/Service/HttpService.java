package com.example.chayenjr.digiowallet.Service;

import com.example.chayenjr.digiowallet.Main.manager.AccountDetails;
import com.example.chayenjr.digiowallet.Main.manager.LoginDetails;
import com.example.chayenjr.digiowallet.Main.view.TransactionDetails;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Chayenjr on 6/7/2559.
 */
public class HttpService {
    public static final String API_URL = "http://uat-uniwalletserver.digio.co.th:3300/service/";
    public static HttpService instance = null;
    private static Map<String, String> headers = new HashMap<String, String>();
    public static HttpBinService service;

    public static HttpService getInstance() {
        if(instance == null) {
            instance = new HttpService();
        }
        return instance;
    }
    /**
     * Generic HttpBin.org Response Container
     */
    public static class HttpBinResponse {

        @SerializedName("success") private Boolean success;
        @SerializedName("error_code") private String error_code;
        @SerializedName("ref_otp") private String ref_otp;
        @SerializedName("token") private String token;
        @SerializedName("details") private LoginDetails details;
        @SerializedName("account_details") private AccountDetails accountDetails;
        @SerializedName("datetime") private String datetime;
        @SerializedName("Default_account") private String default_account;
        @SerializedName("transaction_details") private TransactionDetails transactionDetails;


        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public void setError_code(String error_code) {
            this.error_code = error_code;
        }

        public String getError_code(){
            return error_code;
        }

        public String getRef_OTP() {
            return ref_otp;
        }

        public String getToken() {
            return token;
        }

        public LoginDetails getDetails() {
            return details;
        }

        public void setDetails(LoginDetails details) {
            this.details = details;
        }

        public AccountDetails getAccountDetails() {
            return accountDetails;
        }

        public void setAccountDetails(AccountDetails accountDetails) {
            this.accountDetails = accountDetails;
        }

        public String getDatetime() {
            return datetime;
        }

        public String getDefault_account() {
            return default_account;
        }

        public TransactionDetails getTransactionDetails() {
            return transactionDetails;
        }

        public void setTransactionDetails(TransactionDetails transactionDetails) {
            this.transactionDetails = transactionDetails;
        }
    }

    public static class RegisterContact {

        @SerializedName("firstname")
        @Expose
        String firstname;
        @SerializedName("lastname")
        @Expose
        String lastname;
        @SerializedName("mobile")
        @Expose
        String mobile;
        @SerializedName("card_id")
        @Expose
        String card_id;
        @SerializedName("nonce")
        @Expose
        String nonce;
        @SerializedName("versions")
        @Expose
        String versions;

        public RegisterContact(String firstname, String lastname, String mobile, String card_id, String nonce, String versions) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.mobile = mobile;
            this.card_id = card_id;
            this.nonce = nonce;
            this.versions = versions;
        }

        public String getFirstname(){return firstname;}

        public String getLastname(){return lastname;}

        public String getMobile(){return mobile;}

        public String getCard_id(){return card_id;}

        public static String getNonce(){
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = new Date();
            String nonce = dateFormat.format(date) + String.valueOf(100000 + new Random().nextInt(899999));
            return nonce;
        }

        public static String getVersions(){return "1.0.0";}
    }

    /**
     * HttpBin.org service definition
     */
    public interface HttpBinService {
        @GET("mobile_register")
        Call<HttpBinResponse> getSuccess();

        // request /get?testArg=...
        @GET("mobile_register")
        Call<HttpBinResponse> getWithJson(
                @Query("success") Boolean success,
                @Query("error_code") String error_code
        );

        // POST form encoded with form field params
        //mobile service doc 1
        @FormUrlEncoded
        @POST("mobile_register")
        Call<HttpBinResponse> postWithFormJson_createAccount(
                @Field("firstname") String firstname,
                @Field("lastname") String lastname,
                @Field("card_id") String card_id,
                @Field("mobile") String mobile,
                @Field("nonce") String nonce,
                @Field("versions") String versions
        );

        //mobile service doc 2
        @FormUrlEncoded
        @POST("mobile_pin")
        Call<HttpBinResponse> postWithFormJson_pin(
                @Field("pin") String pin,
                @Field("mobile") String mobile,
                @Field("versions") String versions,
                @Field("nonce") String nonce
        );

        //mobile service doc 3
        @FormUrlEncoded
        @POST("mobile_login")
        Call<HttpBinResponse> postWithFormJson_login(
                @Field("mobile") String mobile,
                @Field("pin") String pin,
                @Field("versions") String versions,
                @Field("nonce") String nonce
        );

        //mobile service doc 4
        @FormUrlEncoded
        @POST("mobile_logout")
        Call<HttpBinResponse> postWithFormJson_logout(
                @Field("token") String token,
                @Field("versions") String versions,
                @Field("nonce") String nonce
        );

        //mobile service doc 5
        @FormUrlEncoded
        @POST("mobile_reqOTP")
        Call<HttpBinResponse> postWithFormJson_reqOTP(
                @Field("mobile") String mobile,
                @Field("versions") String versions,
                @Field("nonce") String nonce
        );

        //mobile service doc 6
        @FormUrlEncoded
        @POST("mobile_verifyOTP")
        Call<HttpBinResponse> postWithFormJson_verifyOTP(
                @Field("ref_otp") String ref_otp,
                @Field("otp") String otp,
                @Field("versions") String versions,
                @Field("nonce") String nonce
        );

        //mobile service doc 7
        @FormUrlEncoded
        @POST("mobile_getAccount")
        Call<HttpBinResponse> postWithFormJson_getAccount(
                @Field("token") String token,
                @Field("Acc_no") String Acc_no,
                @Field("versions") String versions,
                @Field("nonce") String nonce
        );

        //mobile service doc 10
        @FormUrlEncoded
        @POST("mobile_transaction/transfer")
        Call<HttpBinResponse> postWithFormJson_transaction_transfer(
                @Field("token") String token,
                @Field("from_Acc_no") String from_Acc_no,
                @Field("to_Acc_no") String to_Acc_no,
                @Field("amount") String amount,
                @Field("versions") String versions,
                @Field("nonce") String nonce
        );

        //mobile service doc 12
        @FormUrlEncoded
        @POST("mobile_getTransactions")
        Call<HttpBinResponse> postWithFormJson_getTransactions(
                @Field("token") String token,
                @Field("mobile") String mobile,
                @Field("versions") String versions,
                @Field("nonce") String nonce,
                @Field("type") String type,
                @Field("ref") String ref,
                @Field("Acc_no") String Acc_no,
                @Field("datefrom") String datefrom,
                @Field("dateto") String dateto
        );

        //mobile service doc 13
        @FormUrlEncoded
        @POST("mobile_verifyPin")
        Call<HttpBinResponse> postWithFormJson_verifyPIN(
                @Field("pin") String pin,
                @Field("mobile") String mobile,
                @Field("versions") String versions,
                @Field("nonce") String nonce,
                @Field("token") String token
        );

        //mobile service doc 14
        @FormUrlEncoded
        @POST("mobile_setAccount")
        Call<HttpBinResponse> postWithFormJson_setDefaultAccount(
                @Field("token") String token,
                @Field("Acc_no") String Acc_no,
                @Field("versions") String versions,
                @Field("nonce") String nonce
        );

        //mobile service doc 16
        @FormUrlEncoded
        @POST("mobile_checkToken")
        Call<HttpBinResponse> postWithFormJson_checkToken(
                @Field("token") String token,
                @Field("versions") String versions,
                @Field("nonce") String nonce
        );

        // POST with a JSON body
        @POST("mobile_register")
        Call<HttpBinResponse> postWithJson(
                @Body RegisterContact registerContact
        );
    }

    public HttpBinService getService() {
        return service;
    }

    public static void testApiRequest() {
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.networkInterceptors().add(new Interceptor() {
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();

                System.out.println("Adding headers:" + headers);
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    builder.addHeader(entry.getKey(), entry.getValue());
                }

                return chain.proceed(builder.build());
            }
        });

        // Retrofit setup
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Service setup
        service = retrofit.create(HttpBinService.class);

        // Prepare the HTTP request
    }
}
