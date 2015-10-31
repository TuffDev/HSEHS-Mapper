package com.example.adam.myfirstapp;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by root on 10/25/15.
 */
public class mapPoint {
    // make classes to find neighbors

    public int[] startPoint = new int[2];
    public int[] endPoint = new int[2];
    public ArrayList<ArrayList<Integer>> walkableCoords = new ArrayList<>();
    
    public void addLineToWalkable(ArrayList<ArrayList<Integer>> coordPath){
        for (ArrayList<Integer> element : coordPath) {
            walkableCoords.add(element);
        }
    }

    public void addPoint(int x, int y) {
        ArrayList<Integer> pnt = new ArrayList<>();
        pnt.add(x);
        pnt.add(y);
        walkableCoords.add(pnt);
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
}
