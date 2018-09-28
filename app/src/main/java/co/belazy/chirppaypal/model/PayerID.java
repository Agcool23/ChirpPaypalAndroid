package co.belazy.chirppaypal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by GadgetFreak on 17-09-2018.
 */
public class PayerID {
    @SerializedName("payer_id")
    @Expose
    private String payerId;

    public PayerID(String payerId) {
        this.payerId = payerId;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }
}
