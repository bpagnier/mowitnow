package perso.bpagnier.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tester {

	private static final Logger logger = LoggerFactory.getLogger(Tester.class);

	public static void main(String[] args) {
		logger.info("COUCOU !");
		new Tester();
	}

}