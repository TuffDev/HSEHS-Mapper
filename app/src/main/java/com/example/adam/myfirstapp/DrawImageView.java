package com.example.adam.myfirstapp;

/**
 * Created by root on 10/24/15.
 */
//package CustomWidgets;

        import android.content.Context;
        import android.graphics.Canvas;
        import android.graphics.Paint;
        import android.util.AttributeSet;
        import android.widget.ImageView;
        import android.util.Log;

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
    public Pathway pathway;

    public DrawImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        currentPaint = new Paint();
        currentPaint.setDither(true);
        currentPaint.setColor(0xFF00CC00);  // alpha.r.g.b
        currentPaint.setStyle(Paint.Style.FILL);
        currentPaint.setStrokeJoin(Paint.Join.BEVEL);
        currentPaint.setStrokeCap(Paint.Cap.SQUARE);
        currentPaint.setStrokeWidth(1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (drawLine) {
            if (pathway != null) {
                for (int i = 0; i < pathway.getLength(); i++) {

                        x = pathway.getStep(i).getX();
                        y = pathway.getStep(i).getY();
                        canvas.drawCircle(x - 11, y + 40, 3, currentPaint);
                        Log.d("LOG: ", "Dot drawn");

                }
            }
        }
    }

    public void setPath(Pathway path) {
        pathway = path;
    }
}