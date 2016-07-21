package com.example.chayenjr.digiowallet.Main.SourceOfFund;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.chayenjr.digiowallet.LoginRegister;
import com.example.chayenjr.digiowallet.Main.HomePage;
import com.example.chayenjr.digiowallet.R;
import com.example.chayenjr.digiowallet.Service.HttpService;
import com.example.chayenjr.digiowallet.Tranfer.RadioAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class SourceOfFundListFragment extends Fragment {

    ListView list_acc_num_SourceOfFund;
    RadioAdapter adapter;
    Button set_default;

    String default_account;

    public SourceOfFundListFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static SourceOfFundListFragment newInstance() {
        SourceOfFundListFragment fragment = new SourceOfFundListFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_source_of_fund_list, container, false);
        initInstances(rootView, savedInstanceState);

        adapter.notifyDataSetChanged();
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        list_acc_num_SourceOfFund = (ListView)rootView.findViewById(R.id.list_account_number_sourceOfFund);
        setListAccount();
        set_default = (Button)rootView.findViewById(R.id.set_default_acc_button);
        set_default.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                default_account = adapter.getTextRadio(list_acc_num_SourceOfFund.getCheckedItemPosition());
                sendgetName_receiverAccount();
            }
        });
    }

    private void setListAccount(){
        int i = 0;
        String[] temp = new String[HomePage.accountDetails.getAccounts().size()];
        while(i < HomePage.accountDetails.getAccounts().size()){
            temp[i] = HomePage.accountDetails.getAccounts().get(i).getNumber();
            i++;
        }
        adapter = new RadioAdapter();
        adapter.setAccount_number(temp);
        list_acc_num_SourceOfFund.setAdapter(adapter);
        list_acc_num_SourceOfFund.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    private void sendgetName_receiverAccount(){
        Call<HttpService.HttpBinResponse> call_setAccount = HttpService.service.postWithFormJson_setDefaultAccount(
                LoginRegister.check_token, default_account,
                HttpService.RegisterContact.getVersions(), HttpService.RegisterContact.getNonce());

        call_setAccount.enqueue(new Callback<HttpService.HttpBinResponse>() {
            @Override
            public void onResponse(Call<HttpService.HttpBinResponse> call, Response<HttpService.HttpBinResponse> response) {
                if(response.body().getSuccess()){
                    getActivity().finish();
                    HomePage.call_account();
                    HomePage.usermoney.setText(HomePage.account_details);
                    Log.d("after change", HomePage.account_details);
                }
            }

            @Override
            public void onFailure(Call<HttpService.HttpBinResponse> call, Throwable t) {

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
