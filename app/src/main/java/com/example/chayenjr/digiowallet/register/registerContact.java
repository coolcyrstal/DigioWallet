package com.example.chayenjr.digiowallet.register;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Chayenjr on 5/7/2559.
 */
public class RegisterContact {

    String firstname;
    String lastname;
    String mobile;
    String card_id;
    String nonce;
    String versions;

    public RegisterContact(String firstname, String lastname, String mobile, String card_id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
        this.card_id = card_id;
    }

    public String getFirstname(){return firstname;}

    public String getLastname(){return lastname;}

    public String getMobile(){return mobile;}

    public String getCard_id(){return card_id;}

    public String getNonce(){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String nonce = dateFormat.format(date) + String.valueOf(100000 + new Random().nextInt(899999));
        return nonce;
    }

    public String getVersions(){return "1.0.0";}
}
