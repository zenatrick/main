package team.easytravel.logic.commands.packinglist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import team.easytravel.logic.commands.CommandResult;
import team.easytravel.model.listmanagers.packinglistitem.PackingListItem;
import team.easytravel.model.listmanagers.packinglistpreset.PresetDataUtil;

public class AddPresetCommandTest {

    @Test
    public void execute_itemAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingItemAdded modelStub = new ModelStubAcceptingItemAdded();
        CommandResult commandResult = new AddPresetCommand(PresetDataUtil.beachPreset()
                , "beach").execute(modelStub);

        assertEquals(String.format(AddPresetCommand.MESSAGE_SUCCESS, "beach"),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        PackingListItem[] essentials = PresetDataUtil.essentialsPreset();
        PackingListItem[] swimming = PresetDataUtil.swimmingPreset();
        AddPresetCommand addEssentialsCommand = new AddPresetCommand(essentials, "essentials");
        AddPresetCommand addSwimmingCommand= new AddPresetCommand(swimming, "swimming");

        // same object -> returns true
        assertEquals(addEssentialsCommand, addEssentialsCommand);

        // same values -> returns true
        AddPresetCommand addEssentialsCommandCopy = new AddPresetCommand(essentials, "essentials");
        assertEquals(addEssentialsCommand, addEssentialsCommandCopy);

        // different types -> returns false
        assertNotEquals(1, addEssentialsCommand);

        // null -> returns false
        assertNotEquals(null, addEssentialsCommand);

        // different PackingListItem -> returns false
        assertNotEquals(addEssentialsCommand,addSwimmingCommand);
    }

    @Test
    void execute() {
    }

    @Test
    void testEquals() {
    }
}
