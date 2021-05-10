package com.mehmetsakiratasayin.bombdisposal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity3 extends AppCompatActivity {

    Random random = new Random();
    int count=0;
    int randomSayi;

    TextView time,
            counter;
    ImageView imageView1,imageView2,imageView3,imageView4,
            imageView5,imageView6,imageView7,imageView8,
            imageView9,imageView10,imageView11,imageView12,
            imageView13,imageView14,imageView15,imageView16;
    ImageView [] ImageArray;
    SharedPreferences sharedPreferences;
    MediaPlayer ply;
    Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //Media Player Song
        ply=MediaPlayer.create(MainActivity3.this,R.raw.song);
        aSwitch=findViewById(R.id.switch1);
        sharedPreferences =this.getSharedPreferences("com.mehmetsakiratasayin.bombdisposal", Context.MODE_PRIVATE);
        int Savekey=sharedPreferences.getInt("Savekey",0);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if(
                        isChecked==true){
                    ply.start();
                }
                else{
                    ply.pause();
                }
            }
        });

        //ImageView
        time=findViewById(R.id.textView4);
        counter=findViewById(R.id.textView5);
        imageView1=findViewById(R.id.imageView11);
        imageView2=findViewById(R.id.imageView12);
        imageView3=findViewById(R.id.imageView13);
        imageView4=findViewById(R.id.imageView14);
        imageView5=findViewById(R.id.imageView21);
        imageView6=findViewById(R.id.imageView22);
        imageView7=findViewById(R.id.imageView23);
        imageView8=findViewById(R.id.imageView24);
        imageView9=findViewById(R.id.imageView31);
        imageView10=findViewById(R.id.imageView32);
        imageView11=findViewById(R.id.imageView33);
        imageView12=findViewById(R.id.imageView34);
        imageView13=findViewById(R.id.imageView41);
        imageView14=findViewById(R.id.imageView42);
        imageView15=findViewById(R.id.imageView43);
        imageView16=findViewById(R.id.imageView44);
        ImageArray =new ImageView[] {imageView1 , imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8,
                imageView9, imageView10,imageView11,imageView12,imageView13,imageView14,imageView15,imageView16
        };
        counter.setText(String.valueOf(count));


//******************************************
        new CountDownTimer(6000, 1000) {

            public void onTick(long millisUntilFinished) {
                time.setText(" " + millisUntilFinished / 1000);


            }

            public void onFinish() {

                AlertDialog.Builder alert =new AlertDialog.Builder(MainActivity3.this);
                if (count > Savekey) {
                    sharedPreferences.edit().putInt("Savekey",count).apply();
                    // sharedPreferences.flush();
                }
                alert.setTitle("Score: "+count);
                alert.setMessage("Old Max Score: "+Savekey+"\n" +
                        " Do you want to play again?");
                alert.setCancelable(false);
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ply.stop();
                        Toast.makeText(MainActivity3.this,"Okey",Toast.LENGTH_LONG).show();
                        Intent intent =getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ply.stop();
                        Toast.makeText(MainActivity3.this,"Game Over!",Toast.LENGTH_LONG).show();
                        Intent intent =new Intent(MainActivity3.this,MainActivity.class);
                        finish();
                        startActivity(intent);
                    }
                });


                alert.show();
            }
        }.start();

        duzen();
    }
    public void duzen(){
        randomSayi = random.nextInt((15 - 1) + 1) + 1;
        for(ImageView image : ImageArray ){
            image.setImageResource(R.drawable.bomb3);
            image.setVisibility(View.INVISIBLE);


        }
        for(int i=0;i<ImageArray.length;i++){
            if(i==randomSayi){
                ImageArray[i].setVisibility(View.VISIBLE);
                ImageArray[i].setImageResource(R.drawable.bomb3);
            }
            else{}

        }
    }
    public void butonlar(View view) {
        for(int j=0;j<ImageArray.length;j++){
            if(j==randomSayi){
                ImageArray[j].setVisibility(View.INVISIBLE);
            }
            else{}
        }
        count++;
        counter.setText(String.valueOf(count));
        duzen();
    }

}