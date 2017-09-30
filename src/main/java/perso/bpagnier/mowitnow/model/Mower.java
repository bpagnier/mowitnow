package perso.bpagnier.mowitnow.model;

import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mower {

	private Location location;
	private Queue<Instruction> instructions;
	private String name;

	private static Logger logger = LoggerFactory.getLogger(Mower.class);

	public Mower(String name, Location location, Queue<Instruction> instructions) {
		this.name = name;
		this.location = location;
		this.instructions = instructions;
		logger.debug("constructing mower : " + toString());
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