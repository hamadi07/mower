package actions;

import coordinates.Coordinates;
import mower.MowerOrientationEnum;

import static mower.MowerOrientationEnum.*;

public class MowerAction {

    public MowerOrientationEnum turnLeft(MowerOrientationEnum orientation) {
        try {
            Coordinates newCoordinates = getNewCoordinatesToTurnLeft(orientation.getX(), orientation.getY());
            return getMowerOrientationFromCoordinates(newCoordinates.getX(), newCoordinates.getY());
        } catch (Exception e) {
            System.out.println("Invalid Orientation");
            return null;
        }
    }


    public MowerOrientationEnum turnRight(MowerOrientationEnum orientation) {
        try {
            Coordinates newCoordinates = getNewCoordinatesToTurnRight(orientation.getX(), orientation.getY());
            return getMowerOrientationFromCoordinates(newCoordinates.getX(), newCoordinates.getY());
        } catch (Exception e) {
            System.out.println("Invalid Orientation");
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
