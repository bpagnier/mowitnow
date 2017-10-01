package perso.bpagnier.test;

import org.junit.Test;

import perso.bpagnier.mowitnow.model.Lawn;

public class LawnTest {

	@Test(expected = IllegalArgumentException.class)
	public void widthMustBePositive() {
		new Lawn(-1, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void heightMustBePositive() {
		new Lawn(0, -1);
	}

}