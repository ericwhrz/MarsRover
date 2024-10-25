package marsrover;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grid {
    // 2-D array containing the ids of the robots
    // A default value of zero entails there is no robot at a cell
    private final int[][] grid;

    // Map of robot id to the actual robot object
    private final Map<Integer, Robot> robots;

    private final int maxX;
    private final int maxY;

    public Grid(int rows, int cols) {
        // Initialising the array like this so we can access
        // elements with the more conventional Cartesian coordinates grid[x][y].
        // Adding one as zero is also included
        this.grid = new int[cols+1][rows+1];
        this.robots = new HashMap<>();
        this.maxX = cols;
        this.maxY = rows;
    }

    public int initialiseRobot(int x, int y, Orientation orientation) {
        if (x < 0 || x > maxX || y < 0 || y > maxY) {
            System.out.println("Robot needs to be placed within the grid boundaries");
            return -1;
        }
        if (grid[x][y] != 0) {
            System.out.println("A robot already exists at given coordinates");
            return -1;
        }

        Robot robot = new Robot(x, y, orientation);
        int robotId = robots.size() + 1;
        grid[x][y] = robotId;
        robots.put(robotId, robot);
        return robotId;
    }

    public void ingestCommand(int robotId, List<Command> commands) {
        if (!robots.containsKey(robotId)) {
            System.out.println("Robot does not exist, cannot execute commands");
            return;
        }

        Robot robot = robots.get(robotId);
        for (Command command : commands) {
            if (robot.isLost()) {
                break;
            }

            switch (command) {
                case FORWARD -> {
                    if (canMove(robot)) {
                        grid[robot.getX()][robot.getY()] = 0;
                        robot.move();
                        grid[robot.getX()][robot.getY()] = robotId;
                    } else {
                        robot.markLost();
                    }
                }
                case LEFT -> robot.turnLeft();
                case RIGHT -> robot.turnRight();
                default -> throw new IllegalArgumentException("Command not supported");
            }
        }
    }

    public void printState() {
        robots.values().forEach(System.out::println);
    }

    private boolean canMove(Robot robot) {
        int destX = robot.getX();
        int destY = robot.getY();
        switch (robot.getOrientation()) {
            case N -> destY++;
            case E -> destX++;
            case S -> destY--;
            case W -> destX--;
        }

        return 0 <= destX && destX <= maxX
                && 0 <= destY && destY <= maxY
                && grid[destX][destY] == 0;
    }

    public Robot getRobotAt(int x, int y) {
        if (grid[x][y] == 0) {
            return null;
        }

        return robots.get(grid[x][y]);
    }

    public int getRows() {
        return maxY;
    }

    public int getCols() {
        return maxX;
    }
}
