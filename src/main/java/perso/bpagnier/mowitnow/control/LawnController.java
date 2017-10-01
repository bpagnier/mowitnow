package perso.bpagnier.mowitnow.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import perso.bpagnier.mowitnow.exception.IncorrectLocationException;
import perso.bpagnier.mowitnow.model.Instruction;
import perso.bpagnier.mowitnow.model.Lawn;
import perso.bpagnier.mowitnow.model.Location;
import perso.bpagnier.mowitnow.model.Mower;

public class LawnController {

	private List<Mower> mowers;
	private Lawn lawn;

	private static Logger logger = LoggerFactory.getLogger(LawnController.class);

	public LawnController() {
		mowers = new ArrayList<>();
	}

	public void startMowers() throws IncorrectLocationException {

		// the mower moves one after the other
		for (Mower mower : mowers) {

			Queue<Instruction> instructions = mower.getInstructions();

			// the mowers execute their instruction sequentially
			while (!instructions.isEmpty()) {
				Instruction instruction = instructions.poll();
				Location currentLocation = mower.getLocation();
				Location newLocation = instruction.move(mower.getLocation());

				// if the mower moves, test if the new location is accepted
				if (instruction == Instruction.MOVE_FORWARD) {
					verifyLocation(newLocation);
				}

				// move the mower
				mower.setLocation(newLocation);
				logger.info(mower.getName() + " moved from : " + currentLocation + " to " + newLocation + ". " + mower.getInstructions().size() + " instructions remaning.");

			}
		}
	}

	private void verifyLocation(Location newLocation) throws IncorrectLocationException {
		if (isOutOfBound(lawn, newLocation)) {
			throw new IncorrectLocationException("Mower new location " + newLocation + " is out of bounds.");
		}
		if (isOccupied(lawn, newLocation)) {
			throw new IncorrectLocationException("Mower new location " + newLocation + " is already occupied.");
		}
	}

	private boolean isOccupied(Lawn lawn, Location location) {
		for (Mower m : mowers) {
			Location mowerLocation = m.getLocation();
			if (mowerLocation.getX() == location.getX() && mowerLocation.getY() == location.getY()) {
				return true;
			}
		}
		return false;
	}

	private boolean isOutOfBound(Lawn lawn, Location location) {
		int x = location.getX();
		int y = location.getY();
		int lawnWidth = lawn.getWidth();
		int lawnHeight = lawn.getHeight();
		return x < 0 || y < 0 || x > lawnWidth || y > lawnHeight;
	}

	public void addMower(Mower mower) throws IncorrectLocationException {
		Validate.notNull(lawn, "cannot add mower without a lawn");

		Location mowerLocation = mower.getLocation();

		verifyLocation(mowerLocation); // test location
		mowers.add(mower); // it is correct, add the mower to the mower list
	}

	public String getMowersLocations() {
		StringBuilder sb = new StringBuilder();
		
		for(Mower mower : mowers) {

			if(sb.length() > 0) {
				sb.append("\n");
			}
			
			Location mowerLocation = mower.getLocation();
			sb.append(mowerLocation.getX()).append(" ");
			sb.append(mowerLocation.getY()).append(" ");
			sb.append(mowerLocation.getDirection().getName());
		}
		 
		return sb.toString();
	}

	public List<Mower> getMowers() {
		return mowers;
	}

	public void setMowers(List<Mower> mowers) {
		this.mowers = mowers;
	}

	public Lawn getLawn() {
		return lawn;
	}

	public void setLawn(Lawn lawn) {
		this.lawn = lawn;
	}

}
