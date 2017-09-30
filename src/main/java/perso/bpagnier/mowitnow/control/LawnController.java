package perso.bpagnier.mowitnow.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import perso.bpagnier.mowitnow.exception.IncorrectMowerLocationException;
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

	public void startMowers() throws IncorrectMowerLocationException {
		for (Mower mower : mowers) {

			Queue<Instruction> instructions = mower.getInstructions();
			if (instructions.isEmpty()) {
				continue;
			}

			while (!instructions.isEmpty()) {
				Instruction instruction = instructions.poll();
				Location currentLocation = mower.getLocation();
				Location newLocation = instruction.move(mower.getLocation());

				// test if the new location is correct
				verifyLocation(lawn, newLocation);

				// move the mower
				mower.setLocation(newLocation);
				logger.info(mower.getName() + " moved from : " + currentLocation + " to " + newLocation + ". " + mower.getInstructions().size() + " instructions remaning.");

			}
		}
	}

	private void verifyLocation(Lawn lawn2, Location newLocation) throws IncorrectMowerLocationException {
		if (isOutOfBound(lawn, newLocation)) {
			throw new IncorrectMowerLocationException("Mower new location " + newLocation + " is out of bounds.");
		}
		if (isOccupied(lawn, newLocation)) {
			throw new IncorrectMowerLocationException("Mower new location " + newLocation + " is already occupied.");
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

	public void addMower(Mower mower) throws IncorrectMowerLocationException {
		Location mowerLocation = mower.getLocation();

		verifyLocation(lawn, mowerLocation); // test location
		mowers.add(mower); // it is correct, add the mower to the mower list
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
