package au.com.sports.mate.test.general;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import au.com.sports.mate.test.R;

public class GeneralActivity extends AppCompatActivity {
    ArrayList<Player.LiveMatchLineUpsStatsTablePlayer> playerList = new ArrayList<>();
    private ArrayList<Player.LiveMatchLineUpsStatsTablePlayer> sortedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createStubData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortedList = createSortedList(playerList);
//                furtherSort(sortedList);
            }
        });
    }

    private void furtherSort(ArrayList<Player.LiveMatchLineUpsStatsTablePlayer> sortedList) {
        Collections.sort(sortedList, (o1, o2) -> {
            boolean b1 = o1.isFavorite;
            boolean b2 = o2.isFavorite;

            return (b1 != b2) ? (b1) ? -1 : 1 : 0;
        });
    }


    private ArrayList<Player.LiveMatchLineUpsStatsTablePlayer> createSortedList(ArrayList<Player.LiveMatchLineUpsStatsTablePlayer> playerList) {
        Collections.sort(playerList, new PlayerItemComparator(0));
        return playerList;
    }


    private void createStubData() {
        ArrayList<String> s = new ArrayList<>();
        s.add("1.0");
        s.add("1.1");
        playerList.add(new Player.LiveMatchLineUpsStatsTablePlayer("Luke", "Shey", 13, 280078, 10, s, false));
        ArrayList<String> s1 = new ArrayList<>();
        s1.add("4.0");
        s1.add("1.1");
        playerList.add(new Player.LiveMatchLineUpsStatsTablePlayer("Josh", "Kennedy", 17, 240406,  10, s1, false ));
        ArrayList<String> s2 = new ArrayList<>();
        s2.add("3.1");
        s2.add("2.1");
        playerList.add(new Player.LiveMatchLineUpsStatsTablePlayer("Dom", "Sheed", 4, 296296,  10 , s2, false));
        ArrayList<String> s3 = new ArrayList<>();
        s3.add("1.0");
        s3.add("1.1");
        playerList.add(new Player.LiveMatchLineUpsStatsTablePlayer("Nathan", "Vardy", 19, 280595,  8 , s3, false));
        ArrayList<String> s4 = new ArrayList<>();
        s4.add("1.1");
        s4.add("1.1");
        playerList.add(new Player.LiveMatchLineUpsStatsTablePlayer("Shanon", "Hurn", 25, 240283, 8 , s4, false));
        ArrayList<String> s5 = new ArrayList<>();
        s5.add("6.0");
        s5.add("2.1");
        playerList.add(new Player.LiveMatchLineUpsStatsTablePlayer("Shanon", "Hurn", 25, 240283,  8 , s5, false));
        ArrayList<String> s6 = new ArrayList<>();
        s6.add("1.1");
        s6.add("2.1");
        playerList.add(new Player.LiveMatchLineUpsStatsTablePlayer("Travis", "Varcoe", 18, 250290,  7 , s6, true));
    }


}
