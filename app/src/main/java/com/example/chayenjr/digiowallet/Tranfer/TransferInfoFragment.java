package com.example.chayenjr.digiowallet.Tranfer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;

import com.example.chayenjr.digiowallet.R;

public class TransferInfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
   private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Gallery gallery;
    AppCompatTextView tvSelectBankName;

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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     //* @param param1 Parameter 1.
     //* @param param2 Parameter 2.
     * @return A new instance of fragment TransferInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
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
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    // TODO: Rename method, update argument and hook method into UI event
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
}
