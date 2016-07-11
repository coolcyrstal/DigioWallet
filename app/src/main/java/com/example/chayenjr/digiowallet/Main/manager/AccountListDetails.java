package com.example.chayenjr.digiowallet.Main.manager;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Chayenjr on 11/7/2559.
 */
public class AccountListDetails {
    @SerializedName("number") private String number;
    @SerializedName("name") private String name;
    @SerializedName("status") private String status;
    @SerializedName("branch") private String branch;
    @SerializedName("avaliable_balance") private String avaliable_balance;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAvaliable_balance() {
        return avaliable_balance;
    }

    public void setAvaliable_balance(String avaliable_balance) {
        this.avaliable_balance = avaliable_balance;
    }
}
