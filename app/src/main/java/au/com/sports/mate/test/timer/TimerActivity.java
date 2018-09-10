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
    long startInterval = 50 * 1000l; // 50 sec
    private long remainingTime;

//    MyCount newTimer1;

//    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        ButterKnife.bind(this);

        /*timer = new CountDownTimer(SharedPreferencesUtil.getRefreshTime(getApplicationContext()) == 0 ? startInterval : SharedPreferencesUtil.getRefreshTime(getApplicationContext()), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                remainingTime = millisUntilFinished;
                timerValue.setText("left:"+remainingTime/1000);
            }

            @Override
            public void onFinish() {
                timerValue.setText("DONE");
            }
        }.start();*/
        counter = new MyCount(SharedPreferencesUtil.getRefreshTime(getApplicationContext()) == 0 ? startInterval : SharedPreferencesUtil.getRefreshTime(getApplicationContext()), 1000);
        counter.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
//        counter.pause();
        SharedPreferencesUtil.setRefreshTime(getApplicationContext(), remainingTime);
        Log.d("Hiten", "onPAuse called *** " + CustomUtil.isOtherAppInForeground(getApplicationContext()));
        if (CustomUtil.isOtherAppInForeground(getApplicationContext())) {
            counter.cancel();
            counter = null;
        }
        Log.d("Hiten", "onPAuse called *** " + remainingTime);
//        counter = null;

    }

    @Override
    protected void onResume() {
        super.onResume();
//        counter.resume();

        long interval = SharedPreferencesUtil.getRefreshTime(getApplicationContext());
        if (interval != 0) {
            Log.d("Hiten", "OnResume called *** " + interval);
        } else {
            Log.d("Hiten", "OnResume called emptyyy -- ");
        }
        if (counter != null) {
            counter.cancel();
        }

        counter = new MyCount(interval != 0 ? interval : startInterval, 1000);
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
            }

            @Override
            public void onTick(long millisUntilFinished) {
//            Log.d("Hiten","onTIck --"+millisUntilFinished);
                remainingTime = millisUntilFinished;
                timerValue.setText("left:" + remainingTime / 1000);
            }

            public void pause() {
                // Stop current timer
                Log.d("Hiten", "inside onPause on timer called ");
                SharedPreferencesUtil.setRefreshTime(getApplicationContext(), remainingTime);
                counter.cancel();
            }

            public MyCount resume() {
                // Create a counter with last saved data (just before pause)
                long interval = SharedPreferencesUtil.getRefreshTime(getApplicationContext());

                if (interval != 0) {
                    Log.d("Hiten", "OnResume called *** " + interval);
                } else {
                    Log.d("Hiten", "OnResume called emptyyy -- ");
                }
                MyCount newTimer = new MyCount(interval != 0 ? remainingTime : startInterval, 1000);
                // Start this new timer that start where old one stop
                newTimer.start();
                // Return this new timer
                return newTimer;
            }

        /*public abstract class CountDownTimer {

         *//**
         * Millis since epoch when alarm should stop.
         *//*
            private final long mMillisInFuture;

            *//**
         * The interval in millis that the user receives callbacks
         *//*
            private final long mCountdownInterval;

            private long mStopTimeInFuture;

            private long mPauseTime;

            private boolean mCancelled = false;

            private boolean mPaused = false;

            *//**
         * @param millisInFuture The number of millis in the future from the call
         *   to {@link #start()} until the countdown is done and {@link #onFinish()}
         *   is called.
         * @param countDownInterval The interval along the way to receive
         *   {@link #onTick(long)} callbacks.
         *//*
            public CountDownTimer(long millisInFuture, long countDownInterval) {
                mMillisInFuture = millisInFuture;
                mCountdownInterval = countDownInterval;
            }

            *//**
         * Cancel the countdown.
         *
         * Do not call it from inside CountDownTimer threads
         *//*
            public final void cancel() {
                mHandler.removeMessages(MSG);
                mCancelled = true;
            }

            *//**
         * Start the countdown.
         *//*
            public synchronized final CountDownTimer start() {
                if (mMillisInFuture <= 0) {
                    onFinish();
                    return this;
                }
                mStopTimeInFuture = SystemClock.elapsedRealtime() + mMillisInFuture;
                mHandler.sendMessage(mHandler.obtainMessage(MSG));
                mCancelled = false;
                mPaused = false;
                return this;
            }

            *//**
         * Pause the countdown.
         *//*
            public long pause() {
                mPauseTime = mStopTimeInFuture - SystemClock.elapsedRealtime();
                mPaused = true;
                return mPauseTime;
            }

            *//**
         * Resume the countdown.
         *//*
            public long resume() {
                mStopTimeInFuture = mPauseTime + SystemClock.elapsedRealtime();
                mPaused = false;
                mHandler.sendMessage(mHandler.obtainMessage(MSG));
                return mPauseTime;
            }

            *//**
         * Callback fired on regular interval.
         * @param millisUntilFinished The amount of time until finished.
         *//*
            public abstract void onTick(long millisUntilFinished);

            *//**
         * Callback fired when the time is up.
         *//*
            public abstract void onFinish();


            private static final int MSG = 1;


            // handles counting down
            private Handler mHandler = new Handler() {

                @Override
                public void handleMessage(Message msg) {

                    synchronized (CountDownTimer.this) {
                        if (!mPaused) {
                            final long millisLeft = mStopTimeInFuture - SystemClock.elapsedRealtime();

                            if (millisLeft <= 0) {
                                onFinish();
                            } else if (millisLeft < mCountdownInterval) {
                                // no tick, just delay until done
                                sendMessageDelayed(obtainMessage(MSG), millisLeft);
                            } else {
                                long lastTickStart = SystemClock.elapsedRealtime();
                                onTick(millisLeft);

                                // take into account user's onTick taking time to execute
                                long delay = lastTickStart + mCountdownInterval - SystemClock.elapsedRealtime();

                                // special case: user's onTick took more than interval to
                                // complete, skip to next interval
                                while (delay < 0) delay += mCountdownInterval;

                                if (!mCancelled) {
                                    sendMessageDelayed(obtainMessage(MSG), delay);
                                }
                            }
                        }
                    }
                }
            };
        }*/
    }
}

