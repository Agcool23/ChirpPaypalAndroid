package co.belazy.chirppaypal.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import co.belazy.chirppaypal.R;

public class SendReceiveActivity extends AppCompatActivity{
    Button btnSend,btnReceive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_receive_actiity);
        btnSend = findViewById(R.id.send);
        btnReceive = findViewById(R.id.receive);

        btnReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SendReceiveActivity.this,RequestPaymentActivity.class).putExtra("from","receive"));
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SendReceiveActivity.this,ChirpingActivity.class).putExtra("from","send"));
            }
        });

    }
}
