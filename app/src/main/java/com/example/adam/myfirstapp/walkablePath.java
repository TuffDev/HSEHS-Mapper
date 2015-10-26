package com.example.adam.myfirstapp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 10/24/15.
 */
public class walkablePath {

    public int x1 = 0;
    public int x2 = 0;
    public int y1 = 0;
    public int y2 = 0;
    public ArrayList<ArrayList<Integer>> coordList = new ArrayList<ArrayList<Integer>>();

    public void addLineArray() { // adds every point on a line to a list
        if (x1 == x2) {
            int i = 0;

            if (y1 > y2) {
                while (i < Math.abs(y1 - y2)) {
                    ArrayList<Integer> pointArray = new ArrayList<>();
                    pointArray.add(x1);
                    pointArray.add(y1 - i);
                    coordList.add(pointArray);
                    pointArray.clear();
                    i++;
                }

            }

            else if (y2 > y1) {
                while (i < Math.abs(y1 - y2)) {
                    ArrayList<Integer> pointArray = new ArrayList<>();
                    pointArray.add(x1);
                    pointArray.add(y2 - i);
                    coordList.add(pointArray);
                    pointArray.clear();
                    i++;
                }
            }

        }

        else if (y1 == y2) {
            int i = 0;

            if (x1 > x2) {
                while (i < Math.abs(y1 - y2)) {
                    ArrayList<Integer> pointArray = new ArrayList<>();
                    pointArray.add(x1 - i);
                    pointArray.add(y1);
                    coordList.add(pointArray);
                    pointArray.clear();
                    i++;
                }

            }

            else if (y2 > y1) {
                while (i < Math.abs(y1 - y2)) {
                    ArrayList<Integer> pointArray = new ArrayList<>();
                    pointArray.add(x1 - i);
                    pointArray.add(y1);
                    coordList.add(pointArray);
                    pointArray.clear();
                    i++;
                }
            }
        }
    }
}
