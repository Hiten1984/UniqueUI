package au.com.sports.mate.test.ui.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import au.com.sports.mate.test.R;
import au.com.sports.mate.test.customView.FinalShapeView;
import au.com.sports.mate.test.customView.RoundShapeView;
import au.com.sports.mate.test.customView.StageShapeView;
import au.com.sports.mate.test.model.AnnotationsItem;
import au.com.sports.mate.test.model.ConnectionsItem;
import au.com.sports.mate.test.model.FinalsGridRows;
import au.com.sports.mate.test.response.FinalsGridResponse;
import au.com.sports.mate.test.util.CustomUtil;

public class MainActivity extends AppCompatActivity {

    private List<FinalsGridRows> rowItems;
    private List<ConnectionsItem> connectionItems;
    private List<AnnotationsItem> annotationItems;
    private LinearLayout container;
    private String savedId ="";
    private String savedTopElementID="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finals);
        container = findViewById(R.id.main_activity_container);
        loadData();
    }

    private void loadData() {
        FinalsGridResponse data = CustomUtil.fromJson(this, "finals_grid_updated_1");
        Log.d("Hiten", "sizee--- "+data.content.getFinalsGrid().size());
        parseDataIntoDifferentRows(data.getContent().getFinalsGrid());
    }

    private void parseDataIntoDifferentRows(List<FinalsGridResponse.FinalsGridItems> finalsGridItems) {

        if (finalsGridItems == null || CustomUtil.isEmpty(finalsGridItems.get(0).getRows()))
            return;


        rowItems = finalsGridItems.get(0).getRows();
        connectionItems = finalsGridItems.get(0).getConnections();
        annotationItems = finalsGridItems.get(0).getAnnotations();

        container.removeAllViews();

        for (FinalsGridRows rowItem : rowItems) {
            container.addView(attachFinalsGridView(rowItem));
        }

    }

    private View attachFinalsGridView(FinalsGridRows rowItem) {
        if (CustomUtil.isEmpty(rowItem.getItems()))
            return null;
        List<FinalsGridRows.FinalsGridRowItemsss> finalsGridRowItemsss = rowItem.getItems().get(0);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        params.height = (int) CustomUtil.pxFromDp(this,100);
        params.setMargins(10,10,10,10);
        linearLayout.setLayoutParams(params);
        for (FinalsGridRows.FinalsGridRowItemsss item : rowItem.getItems().get(0)) {
            switch (item.getStyle()) {
                case "round":
                    linearLayout.addView(createStyleRoundView(item));
                    break;
                case "stage":
                    linearLayout.addView(createStyleStageView(item));
                    break;
                case "final":
                    linearLayout.addView(createStyleFinalView(item));
                    break;
                /*default:
                        view.addView(createStyleEmptyView(view, row));
                        break;*/
            }
        }
        return linearLayout;

    }

    private View createStyleRoundView(FinalsGridRows.FinalsGridRowItemsss row) {
        RoundShapeView roundShapeView = new RoundShapeView(this);
        roundShapeView.setTeam1Name(row.getLeftTeamID());
        roundShapeView.setTeam2Name(row.getRightTeamID());
        return roundShapeView;
    }

    private View createStyleStageView(FinalsGridRows.FinalsGridRowItemsss row) {
        StageShapeView stageShapeView;
        if(row.getLeftTeamID() != null) {
            stageShapeView = new StageShapeView(this, true);
            stageShapeView.setTeam1Name(row.getLeftTeamID());
            stageShapeView.setTeam2Name(row.getRightTeamID());
        } else {
            stageShapeView = new StageShapeView(this, false);
            stageShapeView.setmFinalsGridText1(row.getName());
        }

        //stageShapeView.setmFinalsGridText1(row.get);
        /*View view = LayoutInflater.from(this).inflate(R.layout.finals_grid_style_stage, parent, false);

        String currentID = row.getElementID();

        if(!currentID.equals(savedId) && !topElementID.equals(savedTopElementID)) {
            ((TextView) view.findViewById(R.id.finals_grid_text1)).setText(row.getName());
            savedId = currentID;
            savedTopElementID = topElementID;
        }


        if(!savedId.equals(row.getElementID())) {
            TextView text2 = view.findViewById(R.id.finals_grid_text2);
            text2.setVisibility(View.VISIBLE);
            text2.setText(row.getName());
            savedId = currentID;
        }
        return view;*/
        return stageShapeView;
    }

    private View createStyleFinalView(FinalsGridRows.FinalsGridRowItemsss row) {

        FinalShapeView finalShapeView = new FinalShapeView(this);
        finalShapeView.setmFinalsGridFinalsTrophyImage(row.getTrophyRemoteImageURL());
       /* View view = LayoutInflater.from(this).inflate(R.layout.finals_grid_style_final, parent, false);
        ImageView imageView = view.findViewById(R.id.finals_grid_finals_trophy_image);
        String trophy = row.getTrophyRemoteImageURL();
        if (!TextUtils.isEmpty(trophy)) {
            Picasso.with(parent.getContext()).load(trophy).placeholder(R.drawable.app_placeholder).into(imageView);
        }
        return view;*/
        return finalShapeView;
    }


    private int getPixelsToDP(int dp) {
        float scale = getResources().getDisplayMetrics().density;
        int pixels = (int) (dp * scale + 0.5f);
        return pixels;
    }
}
