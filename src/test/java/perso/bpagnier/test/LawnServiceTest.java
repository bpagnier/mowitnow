package perso.bpagnier.test;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import perso.bpagnier.mowitnow.exception.OccupiedLocationException;
import perso.bpagnier.mowitnow.exception.OutOfBoundLocationException;
import perso.bpagnier.mowitnow.model.Direction;
import perso.bpagnier.mowitnow.model.Instruction;
import perso.bpagnier.mowitnow.model.Lawn;
import perso.bpagnier.mowitnow.model.Location;
import perso.bpagnier.mowitnow.model.Mower;
import perso.bpagnier.mowitnow.service.LawnService;

public class LawnServiceTest {

	@Test(expected = IllegalArgumentException.class)
	public void mowerCannotBeAddedWithoutLawn() throws OutOfBoundLocationException, OccupiedLocationException  {
		new LawnService().addMower(new Mower(new Location(0, 0, Direction.EAST), new LinkedList<>()));
	}
	
	@Test(expected = OccupiedLocationException.class)
	public void mowerCannotBeInOccupiedLocation() throws OutOfBoundLocationException, OccupiedLocationException  {
		LawnService lawnService = new LawnService();
		lawnService.setLawn(new Lawn(5, 5));
		lawnService.addMower(new Mower(new Location(0, 0, Direction.EAST), new LinkedList<>()));
		lawnService.addMower(new Mower(new Location(0, 0, Direction.NORTH), new LinkedList<>()));
	}
	
	@Test(expected = OutOfBoundLocationException.class)
	public void mowerCannotBeOutOfBounds() throws OutOfBoundLocationException, OccupiedLocationException  {
		LawnService lawnService = new LawnService();
		lawnService.setLawn(new Lawn(5, 5));
		lawnService.addMower(new Mower(new Location(50, 0, Direction.EAST), new LinkedList<>()));
	}
	
	@Test
	public void mowersMustMoveCorrectly() throws OutOfBoundLocationException, OccupiedLocationException {
		
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
		Mower mower1 = new Mower(new Location(1, 2, Direction.NORTH), instructions);

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
		Mower mower2 = new Mower(new Location(3, 3, Direction.EAST), instructions2);

		LawnService service = new LawnService();

		service.setLawn(lawn);
		service.addMower(mower1);
		service.addMower(mower2);

		service.startMowers();
		
		String mowerLocations = service.getMowersLocations();

		assertEquals(mowerLocations, "1 3 N\n5 1 E");
	}

}