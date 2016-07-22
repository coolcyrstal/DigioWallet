package com.example.chayenjr.digiowallet.Main.SourceOfFund;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chayenjr.digiowallet.R;


public class SuccessAddBankAcc extends Fragment {

    public SuccessAddBankAcc() {
        // Required empty public constructor
    }

    public static SuccessAddBankAcc newInstance() {
        SuccessAddBankAcc fragment = new SuccessAddBankAcc();
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
        return inflater.inflate(R.layout.fragment_success_add_bank_acc, container, false);
    }
}
