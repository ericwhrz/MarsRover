package marsrover;

public enum Command {
    FORWARD,
    LEFT,
    RIGHT;

    public static Command fromChar(char c) {
        return switch (c) {
            case 'F' -> FORWARD;
            case 'L' -> LEFT;
            case 'R' -> RIGHT;
            default -> throw new IllegalArgumentException("Command not supported");
        };
    }
}
