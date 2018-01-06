package actions;

import coordinates.Coordinates;
import mower.Mower;
import mower.MowerOrientationEnum;
import util.ConsoleOverride;

import static mower.MowerOrientationEnum.*;

public class MowerAction {

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

    public void movingForward(Mower mower) {
        try {
            switch (mower.getOrientation()) {
                case NORTH:
                    mower.getPosition().setY(mower.getPosition().getY() + 1);
                    break;
                case EAST:
                    mower.getPosition().setX(mower.getPosition().getX() + 1);
                    break;
                case WEST:
                    mower.getPosition().setX(mower.getPosition().getX() - 1);
                    break;
                case SOUTH:
                    mower.getPosition().setY(mower.getPosition().getY() - 1);
                    break;
            }
        } catch (NullPointerException e) {
            console.print("Invalid Mower");
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
