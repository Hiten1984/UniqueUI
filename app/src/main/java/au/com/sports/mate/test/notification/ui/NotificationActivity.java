package au.com.sports.mate.test.notification.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import au.com.sports.mate.test.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationActivity extends AppCompatActivity {

    @BindView(R.id.welcome_text)
    TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        if(intent.getExtras() != null && intent.getExtras().containsKey("text")) {
            welcomeText.setText(""+intent.getExtras().get("text"));
        }

    }

}
