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
import android.widget.ListView;

import com.example.chayenjr.digiowallet.Main.HomePage;
import com.example.chayenjr.digiowallet.R;

public class TransferInfoFragment extends Fragment {

    private EditText accountNumber;
    private EditText creditAmount;
    private EditText noteDescription;
    private Button btnNext;

    public interface OnFragmentListener {
        void setOnClickButtonNext();
    }

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    public static Gallery gallery;
    AppCompatTextView tvSelectBankName;
    public static String to_account_number;
    public static String credit_amount;
    public static String text_note_description;
    public static String from_account_num;
    ListView radiolist_account;
    UserAccountAdapter adapter;

    private TransferFragment.OnFragmentInteractionListener mListener;

    public static Integer[] imageIDs = {
            R.drawable.kbank_selector,
            R.drawable.ktb_selector,
            R.drawable.scb_selector,
            R.drawable.krungsri_selector,
            R.drawable.tbank_selector,
            R.drawable.digio_selector
    };

    public static Integer[] bankName = {
            R.string.kbank,
            R.string.ktb,
            R.string.scb,
            R.string.krungsri,
            R.string.tbank,
            R.string.digio
    };

    public static BankAdapter bankAdapter;


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

        adapter.notifyDataSetChanged();
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
        accountNumber = (EditText)rootView.findViewById(R.id.editAccountNumber);
        creditAmount = (EditText) rootView.findViewById(R.id.editAmount);
        noteDescription = (EditText) rootView.findViewById(R.id.editNote);
        btnNext = (Button) rootView.findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnFragmentListener listener = (OnFragmentListener) getActivity();
                listener.setOnClickButtonNext();
                to_account_number = accountNumber.getText().toString();
                credit_amount = creditAmount.getText().toString();
                text_note_description = noteDescription.getText().toString();
                from_account_num = UserAccountAdapter.getTextRadio();
                Log.d("Radiotext", UserAccountAdapter.getTextRadio());
            }
        });
        radiolist_account = (ListView)rootView.findViewById(R.id.list_account_number);
        adapter = new UserAccountAdapter();
        getUserAccountNumber();
    }

    private void getUserAccountNumber(){
        int i = 0;
        String[] temp = new String[HomePage.accountDetails.getAccounts().size()];
        while(i < HomePage.accountDetails.getAccounts().size()){
            temp[i] = HomePage.accountDetails.getAccounts().get(i).getNumber();
            i++;
        }
        Log.d("size", ""+HomePage.accountDetails.getAccounts().size());
        adapter.setAccount_number(temp);
        radiolist_account.setAdapter(adapter);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
   /* public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/

    public String getNoteDescription() {
        return noteDescription.getText().toString();
    }

    public String getCreditAmount() {
        return creditAmount.getText().toString();
    }

    public String getAccountNumber() {
        return accountNumber.getText().toString();
    }
}
