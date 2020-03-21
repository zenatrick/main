package seedu.address.logic.parser.activity;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.activity.EditActivityCommand;
import seedu.address.logic.commands.fixedexpense.EditFixedExpenseCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.util.attributes.tag.Tag;

/**
 * Parses input arguments and creates a new EditActivityCommand.
 */
public class EditActivityParser implements Parser<EditActivityCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditActivityCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE ,
                        PREFIX_PRIORITY, PREFIX_DURATION, PREFIX_LOCATION, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditActivityCommand.MESSAGE_USAGE), pe);
        }

        EditActivityCommand.EditActivityDescriptor editActivityDescriptor =
                new EditActivityCommand.EditActivityDescriptor();

        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editActivityDescriptor.setTitle(ParserUtil
                    .parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            editActivityDescriptor.setPriority(ParserUtil
                    .parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get()));
        }
        if (argMultimap.getValue(PREFIX_DURATION).isPresent()) {
            editActivityDescriptor.setDuration(ParserUtil
                    .parseDuration(argMultimap.getValue(PREFIX_DURATION).get()));
        }
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            editActivityDescriptor.setLocation(ParserUtil
                    .parseLocation(argMultimap.getValue(PREFIX_LOCATION).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editActivityDescriptor::setTags);

        if (!editActivityDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditActivityCommand.MESSAGE_NOT_EDITED);
        }

        return new EditActivityCommand(index, editActivityDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}
