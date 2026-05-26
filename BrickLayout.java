import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BrickLayout {

    private ArrayList<Brick> bricks;
    private int[][] grid;
    private int nextBrick; // index of the next brick to drop

    public BrickLayout(String inputFile) {
        ArrayList<String> fileData = getFileData(inputFile);
        bricks = new ArrayList<Brick>(); // fixed: was re-declaring a local variable
        for (String line : fileData) {
            String[] points = line.split(",");
            int start = Integer.parseInt(points[0]);
            int end = Integer.parseInt(points[1]);
            Brick b = new Brick(start, end);
            bricks.add(b);
        }
        grid = new int[30][40];
        nextBrick = 0; // start at the first brick
    }

    public int[][] getGrid() {
        return grid;
    }

    public void dropOneBrick() {
        // If all bricks have already been dropped, do nothing
        if (nextBrick >= bricks.size()) {
            return;
        }

        // Get the next brick to drop
        Brick b = bricks.get(nextBrick);
        int start = b.getStart();
        int end   = b.getEnd();

        // Find the lowest row where the brick can land.
        // We scan from the bottom row (29) upward (toward row 0).
        // The brick lands on the first row (from the bottom) where
        // every column from start to end is still empty (== 0).
        int landingRow = -1;
        for (int r = 29; r >= 0; r--) {
            boolean rowClear = true;
            for (int c = start; c <= end; c++) {
                if (grid[r][c] == 1) {
                    rowClear = false;
                    break;
                }
            }
            if (rowClear) {
                landingRow = r;
                break; // found the lowest clear row — stop here
            }
        }

        // Place the brick in the grid if a valid row was found
        if (landingRow != -1) {
            for (int c = start; c <= end; c++) {
                grid[landingRow][c] = 1;
            }
        }

        // Advance to the next brick for the following click
        nextBrick++;
    }

    public ArrayList<String> getFileData(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }
        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine()) {
            fileData.add(s.nextLine());
        }
        return fileData;
    }
}
