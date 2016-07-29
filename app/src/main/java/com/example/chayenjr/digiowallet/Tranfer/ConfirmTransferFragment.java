package com.example.chayenjr.digiowallet.Tranfer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chayenjr.digiowallet.LoginRegister;
import com.example.chayenjr.digiowallet.R;
import com.example.chayenjr.digiowallet.Service.HttpService;

import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class ConfirmTransferFragment extends Fragment {

    private Button btnConfirm;

    public interface OnFragmentListener {
        void setOnClickConfirmButton();
    }

    private TextView customerNumber;
    private TextView customerName;
    private TextView bankAccount;
    private TextView creditAmount;
    private TextView creditFee;
    private TextView creditTotal;
    private TextView descriptionText;
    private TextView textBankName;
    private ImageView iconBank;

    public static String dateTimeOnTransfer;
    public static String money_fee;
    public static String money_total;

    Integer[] ic_bank_ID = {
            R.drawable.ic_kbank,
            R.drawable.ic_ktb,
            R.drawable.ic_scb,
            R.drawable.ic_krungsri,
            R.drawable.ic_tbank,
            R.drawable.ic_bbl,
            R.drawable.ic_digio
    };

    public ConfirmTransferFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static ConfirmTransferFragment newInstance(String accountNumber, String amount, String note) {
        ConfirmTransferFragment fragment = new ConfirmTransferFragment();
        Bundle args = new Bundle();
        args.putString("account",accountNumber);
        args.putString("amount",amount);
        args.putString("note",note);
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
        View rootView = inflater.inflate(R.layout.fragment_confirm_transfer, container, false);
        initInstances(rootView, savedInstanceState);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTransfer();
            }
        });

        setInstance();

        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        customerNumber = (TextView) rootView.findViewById(R.id.textCustomerNumber);
        customerName = (TextView) rootView.findViewById(R.id.textCustomerName);
        bankAccount = (TextView) rootView.findViewById(R.id.bank_name);
        creditAmount = (TextView) rootView.findViewById(R.id.textAmount);
        creditFee = (TextView) rootView.findViewById(R.id.textFee);
        creditTotal = (TextView) rootView.findViewById(R.id.textTotal);
        descriptionText = (TextView) rootView.findViewById(R.id.textDescription);
        textBankName = (TextView)rootView.findViewById(R.id.textBankName);
        iconBank = (ImageView)rootView.findViewById(R.id.iconBank);
        btnConfirm = (Button) rootView.findViewById(R.id.btnConfirm);
    }

    private void setInstance(){
        customerNumber.setText(TransferInfoFragment.to_account_number);
        creditAmount.setText(TransferInfoFragment.credit_amount);
        StringTokenizer splitAmount = new StringTokenizer(TransferInfoFragment.credit_amount, ".");
        money_fee = creditFee.getText().toString();
        StringTokenizer splitFee = new StringTokenizer(money_fee, ".");
        int total = Integer.parseInt(splitAmount.nextToken()) + Integer.parseInt(splitFee.nextToken());
        money_total = String.valueOf(total) + ".00";
        creditTotal.setText(money_total);
        descriptionText.setText(TransferInfoFragment.text_note_description);
        customerName.setText(TransferInfoFragment.to_account_name);

        textBankName.setText(TransferInfoFragment.bankName[TransferInfoFragment.gallery.getSelectedItemPosition()]);
        iconBank.setImageResource(ic_bank_ID[TransferInfoFragment.gallery.getSelectedItemPosition()]);
    }

    private void sendTransfer(){
        Call<HttpService.HttpBinResponse> call_transfer = HttpService.service.postWithFormJson_transaction_transfer(
                LoginRegister.check_token, TransferInfoFragment.from_account_num,
                TransferInfoFragment.to_account_number, TransferInfoFragment.credit_amount,
                HttpService.RegisterContact.getVersions(), HttpService.RegisterContact.getNonce());

        call_transfer.enqueue(new Callback<HttpService.HttpBinResponse>() {
            @Override
            public void onResponse(Call<HttpService.HttpBinResponse> call, Response<HttpService.HttpBinResponse> response) {
                if(response.body().getSuccess()){
                    dateTimeOnTransfer = response.body().getDatetime().split(" GMT")[0];
                    OnFragmentListener listener = (OnFragmentListener) getActivity();
                    listener.setOnClickConfirmButton();
                } else{
                    checkTransfer((AppCompatActivity) getContext(), "Transfer not success", "Please check you have login or check account number.", "OK");
                }
            }

            @Override
            public void onFailure(Call<HttpService.HttpBinResponse> call, Throwable t) {

            }
        });
    }

    private AlertDialog checkTransfer(final AppCompatActivity act, CharSequence title,
                                      CharSequence message, CharSequence buttonYes){
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title).setMessage(message).setPositiveButton(buttonYes, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        return downloadDialog.show();
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

    public void setBankAccountText(String s) {
        bankAccount.setText(s);
    }

//    public void setCreditAmountText(String s) {
//        creditAmount.setText(s);
//    }
//
//    public void setCreditFeeText(String s) {
//        creditAmount.setText(s);
//    }
//
//    public void setCreditTotalText(String s) {
//        creditTotal.setText(s);
//    }
//
//    public void setCustomerNameText(String s) {
//        customerName.setText(s);
//    }
//
//    public void setCustomerNumberText(String s) {
//        customerNumber.setText(s);
//    }
//
//    public void setDescriptionText(String s) {
//        descriptionText.setText(s);
//    }
}
