package au.com.sports.mate.test.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

    private static SharedPreferences getSharedPreferences(Context ctx) {
        return ctx.getSharedPreferences("My_APP_Preferences", Context.MODE_PRIVATE);
    }

    public static void setRefreshTime(Context ctx, long refreshInterval) {
        SharedPreferences prefs = getSharedPreferences(ctx);
        prefs.edit().putLong("news_time", refreshInterval).commit();
    }

    public static long getRefreshTime(Context ctx) {
        SharedPreferences prefs = getSharedPreferences(ctx);
        return prefs.getLong("news_time", 0L);
    }

}
