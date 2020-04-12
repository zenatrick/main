package team.easytravel.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static team.easytravel.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import team.easytravel.commons.core.time.DateTime;
import team.easytravel.logic.parser.exceptions.ParseException;
import team.easytravel.model.listmanagers.activity.Tag;
import team.easytravel.model.trip.Budget;
import team.easytravel.model.trip.ExchangeRate;
import team.easytravel.model.util.attributes.Location;
import team.easytravel.model.util.attributes.Title;
import team.easytravel.testutil.Assert;
import team.easytravel.testutil.TypicalIndexes;

public class ParserUtilTest {

    //--- Commons --
    public static final String INVALID_TITLE = "Cheese land&"; // '&' not allowed in title
    public static final String VALID_TITLE = "this is a pretty good title";
    public static final String INVALID_LOCATION = "&&&"; // '&' is not allowed
    public static final String VALID_LOCATION = "Cheese land";
    public static final String INVALID_DATE = "a"; //start date must be a date
    public static final String VALID_DATE = "28-09-2020 14:00";

    //-- Trip --
    public static final String INVALID_EXCHANGE_RATE = "sss"; //cannot be 0
    public static final Double VALID_EXCHANGE_RATE = 17.3;
    public static final Integer INVALID_BUDGET = -33; //cannot be negative
    public static final Integer VALID_BUDGET = 1700;

    //-- Activity --
    public static final String INVALID_TAG = "&"; //Tag cannot be '&'
    public static final String VALID_TAG_1 = "expensive";
    public static final String VALID_TAG_2 = "cool";
    public static final String INVALID_DURATION = "$"; //THIS IS NOT A VALID
    public static final String VALID_DURATION = "1";

    // -- Accommodation --
    public static final String INVALID_ACC_NAME = "."; //non alphanumerica
    public static final String VALID_ACC_NAME = "Hillet";
    public static final String INVALID_DAY = "a";
    public static final String VALID_DAY = "28-09-2020";

    //--FixedExpense
    public static final String INVALID_AMOUNT = "KK";
    public static final String VALID_AMOUNT = "100";
    public static final String INVALID_DESC = "&&**";
    public static final String VALID_DESC = "This is a valid desc";
    public static final String VALID_CATEGORY = "activities";
    public static final String INVALID_CATEGORY = "baka";

    // -- PackingListitem --
    public static final String INVALID_ITEM_CAT = "^^^";
    public static final String VALID_ITEM_CAT = "male";
    public static final String INVALID_ITEM_NAME = "(((";
    public static final String VALID_ITEM_NAME = "underwear";
    public static final String INVALID_QUANTITY = "2SS";
    public static final String VALID_QUANTITY = "2";

    // -- Transportbooking --
    public static final String INVALID_MODE = "stupid";
    public static final String VALID_MODE = "plane";
    
    private static final String WHITESPACE = " \t\r\n";




    //-- Commons --

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        Assert.assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(TypicalIndexes.INDEX_FIRST, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(TypicalIndexes.INDEX_FIRST, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseTitle_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseTitle((String) null));
    }

    @Test
    public void parseTitle_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseTitle(INVALID_TITLE));
    }

    @Test
    public void parseTitle_validValueWithoutWhitespace_returnsTitle() throws Exception {
        Title expectedName = new Title(VALID_TITLE);
        assertEquals(expectedName, ParserUtil.parseTitle(VALID_TITLE));
    }

    @Test
    public void parseTitle_validValueWithWhitespace_returnsTrimmedTitle() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_TITLE + WHITESPACE;
        Title expectedName = new Title(VALID_TITLE);
        assertEquals(expectedName, ParserUtil.parseTitle(nameWithWhitespace));
    }

    //location
    @Test
    public void parseLocation_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseLocation((String) null));
    }

    @Test
    public void parseLocation_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseLocation(INVALID_LOCATION));
    }

    @Test
    public void parseLocation_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Location expectedLocation = new Location(VALID_LOCATION);
        assertEquals(expectedLocation, ParserUtil.parseLocation(VALID_LOCATION));
    }

    @Test
    public void parseLocation_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String locationWithWhitespace = WHITESPACE + VALID_LOCATION + WHITESPACE;
        Location expectedLocation = new Location(VALID_LOCATION);
        assertEquals(expectedLocation, ParserUtil.parseLocation(locationWithWhitespace));
    }

    //location
    @Test
    public void parseDateTime_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseDateTime((String) null));
    }

    @Test
    public void parseDateTime_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseDateTime(INVALID_DATE));
    }

    @Test
    public void parseDateTime_validValueWithoutWhitespace_returnsAddress() throws Exception {
        DateTime expectedDateTime = DateTime.fromString(VALID_DATE);
        assertEquals(expectedDateTime, ParserUtil.parseDateTime(VALID_DATE));
    }

    @Test
    public void parseDateTime_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String dateTimeWithWhitespace = WHITESPACE + VALID_DATE + WHITESPACE;
        DateTime expectedDateTime = DateTime.fromString(VALID_DATE);
        assertEquals(expectedDateTime, ParserUtil.parseDateTime(dateTimeWithWhitespace));
    }

    // -- TRIP --
    //location
    @Test
    public void parseExchangeRate_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseExchangeRate(null));
    }

    @Test
    public void parseExchangeRate_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseExchangeRate(INVALID_EXCHANGE_RATE));
    }

    @Test
    public void parseExchangeRate_validValueWithoutWhitespace_returnsExchangeRate() throws Exception {
        ExchangeRate expectedExchangeRate = new ExchangeRate(VALID_EXCHANGE_RATE);
        assertEquals(expectedExchangeRate, ParserUtil.parseExchangeRate(VALID_EXCHANGE_RATE.toString()));
    }

    @Test
    public void parseExchangeRate_validValueWithWhitespace_returnsTrimmedExchangeRate() throws Exception {
        String exchangeRateWithWhite = WHITESPACE + VALID_EXCHANGE_RATE + WHITESPACE;
        ExchangeRate expectedExchangeRate = new ExchangeRate(VALID_EXCHANGE_RATE);
        assertEquals(expectedExchangeRate, ParserUtil.parseExchangeRate(exchangeRateWithWhite));
    }

    //budget
    @Test
    public void parseBudget_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseBudget(null));
    }

    @Test
    public void parseBudget_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseBudget(INVALID_BUDGET.toString()));
    }


    @Test
    public void parseBudget_validValueWithoutWhitespace_returnsBudget() throws Exception {
        Budget expectedBudget = new Budget(VALID_BUDGET);
        assertEquals(expectedBudget, ParserUtil.parseBudget(VALID_BUDGET.toString()));
    }

    @Test
    public void parseBudget_validValueWithWhitespace_returnsTrimmedBudget() throws Exception {
        String budgetWithWhite = WHITESPACE + VALID_BUDGET + WHITESPACE;
        Budget expectedBudget = new Budget(VALID_BUDGET);
        assertEquals(expectedBudget, ParserUtil.parseBudget(budgetWithWhite));
    }

    // -- Activity --
    @Test
    public void parseTag_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        Assert.assertThrows(ParseException.class, () ->
                ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }




    //    @Test
    //    public void parsePhone_null_throwsNullPointerException() {
    //        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    //    }
    //
    //    @Test
    //    public void parsePhone_invalidValue_throwsParseException() {
    //        Assert.assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    //    }
    //
    //    @Test
    //    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
    //        Phone expectedPhone = new Phone(VALID_PHONE);
    //        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    //    }
    //
    //    @Test
    //    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
    //        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
    //        Phone expectedPhone = new Phone(VALID_PHONE);
    //        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    //    }
    //
    //
    //    @Test
    //    public void parseEmail_null_throwsNullPointerException() {
    //        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    //    }
    //
    //    @Test
    //    public void parseEmail_invalidValue_throwsParseException() {
    //        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    //    }
    //
    //    @Test
    //    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
    //        Email expectedEmail = new Email(VALID_EMAIL);
    //        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    //    }
    //
    //    @Test
    //    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
    //        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
    //        Email expectedEmail = new Email(VALID_EMAIL);
    //        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    //    }
    //

}
