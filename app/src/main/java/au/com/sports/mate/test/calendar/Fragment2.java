package au.com.sports.mate.test.calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import au.com.sports.mate.test.R;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.text_view)
    TextView textView;

    public Fragment2() {
        // Required empty public constructor
    }

    public static Fragment2 newInstance() {
        return new Fragment2();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment2, container, false);
        if (v != null) {
            ButterKnife.bind(this, v);
        }
        return v;
    }

    public void updateData(int date) {
        textView.setText(String.valueOf(date));
    }
}
