package au.com.sports.mate.test.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import au.com.sports.mate.test.response.FinalsGridResponse;

public class CustomUtil {

    public static FinalsGridResponse fromJson(Context context, String path) {
        try {
            Gson gson = new Gson();
            Reader reader = getReader(context, path);
            return gson.fromJson(reader, FinalsGridResponse.class);
        } catch (Exception e) {
        }
        return null;
    }


    private static Reader getReader(Context context, String path) {
        return new InputStreamReader(context.getResources().openRawResource(
                context.getResources().getIdentifier(path, "raw", context.getPackageName())
        ));
    }

    public static boolean isEmpty(List list) {
        return (list == null || list.size() == 0);
    }

    public static float dpFromPx(final Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static boolean isOtherAppInForeground(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            return true;

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo runningAppProcess : runningAppProcesses) {
            if (runningAppProcess.processName.equals(context.getPackageName()) && runningAppProcess.importance == ActivityManager.RunningAppProcessInfo
                    .IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }
}
