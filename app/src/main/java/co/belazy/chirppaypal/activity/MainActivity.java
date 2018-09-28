package co.belazy.chirppaypal.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import co.belazy.chirppaypal.api.ApiService;
import co.belazy.chirppaypal.util.DataHelper;
import co.belazy.chirppaypal.R;
import co.belazy.chirppaypal.api.RestManager;
import co.belazy.chirppaypal.model.TokenResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button save;
    EditText email;
    Boolean isFirstTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences app_preferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = app_preferences.edit();
        isFirstTime = app_preferences.getBoolean("isFirstTimeCallData", true);
        String PaypalToken = "PAYPAL_TOKEN";
        String PayPalSecret = "PAYPAL_SECRET";
        String base = PaypalToken + ":" + PayPalSecret;
        String authHeader = "Basic " + Base64.encodeToString(base.getBytes(),Base64.NO_WRAP);
        ApiService service = RestManager.getRetrofit().create(ApiService.class);
        save = findViewById(R.id.save);
        email = findViewById(R.id.email);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!email.getText().toString().isEmpty()) {
                    DataHelper.setUserEmail(email.getText().toString());
                    startActivity(new Intent(MainActivity.this,SendReceiveActivity.class));
                }else{
                    email.setError("Please fill your Paypal email");
                }
            }
        });
        Call<TokenResponseModel> call = service.getToken(authHeader,"client_credentials");
        call.enqueue(new Callback<TokenResponseModel>() {
            @Override
            public void onResponse(Call<TokenResponseModel> call, Response<TokenResponseModel> response) {
                if (response.isSuccessful()){
                    Log.e("Token",response.body().getAccessToken());
                    RelativeLayout layout = findViewById(R.id.layout);
                    if (isFirstTime) {
                        layout.setVisibility(View.VISIBLE);
                        DataHelper.setAccessToken(response.body().getAccessToken());
                        editor.putBoolean("isFirstTimeCallData", false);
                        editor.apply();
                    }else{
                        DataHelper.setAccessToken(response.body().getAccessToken());
                        startActivity(new Intent(MainActivity.this,SendReceiveActivity.class));
                    }
                }
            }

            @Override
            public void onFailure(Call<TokenResponseModel> call, Throwable t) {
                Log.e("Fail",t.getMessage());
            }
        });
    }
}
