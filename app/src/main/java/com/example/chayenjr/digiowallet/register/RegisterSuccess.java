package com.example.chayenjr.digiowallet.register;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chayenjr.digiowallet.R;


public class RegisterSuccess extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public RegisterSuccess() {
        // Required empty public constructor
    }

    public static RegisterSuccess newInstance() {
        RegisterSuccess fragment = new RegisterSuccess();
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
        return inflater.inflate(R.layout.fragment_register_success, container, false);
    }
}
