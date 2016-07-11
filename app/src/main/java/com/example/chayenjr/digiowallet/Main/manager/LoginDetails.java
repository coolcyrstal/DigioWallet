package com.example.chayenjr.digiowallet.Main.manager;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Chayenjr on 8/7/2559.
 */
public class LoginDetails {
    @SerializedName("F_NAME") private String F_NAME;
    @SerializedName("L_NAME") private String L_NAME;
    @SerializedName("Mobile") private String Mobile;
    @SerializedName("Card_id") private String Card_id;
    @SerializedName("Last_login") private String Last_login;

    public LoginDetails(){

    }

    public String getF_NAME() {
        return F_NAME;
    }

    public String getL_NAME() {
        return L_NAME;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getCard_id() {
        return Card_id;
    }

    public String getLast_login() {
        return Last_login;
    }

    public void setF_NAME(String f_NAME) {
        F_NAME = f_NAME;
    }

    public void setL_NAME(String l_NAME) {
        L_NAME = l_NAME;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public void setCard_id(String Card_id) {
        this.Card_id = Card_id;
    }

    public void setLast_login(String last_login) {
        Last_login = last_login;
    }
}
