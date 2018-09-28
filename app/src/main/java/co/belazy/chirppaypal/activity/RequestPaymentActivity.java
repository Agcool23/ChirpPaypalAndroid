package co.belazy.chirppaypal.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import co.belazy.chirppaypal.api.ApiService;
import co.belazy.chirppaypal.util.DataHelper;
import co.belazy.chirppaypal.R;
import co.belazy.chirppaypal.api.RestManager;
import co.belazy.chirppaypal.model.Amount;
import co.belazy.chirppaypal.model.Link;
import co.belazy.chirppaypal.model.Payee;
import co.belazy.chirppaypal.model.Payer;
import co.belazy.chirppaypal.model.PaymentModel;
import co.belazy.chirppaypal.model.RedirectUrls;
import co.belazy.chirppaypal.model.ResponseModel;
import co.belazy.chirppaypal.model.Transaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestPaymentActivity extends AppCompatActivity {

    Button btnRequest, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_payment);
        btnBack = findViewById(R.id.back);
        btnRequest = findViewById(R.id.request);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String base = DataHelper.getAccessToken();
                String authHeader = "Bearer " + base;
                PaymentModel model = new PaymentModel();
                model.setIntent("sale");
                model.setPayer(new Payer("paypal"));
                model.setRedirectUrls(new RedirectUrls("http://chirp.io","http://chirp.io/cancel"));
                ArrayList<Transaction> transactions = new ArrayList<>();
                transactions.add(new Transaction(new Payee(DataHelper.getUserEmail()),"P2P",new Amount("10","USD")));
                model.setTransactions(transactions);
                ApiService service = RestManager.getRetrofit().create(ApiService.class);
                Call<ResponseModel> call = service.payment(authHeader,model);
                call.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        if (response.isSuccessful()){
                            Log.e("response",response.body()+"");
                            Log.e("json",new Gson().toJson(response.body()));
                            ResponseModel responseModel = response.body();
                            DataHelper.setPaymentId(responseModel.getId());
                            ArrayList<Link> links = responseModel.getLinks();
                            String url = null;
                            for (int i = 0; i< links.size();i++){
                                if (links.get(i).getMethod().equalsIgnoreCase("REDIRECT")){
                                    url = links.get(i).getHref();
                                }
                            }
                            Uri uri = Uri.parse(url);
/*                            String server = uri.getAuthority();
                            String path = uri.getPath();
                            String protocol = uri.getScheme();
                            Set<String> args = uri.getQueryParameterNames();*/

                            String paymentToken = uri.getQueryParameter("token");
                            DataHelper.setPaymentToken(paymentToken);
                            startActivity(new Intent(RequestPaymentActivity.this,ChirpingActivity.class).putExtra("token",paymentToken));
                            Log.e("token",paymentToken);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Log.e("failure",t.getMessage());
                    }
                });
            }
        });
    }

    private String makeJSON(){
        String json = "{\n" +
                "   \"intent\": \"sale\",\n" +
                "   \"redirect_urls\": {\n" +
                "      \"return_url\": \"http://chirp.io\",\n" +
                "      \"cancel_url\": \"http://chirp.io/cancel\"\n" +
                "   },\n" +
                "   \"transactions\": [\n" +
                "      {\n" +
                "         \"payee\": {\n" +
                "            \"email\":\"Agcool23-1@hotmail.com\"\n" +
                "         },\n" +
                "         \"description\": \"P2P\",\n" +
                "         \"amount\": {\n" +
                "            \"total\": \"10\",\n" +
                "            \"currency\": \"USD\"\n" +
                "         }\n" +
                "      }\n" +
                "   ],\n" +
                "   \"payer\": {\n" +
                "      \"payment_method\": \"paypal\"\n" +
                "   }\n" +
                "}";
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }
}
