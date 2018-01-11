package com.actions;

import com.component.coordinates.Coordinates;
import com.component.grid.Grid;
import com.component.mower.Mower;
import com.component.mower.MowerOrientationEnum;
import com.util.ConsoleOverride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.component.mower.MowerOrientationEnum.*;

@Service
public class MowerActionService {

    @Autowired
    private ConsoleOverride console;
    private static final String EMPTY = "V";

    public Mower createMower(String[] mowerStartPosition) {
        MowerOrientationEnum startOrientation = getOrientationByCode(mowerStartPosition[2]);
        Coordinates startCoordinates = getCoordinateFromString(mowerStartPosition);
        return new Mower(startOrientation, startCoordinates);
    }

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

    public void movingMower(Grid grid, Mower mower, String instructions) {
        int instructionEvent = 0;
        while (instructionEvent <= instructions.length() - 1) {

            switch (instructions.charAt(instructionEvent)) {
                case 'G': {
                    mower.setOrientation(turnLeft(mower.getOrientation()));
                    break;
                }
                case 'D': {
                    mower.setOrientation(turnRight(mower.getOrientation()));
                    break;
                }
                case 'A': {
                    movingForward(mower, grid);
                    break;
                }
            }
            instructionEvent++;
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

    private Coordinates getCoordinateFromString(String[] coodinates) {
        int x = Integer.valueOf(coodinates[0]);
        int y = Integer.valueOf(coodinates[1]);
        return new Coordinates(x, y);
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
