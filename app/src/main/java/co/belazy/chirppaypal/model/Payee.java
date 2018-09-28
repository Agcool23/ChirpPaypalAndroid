package co.belazy.chirppaypal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by GadgetFreak on 14-09-2018.
 */
public class Payee {
    @SerializedName("email")
    @Expose
    private String email;

    public Payee(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
