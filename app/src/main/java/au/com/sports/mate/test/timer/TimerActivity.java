package au.com.sports.mate.test.timer;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import au.com.sports.mate.test.R;
import au.com.sports.mate.test.util.CustomUtil;
import au.com.sports.mate.test.util.SharedPreferencesUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TimerActivity extends AppCompatActivity {


    @BindView(R.id.timer_value)
    TextView timerValue;

    MyCount counterTask;
    long startInterval = 20 * 1000l; // 20 sec
    private long remainingTime;
    long interval = 0l;
    private long newInterval = 0;
    private boolean isFinish;
//    MyCount newTimer1;

//    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        ButterKnife.bind(this);
//        counter = new MyCount(SharedPreferencesUtil.getRefreshTime(getApplicationContext()) == 0 ? startInterval : SharedPreferencesUtil.getRefreshTime(getApplicationContext()), 1000);
//        counter.start();

    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferencesUtil.setRefreshTime(getApplicationContext(), remainingTime);
        /*counter.cancel();
        counter = null;*/
        counterTask.setPaused(true);
        Log.d("Hiten", "onPAuse called *** " + remainingTime);

    }

    @Override
    protected void onStart() {
        super.onStart();
        interval = SharedPreferencesUtil.getRefreshTime(getApplicationContext());
        Log.d("Hiten", "OnResume called *** " + interval);
        Log.d("Hiten", "inside onStart countertask value -- " + counterTask);
        if(counterTask == null || counterTask.isCanceled() && interval == 0) {
            Log.d("Hiten", "OnResume called **111111* ");
            counterTask = new MyCount(startInterval, 1000);
        } else {
            Log.d("Hiten", "OnResume called **22222222* ");
            counterTask.setPaused(false);
            counterTask.cancel();
            counterTask = null;
            counterTask = new MyCount(interval, 1000);
        }

       /* if (interval != 0) {
            if (counterTask != null || counterTask.isNewsCanceled()) {
                counterTask.cancel();
                counterTask = null;
            }
            counterTask = new MyCount(interval, 1000);
        } else {
            counterTask = new MyCount(startInterval, 1000);
        }*/
        SharedPreferencesUtil.setRefreshTime(getApplicationContext(), 0l);
        Log.d("Hiten", "OnResume called after setting to 0 *** " + SharedPreferencesUtil.getRefreshTime(getApplicationContext()));
        counterTask.start();
    }

    public class MyCount extends CountDownTimer {
        private boolean paused = false;
        private boolean canceled = false;
        private int pauseCount = 0;

        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        public boolean isPaused() {
            return paused;
        }

        public boolean isCanceled() {
            return canceled;
        }

        public void setPaused(boolean paused) {
            this.paused = paused;
        }

        public void setCanceled() {
            canceled = true;
            cancel();
        }

        @Override
        public void onFinish() {
            timerValue.setText("DONE");
            interval = 0l;
            if(counterTask != null) {
                counterTask.cancel();
                counterTask = null;
            }
            SharedPreferencesUtil.setRefreshTime(getApplicationContext(), 0l);
            onStart();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            remainingTime = millisUntilFinished;
            timerValue.setText("left:" + remainingTime / 1000);
            if(isPaused()) {
                Log.d("Hiten", "inside run -- timer task is paused " + pauseCount);
                pauseCount++;
                if(pauseCount == 3) {
                    Log.d("Hiten", "inside run -- timer task is cancelled " + pauseCount);
                    setCanceled();
                }
                return;
            }
            pauseCount = 0;
//            remainingTime = millisUntilFinished;
//            timerValue.setText("left:" + remainingTime / 1000);
        }

    }
}

