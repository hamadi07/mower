package grid;

import mower.Mower;

public class Grid {

    private static final String EMPTY = "V";
    private String grid[][];

    public Grid(int width, int height) {
        grid = new String[width][height];
        for (int y = height - 1; y >= 0; y--)
            for (int x = 0; x < width; x++)
                grid[x][y] = EMPTY;
    }

    public void add(Mower mower) {
        grid[mower.getPosition().getX()][mower.getPosition().getY()] = mower.getOrientation().getCode();
    }

    public String[][] getGrid() {
        return grid;
    }
}
