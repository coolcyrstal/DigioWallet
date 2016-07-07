package com.example.chayenjr.digiowallet.register;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chayenjr.digiowallet.R;


public class OTPpage extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button reqOTPbutton;
    static EditText textOTP;

    public OTPpage() {
        // Required empty public constructor
    }

    public static OTPpage newInstance() {
        OTPpage fragment = new OTPpage();
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
        View view = inflater.inflate(R.layout.fragment_otppage, container, false);
        // Inflate the layout for this fragment
        reqOTPbutton = (Button)view.findViewById(R.id.reqOTPbutton);
        textOTP = (EditText)view.findViewById(R.id.text_otpcode);

        reqOTPbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterPage.requestOTP();
            }
        });
        ((TextView)view.findViewById(R.id.mobileNum_otp)).setText(
                RegisterPage.textCreateMobileNum.getText().toString().substring(0, 3) + " xxx " +
                        RegisterPage.textCreateMobileNum.getText().toString().substring(6, 10));
        return view;
    }

    public static String getOTPText(){
        return textOTP.getText().toString();
    }
}
