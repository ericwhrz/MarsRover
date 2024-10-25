package marsrover;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RobotTest {
    @Test
    public void robotIsNotLostOnInitialisation() {
        Robot robot = new Robot(1, 1, Orientation.W);
        assertFalse(robot.isLost());
    }

    @Test
    public void robotIsLostWhenMarkedLost() {
        Robot robot = new Robot(1, 1, Orientation.W);
        robot.markLost();
        assertTrue(robot.isLost());
    }

    @Test
    public void robotCanMove() {
        Robot robot = new Robot(2, 4, Orientation.N);
        robot.move();

        assertEquals(2, robot.getX());
        assertEquals(5, robot.getY());
        assertEquals(Orientation.N, robot.getOrientation());
    }

    @Test
    public void robotCanTurn() {
        Robot robot = new Robot(2, 4, Orientation.N);

        robot.turnLeft();
        assertEquals(2, robot.getX());
        assertEquals(4, robot.getY());
        assertEquals(Orientation.W, robot.getOrientation());

        robot.turnRight();
        robot.turnRight();
        assertEquals(Orientation.E, robot.getOrientation());
    }

    @Test
    public void testToString() {
        Robot robot = new Robot(2, 4, Orientation.N);
        assertEquals("(2, 4, N)", robot.toString());

        robot.markLost();
        assertEquals("(2, 4, N) LOST", robot.toString());
    }
}
