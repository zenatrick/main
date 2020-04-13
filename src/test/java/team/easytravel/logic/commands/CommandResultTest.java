package team.easytravel.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback" , CommandResult.Action.NONE);

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback" , CommandResult.Action.NONE)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different", CommandResult.Action.NONE)));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", CommandResult.Action.HELP)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback",
                CommandResult.Action.EXIT)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback" , CommandResult.Action.NONE);

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback",
                CommandResult.Action.NONE).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different",
                CommandResult.Action.NONE).hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                CommandResult.Action.HELP).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                CommandResult.Action.EXIT).hashCode());
    }
}
