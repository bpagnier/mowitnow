package perso.bpagnier.mowitnow.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import perso.bpagnier.mowitnow.exception.OccupiedLocationException;
import perso.bpagnier.mowitnow.exception.OutOfBoundLocationException;
import perso.bpagnier.mowitnow.model.Instruction;
import perso.bpagnier.mowitnow.model.Lawn;
import perso.bpagnier.mowitnow.model.Location;
import perso.bpagnier.mowitnow.model.Mower;

public class LawnService {

	private List<Mower> mowers;
	private Lawn lawn;

	private static Logger logger = LoggerFactory.getLogger(LawnService.class);

	public LawnService() {
		mowers = new ArrayList<>();
	}

	public void startMowers() throws OccupiedLocationException {

		// the mower moves one after the other
		for (Mower mower : mowers) {

			Queue<Instruction> instructions = mower.getInstructions();

			// the mowers execute their instruction sequentially
			while (!instructions.isEmpty()) {
				Instruction instruction = instructions.poll();
				Location currentLocation = mower.getLocation();
				Location newLocation = instruction.move(mower.getLocation());

				// if case of moving forward, there are different possibilities
				if (instruction == Instruction.MOVE_FORWARD) {

					if (isOutOfBound(lawn, newLocation)) { // out of bounds : do nothing
						continue;
					}

					else if (isOccupied(lawn, newLocation)) { // already occupied : exception
						throw new OccupiedLocationException("Mower new location " + newLocation + " is already occupied.");
					}

					else { // everything seems good here, let's move the mower
						mower.setLocation(newLocation);
					}
				}
				
				else {
					// always apply rotation instructions
					mower.setLocation(newLocation);
				}
			

				logger.info("mower moved from : " + currentLocation + " to " + newLocation + ". " + mower.getInstructions().size() + " instructions remaning.");

			}
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

	public void addMower(Mower mower) throws OutOfBoundLocationException, OccupiedLocationException {
		Validate.notNull(lawn, "cannot add mower without a lawn");

		Location mowerLocation = mower.getLocation();

		if (isOccupied(lawn, mowerLocation)) {
			throw new OccupiedLocationException("location " + mowerLocation + " is already occupied.");
		}
		if (isOutOfBound(lawn, mowerLocation)) {
			throw new OutOfBoundLocationException("location " + mowerLocation + " is out of the lawn bounds.");
		}

		mowers.add(mower); // it is correct, add the mower to the mower list
	}

	public String getMowersLocations() {
		StringBuilder sb = new StringBuilder();

		for (Mower mower : mowers) {

			if (sb.length() > 0) {
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
