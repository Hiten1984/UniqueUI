package au.com.sports.mate.test.util;

import android.content.Context;

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

}
