package com.mehmetsakiratasayin.bombdisposal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;


public class MainActivity2 extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView=findViewById(R.id.textView2);
        new CountDownTimer(4000, 1000) {

            public void onTick(long millisUntilFinished) {
                textView.setText(String.valueOf(millisUntilFinished / 1000));
            }
            public void onFinish() {
                Intent intent =new Intent(MainActivity2.this,MainActivity3.class);
                finish();
                startActivity(intent);

            }
        }.start();
    }
}