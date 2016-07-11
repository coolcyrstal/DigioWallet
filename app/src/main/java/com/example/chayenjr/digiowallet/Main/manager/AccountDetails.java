package com.example.chayenjr.digiowallet.Main.manager;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Chayenjr on 11/7/2559.
 */
public class AccountDetails {
    @SerializedName("card_id") private String card_id;
    @SerializedName("card_type") private String card_type;
    @SerializedName("name") private String name;
    @SerializedName("mobile_no") private String mobile_no;
    @SerializedName("email") private String email;
    @SerializedName("accounts") private List<AccountListDetails> accounts;

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AccountListDetails> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountListDetails> accounts) {
        this.accounts = accounts;
    }
}
