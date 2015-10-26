package com.example.adam.myfirstapp;

import android.graphics.Point;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.app.Application;
import android.app.Activity;
import android.content.res.Resources;

public class MyActivity extends ActionBarActivity {

    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        DrawImageView drawView = (DrawImageView) findViewById(R.id.view);

        drawView.x = 20;
        drawView.y = 20;
        drawView.x2 = 200;
        drawView.y2 = 200;
        drawView.invalidate();
        drawView.drawLine = true;

        setTitle("HSE-Mapping Development Version");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static Bitmap getMutableBitmap(Resources resources,int resId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        return BitmapFactory.decodeResource(resources, resId, options);
    }


    public void onImageClick() {
        TextView t=(TextView)findViewById(R.id.textView);
        Bitmap myBitmap = getMutableBitmap(ctx.getResources(), R.drawable.buildingdownstairsnob);
        t.setText("" + myBitmap.getWidth());

    }
}
