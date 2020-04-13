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
import team.easytravel.logic.commands.fixedexpense.AddFixedExpenseCommand;
import team.easytravel.logic.commands.fixedexpense.ClearFixedExpenseCommand;
import team.easytravel.logic.commands.fixedexpense.DeleteFixedExpenseCommand;
import team.easytravel.logic.commands.fixedexpense.EditFixedExpenseCommand;
import team.easytravel.logic.commands.fixedexpense.ListFixedExpenseCommand;
import team.easytravel.logic.commands.packinglist.AddItemCommand;
import team.easytravel.logic.commands.packinglist.ClearItemCommand;
import team.easytravel.logic.commands.packinglist.DeleteItemCommand;
import team.easytravel.logic.commands.packinglist.EditItemCommand;
import team.easytravel.logic.commands.packinglist.ListItemCommand;
import team.easytravel.logic.commands.transportbooking.AddTransportBookingCommand;
import team.easytravel.logic.commands.transportbooking.ClearTransportBookingCommand;
import team.easytravel.logic.commands.transportbooking.DeleteTransportBookingCommand;
import team.easytravel.logic.commands.transportbooking.EditTransportBookingCommand;
import team.easytravel.logic.commands.transportbooking.ListTransportBookingCommand;
import team.easytravel.logic.commands.trip.DeleteTripCommand;
import team.easytravel.logic.commands.trip.SetTripCommand;
import team.easytravel.logic.parser.exceptions.ParseException;
import team.easytravel.model.listmanagers.accommodationbooking.AccommodationBooking;
import team.easytravel.model.listmanagers.activity.Activity;
import team.easytravel.model.listmanagers.activity.ActivityContainKeywordPredicate;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpense;
import team.easytravel.model.listmanagers.packinglistitem.PackingListItem;
import team.easytravel.model.listmanagers.transportbooking.TransportBooking;
import team.easytravel.model.trip.Trip;
import team.easytravel.testutil.accommodationbooking.AccommodationBookingBuilder;
import team.easytravel.testutil.accommodationbooking.AccommodationUtil;
import team.easytravel.testutil.accommodationbooking.EditAccommodationBookingDescriptorBuilder;
import team.easytravel.testutil.activity.ActivityBuilder;
import team.easytravel.testutil.activity.ActivityUtil;
import team.easytravel.testutil.activity.EditActivityDescriptorBuilder;
import team.easytravel.testutil.fixedexpense.EditFixedExpenseDescriptorBuilder;
import team.easytravel.testutil.fixedexpense.FixedExpenseBuilder;
import team.easytravel.testutil.fixedexpense.FixedExpenseUtil;
import team.easytravel.testutil.packinglist.EditItemDescriptorBuilder;
import team.easytravel.testutil.packinglist.PackingListItemBuilder;
import team.easytravel.testutil.packinglist.PackingListUtil;
import team.easytravel.testutil.transportbooking.EditTransportBookingDescriptorBuilder;
import team.easytravel.testutil.transportbooking.TransportBookingBuilder;
import team.easytravel.testutil.transportbooking.TransportUtil;
import team.easytravel.testutil.trip.TripBuilder;
import team.easytravel.testutil.trip.TripUtil;

public class EasyTravelParserTest {

    private final EasyTravelParser parser = new EasyTravelParser();

    // -- Trip --
    @Test
    public void parseTripCommand_add() throws Exception {
        Trip trip = new TripBuilder().build();
        SetTripCommand command = (SetTripCommand) parser.parseCommand(TripUtil.getAddCommand(trip));
        assertEquals(new SetTripCommand(trip), command);
    }

    @Test
    public void parseTripCommand_delete() throws Exception {
        DeleteTripCommand command = (DeleteTripCommand) parser.parseCommand(
                DeleteTripCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteTripCommand(), command);
    }

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

    // -- FixedExpense --
    @Test
    public void parseFixedExpenseCommand_add() throws Exception {
        FixedExpense fixedExpense = new FixedExpenseBuilder().build();
        AddFixedExpenseCommand command = (AddFixedExpenseCommand)
                parser.parseCommand(FixedExpenseUtil.getAddCommand(fixedExpense));
        assertEquals(new AddFixedExpenseCommand(fixedExpense, false), command);
    }

    @Test
    public void parseFixedExpenseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearFixedExpenseCommand.COMMAND_WORD)
                instanceof ClearFixedExpenseCommand);
        assertTrue(parser.parseCommand(ClearFixedExpenseCommand.COMMAND_WORD + " 3")
                instanceof ClearFixedExpenseCommand);
    }

    @Test
    public void parseFixedExpenseCommand_delete() throws Exception {
        DeleteFixedExpenseCommand command = (DeleteFixedExpenseCommand) parser.parseCommand(
                DeleteFixedExpenseCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteFixedExpenseCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseFixedExpenseCommand_edit() throws Exception {
        FixedExpense fixedExpense = new FixedExpenseBuilder().build();
        EditFixedExpenseCommand.EditFixedExpenseDescriptor descriptor =
                new EditFixedExpenseDescriptorBuilder(fixedExpense).build();
        EditFixedExpenseCommand command = (EditFixedExpenseCommand) parser
                .parseCommand(EditFixedExpenseCommand.COMMAND_WORD
                        + " " + INDEX_FIRST.getOneBased() + " " + FixedExpenseUtil
                        .getEditFixedExpenseDescriptorDetails(descriptor));
        assertEquals(new EditFixedExpenseCommand(INDEX_FIRST, descriptor, false), command);
    }


    @Test
    public void parseFixedExpenseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListFixedExpenseCommand.COMMAND_WORD)
                instanceof ListFixedExpenseCommand);
        assertTrue(parser.parseCommand(ListFixedExpenseCommand.COMMAND_WORD
                + " 3") instanceof ListFixedExpenseCommand);
    }

    // -- Transport --
    @Test
    public void parseTransportCommand_add() throws Exception {
        TransportBooking transportBooking = new TransportBookingBuilder().build();
        AddTransportBookingCommand command = (AddTransportBookingCommand)
                parser.parseCommand(TransportUtil.getAddCommand(transportBooking));
        assertEquals(new AddTransportBookingCommand(transportBooking), command);
    }

    @Test
    public void parseTransportCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearTransportBookingCommand.COMMAND_WORD)
                instanceof ClearTransportBookingCommand);
        assertTrue(parser.parseCommand(ClearTransportBookingCommand.COMMAND_WORD + " 3")
                instanceof ClearTransportBookingCommand);
    }

    @Test
    public void parseTransportCommand_delete() throws Exception {
        DeleteTransportBookingCommand command = (DeleteTransportBookingCommand) parser.parseCommand(
                DeleteTransportBookingCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteTransportBookingCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseTransportCommand_edit() throws Exception {
        TransportBooking transportBooking = new TransportBookingBuilder().build();
        EditTransportBookingCommand.EditTransportBookingDescriptor descriptor =
                new EditTransportBookingDescriptorBuilder(transportBooking).build();
        EditTransportBookingCommand command = (EditTransportBookingCommand) parser
                .parseCommand(EditTransportBookingCommand.COMMAND_WORD
                        + " " + INDEX_FIRST.getOneBased() + " " + TransportUtil
                        .getEditTransportBookingDescriptorDetails(descriptor));
        assertEquals(new EditTransportBookingCommand(INDEX_FIRST, descriptor), command);
    }


    @Test
    public void parseTransportCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListTransportBookingCommand.COMMAND_WORD)
                instanceof ListTransportBookingCommand);
        assertTrue(parser.parseCommand(ListTransportBookingCommand.COMMAND_WORD
                + " 3") instanceof ListTransportBookingCommand);
    }

    //-- PackingList --
    @Test
    public void parsePackingCommand_add() throws Exception {
        PackingListItem packingListItem = new PackingListItemBuilder().build();
        AddItemCommand command = (AddItemCommand)
                parser.parseCommand(PackingListUtil.getAddCommand(packingListItem));
        assertEquals(new AddItemCommand(packingListItem), command);
    }

    @Test
    public void parsePackingCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearItemCommand.COMMAND_WORD)
                instanceof ClearItemCommand);
        assertTrue(parser.parseCommand(ClearItemCommand.COMMAND_WORD + " 3")
                instanceof ClearItemCommand);
    }

    @Test
    public void parsePackingCommand_delete() throws Exception {
        DeleteItemCommand command = (DeleteItemCommand) parser.parseCommand(
                DeleteItemCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteItemCommand(INDEX_FIRST), command);
    }

    @Test
    public void parsePackingCommand_edit() throws Exception {
        PackingListItem packingListItem = new PackingListItemBuilder().build();
        EditItemCommand.EditItemDescriptor descriptor =
                new EditItemDescriptorBuilder(packingListItem).build();
        EditItemCommand command = (EditItemCommand) parser
                .parseCommand(EditItemCommand.COMMAND_WORD
                        + " " + INDEX_FIRST.getOneBased() + " " + PackingListUtil
                        .getEditPackingListBookingDescriptorDetails(descriptor));
        assertEquals(new EditItemCommand(INDEX_FIRST, descriptor), command);
    }


    @Test
    public void parsePackingCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListItemCommand.COMMAND_WORD)
                instanceof ListItemCommand);
        assertTrue(parser.parseCommand(ListItemCommand.COMMAND_WORD
                + " 3") instanceof ListItemCommand);
    }


    // -- Commons --
    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class,
                MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
