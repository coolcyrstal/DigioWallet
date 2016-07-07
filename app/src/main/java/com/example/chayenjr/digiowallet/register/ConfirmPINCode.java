package com.example.chayenjr.digiowallet.register;

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

import com.example.chayenjr.digiowallet.R;
import com.example.chayenjr.digiowallet.Service.HttpService;
import com.mhk.android.passcodeview.PasscodeView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ConfirmPINCode extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public ConfirmPINCode() {
        // Required empty public constructor
    }

    public static ConfirmPINCode newInstance() {
        ConfirmPINCode fragment = new ConfirmPINCode();
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
        View view = inflater.inflate(R.layout.fragment_confirm_pincode, container, false);

        final PasscodeView passcodeView = (PasscodeView)view.findViewById(R.id.passcode_view_confirm);
        passcodeView.requestToShowKeyboard();
        passcodeView.setPasscodeEntryListener(new PasscodeView.PasscodeEntryListener() {
            @Override
            public void onPasscodeEntered(String passcode) {
                Toast.makeText(getActivity().getApplicationContext(), "Passcode entered: " + passcode, Toast.LENGTH_SHORT).show();
                if(CreatePINCode.check_passcode.equals(passcode)){
                    goSuccess();
                    sendPIN(passcode);
                } else{
                    checkPasscode((AppCompatActivity) getContext(), "PIN Code not match!", "Please input PIN code again.", "OK");
                    passcodeView.clearText();
                }
            }
        });
        return view;
    }

    private void goSuccess(){
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.register_info);
        if (fragment instanceof RegisterSuccess == false) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.register_info, RegisterSuccess.newInstance(), "Register success page")
                    .addToBackStack(null)
                    .commit();
            InputMethodManager inputMethodManager = (InputMethodManager) getContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }else Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
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

    private void sendPIN(String passcode){
        Call<HttpService.HttpBinResponse> call_sendPIN = HttpService.service.postWithFormJson_pin(
                passcode, RegisterPage.textCreateMobileNum.getText().toString(),
                HttpService.RegisterContact.getVersions(), HttpService.RegisterContact.getNonce());

        call_sendPIN.enqueue(new Callback<HttpService.HttpBinResponse>() {
            @Override
            public void onResponse(Call<HttpService.HttpBinResponse> call, Response<HttpService.HttpBinResponse> response) {

            }

            @Override
            public void onFailure(Call<HttpService.HttpBinResponse> call, Throwable t) {

            }
        });
    }
}
