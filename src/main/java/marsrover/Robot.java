package marsrover;

public class Robot {
    private int x;
    private int y;
    private Orientation orientation;
    private boolean lost;

    public Robot(int x, int y, Orientation orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.lost = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public boolean isLost() {
        return lost;
    }

    /**
     * It is the caller's responsibility to ensure that the robot can move
     */
    public void move() {
        switch (orientation) {
            case N -> y++;
            case E -> x++;
            case S -> y--;
            case W -> x--;
        }
    }

    public void turnLeft() {
        orientation = switch (orientation) {
            case N -> Orientation.W;
            case E -> Orientation.N;
            case S -> Orientation.E;
            case W -> Orientation.S;
        };
    }

    public void turnRight() {
        orientation = switch (orientation) {
            case N -> Orientation.E;
            case E -> Orientation.S;
            case S -> Orientation.W;
            case W -> Orientation.N;
        };
    }

    public void markLost() {
        lost = true;
    }

    @Override
    public String toString() {
        String state = "(" + x + ", " + y + ", " + orientation + ")";
        return lost ? state + " LOST" : state;
    }
}
