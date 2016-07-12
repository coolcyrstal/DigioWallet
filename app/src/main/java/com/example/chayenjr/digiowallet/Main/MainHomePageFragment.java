package com.example.chayenjr.digiowallet.Main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chayenjr.digiowallet.Main.view.CustomButton;
import com.example.chayenjr.digiowallet.R;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class MainHomePageFragment extends Fragment {

    CustomButton mTransferBtn;

    public MainHomePageFragment() {
        super();
    }

    public interface TransferListener{
        void onTransferClickListener();
    }

    @SuppressWarnings("unused")
    public static MainHomePageFragment newInstance() {
        MainHomePageFragment fragment = new MainHomePageFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_main_homepage, container, false);
        initInstances(rootView, savedInstanceState);
        setTransferListener();
        return rootView;
    }

    private void setTransferListener() {
        mTransferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransferListener listener = (TransferListener) getActivity();
                listener.onTransferClickListener();
            }
        });
    }



    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        mTransferBtn = (CustomButton) rootView.findViewById(R.id.transfer_btn);
        // Init 'View' instance(s) with rootView.findViewById here
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
