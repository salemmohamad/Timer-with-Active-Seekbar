package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar1;
    Button button;
    MediaPlayer song;
    TextView editText;

    CountDownTimer countDownTimer;
    boolean seekBarActive=false;

    public void printingTime( int progress)
    {
        if((progress%60)<10 &&(progress/60)<10 )
        {
            editText.setText("0"+String.valueOf(progress/60)+" : 0"+String.valueOf(progress%60));
        }else if ((progress/60)<10 )
        {
            editText.setText("0"+String.valueOf(progress/60)+" : "+String.valueOf(progress%60));
        }else if((progress%60)<10)
        {
            editText.setText(String.valueOf(progress/60)+" : 0"+String.valueOf(progress%60));
        }else
        {
            editText.setText(String.valueOf(progress/60)+" : "+String.valueOf(progress%60));
        }
    }
    public  void resetTimer()
    {
        editText.setText(String.valueOf("00 : 00"));
        button.setText("restart");
        seekBar1.setEnabled(true);
        seekBarActive=false;
        countDownTimer.cancel();
        seekBar1.setProgress(0);
    }

    public void Clicked(View v)
    {


        if(seekBarActive==false&&seekBar1.getProgress()!=0)
        {
            button.setText("Stop");
             countDownTimer= new CountDownTimer((seekBar1.getProgress()) * 1000, 1000) // 1st number is the total time, and decremented by 2nd number
            {
                @Override
                public void onTick(long millisUntilFinished) {
                    seekBar1.setEnabled(false);
                    printingTime((int) (millisUntilFinished/1000));
                    seekBarActive=true;
                    seekBar1.setProgress((int) (millisUntilFinished/1000));
                }

                @Override
                public void onFinish()
                {
                    song.start();
                    resetTimer();
                }
            }.start();
        }
        else if(seekBar1.getProgress()==0)
        {
            editText.setText("Please choose time ...");
        }
        else
        {
            resetTimer();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);

        song=MediaPlayer.create(this,R.raw.bell323232);
        editText=findViewById(R.id.text1);
        editText.setText("00 : 00");
        seekBar1=findViewById(R.id.seekBar);

        seekBar1.setMin(0);
        seekBar1.setMax((60*6)+1);



        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                printingTime(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

//salem says hi 29/3/2020


    }
}
