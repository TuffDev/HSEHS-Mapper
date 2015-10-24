package com.example.adam.myfirstapp;

/**
 * Created by root on 10/24/15.
 */
//package CustomWidgets;

        import android.content.Context;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.Path;
        import android.util.AttributeSet;
        import android.widget.ImageView;

/**
 * Allows to draw rectangle on ImageView.
 *
 * @author Maciej Nux Jaros
 */
public class DrawImageView extends ImageView {
    private Paint currentPaint;
    public boolean drawLine = false;
    public float y;
    public float x;
    public float x2;
    public float y2;

    public DrawImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        currentPaint = new Paint();
        currentPaint.setDither(true);
        currentPaint.setColor(0xFF00CC00);  // alpha.r.g.b
        currentPaint.setStyle(Paint.Style.STROKE);
        currentPaint.setStrokeJoin(Paint.Join.ROUND);
        currentPaint.setStrokeCap(Paint.Cap.ROUND);
        currentPaint.setStrokeWidth(20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (drawLine)
        {
            Path p = new Path();
            p.moveTo(x, y);
            p.lineTo(x2, y2);
            canvas.drawPath(p, currentPaint);

        }
    }
}