package com.example.jeffersonvelez.soho;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.w3c.dom.Text;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Splash extends AppCompatActivity {

    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.percentage) TextView percentage;
    int progressStatus=0;
    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        showProgressBar();

    }

    public void showProgressBar() {

        new Thread(new Runnable(){
            public void run(){
                Intent splashIntent = new Intent(Splash.this,NavDrawer.class);
                while (progressStatus < 100){
                    progressStatus += 1;
                    // Update the progress bar
                    mHandler.post(new Runnable(){
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            percentage.setText(""+progressStatus+"%");
                        }
                    });
                    try{
                        Thread.sleep(30);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
                startActivity(splashIntent);
            }
        }).start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
