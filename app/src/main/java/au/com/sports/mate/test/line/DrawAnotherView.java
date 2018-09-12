package au.com.sports.mate.test.line;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawAnotherView extends View {
    Paint paint = new Paint();
    View startView;
    View endView;

    public DrawAnotherView(Context context, View startView, View endView) {
        super(context);
        paint.setColor(Color.RED);
        this.startView = startView;
        this.endView = endView;
    }

    public void onDraw(Canvas canvas) {
        canvas.drawLine(startView.getX(), startView.getY(), endView.getX(), endView.getY(), paint);
    }

}