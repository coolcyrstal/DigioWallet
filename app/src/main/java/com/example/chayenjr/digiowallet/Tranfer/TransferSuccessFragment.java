package com.example.chayenjr.digiowallet.Tranfer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.chayenjr.digiowallet.R;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class TransferSuccessFragment extends Fragment {


    public interface OnFragmentListener {
        void setOnClickDoneButtonListener();
    }

    private Button btnDone;

    public TransferSuccessFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static TransferSuccessFragment newInstance() {
        TransferSuccessFragment fragment = new TransferSuccessFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_transfer_success, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        btnDone = (Button) rootView.findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnFragmentListener listener = (OnFragmentListener) getActivity();
                listener.setOnClickDoneButtonListener();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

}
