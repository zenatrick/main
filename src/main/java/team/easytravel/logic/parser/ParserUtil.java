package team.easytravel.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import team.easytravel.commons.core.index.Index;
import team.easytravel.commons.core.time.Date;
import team.easytravel.commons.core.time.DateTime;
import team.easytravel.commons.util.StringUtil;
import team.easytravel.logic.commands.fixedexpense.SortFixedExpenseCommand;
import team.easytravel.logic.commands.packinglist.SortItemCommand;
import team.easytravel.logic.parser.exceptions.ParseException;
import team.easytravel.model.listmanagers.accommodationbooking.AccommodationName;
import team.easytravel.model.listmanagers.accommodationbooking.Day;
import team.easytravel.model.listmanagers.accommodationbooking.Remark;
import team.easytravel.model.listmanagers.activity.Duration;
import team.easytravel.model.listmanagers.fixedexpense.Amount;
import team.easytravel.model.listmanagers.fixedexpense.Description;
import team.easytravel.model.listmanagers.fixedexpense.FixedExpenseCategory;
import team.easytravel.model.listmanagers.packinglistitem.ItemCategory;
import team.easytravel.model.listmanagers.packinglistitem.ItemName;
import team.easytravel.model.listmanagers.packinglistitem.Quantity;
import team.easytravel.model.listmanagers.transportbooking.Mode;
import team.easytravel.model.person.Address;
import team.easytravel.model.person.Email;
import team.easytravel.model.person.Name;
import team.easytravel.model.person.Phone;
import team.easytravel.model.trip.Budget;
import team.easytravel.model.util.attributes.Location;
import team.easytravel.model.util.attributes.Title;
import team.easytravel.model.util.attributes.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    /**
     * The constant MESSAGE_INVALID_INDEX.
     */
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @param oneBasedIndex the one based index
     * @return the index
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param name the name
     * @return the name
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param phone the phone
     * @return the phone
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param address the address
     * @return the address
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     *
     * @throws ParseException the parse exception
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String amount} into a {@code Amount}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code amount} is invalid.
     */
    public static Amount parseAmount(String amount) throws ParseException {
        requireNonNull(amount);
        String trimmedAmount = amount.trim();
        if (!Amount.isValidAmount(trimmedAmount)) {
            throw new ParseException(Amount.MESSAGE_CONSTRAINTS);
        }
        return new Amount(trimmedAmount);
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String category} into a {@code FixedExpenseCategory}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code category} is invalid.
     */
    public static FixedExpenseCategory parseCategory(String category) throws ParseException {
        requireNonNull(category);
        String trimmedCategory = category.trim();
        if (!FixedExpenseCategory.isValidCategory(trimmedCategory)) {
            throw new ParseException(FixedExpenseCategory.MESSAGE_CONSTRAINTS);
        }
        return new FixedExpenseCategory(trimmedCategory);
    }

    /**
     * Parses a {@code String sortIdentifier}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code sortIdentifier} is invalid.
     */
    public static String parseSortIdentifier(String sortIdentifier) throws ParseException {
        requireNonNull(sortIdentifier);

        switch (sortIdentifier) {
        case SortFixedExpenseCommand.SORT_ASCENDING:
            return SortFixedExpenseCommand.SORT_ASCENDING;
        case SortFixedExpenseCommand.SORT_DESCENDING:
            return SortFixedExpenseCommand.SORT_DESCENDING;
        default:
            throw new ParseException("String must consist of either high for descending order"
                    + " or low for ascending order");
        }
    }

    /**
     * Parse sort item identifier string.
     *
     * @param sortIdentifier the sort identifier
     * @return the string
     * @throws ParseException the parse exception
     */
    public static String parseSortItemIdentifier(String sortIdentifier) throws ParseException {
        requireNonNull(sortIdentifier);

        switch (sortIdentifier) {
        case SortItemCommand.SORT_ASCENDING:
            return SortItemCommand.SORT_ASCENDING;
        case SortItemCommand.SORT_DESCENDING:
            return SortItemCommand.SORT_DESCENDING;
        default:
            throw new ParseException("String must consist of either high for descending order"
                    + " or low for ascending order");
        }
    }

    /**
     * Parses a {@code String sortParameters} into a {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code sortParameters} is invalid.
     */
    public static String parseSortParameters(String sortParameters) throws ParseException {
        requireNonNull(sortParameters);

        switch (sortParameters) {
        case SortFixedExpenseCommand.AMOUNT:
            return SortFixedExpenseCommand.AMOUNT;

        case SortFixedExpenseCommand.CATEGORY:
            return SortFixedExpenseCommand.CATEGORY;

        case SortFixedExpenseCommand.DESCRIPTION:
            return SortFixedExpenseCommand.DESCRIPTION;

        default:
            throw new ParseException("Parameters must consist of only amount/description/category");
        }

    }


    /**
     * Parse sort item parameters string.
     *
     * @param sortParameters the sort parameters
     * @return the string
     * @throws ParseException the parse exception
     */
    public static String parseSortItemParameters(String sortParameters) throws ParseException {
        requireNonNull(sortParameters);

        switch (sortParameters) {
        case SortItemCommand.ITEM:
            return SortItemCommand.ITEM;

        case SortItemCommand.CATEGORY:
            return SortItemCommand.CATEGORY;

        case SortItemCommand.QUANTITY:
            return SortItemCommand.QUANTITY;

        default:
            throw new ParseException("Parameters must consist of only item/quantity/category");
        }

    }
    /**
     * Parses a {@code String mode} into an {@code Mode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code mode} is invalid.
     */
    public static Mode parseMode(String mode) throws ParseException {
        requireNonNull(mode);
        String trimmedMode = mode.trim();
        if (!Mode.isValidMode(trimmedMode)) {
            throw new ParseException(Mode.MESSAGE_CONSTRAINTS);
        }
        return new Mode(trimmedMode);
    }

    /**
     * Parses a {@code String location} into an {@code Location}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code location} is invalid.
     */
    public static Location parseLocation(String location) throws ParseException {
        requireNonNull(location);
        String trimmedLocation = location.trim();
        if (!Location.isValidLocation(trimmedLocation)) {
            throw new ParseException(Location.MESSAGE_CONSTRAINTS);
        }
        return new Location(trimmedLocation);
    }

    /**
     * Parses a {@code String time} into an {@code DateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dateTime} is invalid.
     */
    public static DateTime parseDateTime(String dateTime) throws ParseException {
        requireNonNull(dateTime);
        String trimmedDateTime = dateTime.trim();
        if (!DateTime.isValidDateTime(trimmedDateTime)) {
            throw new ParseException(DateTime.MESSAGE_CONSTRAINTS);
        }
        return DateTime.fromString(trimmedDateTime);
    }

    /**
     * Parses a {@code String time} into an {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static Date parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!Date.isValidDate(trimmedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        return Date.fromString(trimmedDate);
    }

    /**
     * Parses a {@code String name} into an {@code ItemName}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException the parse exception
     */
    public static ItemName parseItemName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!ItemName.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new ItemName(trimmedName);
    }


    /**
     * Parse {@code String quantity} into a {@code Quantity}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException the parse exception
     */
    public static Quantity parseQuantity(String quantity) throws ParseException {
        requireNonNull(quantity);
        String trimmedQuantity = quantity.trim();
        try {
            Integer intQuantity = Integer.parseInt(trimmedQuantity);
            if (!Quantity.isValidQuantity(intQuantity)) {
                throw new ParseException(Quantity.MESSAGE_CONSTRAINTS);
            }
            return new Quantity(intQuantity);
        } catch (NumberFormatException e) {
            throw new ParseException(Quantity.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String category} into a {@code FixedExpenseCategory}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException the parse exception
     */
    public static ItemCategory parseItemCategory(String category) throws ParseException {
        requireNonNull(category);
        String trimmedCategory = category.trim();
        if (!ItemCategory.isValidItemCategory(trimmedCategory)) {
            throw new ParseException(FixedExpenseCategory.MESSAGE_CONSTRAINTS);
        }
        return new ItemCategory(trimmedCategory);
    }

    /**
     * Parses a {@code String Duration} into a {@code {Duration}}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code duration} is invalid.
     */
    public static Duration parseDuration(String duration) throws ParseException {
        requireNonNull(duration);
        try {
            Integer intDuration = Integer.parseInt(duration);
            if (!Duration.isValidDuration(intDuration)) {
                throw new ParseException(Duration.MESSAGE_CONSTRAINTS);
            }
            return new Duration(intDuration);
        } catch (NumberFormatException e) {
            throw new ParseException(Duration.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String title} into an {@code Title}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code title} is invalid.
     */
    public static Title parseTitle(String title) throws ParseException {
        requireNonNull(title);
        String trimmedTitle = title.trim();
        if (!Title.isValidTitle(trimmedTitle)) {
            throw new ParseException(Title.MESSAGE_CONSTRAINTS);
        }
        return new Title(trimmedTitle);
    }

    /**
     * Parses a {@code String name} into an {@code AccommodationName}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException the parse exception
     */
    public static AccommodationName parseAccommodationName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!AccommodationName.isValidName(trimmedName)) {
            throw new ParseException(AccommodationName.MESSAGE_CONSTRAINTS);
        }
        return new AccommodationName(trimmedName);
    }

    /**
     * Parses a {@code String day} into an {@code Day}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException the parse exception
     */
    public static Day parseDay(String day) throws ParseException {
        requireNonNull(day);
        String trimmedDay = day.trim();
        try {
            Integer intDay = Integer.parseInt(trimmedDay);
            if (!Day.isValidDay(intDay)) {
                throw new ParseException(Day.MESSAGE_CONSTRAINTS);
            }
            return new Day(intDay);
        } catch (NumberFormatException e) {
            throw new ParseException(Day.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String remark} into an {@code Remark}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException the parse exception
     */
    public static Remark parseRemark(String remark) throws ParseException {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        if (!Remark.isValidRemark(trimmedRemark)) {
            throw new ParseException(Remark.MESSAGE_CONSTRAINTS);
        }
        return new Remark(trimmedRemark);
    }

    /**
     * Parses a {@code String budget} into an {@code Budget}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException the parse exception
     */
    public static Budget parseBudget(String budget) throws ParseException {
        requireNonNull(budget);
        String trimmedBudget = budget.trim();
        try {
            Integer intBudget = Integer.parseInt(trimmedBudget);
            if (!Budget.isValidBudget(intBudget)) {
                throw new ParseException(Budget.MESSAGE_CONSTRAINTS);
            }
            return new Budget(intBudget);
        } catch (NumberFormatException e) {
            throw new ParseException(Budget.MESSAGE_CONSTRAINTS);
        }
    }
}
