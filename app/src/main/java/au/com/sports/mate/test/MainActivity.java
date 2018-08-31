package au.com.sports.mate.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

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
        container = findViewById(R.id.container);
        loadData();
    }

    private void loadData() {
        FinalsGridResponse data = CustomUtil.fromJson(this, "finals_grid");
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

        for (FinalsGridRows rowItem : finalsGridItems.get(0).getRows()) {
            attachFinalsGridView(container, rowItem, rowItem.getElementID());
        }

    }

    private void attachFinalsGridView(ViewGroup parent, FinalsGridRows rowItem, String topElementID) {
        if (CustomUtil.isEmpty(rowItem.getItems()))
            return;

        ViewGroup view = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.finals_grid_section, parent, false);
        parent.addView(view);

        for (FinalsGridRows.FinalsGridRowItemsss row : rowItem.getItems().get(0)) {
            switch (row.getStyle()) {
                case "round":
                    view.addView(createStyleRoundView(view, row));
                    break;
                case "stage":
                    view.addView(createStyleStageView(view, row, topElementID));
                    break;
                case "final":
                    view.addView(createStyleFinalView(view, row));
                    break;
                /*default:
                        view.addView(createStyleEmptyView(view, row));
                        break;*/
            }
        }

    }

    private View createStyleRoundView(ViewGroup parent, FinalsGridRows.FinalsGridRowItemsss row) {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_main, parent, false);


        AnotherCustomLayout another = view.findViewById(R.id.another_layout_view);
        LayoutInflater layoutInflater = getLayoutInflater();
        String tag, tag1;
        for (int i = 0; i < rowItems.size(); i++) {
            tag = "#t1-" + row.getLeftTeamID();
            tag1 = "#t2-" + row.getRightTeamID();
            View customView = layoutInflater.inflate(R.layout.custom_view_layout, null, false);

            LinearLayout container = customView.findViewById(R.id.container);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.rightMargin = getPixelsToDP(10);
            container.setLayoutParams(layoutParams);

            TextView tagTextView1 = customView.findViewById(R.id.team1_text);
            TextView tagTextView2 = customView.findViewById(R.id.team2_text);
            tagTextView1.setText(tag);
            tagTextView2.setText(tag1);
            another.addView(customView, layoutParams);
        }
        return view;
    }

    private View createStyleStageView(ViewGroup parent, FinalsGridRows.FinalsGridRowItemsss row, String topElementID) {
        View view = LayoutInflater.from(this).inflate(R.layout.finals_grid_style_stage, parent, false);

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
        return view;
    }

    private View createStyleFinalView(ViewGroup parent, FinalsGridRows.FinalsGridRowItemsss row) {
        View view = LayoutInflater.from(this).inflate(R.layout.finals_grid_style_final, parent, false);
        ImageView imageView = view.findViewById(R.id.finals_grid_finals_trophy_image);
        String trophy = row.getTrophyRemoteImageURL();
        if (!TextUtils.isEmpty(trophy)) {
            Picasso.with(parent.getContext()).load(trophy).placeholder(R.drawable.app_placeholder).into(imageView);
        }
        return view;
    }


    private int getPixelsToDP(int dp) {
        float scale = getResources().getDisplayMetrics().density;
        int pixels = (int) (dp * scale + 0.5f);
        return pixels;
    }
}
