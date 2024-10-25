package marsrover;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            Grid grid = Parser.parseDimension(scanner.nextLine());
            if (grid == null) {
                System.out.println("Could not parse dimensions to initialise grid");
                return;
            }

            while (scanner.hasNextLine()) {
                Parser.parseStateAndCommand(scanner.nextLine(), grid);
            }

            grid.printState();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found");
        }
    }
}