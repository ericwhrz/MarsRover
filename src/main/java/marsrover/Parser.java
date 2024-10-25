package marsrover;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final Pattern ROBOT_PATTERN = Pattern.compile("\\((\\d+),(\\d+),([NESW])\\)([A-Z]+)");

    public static Grid parseDimension(String dimensionStr) {
        String[] dimensions = dimensionStr.trim().split("\\s+");

        if (dimensions.length != 2) {
            System.out.println("Must provide exactly two dimensions");
            return null;
        }

        try {
            int rows = Integer.parseInt(dimensions[0]);
            int cols = Integer.parseInt(dimensions[1]);
            return (rows > 0 && cols > 0) ? new Grid(rows, cols) : null;
        } catch (NumberFormatException e) {
            System.out.println("Dimensions must be positive integers");
            return null;
        }
    }

    public static void parseStateAndCommand(String str, Grid grid) {
        Matcher matcher = ROBOT_PATTERN.matcher(str.replaceAll("\\s+", ""));
        if (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            Orientation orientation = Orientation.valueOf(matcher.group(3));
            int robotId = grid.initialiseRobot(x, y, orientation);

            String commandStr = matcher.group(4);
            List<Command> commands = new ArrayList<>();
            for (int i = 0; i < commandStr.length(); i++) {
                commands.add(Command.fromChar(commandStr.charAt(i)));
            }
            grid.ingestCommand(robotId, commands);
        } else {
            throw new IllegalArgumentException("Robot string is in a wrong format");
        }
    }
}
