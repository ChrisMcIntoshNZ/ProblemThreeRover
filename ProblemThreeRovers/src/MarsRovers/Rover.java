package MarsRovers;

import java.util.ArrayList;

public class Rover {
	public Direction facing;
	public int x;
	public int y;
	public ArrayList<Command> commands;

	public enum Direction {
		NORTH {
			@Override
			public String toString() {
				return "N";
			}
		},
		EAST {
			@Override
			public String toString() {
				return "E";
			}
		},
		SOUTH {
			@Override
			public String toString() {
				return "S";
			}
		},
		WEST {
			@Override
			public String toString() {
				return "W";
			}
		};

	}

	public enum Command {
		L, R, M
	}

	public Rover(int x, int y, String f) {
		this.x = x;
		this.y = y;
		this.commands = new ArrayList<Command>();
		switch (f) {
		case "N":
			this.facing = Direction.NORTH;
			break;
		case "S":
			this.facing = Direction.SOUTH;
			break;
		case "E":
			this.facing = Direction.EAST;
			break;
		case "W":
			this.facing = Direction.WEST;
			break;
		default:
			throw new IllegalArgumentException();
		}
	}

	public void turnLeft() {
		switch (facing) {
		case NORTH:
			facing = Direction.EAST;
			break;
		case EAST:
			facing = Direction.SOUTH;
			break;
		case SOUTH:
			facing = Direction.WEST;
			break;
		case WEST:
			facing = Direction.NORTH;
			break;
		}
	}

	public void turnRight() {
		switch (facing) {
		case NORTH:
			facing = Direction.WEST;
			break;
		case EAST:
			facing = Direction.NORTH;
			break;
		case SOUTH:
			facing = Direction.EAST;
			break;
		case WEST:
			facing = Direction.SOUTH;
			break;
		}
	}

	public void addCommandList(String line) throws IllegalAccessException {
		char[] cmds = line.toCharArray();
		for (int i = 0; i < cmds.length; i++) {
			switch (cmds[i]) {
			case 'L':
				commands.add(Command.L);
				break;
			case 'R':
				commands.add(Command.R);
				break;
			case 'M':
				commands.add(Command.M);
				break;
			default:
				throw new IllegalAccessException();
			}
		}
	}

}
