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
    private SchoolMap map = new SchoolMap();
    private PathFinder finder = null;
    private Pathway path;
    private DrawImageView drawView = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        final TextView t=(TextView)findViewById(R.id.textView);

        mP.addLineArray(57, 57, 29, 353);
        mP.addLineArray(57, 312, 63, 63);
        mP.addPoint(56, 47, "A107");
        mP.addPoint(56, 56, "A106");
        mP.addPoint(56, 79, "A104");
        mP.addPoint(77, 65, "A105");
        mP.addPoint(77, 63, "A108");


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
                    /*
                    ArrayList<Integer> pnt = new ArrayList<>();
                    pnt.add(56);
                    pnt.add(47);
                    ArrayList<ArrayList<Integer>> nbors = new ArrayList<>();
                    nbors = mP.getNeighbors(pnt);
                    t.setText("Nbors x" + nbors.get(0).get(0) + "  y:" + nbors.get(0).get(1)); */

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

        String[] endItems = new String[] { "A105" , "A104", "A106", "A107", "A108" };

        ArrayAdapter<String> endAdapter = new ArrayAdapter<>(this,
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
        Log.d("LOG", "Map path called");
        if (drawView == null) {
            drawView = (DrawImageView) findViewById(R.id.view);
        }
        Log.d("LOG", "0");
        if (finder == null) {
            finder = new AStarPathFinder(map, 5000, false);
        }
        Log.d("LOG", "1");
        path = null;
        map.clearVisited();
        Log.d("LOG", "2");
        path = finder.findPath(new UnitMover(0), startPoint.get(0), startPoint.get(1), endPoint.get(0), endPoint.get(1));
        Log.d("LOG", "3");

        if (path != null) {
            for (int i=0;i<path.getLength();i++) {
                if (i + 1 < path.getLength()) {
                    drawView.x = path.getStep(i).getX();
                    drawView.y = path.getStep(i).getY();
                    drawView.x2 = path.getStep(i + 1).getX();
                    drawView.y2 = path.getStep(i + 1).getY();
                    drawView.invalidate();
                    drawView.drawLine = true;
                    Log.d("LOG", "x: " + path.getStep(i).getX() + " y: " + path.getStep(i).getY());
                }
             }
        }
    }
}
