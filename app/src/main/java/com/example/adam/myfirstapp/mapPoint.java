package com.example.adam.myfirstapp;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by root on 10/25/15.
 */
public class mapPoint {
    // make classes to find neighbors

    public ArrayList<ArrayList<Integer>> walkableCoords = new ArrayList<>();
    public ArrayList<String> rooms = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> roomCoords = new ArrayList<>();


    public void addPoint(int x, int y, String room) {
        ArrayList<Integer> pnt = new ArrayList<>();
        pnt.add(x);
        pnt.add(y);
        walkableCoords.add(pnt);
        rooms.add(room);
        roomCoords.add(pnt);
        pnt.clear();
    }

    public ArrayList<ArrayList<Integer>> getNeighbors(ArrayList<Integer> node) {
        int x = node.get(0);
        int y = node.get(1);
        ArrayList<Integer> pnt = new ArrayList<>();
        ArrayList<ArrayList<Integer>> nbors = new ArrayList<>();
        pnt.add(x + 1);
        pnt.add(y);
        if (walkableCoords.contains(pnt)) {
            nbors.add(pnt);
        }
        pnt.clear();
        pnt.add(x - 1);
        pnt.add(y);
        if (walkableCoords.contains(pnt)) {
            nbors.add(pnt);
        }
        pnt.clear();
        pnt.add(x);
        pnt.add(y + 1);
        if (walkableCoords.contains(pnt)) {
            nbors.add(pnt);
        }
        pnt.clear();
        pnt.add(x);
        pnt.add(y -1);
        if (walkableCoords.contains(pnt)) {
            nbors.add(pnt);
        }
            return nbors;
    }

    public void addLineArray(int x1, int x2, int y1, int y2) { // adds every point on a line to a list
        if (x1 == x2) {
            int i = 0;

            if (y1 > y2) {
                while (i < Math.abs(y1 - y2)) {
                    ArrayList<Integer> pointArray = new ArrayList<>();
                    pointArray.add(x1);
                    pointArray.add(y1 - i);
                    walkableCoords.add(pointArray);
                    pointArray.clear();
                    i++;
                }

            }

            else if (y2 > y1) {
                while (i < Math.abs(y1 - y2)) {
                    ArrayList<Integer> pointArray = new ArrayList<>();
                    pointArray.add(x1);
                    pointArray.add(y2 - i);
                    walkableCoords.add(pointArray);
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
                    walkableCoords.add(pointArray);
                    pointArray.clear();
                    i++;
                }

            }

            else if (y2 > y1) {
                while (i < Math.abs(y1 - y2)) {
                    ArrayList<Integer> pointArray = new ArrayList<>();
                    pointArray.add(x1 - i);
                    pointArray.add(y1);
                    walkableCoords.add(pointArray);
                    pointArray.clear();
                    i++;
                }
            }
        }
    }
}
