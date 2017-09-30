package perso.bpagnier.mowitnow.model;

public class Location {

	private int x;
	private int y;

	private Direction direction;

	public Location(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Location turnRight() {
		Direction newDirection;
		switch (direction) {
		case NORTH:
			newDirection = Direction.EAST;
			break;
		case EAST:
			newDirection = Direction.SOUTH;
			break;
		case SOUTH:
			newDirection = Direction.WEST;
			break;
		case WEST:
			newDirection = Direction.NORTH;
			break;
		default:
			throw new IllegalStateException();
		}
		return new Location(x, y, newDirection);
	}

	public Location turnLeft() {
		Direction newDirection;
		switch (direction) {
		case NORTH:
			newDirection = Direction.WEST;
			break;
		case EAST:
			newDirection = Direction.NORTH;
			break;
		case SOUTH:
			newDirection = Direction.EAST;
			break;
		case WEST:
			newDirection = Direction.SOUTH;
			break;
		default:
			throw new IllegalStateException();
		}
		return new Location(x, y, newDirection);
	}

	public Location moveForward() {
		switch (direction) {
		case NORTH:
			return new Location(x, y + 1, direction);
		case EAST:
			return new Location(x + 1, y, direction);
		case SOUTH:
			return new Location(x, y - 1, direction);
		case WEST:
			return new Location(x - 1, y, direction);
		default:
			throw new IllegalStateException();
		}
	}

	public String toString() {
		return "[" + x + ";" + y + ";" + direction.getName() + "]";
	}

}