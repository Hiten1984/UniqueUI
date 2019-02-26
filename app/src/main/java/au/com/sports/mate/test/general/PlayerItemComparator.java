package au.com.sports.mate.test.general;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

class PlayerItemComparator implements Comparator<Player.LiveMatchLineUpsStatsTablePlayer> {


    private final int i;

    public PlayerItemComparator(int i) {
        this.i = i;
    }

    @Override
    public int compare(Player.LiveMatchLineUpsStatsTablePlayer lhs, Player.LiveMatchLineUpsStatsTablePlayer rhs) {
        String lhsValue = lhs.getValuesFull().get(i);
        String rhsValue = rhs.getValuesFull().get(i);
        if (lhsValue.contains(".")) {
            // compare delimited values
            StringTokenizer lhsTokenizer = new StringTokenizer(lhsValue, ".");
            StringTokenizer rhsTokenizer = new StringTokenizer(rhsValue, ".");

            ArrayList<String> lhsValues = new ArrayList<String>();
            ArrayList<String> rhsValues = new ArrayList<String>();

            while (lhsTokenizer.hasMoreElements()) {
                lhsValues.add(lhsTokenizer.nextToken());
                rhsValues.add(rhsTokenizer.nextToken());
            }

            boolean ended = false;
            int index = 0;

            while (index < lhsValues.size() && !ended) {
                int lhsIntValue = Integer.parseInt(lhsValues.get(index));
                int rhsIntValue = Integer.parseInt(rhsValues.get(index));

                if (lhsIntValue > rhsIntValue) {
                    return -1;
                }
                else if (lhsIntValue < rhsIntValue) {
                    return 1;
                }

                index++;
            }

            return 0;
        }
        else {
            lhsValue = lhsValue.replace("%", "");
            rhsValue = rhsValue.replace("%", "");

            // normal compare
            int lhsIntValue = Integer.parseInt(lhsValue);
            int rhsIntValue = Integer.parseInt(rhsValue);

            if (lhsIntValue > rhsIntValue) {
                return -1;
            }
            else if (lhsIntValue < rhsIntValue) {
                return 1;
            }

            return 0;
        }
    }

}
