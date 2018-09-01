package au.com.sports.mate.test.customView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import au.com.sports.mate.test.R;

public class StageShapeView extends LinearLayout {
    private TextView mFinalsGridText1;
    private TextView mFinalsGridText2;

    public StageShapeView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.finals_grid_style_stage, this);
        mFinalsGridText1 = findViewById(R.id.finals_grid_text1);
    }

    public void setmFinalsGridText1(String mFinalsGridText1) {
        this.mFinalsGridText1.setText(mFinalsGridText1);
    }
}
