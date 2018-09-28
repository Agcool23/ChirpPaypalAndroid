package co.belazy.chirppaypal.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by GadgetFreak on 14-09-2018.
 */
public class PaymentModel {
    @SerializedName("intent")
    @Expose
    private String intent;
    @SerializedName("redirect_urls")
    @Expose
    private RedirectUrls redirectUrls;
    @SerializedName("transactions")
    @Expose
    private ArrayList<Transaction> transactions = null;
    @SerializedName("payer")
    @Expose
    private Payer payer;

    public static String getUpdateJsonStringData(PaymentModel mediaModel){
        Gson gson = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(mediaModel);
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        Map<String, Object> data = new Gson().fromJson(json, type);
        for (Iterator<Map.Entry<String, Object>> it = data.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, Object> entry = it.next();
            if (entry.getValue() == null) {
                it.remove();
            } else if (entry.getValue().getClass().equals(ArrayList.class)) {
                if (((ArrayList<?>) entry.getValue()).size() == 0) {
                    it.remove();
                }
            }
        }
        json = new GsonBuilder().create().toJson(data);
        return json;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public RedirectUrls getRedirectUrls() {
        return redirectUrls;
    }

    public void setRedirectUrls(RedirectUrls redirectUrls) {
        this.redirectUrls = redirectUrls;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }
}
