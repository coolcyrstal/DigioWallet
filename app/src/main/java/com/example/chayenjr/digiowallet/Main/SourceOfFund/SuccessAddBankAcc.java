package com.example.chayenjr.digiowallet.Main.SourceOfFund;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.chayenjr.digiowallet.R;


public class SuccessAddBankAcc extends Fragment {

    public interface addSuccessListener {
        void onClickCreateAccBankSuccess();
    }

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
        View rootview = inflater.inflate(R.layout.fragment_success_add_bank_acc, container, false);
        Button btnDoneAddBankAcc = (Button)rootview.findViewById(R.id.btnDoneAddBankAcc);
        btnDoneAddBankAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSuccessListener listener = (addSuccessListener) getActivity();
                listener.onClickCreateAccBankSuccess();
            }
        });
        return rootview;
    }
}
