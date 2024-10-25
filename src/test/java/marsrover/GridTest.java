package marsrover;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GridTest {
    @Test
    public void canInitialiseRobot() {
        Grid grid = new Grid(4, 8);
        int robotId = grid.initialiseRobot(2, 3, Orientation.N);
        Robot robot = grid.getRobotAt(2, 3);
        assertEquals(1, robotId);
        assertEquals(2, robot.getX());
        assertEquals(3, robot.getY());
        assertEquals(Orientation.N, robot.getOrientation());
    }

    @Test
    public void canIngestCommand() {
        Grid grid = new Grid(4, 8);

        int robotId = grid.initialiseRobot(2, 3, Orientation.N);
        grid.ingestCommand(robotId, List.of(Command.FORWARD, Command.LEFT, Command.FORWARD));

        Robot robot = grid.getRobotAt(1, 4);
        assertEquals(1, robotId);
        assertEquals(1, robot.getX());
        assertEquals(4, robot.getY());
        assertEquals(Orientation.W, robot.getOrientation());
        assertFalse(robot.isLost());

        grid.ingestCommand(robotId, List.of(Command.FORWARD, Command.FORWARD));

        assertEquals(0, robot.getX());
        assertEquals(4, robot.getY());
        assertEquals(Orientation.W, robot.getOrientation());
        assertTrue(robot.isLost());
    }
}
