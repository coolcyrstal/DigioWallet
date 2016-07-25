package com.example.chayenjr.digiowallet.Main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chayenjr.digiowallet.LoginRegister;
import com.example.chayenjr.digiowallet.Main.view.CustomButton;
import com.example.chayenjr.digiowallet.R;
import com.example.chayenjr.digiowallet.Service.HttpService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class MainHomePageFragment extends Fragment {

    CustomButton mTransferBtn;

    public MainHomePageFragment() {
        super();
    }

    public interface TransferListener{
        void onTransferClickListener();
    }

    @SuppressWarnings("unused")
    public static MainHomePageFragment newInstance() {
        MainHomePageFragment fragment = new MainHomePageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_homepage, container, false);
        initInstances(rootView, savedInstanceState);
        setTransferListener();
        return rootView;
    }

    private void setTransferListener() {
        mTransferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call_checkToken();
                if(LoginRegister.check_status_login){
                    TransferListener listener = (TransferListener) getActivity();
                    listener.onTransferClickListener();
                }else{
                    checkStatusOnLogin((AppCompatActivity) getContext(), "You are not login", "Your app run overtime.", "OK");
                }
            }
        });
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
                getActivity().finish();
            }
        });
        return downloadDialog.show();
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        mTransferBtn = (CustomButton) rootView.findViewById(R.id.transfer_btn);
        // Init 'View' instance(s) with rootView.findViewById here
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

}
