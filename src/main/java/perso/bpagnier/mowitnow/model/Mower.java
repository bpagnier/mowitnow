package perso.bpagnier.mowitnow.model;

import java.util.Queue;

import org.apache.commons.lang.Validate;

public class Mower {

	private Location location;
	private Queue<Instruction> instructions;
	private String name;

	public Mower(String name, Location location, Queue<Instruction> instructions) {
		Validate.notNull(name, "name cannot be null.");
		Validate.notNull(location, "location cannot be null.");
		Validate.notNull(instructions, "instructions cannot be null, pass empty collection instead.");
		
		this.name = name;
		this.location = location;
		this.instructions = instructions;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Queue<Instruction> getInstructions() {
		return instructions;
	}

	public void setInstructions(Queue<Instruction> instructions) {
		this.instructions = instructions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name + " : " + location + ", [" + instructions.size() + "] instructions remaining.";
	}

}