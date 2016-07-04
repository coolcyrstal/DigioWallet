package com.example.chayenjr.digiowallet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {

    int check_register_page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.register_actionbar);

        Button nextbutton = (Button)findViewById(R.id.nextbutton);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_register_page == 1){
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.register_info);
                    if (fragment instanceof OTPpage == false) {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.register_info, OTPpage.newInstance(), "OTP Page")
                                .addToBackStack(null)
                                .commit();
                    }else Toast.makeText(RegisterPage.this, "Error", Toast.LENGTH_SHORT).show();
                    check_register_page = 2;
                } else{
                }
            }
        });
    }
}
