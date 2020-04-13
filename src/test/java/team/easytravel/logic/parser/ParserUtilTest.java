package team.easytravel.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static team.easytravel.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import team.easytravel.commons.core.time.Date;
import team.easytravel.commons.core.time.DateTime;
import team.easytravel.logic.parser.exceptions.ParseException;
import team.easytravel.model.listmanagers.accommodationbooking.AccommodationName;
import team.easytravel.model.listmanagers.activity.Duration;
import team.easytravel.model.listmanagers.activity.Tag;
import team.easytravel.model.listmanagers.fixedexpense.Amount;
import team.easytravel.model.listmanagers.fixedexpense.Description;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpenseCategory;
import team.easytravel.model.listmanagers.packinglistitem.ItemCategory;
import team.easytravel.model.listmanagers.packinglistitem.ItemName;
import team.easytravel.model.listmanagers.packinglistitem.Quantity;
import team.easytravel.model.listmanagers.transportbooking.Mode;
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
    public static final Integer VALID_DURATION = 1;

    // -- Accommodation --
    public static final String INVALID_ACC_NAME = "."; //non alphanumerica
    public static final String VALID_ACC_NAME = "Hillet";
    public static final String INVALID_DAY = "a";
    public static final String VALID_DAY = "28-09-2020";

    //--FixedExpense
    public static final String INVALID_AMOUNT = "KK";
    public static final String VALID_AMOUNT = "100.00";
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
    public static final Integer VALID_QUANTITY = 2;

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
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseTitle(null));
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
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseLocation(null));
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
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseDateTime(null));
    }

    @Test
    public void parseDateTime_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseDateTime(INVALID_DATE));
    }

    @Test
    public void parseDateTime_validValueWithoutWhitespace_returnsDateTime() throws Exception {
        DateTime expectedDateTime = DateTime.fromString(VALID_DATE);
        assertEquals(expectedDateTime, ParserUtil.parseDateTime(VALID_DATE));
    }

    @Test
    public void parseDateTime_validValueWithWhitespace_returnsTrimmedAddressDateTime() throws Exception {
        String dateTimeWithWhitespace = WHITESPACE + VALID_DATE + WHITESPACE;
        DateTime expectedDateTime = DateTime.fromString(VALID_DATE);
        assertEquals(expectedDateTime, ParserUtil.parseDateTime(dateTimeWithWhitespace));
    }

    // -- TRIP --
    //exchangeRate
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

    //duration
    @Test
    public void parseDuration_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseDuration(null));
    }

    @Test
    public void parseDuration_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseDuration(INVALID_DURATION));
    }

    @Test
    public void parseDuration_validValueWithoutWhitespace_returnsDuration() throws Exception {
        Duration expectedDuration = new Duration(VALID_DURATION);
        assertEquals(expectedDuration, ParserUtil.parseDuration(VALID_DURATION.toString()));
    }

    @Test
    public void parseDuration_validValueWithWhitespace_returnsTrimmedDuration() throws Exception {
        String durationWithWhitespace = WHITESPACE + VALID_DURATION + WHITESPACE;
        Duration expectedDuration = new Duration(VALID_DURATION);
        assertEquals(expectedDuration, ParserUtil.parseDuration(durationWithWhitespace));
    }

    // -- Accommodation --
    @Test
    public void parseDate_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseDate(null));
    }

    @Test
    public void parseDate_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DAY));
    }

    @Test
    public void parseDate_validValueWithoutWhitespace_returnsDate() throws Exception {
        Date expectedDate = Date.fromString(VALID_DAY);
        assertEquals(expectedDate, ParserUtil.parseDate(VALID_DAY));
    }

    @Test
    public void parseDate_validValueWithWhitespace_returnsTrimmedDate() throws Exception {
        String dateWithWhite = WHITESPACE + VALID_DAY + WHITESPACE;
        Date expectedDate = Date.fromString(VALID_DAY);
        assertEquals(expectedDate, ParserUtil.parseDate(dateWithWhite));
    }

    @Test
    public void parseAccommodationName_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseAccommodationName(null));
    }

    @Test
    public void parseAccommodationName_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseAccommodationName(INVALID_ACC_NAME));
    }


    @Test
    public void parseAccommodationName_validValueWithoutWhitespace_returnsAccommodationName() throws Exception {
        AccommodationName expectedName = new AccommodationName(VALID_ACC_NAME);
        assertEquals(expectedName, ParserUtil.parseAccommodationName(VALID_ACC_NAME));
    }

    @Test
    public void parseAccommodationName_validValueWithWhitespace_returnsTrimmedAccommodationName() throws Exception {
        String accommodationWithWhite = WHITESPACE + VALID_ACC_NAME + WHITESPACE;
        AccommodationName expectedName = new AccommodationName(VALID_ACC_NAME);
        assertEquals(expectedName, ParserUtil.parseAccommodationName(accommodationWithWhite));
    }

    //-- Fix Expense --
    @Test
    public void parseAmount_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseAmount(null));
    }

    @Test
    public void parseAmount_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseAmount(INVALID_AMOUNT));
    }

    @Test
    public void parseAmount_validValueWithoutWhitespace_returnsAmount() throws Exception {
        Amount expectedAmount = new Amount(VALID_AMOUNT);
        assertEquals(expectedAmount, ParserUtil.parseAmount(VALID_AMOUNT));
    }

    @Test
    public void parseAmount_validValueWithWhitespace_returnsTrimmedAmount() throws Exception {
        String amountWithWhite = WHITESPACE + VALID_AMOUNT + WHITESPACE;
        Amount expectedAmount = new Amount(VALID_AMOUNT);
        assertEquals(expectedAmount, ParserUtil.parseAmount(amountWithWhite));
    }

    @Test
    public void parseCategory_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseFixedExpenseCategory(null));
    }

    @Test
    public void parseCategory_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseFixedExpenseCategory(INVALID_CATEGORY));
    }

    @Test
    public void parseCategory_validValueWithoutWhitespace_returnsCategory() throws Exception {
        FixedExpenseCategory expected = new FixedExpenseCategory(VALID_CATEGORY);
        assertEquals(expected, ParserUtil.parseFixedExpenseCategory(VALID_CATEGORY));
    }

    @Test
    public void parseCategory_validValueWithWhitespace_returnsTrimmedCategory() throws Exception {
        String catWithWhite = WHITESPACE + VALID_CATEGORY + WHITESPACE;
        FixedExpenseCategory expected = new FixedExpenseCategory(VALID_CATEGORY);
        assertEquals(expected, ParserUtil.parseFixedExpenseCategory(catWithWhite));
    }

    @Test
    public void parseDesc_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseDescription(null));
    }

    @Test
    public void parseDesc_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseDescription(INVALID_DESC));
    }

    @Test
    public void parseDesc_validValueWithoutWhitespace_returnsDesc() throws Exception {
        Description expectedDesc = new Description(VALID_DESC);
        assertEquals(expectedDesc, ParserUtil.parseDescription(VALID_DESC));
    }

    @Test
    public void parseDesc_validValueWithWhitespace_returnsTrimmedDesc() throws Exception {
        String descWithWhite = WHITESPACE + VALID_DESC + WHITESPACE;
        Description expectedDesc = new Description(VALID_DESC);
        assertEquals(expectedDesc, ParserUtil.parseDescription(descWithWhite));
    }

    // -- Packing list --

    //item cat
    @Test
    public void parseItemCat_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseItemCategory(null));
    }

    @Test
    public void parseItemCat_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseItemCategory(INVALID_ITEM_CAT));
    }

    @Test
    public void parseItemCat_validValueWithoutWhitespace_returnsItemCat() throws Exception {
        ItemCategory expectedItemCat = new ItemCategory(VALID_ITEM_CAT);
        assertEquals(expectedItemCat, ParserUtil.parseItemCategory(VALID_ITEM_CAT));
    }

    @Test
    public void parseItemCat_validValueWithWhitespace_returnsTrimmedItemCat() throws Exception {
        String itemCatWithWhitespace = WHITESPACE + VALID_ITEM_CAT + WHITESPACE;
        ItemCategory expectedItemCat = new ItemCategory(VALID_ITEM_CAT);
        assertEquals(expectedItemCat, ParserUtil.parseItemCategory(itemCatWithWhitespace));
    }

    //itemname
    @Test
    public void parseItemName_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseItemName((String) null));
    }

    @Test
    public void parseItemName_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseItemName(INVALID_ITEM_NAME));
    }

    @Test
    public void parseItemName_validValueWithoutWhitespace_returnsItemName() throws Exception {
        ItemName expectedItemName = new ItemName(VALID_ITEM_NAME);
        assertEquals(expectedItemName, ParserUtil.parseItemName(VALID_ITEM_NAME));
    }

    @Test
    public void parseItemName_validValueWithWhitespace_returnsTrimmedItemName() throws Exception {
        String itemNameWithWhitespace = WHITESPACE + VALID_ITEM_NAME + WHITESPACE;
        ItemName expectedItemName = new ItemName(VALID_ITEM_NAME);
        assertEquals(expectedItemName, ParserUtil.parseItemName(itemNameWithWhitespace));
    }

    //quantity
    @Test
    public void parseQuantity_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseQuantity(null));
    }

    @Test
    public void parseQuantity_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseQuantity(INVALID_QUANTITY));
    }

    @Test
    public void parseQuantity_validValueWithoutWhitespace_returnsQuantity() throws Exception {
        Quantity expectedQuantity = new Quantity(VALID_QUANTITY);
        assertEquals(expectedQuantity, ParserUtil.parseQuantity(VALID_QUANTITY.toString()));
    }

    @Test
    public void parseQuantity_validValueWithWhitespace_returnsTrimmedQuantity() throws Exception {
        String quantityWithWhitespace = WHITESPACE + VALID_QUANTITY + WHITESPACE;
        Quantity expectedQuantity = new Quantity(VALID_QUANTITY);
        assertEquals(expectedQuantity, ParserUtil.parseQuantity(quantityWithWhitespace));
    }

    // -- Transport --
    @Test
    public void parseMode_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> ParserUtil.parseMode(null));
    }

    @Test
    public void parseMode_invalidValue_throwsParseException() {
        Assert.assertThrows(ParseException.class, () -> ParserUtil.parseMode(INVALID_MODE));
    }

    @Test
    public void parseMode_validValueWithoutWhitespace_returnsMode() throws Exception {
        Mode expectedMode = new Mode(VALID_MODE);
        assertEquals(expectedMode, ParserUtil.parseMode(VALID_MODE));
    }

    @Test
    public void parseMode_validValueWithWhitespace_returnsTrimmedMode() throws Exception {
        String modeWithWhitespace = WHITESPACE + VALID_MODE + WHITESPACE;
        Mode expectedMode = new Mode(VALID_MODE);
        assertEquals(expectedMode, ParserUtil.parseMode(modeWithWhitespace));
    }

}
