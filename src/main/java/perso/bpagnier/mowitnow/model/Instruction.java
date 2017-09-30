package perso.bpagnier.mowitnow.model;

public enum Instruction {

	TURN_RIGHT {
		public Location move(Location currentLocation) {
			return currentLocation.turnRight();
		}
	},
	TURN_LEFT {
		public Location move(Location currentLocation) {
			return currentLocation.turnLeft();
		}
	},
	MOVE_FORWARD {
		public Location move(Location currentLocation) {
			return currentLocation.moveForward();
		}
	};

	public abstract Location move(Location location);

}
