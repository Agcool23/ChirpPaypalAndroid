package co.belazy.chirppaypal.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import co.belazy.chirppaypal.R;

public class PayPalWebViewActivity extends AppCompatActivity {
    private WebView webView;

    String payUrlStr;

    ProgressDialog dialog;

    String successUrl;
    String unsuccessUrl;
    String token = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_pal_web_view);
        successUrl = "http://chirp.io";
        unsuccessUrl = "http://chirp.io/cancel";
        Intent in = getIntent();
        if (in != null && in.getStringExtra("token") != null){
            token = in.getStringExtra("token");
        }
        dialog = ProgressDialog.show(PayPalWebViewActivity.this, "", "Please wait..", false);

        loadWebViewPaypal();
    }

    private void loadWebViewPaypal() {

        payUrlStr = "https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="+token;

        Log.e("payUrlStr", ""+payUrlStr);

        webView = (WebView) findViewById(R.id.web_view);
        webView.loadUrl(payUrlStr);
        webView.getSettings().setJavaScriptEnabled(true);

        @SuppressWarnings("unused")
        WebSettings settings= webView.getSettings();
        if (Build.VERSION.SDK_INT >= 21) {
            webView.getSettings().setMixedContentMode( WebSettings.MIXED_CONTENT_ALWAYS_ALLOW );
        }
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(final WebView view, String url) {
                Log.e("Loading url...", url);
                view.loadUrl(url);

                String loadWebUrl = view.getUrl();

                Log.e("loadWebUrl", ""+loadWebUrl);
                Uri uri = Uri.parse(url);
                if(url.contains(successUrl)) {
                    //Success Toast
                    String payerID = uri.getQueryParameter("PayerID");
                    Toast.makeText(PayPalWebViewActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PayPalWebViewActivity.this,ChirpingActivity.class).putExtra("PayerId",payerID));

                }else if(url.contains(unsuccessUrl)){
                    //startActivity(new );
                    startActivity(new Intent(PayPalWebViewActivity.this,SuccessFailureActivity.class).putExtra("status","failure"));
                    Toast.makeText(PayPalWebViewActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                }else if (url.contains("checkout/review")){
                    Handler mHandler = new Handler();
                    Runnable mSendPayerId = new Runnable() {
                        @Override
                        public void run() {
                            view.loadUrl("javascript:document.getElementById('confirmButtonTop').click()");
                            view.loadUrl("javascript:document.getElementById('continueButton').click()");
                        }
                    };
                    mHandler.postDelayed(mSendPayerId,1000);

                }

                return true;
            }

            @Override
            public void onPageFinished(final WebView view, String url) {
                if (url.contains("checkout/review")){
                    Handler mHandler = new Handler();
                    Runnable mSendPayerId = new Runnable() {
                        @Override
                        public void run() {
                            view.loadUrl("javascript:document.getElementById('confirmButtonTop').click()");
                            view.loadUrl("javascript:document.getElementById('continueButton').click()");
                        }
                    };
                    mHandler.postDelayed(mSendPayerId,1000);

                }

                Log.e("Finished url...", url);

                String webUrl = view.getUrl();

                Log.e("webUrl", ""+webUrl);


                /*if(webUrl.substring(0,95).equals(successUrl)){

                    Log.e("Getting Success Request", "Test");

                }else{

                    Log.e("Failed to get Request", "Test");

                }*/

                if(dialog.isShowing()){
                    dialog.dismiss();
                }

            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {

                Log.e("Error in url...", description);
                Log.e("Error in failingUrl...", failingUrl);

            }

        });

    }
}
