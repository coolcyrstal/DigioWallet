package com.example.chayenjr.digiowallet.Main.SourceOfFund;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.chayenjr.digiowallet.R;


public class SelectBankPage extends Fragment {

    public interface selectBankListener {
        void onClickCreateAccBank();
        void onClickCreateAccBankWithWebtext();
    }

    public SelectBankPage() {
        // Required empty public constructor
    }

    ImageView select_bankImage_scb;
    ImageView select_bankImage_bbl;
    ImageView select_bankImage_ktb;
    ImageView select_bankImage_kbank;
    ImageView select_bankImage_krungsri;
    ImageView select_bankImage_tbank;
    ImageView select_bankImage_digio;


    public static SelectBankPage newInstance() {
        SelectBankPage fragment = new SelectBankPage();
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
        View rootview = inflater.inflate(R.layout.fragment_select_bank_page, container, false);
        initInstance(rootview);
        return rootview;
    }

    private void initInstance(View rootview){
        select_bankImage_scb = (ImageView)rootview.findViewById(R.id.selected_scb);
        select_bankImage_bbl = (ImageView)rootview.findViewById(R.id.selected_bbl);
        select_bankImage_ktb = (ImageView)rootview.findViewById(R.id.selected_ktb);
        select_bankImage_kbank = (ImageView)rootview.findViewById(R.id.selected_kbank);
        select_bankImage_krungsri = (ImageView)rootview.findViewById(R.id.selected_krungsri);
        select_bankImage_tbank = (ImageView)rootview.findViewById(R.id.selected_tbank);
        select_bankImage_digio = (ImageView)rootview.findViewById(R.id.selected_digio);

        onClickBank();
    }

    private void onClickBank(){
        select_bankImage_scb.setOnClickListener(getButtonOnClickListenerWithNormal(0));
        select_bankImage_bbl.setOnClickListener(getButtonOnClickListenerWithNormal(1));
        select_bankImage_ktb.setOnClickListener(getButtonOnClickListenerWithNormal(2));
        select_bankImage_kbank.setOnClickListener(getButtonOnClickListenerWithNormal(3));
        select_bankImage_digio.setOnClickListener(getButtonOnClickListenerWithNormal(6));

        select_bankImage_krungsri.setOnClickListener(getButtonOnClickListenerWithWebtext(4));
        select_bankImage_tbank.setOnClickListener(getButtonOnClickListenerWithWebtext(5));
    }

    private View.OnClickListener getButtonOnClickListenerWithNormal(final int bank_image_id) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBankListener listener = (selectBankListener) getActivity();
                listener.onClickCreateAccBank();
                BankAccountInfo.bank_image_id = bank_image_id;
            }
        };
    }

    private View.OnClickListener getButtonOnClickListenerWithWebtext(final int bank_image_id) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBankListener listener = (selectBankListener) getActivity();
                listener.onClickCreateAccBankWithWebtext();
                BankAccountInfo.bank_image_id = bank_image_id;
            }
        };
    }
}
