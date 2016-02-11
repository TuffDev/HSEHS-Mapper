package com.example.adam.myfirstapp;

/**
 * The data map from our example game. This holds the state and context of each tile
 * on the map. It also implements the interface required by the path finder. It's implementation
 * of the path finder related methods add specific handling for the types of units
 * and terrain in the example game.
 *
 * @author Kevin Glass
 */
public class SchoolMap implements TileBasedMap {
    /** The map width in tiles */
    public static final int WIDTH = 746;
    /** The map height in tiles */
    public static final int HEIGHT = 964;

    /** Indicate hall at a given location */
    public static final int HALL = 0;
    /** Indicate rooms at given location */
    public static final int ROOM = 1;
    /** Indicate mover at given location */
    public static final int MOVER = 0;


    /** The terrain settings for each tile in the map */
    private int[][] terrain = new int[WIDTH][HEIGHT];
    /** The unit in each tile of the map */
    private int[][] units = new int[WIDTH][HEIGHT];
    /** Indicator if a given tile has been visited during the search */
    private boolean[][] visited = new boolean[WIDTH][HEIGHT];

    /**
     * Create a new test map with some default configuration
     */
    public SchoolMap() {

        //initialize whole map as rooms
        fillArea(0,0,WIDTH,HEIGHT,ROOM);

        fillArea(57, 30, 1, 370, HALL); //A1+B1
        fillArea(55, 64, 256, 1, HALL); //A2
        addPoint(56, 47, HALL); //A107
        addPoint(56, 56, HALL); //A106
        addPoint(56, 79, HALL); //A104
        addPoint(77, 65, HALL); //A105
        addPoint(77, 63, HALL); //A108

    }


    /**
     * Fill an area with a given terrain type, top right corner, down
     *
     * @param x The x coordinate to start filling at
     * @param y The y coordinate to start filling at
     * @param width The width of the area to fill
     * @param height The height of the area to fill
     * @param type The terrain type to fill with
     */
    private void fillArea(int x, int y, int width, int height, int type) {
        for (int xp=x;xp<x+width;xp++) {
            for (int yp=y;yp<y+height;yp++) {
                terrain[xp][yp] = type;
            }
        }
    }

    /**
     * Add a walkable point to the map
     *
     * @param x The x coordinate of the point to add
     * @param y The y coordinate of the point to add
     * @param type the terrain type of the point
     */
    private void addPoint(int x, int y, int type) {
        terrain[x][y] = type;
    }

    /**
     * Clear the array marking which tiles have been visted by the path
     * finder.
     */
    public void clearVisited() {
        for (int y=0;y<getHeightInTiles();y++) {
            for (int x=0;x<getWidthInTiles();x++) {
                    visited[x][y] = false;
            }
        }
    }

    /**
     * @see TileBasedMap#pathFinderVisited(int, int)
     */
    public boolean visited(int x, int y) {
        return visited[x][y];
    }

    /**
     * Get the terrain at a given location
     *
     * @param x The x coordinate of the terrain tile to retrieve
     * @param y The y coordinate of the terrain tile to retrieve
     * @return The terrain tile at the given location
     */
    public int getTerrain(int x, int y) {
        return terrain[x][y];
    }

    /**
     * Get the unit at a given location
     *
     * @param x The x coordinate of the tile to check for a unit
     * @param y The y coordinate of the tile to check for a unit
     * @return The ID of the unit at the given location or 0 if there is no unit
     */
    public int getUnit(int x, int y) {
        return 0; // unit is always 0
    }

    /**
     * Set the unit at the given location
     *
     * @param x The x coordinate of the location where the unit should be set
     * @param y The y coordinate of the location where the unit should be set
     * @param unit The ID of the unit to be placed on the map, or 0 to clear the unit at the
     * given location
     */
    public void setUnit(int x, int y, int unit) {
        units[x][y] = unit;
    }

    /**
     * @see TileBasedMap#blocked(Mover, int, int)
     */
    public boolean blocked(Mover mover, int x, int y) {

        // if the terrain does not have a value of 0, return true to block
        //return terrain[x][y] != MOVER;
        if (terrain[x][y] == 0) {
            return false;
        }
        else return true;

        /*

        int unit = ((UnitMover) mover).getType();

        // planes can move anywhere

        if (unit == PLANE) {
            return false;
        }
        // tanks can only move across grass

        if (unit == TANK) {
            return terrain[x][y] != GRASS;
        }
        // boats can only move across water

        if (unit == BOAT) {
            return terrain[x][y] != WATER;
        }

        // unknown unit so everything blocks

        return true;
        */
        // if it is a hall, then it's not blocked
    }

    /**
     * @see TileBasedMap#getCost(Mover, int, int, int, int)
     */
    public float getCost(Mover mover, int sx, int sy, int tx, int ty) {
        // cost is uniform for now
        return 1;
    }

    /**
     * @see TileBasedMap#getHeightInTiles()
     */
    public int getHeightInTiles() {
        return HEIGHT;
    }

    /**
     * @see TileBasedMap#getWidthInTiles()
     */
    public int getWidthInTiles() {
        return WIDTH;
    }

    /**
     * @see TileBasedMap#pathFinderVisited(int, int)
     */
    public void pathFinderVisited(int x, int y) {
        visited[x][y] = true;
    }


}
