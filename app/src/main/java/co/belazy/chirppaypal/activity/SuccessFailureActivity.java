package co.belazy.chirppaypal.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import co.belazy.chirppaypal.R;

public class SuccessFailureActivity extends AppCompatActivity {
    String status = null;
    ImageView imgStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_failure);
        imgStatus = findViewById(R.id.status);
        Intent in = getIntent();
        if (in != null && in.getStringExtra("status") != null){
            status = in.getStringExtra("status");
        }

        if (status.equalsIgnoreCase("success")){
            imgStatus.setImageResource(R.drawable.tick);
        }else{
            imgStatus.setImageResource(R.drawable.ic_error);
        }

    }
}
