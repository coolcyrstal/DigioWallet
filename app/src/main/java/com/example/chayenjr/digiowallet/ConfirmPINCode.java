package com.example.chayenjr.digiowallet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ConfirmPINCode extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public ConfirmPINCode() {
        // Required empty public constructor
    }

    public static ConfirmPINCode newInstance(String param1, String param2) {
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
        return inflater.inflate(R.layout.fragment_confirm_pincode, container, false);
    }
}
