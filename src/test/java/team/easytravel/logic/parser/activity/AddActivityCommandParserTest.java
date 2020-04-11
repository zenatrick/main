package team.easytravel.logic.parser.activity;

import static team.easytravel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static team.easytravel.logic.commands.CommandTestUtil.ACTIVITY_DURATION_DESC_CHEESE;
import static team.easytravel.logic.commands.CommandTestUtil.ACTIVITY_DURATION_DESC_FINLAND;
import static team.easytravel.logic.commands.CommandTestUtil.ACTIVITY_LOCATION_DESC_CHEESE;
import static team.easytravel.logic.commands.CommandTestUtil.ACTIVITY_LOCATION_DESC_FINLAND;
import static team.easytravel.logic.commands.CommandTestUtil.ACTIVITY_TITLE_DESC_CHEESE;
import static team.easytravel.logic.commands.CommandTestUtil.ACTIVITY_TITLE_DESC_FINLAND;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_DURATION_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static team.easytravel.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static team.easytravel.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static team.easytravel.logic.commands.CommandTestUtil.TAG_DESC_EXPENSIVE;
import static team.easytravel.logic.commands.CommandTestUtil.TAG_DESC_SIGHTSEEING;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ACTIVITY_DURATION_CHEESE;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_ACTIVITY_TITLE_CHEESE;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_LOCATION_CHEESE;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_TAG_EXPENSIVE;
import static team.easytravel.logic.commands.CommandTestUtil.VALID_TAG_SIGHTSEEING;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseFailure;
import static team.easytravel.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static team.easytravel.testutil.activity.TypicalActivity.ACTIVITY_CHEESE;

import org.junit.jupiter.api.Test;

import team.easytravel.logic.commands.activity.AddActivityCommand;
import team.easytravel.model.listmanagers.activity.Activity;
import team.easytravel.model.listmanagers.activity.Duration;
import team.easytravel.model.listmanagers.activity.Tag;
import team.easytravel.model.util.attributes.Location;
import team.easytravel.model.util.attributes.Title;
import team.easytravel.testutil.activity.ActivityBuilder;

public class AddActivityCommandParserTest {
    private AddActivityCommandParser parser = new AddActivityCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Activity expectedActivity = new ActivityBuilder(ACTIVITY_CHEESE).withTags(VALID_TAG_SIGHTSEEING).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ACTIVITY_TITLE_DESC_CHEESE
                + ACTIVITY_DURATION_DESC_CHEESE
                + ACTIVITY_LOCATION_DESC_CHEESE
                + TAG_DESC_SIGHTSEEING, new AddActivityCommand(expectedActivity));

        // multiple names - last name accepted
        assertParseSuccess(parser, ACTIVITY_TITLE_DESC_FINLAND + ACTIVITY_TITLE_DESC_CHEESE
                + ACTIVITY_DURATION_DESC_CHEESE
                + ACTIVITY_LOCATION_DESC_CHEESE
                + TAG_DESC_SIGHTSEEING, new AddActivityCommand(expectedActivity));

        // multiple Duration - last duration accepted
        assertParseSuccess(parser, ACTIVITY_TITLE_DESC_CHEESE + ACTIVITY_DURATION_DESC_FINLAND
                + ACTIVITY_DURATION_DESC_CHEESE
                + ACTIVITY_LOCATION_DESC_CHEESE
                + TAG_DESC_SIGHTSEEING, new AddActivityCommand(expectedActivity));

        // multiple location - last location accepted
        assertParseSuccess(parser, ACTIVITY_TITLE_DESC_CHEESE + ACTIVITY_DURATION_DESC_CHEESE
                + ACTIVITY_LOCATION_DESC_FINLAND
                + ACTIVITY_LOCATION_DESC_CHEESE
                + TAG_DESC_SIGHTSEEING, new AddActivityCommand(expectedActivity));


        // multiple tags - all accepted
        Activity expectedActivityMultipleTags = new ActivityBuilder(ACTIVITY_CHEESE)
                .withTags(VALID_TAG_SIGHTSEEING, VALID_TAG_EXPENSIVE)
                .build();
        assertParseSuccess(parser, ACTIVITY_TITLE_DESC_CHEESE + ACTIVITY_DURATION_DESC_CHEESE
                + ACTIVITY_LOCATION_DESC_CHEESE
                + TAG_DESC_SIGHTSEEING + TAG_DESC_EXPENSIVE, new AddActivityCommand(expectedActivityMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Activity expectedActivity = new ActivityBuilder(ACTIVITY_CHEESE).withTags().build();
        assertParseSuccess(parser, ACTIVITY_TITLE_DESC_CHEESE + ACTIVITY_DURATION_DESC_CHEESE
                        + ACTIVITY_LOCATION_DESC_CHEESE,
                new AddActivityCommand(expectedActivity));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddActivityCommand.MESSAGE_USAGE);

        // missing title prefix
        assertParseFailure(parser, VALID_ACTIVITY_TITLE_CHEESE + ACTIVITY_DURATION_DESC_CHEESE
                        + ACTIVITY_LOCATION_DESC_CHEESE,
                expectedMessage);

        // missing duration prefix
        assertParseFailure(parser, ACTIVITY_TITLE_DESC_CHEESE + VALID_ACTIVITY_DURATION_CHEESE
                        + ACTIVITY_LOCATION_DESC_CHEESE,
                expectedMessage);

        // missing location prefix
        assertParseFailure(parser, ACTIVITY_TITLE_DESC_CHEESE + ACTIVITY_DURATION_DESC_CHEESE
                        + VALID_LOCATION_CHEESE,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_ACTIVITY_TITLE_CHEESE
                        + VALID_ACTIVITY_DURATION_CHEESE + VALID_LOCATION_CHEESE,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_TITLE_DESC + ACTIVITY_DURATION_DESC_CHEESE
                + ACTIVITY_LOCATION_DESC_CHEESE, Title.MESSAGE_CONSTRAINTS);

        // invalid duration
        assertParseFailure(parser, ACTIVITY_TITLE_DESC_CHEESE + INVALID_DURATION_DESC
                + ACTIVITY_LOCATION_DESC_CHEESE, Duration.MESSAGE_CONSTRAINTS);

        // invalid location
        assertParseFailure(parser, ACTIVITY_TITLE_DESC_CHEESE + ACTIVITY_DURATION_DESC_CHEESE
                + INVALID_LOCATION_DESC, Location.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, ACTIVITY_TITLE_DESC_CHEESE + ACTIVITY_DURATION_DESC_CHEESE
                + ACTIVITY_LOCATION_DESC_CHEESE + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TITLE_DESC + ACTIVITY_DURATION_DESC_CHEESE
                        + INVALID_LOCATION_DESC,
                Title.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + ACTIVITY_TITLE_DESC_CHEESE
                        + ACTIVITY_DURATION_DESC_CHEESE
                        + ACTIVITY_LOCATION_DESC_CHEESE + TAG_DESC_SIGHTSEEING + TAG_DESC_EXPENSIVE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddActivityCommand.MESSAGE_USAGE));
    }
}
