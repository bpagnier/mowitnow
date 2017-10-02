package perso.bpagnier.mowitnow.service;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import perso.bpagnier.mowitnow.exception.InstructionFileIncorrectException;
import perso.bpagnier.mowitnow.exception.OccupiedLocationException;
import perso.bpagnier.mowitnow.exception.OutOfBoundLocationException;
import perso.bpagnier.mowitnow.model.Direction;
import perso.bpagnier.mowitnow.model.Instruction;
import perso.bpagnier.mowitnow.model.Lawn;
import perso.bpagnier.mowitnow.model.Location;
import perso.bpagnier.mowitnow.model.Mower;

public class InstructionService {

	private static final String LAWN_DEFINITION_PATTERN = "^(\\d+) (\\d+)$";
	private static final String MOWER_DEFINITON_PATTERN = "^(\\d+) (\\d+) ([NESW])$";
	private static final String MOWER_INSTRUCTION_PATTERN = "^[AGD]+$";

	private static Logger logger = LoggerFactory.getLogger(InstructionService.class);

	public void parseInstructionFile(LawnService lawnService, String filePath) throws InstructionFileIncorrectException, OutOfBoundLocationException, OccupiedLocationException {

		InputStream fileInputStream = getClass().getClassLoader().getResourceAsStream(filePath);
		if (fileInputStream == null) {
			throw new InstructionFileIncorrectException("the instruction file cannot be found");
		}

		Scanner scanner = new Scanner(fileInputStream);

		// first line : lawn dimensions
		lawnService.setLawn(parseLawn(scanner.nextLine()));

		// rest of the lines : mowers and their instructions
		while (scanner.hasNextLine()) {

			Mower mower = parseMower(scanner.nextLine());
			Queue<Instruction> instructions = parseInstructions(scanner.nextLine());
			mower.setInstructions(instructions);

			lawnService.addMower(mower);
			logger.info("new mower added to the lawn : " + mower);
		}

		scanner.close();
	}

	private Lawn parseLawn(String lawnLine) throws InstructionFileIncorrectException {

		// check line pattern
		Pattern lawnPattern = Pattern.compile(LAWN_DEFINITION_PATTERN);
		Matcher lawnMatcher = lawnPattern.matcher(lawnLine);
		if (!lawnMatcher.matches()) {
			throw new InstructionFileIncorrectException("the first line (lawn dimensions) is badly formatted.");
		}

		// create lawn
		int width = Integer.parseInt(lawnMatcher.group(1));
		int height = Integer.parseInt(lawnMatcher.group(2));
		return new Lawn(width, height);
	}

	private Mower parseMower(String mowerLine) throws InstructionFileIncorrectException {

		// check line pattern
		Pattern mowerPattern = Pattern.compile(MOWER_DEFINITON_PATTERN);
		Matcher mowerMatcher = mowerPattern.matcher(mowerLine);
		if (!mowerMatcher.matches()) {
			throw new InstructionFileIncorrectException("mower definition is incorrect");
		}

		// create mower
		int x = Integer.parseInt(mowerMatcher.group(1));
		int y = Integer.parseInt(mowerMatcher.group(2));
		Direction direction = parseDirection(mowerMatcher.group(3));
		return new Mower(new Location(x, y, direction), new LinkedList<Instruction>());

	}

	private Queue<Instruction> parseInstructions(String instructionsLine) throws InstructionFileIncorrectException {

		// test if the instruction line is correct
		Pattern instructionPattern = Pattern.compile(MOWER_INSTRUCTION_PATTERN);
		Matcher instructionMatcher = instructionPattern.matcher(instructionsLine);
		if (!instructionMatcher.matches()) {
			throw new InstructionFileIncorrectException("instruction line is incorrect");
		}

		// create instructions
		Queue<Instruction> instructions = new LinkedList<>();
		for (char c : instructionsLine.toCharArray()) {
			instructions.add(parseInstruction(c));
		}
		return instructions;
	}

	private Direction parseDirection(String direction) {
		switch (direction) {
		case "N":
			return Direction.NORTH;
		case "E":
			return Direction.EAST;
		case "S":
			return Direction.SOUTH;
		case "W":
			return Direction.WEST;
		default:
			throw new IllegalArgumentException("Direction [" + direction + "] incorrect.");
		}
	}

	private Instruction parseInstruction(char instruction) {
		switch (instruction) {
		case 'D':
			return Instruction.TURN_RIGHT;
		case 'G':
			return Instruction.TURN_LEFT;
		case 'A':
			return Instruction.MOVE_FORWARD;
		default:
			throw new IllegalArgumentException("Instruction [" + instruction + "] incorrect.");
		}
	}

}
