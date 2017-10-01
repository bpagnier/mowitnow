package perso.bpagnier.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import perso.bpagnier.mowitnow.model.Direction;
import perso.bpagnier.mowitnow.model.Location;

public class LocationTest {

	@Test
	public void testMoveRight() {
		Location location = new Location(0, 0, Direction.NORTH);
		Location newLocation = location.turnRight();
		assertEquals(new Location(0, 0, Direction.EAST), newLocation);
	}

	@Test
	public void testMoveLeft() {
		Location location = new Location(0, 0, Direction.NORTH);
		Location newLocation = location.turnLeft();
		assertEquals(new Location(0, 0, Direction.WEST), newLocation);
	}

	@Test
	public void testMoveForward() {
		Location location = new Location(0, 0, Direction.NORTH);
		Location newLocation = location.moveForward();
		assertEquals(new Location(0, 1, Direction.NORTH), newLocation);
	}

	@Test(expected = IllegalArgumentException.class)
	public void directionMustNotBeNull() {
		new Location(0, 0, null);
	}

}