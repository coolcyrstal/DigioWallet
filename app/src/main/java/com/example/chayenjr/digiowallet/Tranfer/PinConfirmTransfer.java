package com.example.chayenjr.digiowallet.Tranfer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.chayenjr.digiowallet.LoginRegister;
import com.example.chayenjr.digiowallet.R;
import com.example.chayenjr.digiowallet.Service.HttpService;
import com.mhk.android.passcodeview.PasscodeView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PinConfirmTransfer extends Fragment {

    public interface pinCodeListener {
        void pinCodeSuccess();
    }

    public PinConfirmTransfer() {
        // Required empty public constructor
    }

    public static PinConfirmTransfer newInstance() {
        PinConfirmTransfer fragment = new PinConfirmTransfer();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_pin_confirm_transfer, container, false);
        final PasscodeView passcodeView = (PasscodeView)rootview.findViewById(R.id.passcode_view_confirm_tracsaction);
        InputMethodManager inputMethodManager = (InputMethodManager) getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
        passcodeView.requestToShowKeyboard();
        passcodeView.setPasscodeEntryListener(new PasscodeView.PasscodeEntryListener() {
            @Override
            public void onPasscodeEntered(String passcode) {
                Toast.makeText(getActivity().getApplicationContext(), "Passcode entered: " + passcode, Toast.LENGTH_SHORT).show();
                sendverifyPIN(passcode);
                passcodeView.clearText();
            }
        });

        return rootview;
    }

    private void goSuccess(){
        //go confirm transfer page

        pinCodeListener listener = (pinCodeListener) getActivity();
        listener.pinCodeSuccess();

        InputMethodManager inputMethodManager = (InputMethodManager) getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    private AlertDialog checkPasscode(final AppCompatActivity act, CharSequence title,
                                      CharSequence message, CharSequence buttonYes){
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title).setMessage(message).setPositiveButton(buttonYes, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        return downloadDialog.show();
    }

    private void sendverifyPIN(String passcode){
        Call<HttpService.HttpBinResponse> call_sendVerifyPIN = HttpService.service.postWithFormJson_verifyPIN(
                passcode, LoginRegister.text_mobileNum.getText().toString(),
                HttpService.RegisterContact.getVersions(), HttpService.RegisterContact.getNonce(),
                LoginRegister.check_token);

        call_sendVerifyPIN.enqueue(new Callback<HttpService.HttpBinResponse>() {
            @Override
            public void onResponse(Call<HttpService.HttpBinResponse> call, Response<HttpService.HttpBinResponse> response) {
                if(response.body().getSuccess()){
                    goSuccess();
                } else{
                    checkPasscode((AppCompatActivity) getContext(), "PIN Code not match!", "Please input PIN code again.", "OK");
                }
            }

            @Override
            public void onFailure(Call<HttpService.HttpBinResponse> call, Throwable t) {

            }
        });
    }
}
