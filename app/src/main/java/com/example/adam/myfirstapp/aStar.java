package com.example.adam.myfirstapp;

import java.util.ArrayList;

/**
 * Created by root on 10/27/15.
 */
public class aStar {
    public ArrayList<ArrayList<Integer>> shortestPath = new ArrayList<>();
    public ArrayList<Integer> startPoint = new ArrayList<>();
    public ArrayList<Integer> endPoint = new ArrayList<>();
    public ArrayList<ArrayList<ArrayList<Integer>>> parentList = new ArrayList<>();


    public void findShortestPath() {
        ArrayList<ArrayList<Integer>> openList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> closedList = new ArrayList<>();
        boolean pointFound = false;
        int i = 0;

        startPoint.add(0); // add G value to start point
        openList.add(startPoint);
        while (!openList.isEmpty() && pointFound == false) {
            i++;
            ArrayList<Integer> lowest = new ArrayList<>();
            for (ArrayList<Integer> pnt : openList) {
                if (lowest.isEmpty()) {
                    lowest = pnt;
                }

                else if (getFValue(pnt) < getFValue(lowest)) {
                    lowest = pnt;
                }
            }

            ArrayList<Integer> currentNode = lowest;
            closedList.add(currentNode);
            openList.remove(currentNode);

            for (ArrayList<Integer> pnt : closedList) {
                ArrayList<Integer> point = new ArrayList<>();
                point.add(pnt.get(0));
                point.add(pnt.get(1));
                if (point == endPoint) {
                    reconstructPathCoords(pnt);
                    pointFound = true;
                }
            }

            mapPoint mp = new mapPoint();
            ArrayList<ArrayList<Integer>> nbors = mp.getNeighbors(currentNode);

            for (ArrayList<Integer> pnt : nbors) {
                pnt.add(i);
                int tentativeG = getGVal(currentNode);
                if (closedList.contains(pnt) && tentativeG >= getGVal(pnt)) {
                    break;
                }

                if (!openList.contains(pnt) || tentativeG < getGVal(pnt)) {
                    setParent(pnt, currentNode);
                    openList.add(pnt);
                }
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
