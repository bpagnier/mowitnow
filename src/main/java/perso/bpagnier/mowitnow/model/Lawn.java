package perso.bpagnier.mowitnow.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lawn {

	private int width;
	private int height;

	private static Logger logger = LoggerFactory.getLogger(Lawn.class);

	public Lawn(int width, int height) {
		this.width = width;
		this.height = height;
		logger.debug("constructing lawn [" + width + ";" + height + "]");
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