package dk.magnusjensen.adventofcode.cal2022;

import dk.magnusjensen.adventofcode.maths.Coord;
import dk.magnusjensen.adventofcode.templates.Assignment;
import dk.magnusjensen.adventofcode.templates.CalenderAssignment;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CalenderAssignment(calendarName = 2022, assignmentName = "", number = 17, description = "")
public class A9P1 extends Assignment {
    private static Set<String> positions;
    @FXML
    private TextArea output;

    public A9P1(String name) {
        super(name);
    }


    public void partOne(String input) {
        String[] lines = input.split("\n");
        positions = new HashSet<>();
        Coord head = new Coord();
        Coord tail = new Coord();
        for (String line : lines) {
            char direction = line.charAt(0);
            int count = Integer.parseInt(line.split(" ")[1]);
            positions.add(String.format("%d;%d", tail.x, tail.y));
            for (int i = 1; i <= count; i++) {
                switch (direction) {
                    case 'R' -> {
                        head = head.right();
                    }
                    case 'L' -> {
                        head = head.left();
                    }
                    case 'U' -> {
                        head = head.up();
                    }
                    case 'D' -> {
                        head = head.down();
                    }
                }

                tail = checkAndMoveTail(head, tail);
                positions.add(String.format("%d;%d", tail.x, tail.y));
            }
        }

        output.setText("Output: " + positions.size() + "\n" + positions.toString());
    }

    private Coord checkAndMoveTail(Coord head, Coord tail) {
        List<Coord> allNeighbours = head.allNeighbors();
        for (Coord neighbour : allNeighbours) {
            if (neighbour.x == tail.x && neighbour.y == tail.y) return tail;
        }

        // Up and right.
        if ((head.y > tail.y + 1 && head.x > tail.x) || (head.x > tail.x + 1 && head.y > tail.y)) {
            Coord newTail = tail.up();
            return newTail.right();
        } else if ((head.y > tail.y + 1 && head.x < tail.x) || (head.x < tail.x - 1 && head.y > tail.y)) { // Up and left
            Coord newTail = tail.up();
            return newTail.left();
        } else if ((head.y < tail.y - 1 && head.x > tail.x) || (head.x > tail.x + 1 && head.y < tail.y)) {
            Coord newTail = tail.down();
            return newTail.right();
        } else if ((head.y < tail.y - 1 && head.x < tail.x) || (head.x < tail.x - 1 && head.y < tail.y)) {
            Coord newTail = tail.down();
            return newTail.left();
        }

        // All sides.
        if (head.x > tail.x + 1) {
            return tail.right();
        } else if (head.x < tail.x - 1) {
            return tail.left();
        } else if (head.y > tail.y + 1) {
            return tail.up();
        } else if (head.y < tail.y - 1) {
            return tail.down();
        }
        return tail;
    }
}
