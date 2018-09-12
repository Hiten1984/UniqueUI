package au.com.sports.mate.test.line;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import au.com.sports.mate.test.R;

public class DrawLineActivity extends AppCompatActivity {

    //    DrawView drawView;
    DrawAnotherView drawView, drawView1, drawView2, drawView3;

    TextView r1,r2,r3,r4;
    RelativeLayout rl1;


    @Override
    public void onCreate(Bundle savedInstanceState) {



        /**
         *  uncomment this to test Draw VIew
         */

        /* super.onCreate(savedInstanceState);

        drawView = new DrawView(this);
        drawView.setBackgroundColor(Color.WHITE);
        setContentView(drawView);*/



        /**
         * uncomment this to test Draw Another VIew
         */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        r1 = findViewById(R.id.textView1);
        r2 = findViewById(R.id.textView2);
        r3 = findViewById(R.id.textView3);
        r4 = findViewById(R.id.textView4);

        rl1 = findViewById(R.id.rl1);


        drawView = new DrawAnotherView(DrawLineActivity.this, r4, r1);
        drawView1 = new DrawAnotherView(DrawLineActivity.this, r1, r2);
        drawView2 = new DrawAnotherView(DrawLineActivity.this, r2, r3);
        drawView3 = new DrawAnotherView(DrawLineActivity.this, r3, r4);

        rl1.addView(drawView);
        rl1.addView(drawView1);
        rl1.addView(drawView2);
        rl1.addView(drawView3);
    }

}

