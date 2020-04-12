package team.easytravel.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static team.easytravel.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import org.junit.jupiter.api.Test;

import team.easytravel.logic.parser.exceptions.ParseException;
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

    // -- TRIP --
    

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
    //    @Test
    //    public void parseTag_null_throwsNullPointerException() {
    //        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    //    }
    //
    //    @Test
    //    public void parseTag_invalidValue_throwsParseException() {
    //        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    //    }
    //
    //    @Test
    //    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
    //        Tag expectedTag = new Tag(VALID_TAG_1);
    //        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    //    }
    //
    //    @Test
    //    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
    //        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
    //        Tag expectedTag = new Tag(VALID_TAG_1);
    //        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    //    }
    //
    //    @Test
    //    public void parseTags_null_throwsNullPointerException() {
    //        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    //    }
    //
    //    @Test
    //    public void parseTags_collectionWithInvalidTags_throwsParseException() {
    //        Assert.assertThrows(ParseException.class,
    //        () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    //    }
    //
    //    @Test
    //    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
    //        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    //    }
    //
    //    @Test
    //    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
    //        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
    //        Set<Tag> expectedTagSet = new HashSet<>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));
    //
    //        assertEquals(expectedTagSet, actualTagSet);
    //    }
}
