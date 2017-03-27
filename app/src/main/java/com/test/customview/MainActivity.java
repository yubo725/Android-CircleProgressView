package com.test.customview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private CircleProgressView progressView1;
    private CircleProgressView progressView2;
    private CircleProgressView progressView3;
    private int progress1, progress2, progress3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressView1 = (CircleProgressView) findViewById(R.id.circle_progress_view1);
        progressView2 = (CircleProgressView) findViewById(R.id.circle_progress_view2);
        progressView3 = (CircleProgressView) findViewById(R.id.circle_progress_view3);

        progressView1.setProgressColor(Color.RED);
        progressView2.setProgressColor(Color.BLUE);
        progressView3.setProgressColor(Color.BLACK);

        //开线程模拟耗时操作，然后不断改变进度条的进度
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    progress1 += 1;
                    progress2 += 3;
                    progress3 += 5;
                    if (progress1 > 100) {
                        progress1 = 100;
                    }
                    if (progress2 > 100) {
                        progress2 = 100;
                    }
                    if (progress3 > 100) {
                        progress3 = 100;
                    }

                    progressView1.setProgress(progress1);
                    progressView2.setProgress(progress2);
                    progressView3.setProgress(progress3);

                    if (progress1 == 100 && progress2 == 100 && progress3 == 100) {
                        break;
                    }

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
