package au.com.sports.mate.test.customView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import au.com.sports.mate.test.R;

public class RoundShapeView extends LinearLayout {
    private ImageView mTeam1Image;
    private TextView mTeam1;
    private ImageView mTeam2Image;
    private TextView mTeam2;

    public RoundShapeView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.finals_grid_style_round, this);
        mTeam1Image = itemView.findViewById(R.id.team1_image);
        mTeam1 = itemView.findViewById(R.id.team1);
        mTeam2Image = itemView.findViewById(R.id.team2_image);
        mTeam2 = itemView.findViewById(R.id.team2);
    }

    public void setTeam1Name(String mTeam1) {
        this.mTeam1.setText(mTeam1);
    }
    public void setTeam1Image(String url) {

    }

    public void setTeam2Name(String mTeam2) {
        this.mTeam2.setText(mTeam2);
    }
    public void setmTeam2Image(String url) {

    }
}
