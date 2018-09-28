package co.belazy.chirppaypal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by GadgetFreak on 14-09-2018.
 */
public class RedirectUrls {
    @SerializedName("return_url")
    @Expose
    private String returnUrl;
    @SerializedName("cancel_url")
    @Expose
    private String cancelUrl;

    public RedirectUrls(String returnUrl, String cancelUrl) {
        this.returnUrl = returnUrl;
        this.cancelUrl = cancelUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

}
