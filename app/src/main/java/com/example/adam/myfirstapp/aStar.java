package com.example.adam.myfirstapp;

import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by root on 10/27/15.
 */
public class aStar {
    public ArrayList<ArrayList<Integer>> shortestPath = new ArrayList<>();
    public ArrayList<Integer> startPoint = new ArrayList<>();
    public ArrayList<Integer> endPoint = new ArrayList<>();
    public ArrayList<ArrayList<ArrayList<Integer>>> parentList = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> nbors = new ArrayList<>();
    public int i;                  //debug
    public int g = 0;
    public ArrayList<ArrayList<Integer>> openList = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> closedList = new ArrayList<>();
    public ArrayList<Integer> debugPoint = new ArrayList<>();
    public int debugInt = -999;
    public int debugx = 0;
    public int debugy = 0;
    public mapPoint mP = new mapPoint();

    public void findShortestPath() {
        boolean pointFound = false;
        i = 0;

        startPoint.add(0); // add G value to start point
        openList.add(startPoint);
        while (!openList.isEmpty() /*&& !pointFound*/ && i < 30) {
            i++;
            ArrayList<Integer> lowest = new ArrayList<>();
            for (ArrayList<Integer> pnt : openList) {
                if (lowest.isEmpty()) {
                    lowest = pnt;
                    debugInt = 1;                   //DEBUG
                }

                else if (getFValue(pnt) < getFValue(lowest)) {
                    lowest = pnt;
                    debugInt = 2;                  //DEBUG
                }
            }

            ArrayList<Integer> currentNode = lowest;
            closedList.add(currentNode);
            openList.remove(currentNode);
            debugInt = 3;

            for (ArrayList<Integer> pnt : closedList) {
                ArrayList<Integer> point = new ArrayList<>();
                point.add(pnt.get(0));
                point.add(pnt.get(1));
                if (point == endPoint) {
                    reconstructPathCoords(pnt);
                    debugInt = 27;
                    pointFound = true;
                }
            }

            ArrayList<Integer> homePoint = new ArrayList<>();
            homePoint.add(currentNode.get(0));
            homePoint.add(currentNode.get(1));
            nbors = mP.getNeighbors(homePoint);

            for (ArrayList<Integer> pnt : nbors) {
                debugInt = 6;
                pnt.add(i);
                int tentativeG = getGVal(currentNode);
                if (closedList.contains(pnt) && tentativeG >= getGVal(pnt)) {
                    debugInt = 5;
                    //g++;
                    break;
                }

                if (!openList.contains(pnt) || tentativeG < getGVal(pnt)) {
                    setParent(pnt, currentNode);
                    openList.add(pnt);
                    //g++;
                    debugPoint = pnt;                   //DEBUG
                    debugInt = i;
                }
                else debugInt = i;                  //DEBUG
                g++;
            }
        }
    }

    public int getAValue(ArrayList<Integer> node) {
        int pntX = node.get(0);
        int pntY = node.get(1);
        int endX = endPoint.get(0);
        int endY = endPoint.get(1);

        int distX = Math.abs(pntX - endX);
        int distY = Math.abs(pntY - endY);
        int Aval = (distX + distY);

        return Aval;
    }

    public void setParent(ArrayList<Integer> node, ArrayList<Integer> parent) {
        ArrayList<ArrayList<Integer>> parentNodePair = new ArrayList<>();
        parentNodePair.add(node);
        parentNodePair.add(parent);
        parentList.add(parentNodePair);
    }

    public ArrayList<Integer> getParent(ArrayList<Integer> node) {
        ArrayList<Integer> result = new ArrayList<>();
        for (ArrayList<ArrayList<Integer>> parentPair : parentList) {
            if (parentPair.get(0) == node) {
                result = parentPair.get(1);
                break;
            }
        }
        return result;
    }

    public int getGVal(ArrayList<Integer> node) {
        int result = node.get(2);
        return result;
    }

    public int getFValue(ArrayList<Integer> node) {
        int fVal = (getAValue(node) + getGVal(node));
        return fVal;
    }

    public void reconstructPathCoords(ArrayList<Integer> goal) {
        boolean run = true;
        ArrayList<Integer> start = null;
        start.add(startPoint.get(0));
        start.add(startPoint.get(1));
        start.add(0);
        while (run) {
            if (shortestPath.isEmpty()) {
                shortestPath.add(goal);
            }

            else shortestPath.add(getParent(shortestPath.get((shortestPath.size()-1))));

            if (shortestPath.get((shortestPath.size() -1)) == start) {
                run = false;
            }
        }
    }
}
