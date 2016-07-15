package com.example.chayenjr.digiowallet.Tranfer;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.chayenjr.digiowallet.R;


public class TransferFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Button mBankAccountBtn;
    private Button mDigioWalletBtn;
    private Button mCitizenIdBtn;

    interface BtnListener {
        void mBankAccountBtnListener();

        void mDigioWalletBtnListener();

        void mCitizenIdBtnListener();
    }


    public TransferFragment() {
        // Required empty public constructor
    }

    public static TransferFragment newInstance(String param1, String param2) {
        TransferFragment fragment = new TransferFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_tranfer, container, false);
        initInstances(rootView);
        setBtnListener();
        return rootView;
    }

    private void initInstances(View rootView) {
        mBankAccountBtn = (Button) rootView.findViewById(R.id.bank_account_btn);
        mDigioWalletBtn = (Button) rootView.findViewById(R.id.digio_wallet_btn);
        mCitizenIdBtn = (Button) rootView.findViewById(R.id.citizen_id_btn);
    }



    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    /******************
     * Listener
     ******************/
    private void setBtnListener() {
        final BtnListener listener = (BtnListener) getActivity();

        mBankAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.mBankAccountBtnListener();
            }
        });

        mDigioWalletBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.mDigioWalletBtnListener();
            }
        });

        mCitizenIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.mCitizenIdBtnListener();
            }
        });
    }

}
