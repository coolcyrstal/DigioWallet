package com.example.chayenjr.digiowallet.Main.view;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Chayenjr on 25/7/2559.
 */
public class LogpostDetails {
    @SerializedName("_id") private String _id;
    @SerializedName("result") private String result;
    @SerializedName("customer_id") private String customer_id;
    @SerializedName("transaction_type_id") private String transaction_type_id;
    @SerializedName("datetime") private String datetime;
    @SerializedName("total") private String total;
    @SerializedName("fee") private String fee;
    @SerializedName("amount") private String amount;
    @SerializedName("receiver") private String receiver;
    @SerializedName("sender") private String sender;
    @SerializedName("__v") private String __v;
    @SerializedName("reference") private String reference;

    public String get_id() {
        return _id;
    }

    public String getResult() {
        return result;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public String getTransaction_type_id() {
        return transaction_type_id;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getTotal() {
        return total;
    }

    public String getFee() {
        return fee;
    }

    public String getAmount() {
        return amount;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSender() {
        return sender;
    }

    public String get__v() {
        return __v;
    }

    public String getReference() {
        return reference;
    }
}
