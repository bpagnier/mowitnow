package perso.bpagnier.mowitnow.model;

import org.apache.commons.lang.Validate;

public class Lawn {

	private int width;
	private int height;

	public Lawn(int width, int height) {
		Validate.isTrue(width > 0, "lawn width must be positive");
		Validate.isTrue(height > 0, "lawn height must be positive");

		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}