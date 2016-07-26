package com.example.chayenjr.digiowallet.Tranfer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chayenjr.digiowallet.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class TransferSuccessFragment extends Fragment {


    public interface OnFragmentListener {
        void setOnClickDoneButtonListener();
    }

    private Button btnDone;
    TextView to_accountName_forsendslip;
    TextView textnote_forsendslip;
    TextView amount_forsendslip;
    TextView amountFee_forsendslip;
    TextView amountTotal_forsendslip;
    TextView to_accountNumber_forsendslip;
    TextView from_accountNumber_forsendslip;
    TextView dateTime_forsendslip;

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
        setInstances();
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(final View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        btnDone = (Button) rootView.findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnFragmentListener listener = (OnFragmentListener) getActivity();
                listener.setOnClickDoneButtonListener();
                addToGallery(rootView);
            }
        });
        dateTime_forsendslip = (TextView)rootView.findViewById(R.id.dateTime_forsendslip);
        from_accountNumber_forsendslip = (TextView)rootView.findViewById(R.id.from_accountNumber_forsendslip);
        to_accountNumber_forsendslip = (TextView)rootView.findViewById(R.id.to_account_number_forsendslip);
        to_accountName_forsendslip = (TextView)rootView.findViewById(R.id.text_toAccountName_forsendslip);
        amount_forsendslip = (TextView)rootView.findViewById(R.id.amount_forsendslip);
        amountFee_forsendslip = (TextView)rootView.findViewById(R.id.amountFee_forsendslip);
        amountTotal_forsendslip = (TextView)rootView.findViewById(R.id.totalAmount_forsendslip);
        textnote_forsendslip = (TextView)rootView.findViewById(R.id.textnote_forsendslip);
    }

    private void setInstances(){
        dateTime_forsendslip.setText(ConfirmTransferFragment.dateTimeOnTransfer);
        from_accountNumber_forsendslip.setText(TransferInfoFragment.from_account_num);
        to_accountNumber_forsendslip.setText(TransferInfoFragment.to_account_number);
        to_accountName_forsendslip.setText(TransferInfoFragment.to_account_name);
        amount_forsendslip.setText(TransferInfoFragment.credit_amount);
        amountFee_forsendslip.setText(ConfirmTransferFragment.money_fee);
        amountTotal_forsendslip.setText(ConfirmTransferFragment.money_total);
        textnote_forsendslip.setText(TransferInfoFragment.text_note_description);
    }

    private void addToGallery(View rootview){
        View v = rootview.findViewById(R.id.save_image_togallery);
        v.setDrawingCacheEnabled(true);
        Bitmap bm = Bitmap.createBitmap(
                v.getDrawingCache());
        v.setDrawingCacheEnabled(false);

        try {
            Date d = new Date();
            String filename  = (String) DateFormat.format("kkmmss-MMddyyyy", d.getTime());
            File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "/DigioWallet/" + filename + ".jpg");
            if(!dir.exists()){
                File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "/DigioWallet/");
                directory.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(dir);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            out.write(bos.toByteArray());
            updateImage(dir);
            Toast.makeText(getActivity().getApplicationContext(), "Save slip", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateImage(File f) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(f));
        getActivity().sendBroadcast(intent);
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
