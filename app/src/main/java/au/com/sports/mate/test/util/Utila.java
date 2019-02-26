package au.com.sports.mate.test.util;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class Utila {

    public static int getCurrentScreenHeight(Context ctx) {
        DisplayMetrics dm = ctx.getResources().getDisplayMetrics();

        return (ctx.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) ? dm.heightPixels : dm.heightPixels;
    }

    public static int getPixelsForDip(Context ctx, final float dip) {
        DisplayMetrics dm = new DisplayMetrics();

        WindowManager windowMgr = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        windowMgr.getDefaultDisplay().getMetrics(dm);

        return (int) (dip * dm.density);
    }

    public static String replaceUrlParams(String url) {
        if (TextUtils.isEmpty(url))
            return "";

        String advId = "";

        if (!TextUtils.isEmpty(advId))
            url = url.replace("#ADVERTISING_ID#", advId);

        return url;
    }
}
