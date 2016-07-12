package com.example.chayenjr.digiowallet.Tranfer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;

import com.example.chayenjr.digiowallet.R;

public class TransferInfoFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
   private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    Gallery gallery;
    AppCompatTextView tvSelectBankName;
    EditText toAccountNumber;
    EditText amount;
    Button next_button;

    private TransferFragment.OnFragmentInteractionListener mListener;

    Integer[] imageIDs = {
            R.drawable.kbank_selector,
            R.drawable.ktb_selector,
            R.drawable.scb_selector,
            R.drawable.krungsri_selector,
            R.drawable.tbank_selector
    };

    Integer[] bankName = {
            R.string.kbank,
            R.string.ktb,
            R.string.scb,
            R.string.krungsri,
            R.string.tbank
    };

    BankAdapter bankAdapter;


    public TransferInfoFragment() {
        // Required empty public constructor
    }


    public static TransferInfoFragment newInstance(String param1, String param2) {
        TransferInfoFragment fragment = new TransferInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_transfer_info, container, false);
        initInstances(rootView);
        setGalleryAdapter();

        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("select",gallery.getSelectedItemPosition() +"");
                tvSelectBankName.setText(bankName[gallery.getSelectedItemPosition()]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.contentContainer);

                if(fragment instanceof PinConfirmTransfer == false)
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.contentContainer,PinConfirmTransfer.newInstance())
                            .commit();
            }
        });

        return rootView;
    }

    private void setGalleryAdapter() {
        bankAdapter = new BankAdapter(getContext(),imageIDs,gallery);
        gallery.setAdapter(bankAdapter);
        gallery.setSelection(2);
    }

    private void initInstances(View rootView) {
        gallery = (Gallery) rootView.findViewById(R.id.gallery_bank);
        tvSelectBankName = (AppCompatTextView) rootView.findViewById(R.id.bank_name);
        toAccountNumber = (EditText)rootView.findViewById(R.id.to_account_number);
        amount = (EditText)rootView.findViewById(R.id.amount);
        next_button = (Button) rootView.findViewById(R.id.btn_next);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TransferFragment.OnFragmentInteractionListener) {
            mListener = (TransferFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
