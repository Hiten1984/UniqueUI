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

    MyCount counter;
    long startInterval = 10 * 1000l; // 20 sec
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
        counter.cancel();
        counter = null;
        Log.d("Hiten", "onPAuse called *** " + remainingTime);

    }

    @Override
    protected void onStart() {
        super.onStart();
        interval = SharedPreferencesUtil.getRefreshTime(getApplicationContext());
        Log.d("Hiten", "OnResume called *** " + interval);
        if (interval != 0) {
            if (counter != null) {
                counter.cancel();
                counter = null;
            }
            counter = new MyCount(interval, 1000);
        } else {
            counter = new MyCount(startInterval, 1000);
        }
        SharedPreferencesUtil.setRefreshTime(getApplicationContext(), 0l);
        Log.d("Hiten", "OnResume called after setting to 0 *** " + SharedPreferencesUtil.getRefreshTime(getApplicationContext()));
        counter.start();
    }

    public class MyCount extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */

        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            timerValue.setText("DONE");
            interval = 0l;
            if(counter != null) {
                counter.cancel();
                counter = null;
            }
            SharedPreferencesUtil.setRefreshTime(getApplicationContext(), 0l);
            onStart();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            remainingTime = millisUntilFinished;
            timerValue.setText("left:" + remainingTime / 1000);
        }

    }
}

