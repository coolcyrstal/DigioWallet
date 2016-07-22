package com.example.chayenjr.digiowallet.Main.SourceOfFund;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.example.chayenjr.digiowallet.R;


public class BankAccountInfo extends Fragment {

    public interface bankAccInfoListener {
        void onClickGoPinConfirm();
    }

    public BankAccountInfo() {
        // Required empty public constructor
    }

    Button btn_next_AddBankAccount;

    static Integer[] bank_banner = {
            R.drawable.scb_banner,
            R.drawable.bbl_banner,
            R.drawable.ktb_banner,
            R.drawable.kbank_banner,
            R.drawable.krungsri_banner,
            R.drawable.krungsri_banner,//tbank
            R.drawable.digio_banner
    };

    static ImageView select_bankImage;

    public static int bank_image_id;

    public static BankAccountInfo newInstance() {
        BankAccountInfo fragment = new BankAccountInfo();
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
        View rootview = inflater.inflate(R.layout.fragment_bank_account_info, container, false);

        btn_next_AddBankAccount = (Button)rootview.findViewById(R.id.btn_next_AddBankAccount);
        btn_next_AddBankAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bankAccInfoListener listener = (bankAccInfoListener) getActivity();
                listener.onClickGoPinConfirm();
            }
        });
        select_bankImage = (ImageView)rootview.findViewById(R.id.bank_banner);
        setBannerImage(bank_image_id);

        CheckBox checkBox = (CheckBox)rootview.findViewById(R.id.checkbox_set_default);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        return rootview;
    }

    private void setBannerImage(int i){
        select_bankImage.setImageResource(bank_banner[i]);
    }

}
