package team.easytravel.logic.parser.activity;

import static team.easytravel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static team.easytravel.logic.commands.CommandTestUtil.ACTIVITY_DURATION_DESC_CHEESE;
import static team.easytravel.logic.commands.CommandTestUtil.ACTIVITY_DURATION_DESC_FINLAND;
import static team.easytravel.logic.commands.CommandTestUtil.ACTIVITY_LOCATION_DESC_CHEESE;
import static team.easytravel.logic.commands.CommandTestUtil.ACTIVITY_LOCATION_DESC_FINLAND;
import static team.easytravel.logic.commands.CommandTestUtil.ACTIVITY_TITLE_DESC_CHEESE;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_DURATION_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.TAG_DESC_EXPENSIVE;
import static team.easytravel.logic.commands.CommandTestUtil.TAG_DESC_SIGHTSEEING;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ACTIVITY_DURATION_CHEESE;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ACTIVITY_DURATION_FINLAND;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ACTIVITY_TITLE_CHEESE;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_LOCATION_CHEESE;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_LOCATION_FINLAND;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_TAG_EXPENSIVE;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_TAG_SIGHTSEEING;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_ACTIVITY_TAG;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseFailure;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static team.easytravel.testutil.TypicalIndexes.INDEX_FIRST;
import static team.easytravel.testutil.TypicalIndexes.INDEX_SECOND;
import static team.easytravel.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import team.easytravel.commons.core.index.Index;
import team.easytravel.logic.commands.activity.EditActivityCommand;
import team.easytravel.logic.commands.activity.EditActivityCommand.EditActivityDescriptor;
import team.easytravel.model.listmanagers.activity.Duration;
import team.easytravel.model.listmanagers.activity.Tag;
import team.easytravel.model.util.attributes.Location;
import team.easytravel.model.util.attributes.Title;
import team.easytravel.testutil.activity.EditActivityDescriptorBuilder;

public class EditActivityCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_ACTIVITY_TAG;

    private EditActivityCommandParser parser = new EditActivityCommandParser();

    @Test
    public void parse_missingParts_failure() {

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditActivityCommand.MESSAGE_USAGE);
        // no index specified
        assertParseFailure(parser, VALID_ACTIVITY_TITLE_CHEESE, expectedMessage);

        // no field specified
        assertParseFailure(parser, "1", EditActivityCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", expectedMessage);
    }

    @Test
    public void parse_invalidPreamble_failure() {

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditActivityCommand.MESSAGE_USAGE);

        // negative index
        assertParseFailure(parser, "-5" + ACTIVITY_TITLE_DESC_CHEESE, expectedMessage);

        // zero index
        assertParseFailure(parser, "0" + ACTIVITY_TITLE_DESC_CHEESE, expectedMessage);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", expectedMessage);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1"
                + INVALID_TITLE_DESC, Title.MESSAGE_CONSTRAINTS); // invalid title
        assertParseFailure(parser, "1"
                + INVALID_DURATION_DESC, Duration.MESSAGE_CONSTRAINTS); // invalid duration
        assertParseFailure(parser, "1"
                + INVALID_LOCATION_DESC, Location.MESSAGE_CONSTRAINTS); // invalid location
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid duration followed by valid location
        assertParseFailure(parser, "1" + INVALID_DURATION_DESC + ACTIVITY_LOCATION_DESC_CHEESE,
                Duration.MESSAGE_CONSTRAINTS);

        // valid duration followed by invalid duration. The test case for invalid duration followed by valid duration
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + ACTIVITY_DURATION_DESC_CHEESE + INVALID_DURATION_DESC,
                Duration.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_EXPENSIVE + TAG_DESC_SIGHTSEEING + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_EXPENSIVE + TAG_EMPTY + TAG_DESC_SIGHTSEEING,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_EXPENSIVE + TAG_DESC_SIGHTSEEING,
                Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC + INVALID_DURATION_DESC
                        + VALID_LOCATION_CHEESE,
                Title.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + ACTIVITY_LOCATION_DESC_CHEESE + TAG_DESC_EXPENSIVE
                + ACTIVITY_DURATION_DESC_CHEESE + ACTIVITY_TITLE_DESC_CHEESE + TAG_DESC_SIGHTSEEING;

        EditActivityDescriptor descriptor = new EditActivityDescriptorBuilder().withTitle(VALID_ACTIVITY_TITLE_CHEESE)
                .withDuration(VALID_ACTIVITY_DURATION_CHEESE).withLocation(VALID_LOCATION_CHEESE)
                .withTags(VALID_TAG_EXPENSIVE, VALID_TAG_SIGHTSEEING).build();
        EditActivityCommand expectedCommand = new EditActivityCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + ACTIVITY_LOCATION_DESC_CHEESE + ACTIVITY_DURATION_DESC_CHEESE;

        EditActivityDescriptor descriptor = new EditActivityDescriptorBuilder().withLocation(VALID_LOCATION_CHEESE)
                .withDuration(VALID_ACTIVITY_DURATION_CHEESE).build();
        EditActivityCommand expectedCommand = new EditActivityCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + ACTIVITY_TITLE_DESC_CHEESE;
        EditActivityDescriptor descriptor = new EditActivityDescriptorBuilder()
                .withTitle(VALID_ACTIVITY_TITLE_CHEESE).build();
        EditActivityCommand expectedCommand = new EditActivityCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // duration
        userInput = targetIndex.getOneBased() + ACTIVITY_DURATION_DESC_CHEESE;
        descriptor = new EditActivityDescriptorBuilder().withDuration(VALID_ACTIVITY_DURATION_CHEESE).build();
        expectedCommand = new EditActivityCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // location
        userInput = targetIndex.getOneBased() + ACTIVITY_LOCATION_DESC_CHEESE;
        descriptor = new EditActivityDescriptorBuilder().withLocation(VALID_LOCATION_CHEESE).build();
        expectedCommand = new EditActivityCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);


        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_SIGHTSEEING;
        descriptor = new EditActivityDescriptorBuilder().withTags(VALID_TAG_SIGHTSEEING).build();
        expectedCommand = new EditActivityCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + ACTIVITY_DURATION_DESC_CHEESE + ACTIVITY_LOCATION_DESC_CHEESE
                + TAG_DESC_SIGHTSEEING + ACTIVITY_DURATION_DESC_CHEESE + ACTIVITY_LOCATION_DESC_CHEESE
                + TAG_DESC_SIGHTSEEING
                + ACTIVITY_DURATION_DESC_FINLAND + ACTIVITY_LOCATION_DESC_FINLAND + TAG_DESC_EXPENSIVE;

        EditActivityDescriptor descriptor = new EditActivityDescriptorBuilder()
                .withDuration(VALID_ACTIVITY_DURATION_FINLAND)
                .withLocation(VALID_LOCATION_FINLAND).withTags(VALID_TAG_SIGHTSEEING,
                VALID_TAG_EXPENSIVE)
                .build();
        EditActivityCommand expectedCommand = new EditActivityCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_DURATION_DESC + ACTIVITY_DURATION_DESC_CHEESE;
        EditActivityDescriptor descriptor = new EditActivityDescriptorBuilder()
                .withDuration(VALID_ACTIVITY_DURATION_CHEESE).build();
        EditActivityCommand expectedCommand = new EditActivityCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + ACTIVITY_LOCATION_DESC_CHEESE + INVALID_DURATION_DESC
                + ACTIVITY_DURATION_DESC_CHEESE;
        descriptor = new EditActivityDescriptorBuilder().withDuration(VALID_ACTIVITY_DURATION_CHEESE)
                .withLocation(VALID_LOCATION_CHEESE).build();
        expectedCommand = new EditActivityCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditActivityDescriptor descriptor = new EditActivityDescriptorBuilder().withTags().build();
        EditActivityCommand expectedCommand = new EditActivityCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

