package actions;

import coordinates.Coordinates;
import grid.Grid;
import mower.Mower;
import mower.MowerOrientationEnum;
import util.ConsoleOverride;

import static mower.MowerOrientationEnum.*;

public class MowerAction {

    private static final String EMPTY = "V";
    private ConsoleOverride console;

    public MowerOrientationEnum turnLeft(MowerOrientationEnum orientation) {
        try {
            Coordinates newCoordinates = getNewCoordinatesToTurnLeft(orientation.getX(), orientation.getY());
            return getMowerOrientationFromCoordinates(newCoordinates.getX(), newCoordinates.getY());
        } catch (NullPointerException e) {
            console.print("Invalid Orientation");
            return null;
        }
    }

    public MowerOrientationEnum turnRight(MowerOrientationEnum orientation) {
        try {
            Coordinates newCoordinates = getNewCoordinatesToTurnRight(orientation.getX(), orientation.getY());
            return getMowerOrientationFromCoordinates(newCoordinates.getX(), newCoordinates.getY());
        } catch (NullPointerException e) {
            console.print("Invalid Orientation");
            return null;
        }
    }

    public void movingForward(Mower mower, Grid grid) {
        try {

            Coordinates mowerCurrentCoordinates = mower.getPosition();

            switch (mower.getOrientation()) {
                case NORTH:
                    updateMowerInformation(mower, grid, mowerCurrentCoordinates.getX(), mowerCurrentCoordinates.getY() + 1);
                    break;
                case EAST:
                    updateMowerInformation(mower, grid, mowerCurrentCoordinates.getX() + 1, mowerCurrentCoordinates.getY());
                    break;
                case WEST:
                    updateMowerInformation(mower, grid, mowerCurrentCoordinates.getX() - 1, mowerCurrentCoordinates.getY());
                    break;
                case SOUTH:
                    updateMowerInformation(mower, grid, mowerCurrentCoordinates.getX(), mowerCurrentCoordinates.getY() - 1);
                    break;
            }
        } catch (NullPointerException e) {
            console.print("Invalid Mower");
        }
    }

    private void updateMowerInformation(Mower mower, Grid grid, int x, int y) {
        Coordinates newCoordinates = new Coordinates(x, y);
        String newCell = getNewCell(grid, newCoordinates);

        if (newCell != null && newCell.equals(EMPTY)) {
            mower.setPosition(newCoordinates);
        }
    }

    private String getNewCell(Grid grid, Coordinates newCoordinates) {
        try {
            return grid.getGrid()[newCoordinates.getX()][newCoordinates.getY()];
        } catch (ArrayIndexOutOfBoundsException e) {
            console.print("The position [" + newCoordinates.getX() + "][" + newCoordinates.getY() + "] is out of bounds");
            return null;
        }
    }

    private MowerOrientationEnum getMowerOrientationFromCoordinates(int x, int y) {
        switch (x) {
            case 0: {
                switch (y) {
                    case 1:
                        return NORTH;
                    case -1:
                        return SOUTH;
                }
            }
            case 1: {
                switch (y) {
                    case 0:
                        return EAST;
                }
            }

            case -1: {
                switch (y) {
                    case 0:
                        return WEST;
                }
            }
        }
        return null;
    }
}
