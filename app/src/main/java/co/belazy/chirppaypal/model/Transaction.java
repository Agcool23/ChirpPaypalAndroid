package co.belazy.chirppaypal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by GadgetFreak on 14-09-2018.
 */
public class Transaction {
    @SerializedName("payee")
    @Expose
    private Payee payee;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("amount")
    @Expose
    private Amount amount;
    @SerializedName("related_resources")
    @Expose
    private List<Object> relatedResources = null;

    public Transaction(Payee payee, String description, Amount amount) {
        this.payee = payee;
        this.description = description;
        this.amount = amount;
    }

    public List<Object> getRelatedResources() {
        return relatedResources;
    }

    public void setRelatedResources(List<Object> relatedResources) {
        this.relatedResources = relatedResources;
    }

    public Payee getPayee() {
        return payee;
    }

    public void setPayee(Payee payee) {
        this.payee = payee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

}
