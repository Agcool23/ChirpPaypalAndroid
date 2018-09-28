package co.belazy.chirppaypal;

import android.app.Application;
import android.content.Context;

/**
 * Created by GadgetFreak on 14-09-2018.
 */
public class MyApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getAppContext() {
        return mContext;
    }
}
