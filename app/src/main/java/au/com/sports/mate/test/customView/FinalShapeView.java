package au.com.sports.mate.test.customView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import au.com.sports.mate.test.R;

public class FinalShapeView extends LinearLayout {

    private TextView mFinalsGridFinalsTrophyText;
    private ImageView mFinalsGridFinalsTrophyImage;

    public FinalShapeView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.finals_grid_style_final, this);
        mFinalsGridFinalsTrophyText = findViewById(R.id.finals_grid_finals_trophy_text);
        mFinalsGridFinalsTrophyImage = findViewById(R.id.finals_grid_finals_trophy_image);
    }

    public void setmFinalsGridFinalsTrophyImage(String url) {
    }

    public void setmFinalsGridFinalsTrophyText(String mFinalsGridFinalsTrophyText) {
        this.mFinalsGridFinalsTrophyText.setText(mFinalsGridFinalsTrophyText);
    }
}

