package MarsRovers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import MarsRovers.Rover.Command;

public class Map {
	private int mapWidth;
	private int mapHeight;
	// This is a map of booleans to show what is occupied we use this as its easier
	// to keep it updated and check it rather than iterating through all rovers
	// each time we want to check if a space is occupied
	private boolean[][] map;
	private ArrayList<Rover> rovers;

	public static void main(String[] args) {
		new Map(args[0]);
	}

	public Map(String filePath) {
		rovers = new ArrayList<Rover>();
		if (readFile(filePath)) {
			excecuteMovement();
			printFinalPositions();
		}

	}

	private void printFinalPositions() {
		for (Rover r : rovers) {
			System.out.printf("%d %d %s\n", r.x, r.y, r.facing);
		}

	}

	public boolean readFile(String filePath) {
		Scanner scanner;
		try {
			scanner = new Scanner(new File(filePath));
		} catch (FileNotFoundException e1) {
			System.out.printf("Could not find file: %s", filePath);
			return false;
		}
		String line = scanner.nextLine();
		String[] md = line.split(" ");
		// Read in the initial line with map dimensions
		try {
			mapWidth = Integer.parseInt(md[0]);
			mapHeight = Integer.parseInt(md[1]);
			map = new boolean[mapWidth][mapHeight];
		} catch (Exception e) {
			System.out.printf("Error with input file on line: %s", line);
			return false;
		}

		while (scanner.hasNextLine()) {

			// Read in the line with the initial positioning of the rover
			line = scanner.nextLine();
			Rover r;
			try {
				String[] ln = line.trim().split(" ");
				int x = Integer.parseInt(ln[0]);
				int y = Integer.parseInt(ln[1]);
				String f = ln[2];
				r = new Rover(x, y, f);
				rovers.add(r);
				map[x][y] = true;
			} catch (Exception e) {
				System.out.printf("Error with input file on line: %s", line);
				return false;
			}
			// Now read in the instructions for that rover
			try {
				line = scanner.nextLine().trim();
				r.addCommandList(line);

			} catch (Exception e) {
				System.out.printf("Error with input file on line: %s", line);
				return false;
			}

		}
		return true;
	}

	/**
	 * For each rover execute all of it commands.
	 */
	public void excecuteMovement() {
		for (Rover r : rovers) {
			for (Command c : r.commands) {
				switch (c) {
				case L:
					r.turnLeft();
				case R:
					r.turnRight();
				case M:
					move(r);
				}
			}
		}
	}

	private void move(Rover r) {
		int x = r.x;
		int y = r.y;

		// first update the coordinates of the rover based on it's facing
		switch (r.facing) {
		case NORTH:
			y++;
		case SOUTH:
			y--;
		case EAST:
			x++;
		case WEST:
			x--;
		}

		// Now we validate the new coordinates
		// We can't move out of bounds so return if our new coordinates are out of
		// bounds.
		if (x >= mapWidth || x < 0 || y >= mapHeight || y < 0)
			return;
		// Now check if our destination is occupied
		if (map[x][y])
			return;

		// Now we know the move is valid so update the rover and the map;
		map[x][y] = true;
		map[r.x][r.y] = false;
		r.x = x;
		r.y = y;

	}

}
