package co.belazy.chirppaypal.util;

import android.content.Context;
import android.content.SharedPreferences;

import co.belazy.chirppaypal.MyApplication;

/**
 * Created by GadgetFreak on 14-09-2018.
 */
public class DataHelper {
    static String accessToken = "";
    static String payerId = "";
    static String paymentId = "";
    static String paymentToken = "";
    static String userEmail = "";

    public static String getAccessToken() {
        SharedPreferences preferences = MyApplication.getAppContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
        return DataHelper.accessToken = preferences
                .getString("access_token", null);
    }

    public static void setAccessToken(String accessToken) {
        MyApplication.getAppContext().getSharedPreferences("USER", Context.MODE_PRIVATE).edit()
                .putString("access_token", accessToken).apply();
    }

    public static String getPayerId() {
        SharedPreferences preferences = MyApplication.getAppContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
        return DataHelper.payerId = preferences
                .getString("payer_id", null);
    }

    public static void setPayerId(String payerId) {
        MyApplication.getAppContext().getSharedPreferences("USER", Context.MODE_PRIVATE).edit()
                .putString("payer_id", payerId).apply();
    }

    public static String getPaymentId() {
        SharedPreferences preferences = MyApplication.getAppContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
        return DataHelper.paymentId = preferences
                .getString("payment_id", null);
    }

    public static void setPaymentId(String paymentId) {
        MyApplication.getAppContext().getSharedPreferences("USER", Context.MODE_PRIVATE).edit()
                .putString("payment_id", paymentId).apply();
    }

    public static String getPaymentToken() {
        SharedPreferences preferences = MyApplication.getAppContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
        return DataHelper.paymentToken = preferences
                .getString("payment_token", null);
    }

    public static void setPaymentToken(String paymentToken) {
        MyApplication.getAppContext().getSharedPreferences("USER", Context.MODE_PRIVATE).edit()
                .putString("payment_token", paymentToken).apply();
    }

    public static String getUserEmail() {
        SharedPreferences preferences = MyApplication.getAppContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
        return DataHelper.userEmail = preferences
                .getString("email", null);
    }

    public static void setUserEmail(String userEmail) {
        MyApplication.getAppContext().getSharedPreferences("USER", Context.MODE_PRIVATE).edit()
                .putString("email", userEmail).apply();
    }
}
