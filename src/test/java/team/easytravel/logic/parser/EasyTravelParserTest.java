package team.easytravel.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static team.easytravel.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static team.easytravel.testutil.Assert.assertThrows;
import static team.easytravel.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import team.easytravel.logic.commands.accommodationbooking.AddAccommodationBookingCommand;
import team.easytravel.logic.commands.accommodationbooking.ClearAccommodationBookingCommand;
import team.easytravel.logic.commands.accommodationbooking.DeleteAccommodationBookingCommand;
import team.easytravel.logic.commands.accommodationbooking.EditAccommodationBookingCommand;
import team.easytravel.logic.commands.accommodationbooking.EditAccommodationBookingCommand.EditAccommodationBookingDescriptor;
import team.easytravel.logic.commands.accommodationbooking.ListAccommodationBookingCommand;
import team.easytravel.logic.commands.activity.AddActivityCommand;
import team.easytravel.logic.commands.activity.ClearActivityCommand;
import team.easytravel.logic.commands.activity.DeleteActivityCommand;
import team.easytravel.logic.commands.activity.EditActivityCommand;
import team.easytravel.logic.commands.activity.EditActivityCommand.EditActivityDescriptor;
import team.easytravel.logic.commands.activity.FindActivityCommand;
import team.easytravel.logic.commands.activity.ListActivityCommand;
import team.easytravel.logic.parser.exceptions.ParseException;
import team.easytravel.model.listmanagers.accommodationbooking.AccommodationBooking;
import team.easytravel.model.listmanagers.activity.Activity;
import team.easytravel.model.listmanagers.activity.ActivityContainKeywordPredicate;
import team.easytravel.testutil.accommodationbooking.AccommodationBookingBuilder;
import team.easytravel.testutil.accommodationbooking.AccommodationUtil;
import team.easytravel.testutil.accommodationbooking.EditAccommodationBookingDescriptorBuilder;
import team.easytravel.testutil.activity.ActivityBuilder;
import team.easytravel.testutil.activity.ActivityUtil;
import team.easytravel.testutil.activity.EditActivityDescriptorBuilder;

public class EasyTravelParserTest {

    private final EasyTravelParser parser = new EasyTravelParser();

    // -- Activity --
    @Test
    public void parseActivityCommand_add() throws Exception {
        Activity activity = new ActivityBuilder().build();
        AddActivityCommand command = (AddActivityCommand) parser.parseCommand(ActivityUtil.getAddCommand(activity));
        assertEquals(new AddActivityCommand(activity), command);
    }

    @Test
    public void parseActivityCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearActivityCommand.COMMAND_WORD) instanceof ClearActivityCommand);
        assertTrue(parser.parseCommand(ClearActivityCommand.COMMAND_WORD + " 3")
                instanceof ClearActivityCommand);
    }

    @Test
    public void parseActivityCommand_delete() throws Exception {
        DeleteActivityCommand command = (DeleteActivityCommand) parser.parseCommand(
                DeleteActivityCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteActivityCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseActivityCommand_edit() throws Exception {
        Activity activity = new ActivityBuilder().build();
        EditActivityDescriptor descriptor = new EditActivityDescriptorBuilder(activity).build();
        EditActivityCommand command = (EditActivityCommand) parser
                .parseCommand(EditActivityCommand.COMMAND_WORD
                + " " + INDEX_FIRST.getOneBased() + " " + ActivityUtil.getEditActivityDescriptorDetails(descriptor));
        assertEquals(new EditActivityCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseActivityCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindActivityCommand command = (FindActivityCommand) parser.parseCommand(
                FindActivityCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindActivityCommand(new ActivityContainKeywordPredicate(keywords)), command);
    }

    @Test
    public void parseActivityCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListActivityCommand.COMMAND_WORD) instanceof ListActivityCommand);
        assertTrue(parser.parseCommand(ListActivityCommand.COMMAND_WORD
                + " 3") instanceof ListActivityCommand);
    }

    // -- Accommodation --
    @Test
    public void parseAccommodationCommand_add() throws Exception {
        AccommodationBooking accommodationBooking = new AccommodationBookingBuilder().build();
        AddAccommodationBookingCommand command = (AddAccommodationBookingCommand)
                parser.parseCommand(AccommodationUtil.getAddCommand(accommodationBooking));
        assertEquals(new AddAccommodationBookingCommand(accommodationBooking), command);
    }

    @Test
    public void parseAccommodationCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearAccommodationBookingCommand.COMMAND_WORD)
                instanceof ClearAccommodationBookingCommand);
        assertTrue(parser.parseCommand(ClearAccommodationBookingCommand.COMMAND_WORD + " 3")
                instanceof ClearAccommodationBookingCommand);
    }

    @Test
    public void parseAccommodationCommand_delete() throws Exception {
        DeleteAccommodationBookingCommand command = (DeleteAccommodationBookingCommand) parser.parseCommand(
                DeleteAccommodationBookingCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteAccommodationBookingCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseAccommodationCommand_edit() throws Exception {
        AccommodationBooking accommodationBooking = new AccommodationBookingBuilder().build();
        EditAccommodationBookingDescriptor descriptor =
                new EditAccommodationBookingDescriptorBuilder(accommodationBooking).build();
        EditAccommodationBookingCommand command = (EditAccommodationBookingCommand) parser
                .parseCommand(EditAccommodationBookingCommand.COMMAND_WORD
                        + " " + INDEX_FIRST.getOneBased() + " " + AccommodationUtil
                        .getEditAccommodationDescriptorDetails(descriptor));
        assertEquals(new EditAccommodationBookingCommand(INDEX_FIRST, descriptor), command);
    }


    @Test
    public void parseAccommodationCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListAccommodationBookingCommand.COMMAND_WORD)
                instanceof ListAccommodationBookingCommand);
        assertTrue(parser.parseCommand(ListAccommodationBookingCommand.COMMAND_WORD
                + " 3") instanceof ListAccommodationBookingCommand);
    }


    // -- Commons --
    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class,
                MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
