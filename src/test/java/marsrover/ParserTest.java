package marsrover;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ParserTest {
    @Test
    public void canParseDimension() {
        Grid grid = Parser.parseDimension("4 8");
        assertNotNull(grid);
        assertEquals(4, grid.getRows());
        assertEquals(8, grid.getCols());
    }

    @Test
    public void canParseCommand() {
        Grid grid = mock(Grid.class);
        when(grid.initialiseRobot(anyInt(), anyInt(), any(Orientation.class))).thenReturn(1);
        Parser.parseStateAndCommand("(2, 4, N) FLR", grid);

        verify(grid, times(1)).initialiseRobot(eq(2), eq(4), eq(Orientation.N));

        ArgumentCaptor<List<Command>> commandCaptor = ArgumentCaptor.forClass(List.class);
        verify(grid, times(1)).ingestCommand(eq(1), commandCaptor.capture());
        List<Command> commands = commandCaptor.getValue();
        assertEquals(3, commands.size());
        assertEquals(Command.FORWARD, commands.get(0));
        assertEquals(Command.LEFT, commands.get(1));
        assertEquals(Command.RIGHT, commands.get(2));
    }
}
