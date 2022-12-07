package dk.magnusjensen.adventofcode.cal2021;

import dk.magnusjensen.adventofcode.templates.Assignment;
import dk.magnusjensen.adventofcode.templates.CalenderAssignment;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;

import java.io.IOException;

@CalenderAssignment(calendarName = 2021, assignmentName = "Dive", number = 3, description = "Placeholder.")
public class A2P1 extends Assignment {
	
	@FXML
	private TextArea output;

	public A2P1(String name) {
		super(name);
	}

	

	public void partOne(String input) {
		String[] lines = input.split("\n");

		int depth = 0;
		int horizontal = 0;

		for (String line : lines) {
			String[] parts = line.split(" ");
			int value = Integer.parseInt(parts[1]);

			if (parts[0].equalsIgnoreCase("forward")) {
				horizontal += value;
			} else if (parts[0].equalsIgnoreCase("up")) {
				depth -= value;
			} else if (parts[0].equalsIgnoreCase("down")) {
				depth += value;
			}

		}

		output.setText("Depth is: " + depth + "\nHorizontal value is: " + horizontal + "\nResult: " + (horizontal * depth));

	}
}
