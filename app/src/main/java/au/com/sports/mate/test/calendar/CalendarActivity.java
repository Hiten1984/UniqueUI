package au.com.sports.mate.test.calendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.applandeo.materialcalendarview.EventDay;

import java.util.ArrayList;
import java.util.List;

import au.com.sports.mate.test.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalendarActivity extends AppCompatActivity implements Fragment1.onDateClicked {

    @BindView(R.id.top_container)
    FrameLayout topContainer;

    @BindView(R.id.bottom_container)
    FrameLayout bottomContainer;

    private List<EventDay> mEventDays = new ArrayList<>();
    private Fragment1 frag1;
    private Fragment2 frag2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);
    }

    @Override
    public void sendDate(int date) {
        frag2.updateData(date);
    }

    @OnClick(R.id.calendar_button)
    public void onCalendarClicked() {
        loadFragments();
    }

    private void loadFragments() {

        if(frag1 == null) {
            frag1 = Fragment1.newInstance();
            frag2 = Fragment2.newInstance();

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                Transition transition = new Slide(Gravity.TOP);
                frag1.setEnterTransition(transition);
                frag2.setExitTransition(transition);
            }
            FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
            ft1.replace(R.id.top_container, frag1,"FRAGA");
            ft1.commit();


            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                Transition transition = new Slide(Gravity.BOTTOM);
                frag2.setEnterTransition(transition);
                frag1.setExitTransition(transition);
            }
            FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
            ft2.replace(R.id.bottom_container, frag2, "FRAGB");
            ft2.commit();
        } else {
            if(frag1 != null) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Transition outTrans = new Slide(Gravity.BOTTOM);
                    frag1.setExitTransition(outTrans);
                }
                FragmentTransaction ftt1 = getSupportFragmentManager().beginTransaction();
//                ftt1.detach(frag1);
                ftt1.hide(frag1);
                ftt1.commit();
                frag1 = null;
            }
            if(frag2 != null) {
                FragmentTransaction ftt2 = getSupportFragmentManager().beginTransaction();
                ftt2.hide(frag2);
//                ftt2.detach(frag2);
                ftt2.commit();
            }
        }
    }

    @OnClick(R.id.bottom_button)
    public void onBottomButtonClicked() {
        loadFragment2();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_delete:
                loadFragments();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_select, menu);
        return true;
    }

    private void loadFragment1() {
        frag1 = Fragment1.newInstance();

        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft1.setCustomAnimations(R.anim.abc_slide_in_top, R.anim.abc_slide_out_top);
        ft1.replace(R.id.top_container, frag1, "FRAGA");
        ft1.commit();
    }

    private void loadFragment2() {
        frag2 = Fragment2.newInstance();

        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
        ft2.setCustomAnimations(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom);
        ft2.replace(R.id.bottom_container, frag2, "FRAGB");
        ft2.commit();
    }

}
