package perso.bpagnier.test;

import java.util.LinkedList;

import org.junit.Test;

import perso.bpagnier.mowitnow.model.Direction;
import perso.bpagnier.mowitnow.model.Location;
import perso.bpagnier.mowitnow.model.Mower;

public class MowerTest {

	@Test(expected = IllegalArgumentException.class)
	public void locationMustNotBeNull() {
		new Mower(null, new LinkedList<>());
	}

	@Test(expected = IllegalArgumentException.class)
	public void instructionsMustNotBeNull() {
		new Mower(new Location(0, 0, Direction.NORTH), null);
	}

}