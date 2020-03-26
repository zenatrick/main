package team.easytravel.logic.parser.packinglist;

import static team.easytravel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import team.easytravel.commons.core.index.Index;
import team.easytravel.logic.commands.packinglist.CheckItemCommand;
import team.easytravel.logic.commands.packinglist.CheckItemCommand.CheckItemDescriptor;
import team.easytravel.logic.parser.Parser;
import team.easytravel.logic.parser.ParserUtil;
import team.easytravel.logic.parser.exceptions.ParseException;

/**
 * The type Check item parser.
 */
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
