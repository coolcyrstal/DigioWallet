package com.example.chayenjr.digiowallet.Main.view;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Chayenjr on 25/7/2559.
 */
public class TransactionDetails {
    @SerializedName("customer") private String customer;
    @SerializedName("logpost") private List<LogpostDetails> logpost;

    public String getCustomer() {
        return customer;
    }


    public List<LogpostDetails> getLogpost() {
        return logpost;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setLogpost(List<LogpostDetails> logpost) {
        this.logpost = logpost;
    }
}
