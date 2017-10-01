package perso.bpagnier.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import perso.bpagnier.mowitnow.exception.IncorrectLocationException;
import perso.bpagnier.mowitnow.exception.InstructionFileIncorrectException;
import perso.bpagnier.mowitnow.model.Direction;
import perso.bpagnier.mowitnow.model.Location;
import perso.bpagnier.mowitnow.service.InstructionService;
import perso.bpagnier.mowitnow.service.LawnService;

public class InstructionServiceTest {

	@Test(expected = InstructionFileIncorrectException.class)
	public void fileMustExists() throws IncorrectLocationException, InstructionFileIncorrectException {
		new InstructionService().parseInstructionFile(new LawnService(), "c:/wrongpath.txt");
	}

	@Test(expected = InstructionFileIncorrectException.class)
	public void fileMustBeInCorrectFormat() throws IncorrectLocationException, InstructionFileIncorrectException {
		new InstructionService().parseInstructionFile(new LawnService(), "wrongFormat.txt");
	}

	@Test
	public void parsingMustBeCorrect() throws IncorrectLocationException, InstructionFileIncorrectException {
		LawnService lawnService = new LawnService();
		new InstructionService().parseInstructionFile(lawnService, "instructions.txt");

		// lawn
		assertEquals(lawnService.getLawn().getHeight(), 5);
		assertEquals(lawnService.getLawn().getWidth(), 5);

		// mowers
		assertEquals(lawnService.getMowers().size(), 2);
		assertEquals(lawnService.getMowers().get(0).getLocation(), new Location(1, 2, Direction.NORTH));
		assertEquals(lawnService.getMowers().get(1).getLocation(), new Location(3, 3, Direction.EAST));
	}

}