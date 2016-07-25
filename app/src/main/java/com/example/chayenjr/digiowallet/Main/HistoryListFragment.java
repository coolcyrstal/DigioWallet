package com.example.chayenjr.digiowallet.Main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.chayenjr.digiowallet.Main.manager.HistoryListAdapter;
import com.example.chayenjr.digiowallet.Main.view.TransactionDetails;
import com.example.chayenjr.digiowallet.R;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class HistoryListFragment extends Fragment {

    private ListView historyList;
    private HistoryListAdapter historyListAdapater;
    TransactionDetails transactionDetails;

    String[] temp_info;
    String[] temp_date;
    String[] temp_amount;
    int size;

    public HistoryListFragment() {
        super();
    }


    public static HistoryListFragment newInstance() {
        HistoryListFragment fragment = new HistoryListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_history, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        historyList = (ListView) rootView.findViewById(R.id.historyList);
        historyListAdapater = new HistoryListAdapter();
        set_TransactionDetail();
        historyList.setAdapter(historyListAdapater);
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

    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }



    private void set_TransactionDetail(){
        int i = 0;
//        Log.d("test", ""+ HomePage.transactionDetails.getCustomer());
//        Log.d("amount test", "" + transactionDetails.getCustomer());
        temp_info = new String[HomePage.transactionDetails.getLogpost().size()];
        temp_date = new String[HomePage.transactionDetails.getLogpost().size()];
        temp_amount = new String[HomePage.transactionDetails.getLogpost().size()];
        while(i < HomePage.transactionDetails.getLogpost().size()){
            temp_info[i] = "Transfer to " + HomePage.transactionDetails.getLogpost().get(i).getReceiver();
            temp_date[i] = HomePage.transactionDetails.getLogpost().get(i).getDatetime().split(" GMT")[0];
            temp_amount[i] = "-" + HomePage.transactionDetails.getLogpost().get(i).getAmount();
            i++;
        }
        historyListAdapater.setSize(HomePage.transactionDetails.getLogpost().size());
        historyListAdapater.setLogpostInfo(temp_info);
        historyListAdapater.setLogpost_date(temp_date);
        historyListAdapater.setLogpost_amount(temp_amount);
    }
}
