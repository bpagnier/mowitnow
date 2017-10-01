package perso.bpagnier.mowitnow.model;

import java.util.Queue;

import org.apache.commons.lang.Validate;

public class Mower {

	private Location location;
	private Queue<Instruction> instructions;

	public Mower(Location location, Queue<Instruction> instructions) {
		Validate.notNull(location, "location cannot be null.");
		Validate.notNull(instructions, "instructions cannot be null, pass empty collection instead.");

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

	public String toString() {
		return "mower : " + location + ", [" + instructions.size() + "] instructions remaining.";
	}

}