package perso.bpagnier.mowitnow.main;

import java.util.LinkedList;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import perso.bpagnier.mowitnow.control.LawnController;
import perso.bpagnier.mowitnow.exception.IncorrectMowerLocationException;
import perso.bpagnier.mowitnow.model.Direction;
import perso.bpagnier.mowitnow.model.Instruction;
import perso.bpagnier.mowitnow.model.Lawn;
import perso.bpagnier.mowitnow.model.Location;
import perso.bpagnier.mowitnow.model.Mower;

public class MowItNow {

	private static Logger logger = LoggerFactory.getLogger(MowItNow.class);

	public MowItNow() {
		logger.info("### MowItNow starting ###");
	}

	public static void main(String[] args) throws IncorrectMowerLocationException {
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
		Mower mower1 = new Mower("SuperMower 1", new Location(1, 2, Direction.NORTH), instructions);

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
		Mower mower2 = new Mower("SuperMower 2", new Location(1, 2, Direction.EAST), instructions2);

		LawnController controller = new LawnController();

		controller.setLawn(lawn);
		controller.addMower(mower1);
		controller.addMower(mower2);

		controller.startMowers();

		for (Mower m : controller.getMowers()) {
			logger.info(m.toString());
		}

	}

}