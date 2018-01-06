package mower;

import coordinates.Coordinates;

public enum MowerOrientationEnum {

    NORTH("N", 0, 1),
    EAST("E", 1, 0),
    WEST("W", -1, 0),
    SOUTH("S", 0, -1);

    String code;
    int x;
    int y;

    MowerOrientationEnum(String code, int x, int y) {
        this.code = code;
        this.x = x;
        this.y = y;
    }

    public String getCode() {
        return code;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Coordinates getNewCoordinatesToTurnLeft(int a, int b) {
        return new Coordinates(-b, a);
    }

    public static Coordinates getNewCoordinatesToTurnRight(int a, int b) {
        return new Coordinates(b, -a);
    }
}
