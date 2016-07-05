package com.example.chayenjr.digiowallet.register;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.chayenjr.digiowallet.R;
import com.mhk.android.passcodeview.PasscodeView;


public class CreatePINCode extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public CreatePINCode() {
        // Required empty public constructor
    }

    public static CreatePINCode newInstance() {
        CreatePINCode fragment = new CreatePINCode();
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
        View view = inflater.inflate(R.layout.fragment_create_pincode, container, false);

        PasscodeView passcodeView = (PasscodeView)view.findViewById(R.id.passcode_view);
        InputMethodManager inputMethodManager = (InputMethodManager) getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
        passcodeView.requestToShowKeyboard();
        passcodeView.setPasscodeEntryListener(new PasscodeView.PasscodeEntryListener() {
            @Override
            public void onPasscodeEntered(String passcode) {
                Toast.makeText(getActivity().getApplicationContext(), "Passcode entered: " + passcode, Toast.LENGTH_SHORT).show();
                Fragment fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.register_info);
                if (fragment instanceof ConfirmPINCode == false) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.register_info, ConfirmPINCode.newInstance(), "Confirm PIN code page")
                            .addToBackStack(null)
                            .commit();
                }else Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
