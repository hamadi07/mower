package mower;

import coordinates.Coordinates;

public class Mower {
    private MowerOrientationEnum orientation;
    private Coordinates position;

    public Mower(MowerOrientationEnum orientation, Coordinates position) {
        this.orientation = orientation;
        this.position = position;
    }

    public MowerOrientationEnum getOrientation() {
        return orientation;
    }

    public void setOrientation(MowerOrientationEnum orientation) {
        this.orientation = orientation;
    }

    public Coordinates getPosition() {
        return position;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }
}
