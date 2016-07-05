package com.example.chayenjr.digiowallet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mhk.android.passcodeview.PasscodeView;


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

        PasscodeView passcodeView = (PasscodeView)view.findViewById(R.id.passcode_view_confirm);
        passcodeView.setPasscodeEntryListener(new PasscodeView.PasscodeEntryListener() {
            @Override
            public void onPasscodeEntered(String passcode) {
                Toast.makeText(getActivity().getApplicationContext(), "Passcode entered: " + passcode, Toast.LENGTH_SHORT).show();
                getActivity().finish();
//                Fragment fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.register_info);
//                if (fragment instanceof ConfirmPINCode == false) {
//                    getActivity().getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.register_info, ConfirmPINCode.newInstance(), "Confirm PIN code page")
//                            .addToBackStack(null)
//                            .commit();
//                }else Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
