package au.com.sports.mate.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import au.com.sports.mate.test.line.DrawLineActivity;
import au.com.sports.mate.test.timer.TimerActivity;
import au.com.sports.mate.test.ui.view.MainActivity;
import au.com.sports.mate.test.notification.ui.NotificationActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LaunchActivity extends AppCompatActivity {

    @BindView(R.id.button_ui)
    Button uiButton;

    @BindView(R.id.button_fcm)
    Button fcmButton;

    @BindView(R.id.button_timer)
    Button timerButton;

    @BindView(R.id.button_line)
    Button drawLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        String str = "b 0   lb 4   wides 6   nb 0";
        Log.d("Hiten", "Final Split String  -- " +str.replaceAll("\\s{2,}", ", "));
        String[] splitStr = str.split("\\s{2,}");

        for(String s: splitStr) {
            Log.d("Hiten", "Split String  -- " + s);
        }

        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            Log.d("Hiten", "Launch activity called -- "+intent.getExtras());
            Log.d("Hiten", "Launch activity called -- Data -"+intent.getExtras().getString("data"));
            Log.d("Hiten", "Launch activity called -- Notification - "+intent.getExtras().getString("notification"));
            Log.d("Hiten", "Launch activity called -- data key 1  - "+intent.getExtras().getString("custom_key_1"));
//            intent.getExtras().containsKey("notification");
        }
        ButterKnife.bind(this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        Log.d("Hiten", "On New Intent Called -- Launch activity called");
    }

    @OnClick(R.id.button_ui)
    public void onUiCLicked() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_fcm)
    public void onFcmCLicked() {
        Intent intent = new Intent(this, NotificationActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_timer)
    public void onTimerClassCLicked() {
        Intent intent = new Intent(this, TimerActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_line)
    public void onDrawLineCLicked() {
        Intent intent = new Intent(this, DrawLineActivity.class);
        startActivity(intent);
    }
}
