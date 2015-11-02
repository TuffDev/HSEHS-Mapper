package com.example.adam.myfirstapp;

import android.graphics.Point;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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

import java.util.ArrayList;

public class MyActivity extends ActionBarActivity {

    public Context ctx;
    public ArrayList<Integer> startPoint = new ArrayList<>();
    public ArrayList<Integer> endPoint = new ArrayList<>();
    public mapPoint mP = new mapPoint();
    public boolean endPointSet = false;
    public boolean startPointSet = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        final TextView t=(TextView)findViewById(R.id.textView);

        mP.addLineArray(57, 57, 29, 353);
        mP.addLineArray(57, 312, 63, 63);
        mP.addPoint(56, 47, "A107");
        mP.addPoint(56, 56, "A106");
        mP.addPoint(56, 79, "A104");
        mP.addPoint(77, 62, "A105");
        mP.addPoint(77, 64, "A108");


        setTitle("HSE-Mapping Development Version");


        //************START SPINNER***************************************************
        Spinner startSpinner = (Spinner) findViewById(R.id.startSpinner);

        String[] startItems = new String[] { "A104", "A105", "A106", "A107", "A108" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, startItems);

        startSpinner.setAdapter(adapter);

        startSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String roomNum = (String) parent.getItemAtPosition(position);
                t.setText("Item Selected: " + roomNum);
                int roomIndex = mP.rooms.indexOf(roomNum);
                startPointSet = true;
                startPoint = mP.roomCoords.get(roomIndex);
                if (endPointSet) {
                    mapPath();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        //*********************************************************************************************

        //***********************END SPINNER***********************************************************

        Spinner endSpinner = (Spinner) findViewById(R.id.endSpinner);

        String[] endItems = new String[] { "A104", "A105", "A106", "A107", "A108" };

        ArrayAdapter<String> endAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, endItems);

        endSpinner.setAdapter(endAdapter);

        endSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String roomNum = (String) parent.getItemAtPosition(position);
                t.setText("Item Selected: " + roomNum);
                int roomIndex = mP.rooms.indexOf(roomNum);
                endPoint = mP.roomCoords.get(roomIndex);
                endPointSet = true;
                if (startPointSet) { //start Point is set
                    mapPath();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        //*******************************************************************************************

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

    public void mapPath() {
        aStar aS = new aStar();
        TextView t=(TextView)findViewById(R.id.textView);
        t.setText("mapPath() called");
        aS.startPoint = startPoint;
        aS.endPoint = endPoint;
        aS.findShortestPath();
        DrawImageView drawView = (DrawImageView) findViewById(R.id.view);
        int i = 0;
        while (i < aS.shortestPath.size()) {
            ArrayList<Integer> pntA = aS.shortestPath.get(i);
            ArrayList<Integer> pntB = aS.shortestPath.get(i++);
            drawView.x = pntA.get(0);
            drawView.y = pntA.get(1);
            drawView.x2 = pntB.get(0);
            drawView.y2 = pntB.get(1);
            drawView.invalidate();
            drawView.drawLine = true;
        }
    }
}
