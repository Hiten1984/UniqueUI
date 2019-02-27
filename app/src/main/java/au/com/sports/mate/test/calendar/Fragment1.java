package au.com.sports.mate.test.calendar;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.Calendar;

import au.com.sports.mate.test.R;
import butterknife.BindView;
import butterknife.ButterKnife;


public class Fragment1 extends Fragment {
    @BindView(R.id.calendarView)
    CalendarView calendarView;

    onDateClicked dateClicked;
    private int date;

    public Fragment1() {
        // Required empty public constructor
    }

    public static Fragment1 newInstance() {
        return new Fragment1();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment1, container, false);
        if (v != null) {
            ButterKnife.bind(this, v);
        }
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            dateClicked = (onDateClicked) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement TextClicked");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
//                date = eventDay.getCalendar().get(Calendar.DATE);
                date = eventDay.getCalendar().get(Calendar.DAY_OF_MONTH);
                dateClicked.sendDate(date);
                Toast.makeText(getActivity(), "Hello "+eventDay.getCalendar().get(Calendar.DATE), Toast.LENGTH_LONG).show();
            }
        });
    }

    public interface onDateClicked {
        public void sendDate(int date);
    }
}
