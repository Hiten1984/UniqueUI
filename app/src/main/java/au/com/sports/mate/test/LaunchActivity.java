package au.com.sports.mate.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import au.com.sports.mate.test.calendar.CalendarActivity;
import au.com.sports.mate.test.general.GeneralActivity;
import au.com.sports.mate.test.line.DrawLineActivity;
import au.com.sports.mate.test.multiselect.MultiSelectActivity;
import au.com.sports.mate.test.timer.TimerActivity;
import au.com.sports.mate.test.ui.view.MainActivityV;
import au.com.sports.mate.test.notification.ui.NotificationActivity;
import au.com.sports.mate.test.webview.WebViewActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import au.com.sports.mate.test.stickylist.StickyListActivity;

public class LaunchActivity extends AppCompatActivity {

    @BindView(R.id.button_general)
    Button generalButton;

    @BindView(R.id.button_ui)
    Button uiButton;

    @BindView(R.id.button_fcm)
    Button fcmButton;

    @BindView(R.id.button_timer)
    Button timerButton;

    @BindView(R.id.button_line)
    Button drawLine;

    @BindView(R.id.web_view_button)
    Button webViewButton;

    @BindView(R.id.stick_list_button)
    Button stickyList;

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
            Log.d("Hiten", "Launch activity called -- Notification - "+intent.getExtras().getString("notification"));
            Log.d("Hiten", "Launch activity called -- data key 1  - "+intent.getExtras().getString("custom_key_1"));
//            intent.getExtras().containsKey("notification");
        }
        ButterKnife.bind(this);

        List<User> users = new ArrayList<>();
        users.add(new User(1, "Hit", "l"));
        users.add(new User(2, "Pit", "c"));
        users.add(new User(1, "Tit", "s"));
        users.add(new User(1, "Jit", "s"));
        users.add(new User(1, "Fit", "c"));
        users.add(new User(1, "Git", "c"));
        users.add(new User(1, "Qit", "l"));


        sort(users);

    }
    private void sort(List<User> users) {
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.state.compareTo(o2.state);
            }
        });
    }


    @Override
    protected void onNewIntent(Intent intent) {
        Log.d("Hiten", "On New Intent Called -- Launch activity called");
    }

    @OnClick(R.id.button_ui)
    public void onUiCLicked() {
        Intent intent = new Intent(this, MainActivityV.class);
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

    @OnClick(R.id.web_view_button)
    public void onWebViewButtonClicked() {
        Intent intent = new Intent(this, WebViewActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_general)
    public void onGeneralCLicked() {
        Intent intent = new Intent(this, GeneralActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.stick_list_button)
    public void stickyListClicked() {
        Intent intent = new Intent(this, StickyListActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.multi_select_list_button)
    public void mulitSelectClicked() {
        Intent intent = new Intent(this, MultiSelectActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.calendar_view)
    public  void calendarView() {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    private class User {
        int id;
        String name;
        String state;

        public User(int id, String name, String state) {
            this.id = id;
            this.state = state;
            this.name = name;
        }
    }
}
