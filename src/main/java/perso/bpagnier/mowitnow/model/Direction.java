package perso.bpagnier.mowitnow.model;

public enum Direction {

	NORTH("N"), //
	SOUTH("S"), //
	EAST("E"), //
	WEST("W");

	private String name;

	private Direction(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
