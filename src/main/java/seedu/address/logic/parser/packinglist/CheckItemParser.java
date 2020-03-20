package seedu.address.logic.parser.packinglist;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.packinglist.CheckItemCommand;
import seedu.address.logic.commands.packinglist.CheckItemCommand.CheckItemDescriptor;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class CheckItemParser implements Parser<CheckItemCommand> {
    @Override
    public CheckItemCommand parse(String userInput) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(userInput);
            CheckItemDescriptor checkItemDescriptor = new CheckItemDescriptor();
            return new CheckItemCommand(index, checkItemDescriptor);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckItemCommand.MESSAGE_USAGE), pe);
        }
    }
}
