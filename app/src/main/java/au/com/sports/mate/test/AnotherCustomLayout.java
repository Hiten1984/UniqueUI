package au.com.sports.mate.test;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class AnotherCustomLayout extends ViewGroup {

    private static final String TAG = "CustomLayout";
    private LinearLayout cll ;

    public AnotherCustomLayout(Context context){
        super(context);
        init();
    }

    public AnotherCustomLayout(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }

    public AnotherCustomLayout(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        init();
    }

    private void init(){

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){

//		At this time we need to call setMeasuredDimensions(). Lets just call the 
//		parent View's method (see https://github.com/android/platform_frameworks_base/blob/master/core/java/android/view/View.java) 
//		that does:
//		setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
//                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
//		

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        int wspec = MeasureSpec.makeMeasureSpec(getMeasuredWidth()/getChildCount(), MeasureSpec.EXACTLY);
        int hspec = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.EXACTLY);


        for(int i=0; i<getChildCount(); i++){
            View v = getChildAt(i);
            Log.d(TAG, "Measured Width / Height: "+getMeasuredWidth()+", "+getMeasuredHeight());


            v.measure(wspec, hspec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        int itemWidth = (right - left)/getChildCount();
        for(int i=0; i < this.getChildCount(); i++) {
            View v = getChildAt(i);
            v.layout(itemWidth * i, 0, (i+1) * itemWidth, bottom - top);
        }

    }

    private int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

}