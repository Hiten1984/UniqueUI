package au.com.sports.mate.test.stickylist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import au.com.sports.mate.test.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class StickyListActivity extends AppCompatActivity {
    @BindView(R.id.list)
    StickyListHeadersListView stickyList;
    private List<FavTeamData> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky_list);
        ButterKnife.bind(this);
        data = loadData();
        setUpView();
    }

    private void setUpView() {
        StickyListAdapter adapter = new StickyListAdapter(this, data);
        stickyList.setAdapter(adapter);
    }

    private List<FavTeamData> loadData() {
        List<FavTeamData> favTeamDataList  = new ArrayList<>();
        FavTeamData favTeamData = new FavTeamData();

        favTeamData.setTitle("Team 1");

        List<Artcles> artclesList = new ArrayList<>();

        Artcles artcles1 = new Artcles();
        artcles1.title = "News-1";
        artcles1.setSubTitle("SubHeading -1 ");
        artclesList.add(artcles1);

        Artcles artcles2 = new Artcles();
        artcles2.title = "News-2";
        artcles2.setSubTitle("SubHeading -2 ");
        artclesList.add(artcles2);

        favTeamData.setArticles(artclesList);

        favTeamDataList.add(favTeamData);

        FavTeamData favTeamData1 = new FavTeamData();

        favTeamData1.setTitle("Team 2");

        List<Artcles> artclesList1 = new ArrayList<>();

        Artcles artcles11 = new Artcles();
        artcles11.title = "News-22";
        artcles11.setSubTitle("SubHeading -22 ");
        artclesList1.add(artcles11);

        Artcles artcles22 = new Artcles();
        artcles22.title = "News-23";
        artcles22.setSubTitle("SubHeading -23");
        artclesList1.add(artcles22);

        favTeamData1.setArticles(artclesList1);

        favTeamDataList.add(favTeamData1);


        FavTeamData favTeamData2 = new FavTeamData();
        favTeamData2.setTitle("Team 3");

        List<Artcles> artclesList2 = new ArrayList<>();

        Artcles artcles33 = new Artcles();
        artcles33.title = "News-33";
        artcles33.setSubTitle("SubHeading -33 ");
        artclesList2.add(artcles33);

        Artcles artcles44 = new Artcles();
        artcles44.title = "News-34";
        artcles44.setSubTitle("SubHeading -334");
        artclesList2.add(artcles44);

        favTeamData2.setArticles(artclesList2);

        favTeamDataList.add(favTeamData2);


        FavTeamData favTeamData4 = new FavTeamData();
        favTeamData4.setTitle("Team 4");

        List<Artcles> artclesList4 = new ArrayList<>();

        Artcles artcles133 = new Artcles();
        artcles133.title = "News-33";
        artcles133.setSubTitle("SubHeading -33 ");
        artclesList4.add(artcles133);

        Artcles artcles144 = new Artcles();
        artcles144.title = "News-34";
        artcles144.setSubTitle("SubHeading -334");
        artclesList4.add(artcles144);

        favTeamData4.setArticles(artclesList4);

        favTeamDataList.add(favTeamData4);



        return favTeamDataList;
    }

    public class FavTeamData implements Serializable{
        String title;
        List<Artcles> articles;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Artcles> getArticles() {
            return articles;
        }

        public void setArticles(List<Artcles> articles) {
            this.articles = articles;
        }
    }

    public static class Artcles implements Serializable {
        String title;
        String subTitle;

        public Artcles() {}

        public Artcles(String title, String subTitle) {
            this.title  = title;
            this.subTitle = subTitle;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }
    }
}
