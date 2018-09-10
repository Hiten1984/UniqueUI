package au.com.sports.mate.test.customView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import au.com.sports.mate.test.R;

public class StageShapeView extends LinearLayout {
    private TextView mFinalsGridText1;
    private ImageView mTeam1Image;
    private TextView mTeam1;
    private ImageView mTeam2Image;
    private TextView mTeam2;


    public StageShapeView(Context context, boolean hasTeamId) {
        super(context);
        initView(hasTeamId);
    }

    private void initView(boolean hasTeamId) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(hasTeamId) {
            View itemView = inflater.inflate(R.layout.finals_grid_style_round, this);
            mTeam1Image = itemView.findViewById(R.id.team1_image);
            mTeam1 = itemView.findViewById(R.id.team1);
            mTeam2Image = itemView.findViewById(R.id.team2_image);
            mTeam2 = itemView.findViewById(R.id.team2);
        } else {
            inflater.inflate(R.layout.finals_grid_style_stage, this);
            mFinalsGridText1 = findViewById(R.id.finals_grid_text1);
        }
    }

    public void setmFinalsGridText1(String mFinalsGridText1) {
        this.mFinalsGridText1.setText(mFinalsGridText1);
    }

    public void setTeam1Name(String mTeam1) {
        this.mTeam1.setText(mTeam1);
    }
    public void setTeam2Name(String mTeam2) {
        this.mTeam2.setText(mTeam2);
    }
}
