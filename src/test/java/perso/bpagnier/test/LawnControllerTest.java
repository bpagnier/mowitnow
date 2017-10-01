package perso.bpagnier.test;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import perso.bpagnier.mowitnow.control.LawnController;
import perso.bpagnier.mowitnow.exception.IncorrectLocationException;
import perso.bpagnier.mowitnow.model.Direction;
import perso.bpagnier.mowitnow.model.Instruction;
import perso.bpagnier.mowitnow.model.Lawn;
import perso.bpagnier.mowitnow.model.Location;
import perso.bpagnier.mowitnow.model.Mower;

public class LawnControllerTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test(expected = IllegalArgumentException.class)
	public void mowerCannotBeAddedWithoutLawn() throws IncorrectLocationException {
		new LawnController().addMower(new Mower("", new Location(0, 0, Direction.EAST), new LinkedList<>()));
	}
	
	@Test(expected = IncorrectLocationException.class)
	public void mowerCannotBeInOccupiedLocation() throws IncorrectLocationException {
		LawnController lawnController = new LawnController();
		lawnController.setLawn(new Lawn(5, 5));
		lawnController.addMower(new Mower("", new Location(0, 0, Direction.EAST), new LinkedList<>()));
		lawnController.addMower(new Mower("", new Location(0, 0, Direction.NORTH), new LinkedList<>()));
		thrown.expect(IncorrectLocationException.class);
	}
	
	@Test(expected = IncorrectLocationException.class)
	public void mowerCannotBeOutOfBounds() throws IncorrectLocationException {
		LawnController lawnController = new LawnController();
		lawnController.setLawn(new Lawn(5, 5));
		lawnController.addMower(new Mower("", new Location(50, 0, Direction.EAST), new LinkedList<>()));
		thrown.expect(IncorrectLocationException.class);
	}
	
	@Test
	public void mowersMustMoveCorrectly() throws IncorrectLocationException {
		
		Lawn lawn = new Lawn(5, 5);

		Queue<Instruction> instructions = new LinkedList<>();
		instructions.add(Instruction.TURN_LEFT);
		instructions.add(Instruction.MOVE_FORWARD);
		instructions.add(Instruction.TURN_LEFT);
		instructions.add(Instruction.MOVE_FORWARD);
		instructions.add(Instruction.TURN_LEFT);
		instructions.add(Instruction.MOVE_FORWARD);
		instructions.add(Instruction.TURN_LEFT);
		instructions.add(Instruction.MOVE_FORWARD);
		instructions.add(Instruction.MOVE_FORWARD);
		instructions.add(Instruction.MOVE_FORWARD);
		Mower mower1 = new Mower("Mower 1", new Location(1, 2, Direction.NORTH), instructions);

		Queue<Instruction> instructions2 = new LinkedList<>();
		instructions2.add(Instruction.MOVE_FORWARD);
		instructions2.add(Instruction.MOVE_FORWARD);
		instructions2.add(Instruction.TURN_RIGHT);
		instructions2.add(Instruction.MOVE_FORWARD);
		instructions2.add(Instruction.MOVE_FORWARD);
		instructions2.add(Instruction.TURN_RIGHT);
		instructions2.add(Instruction.MOVE_FORWARD);
		instructions2.add(Instruction.TURN_RIGHT);
		instructions2.add(Instruction.TURN_RIGHT);
		instructions2.add(Instruction.MOVE_FORWARD);
		Mower mower2 = new Mower("Mower 2", new Location(3, 3, Direction.EAST), instructions2);

		LawnController controller = new LawnController();

		controller.setLawn(lawn);
		controller.addMower(mower1);
		controller.addMower(mower2);

		controller.startMowers();
		
		String mowerLocations = controller.getMowersLocations();

		assertEquals(mowerLocations, "1 4 N\n5 1 E");
	}

}