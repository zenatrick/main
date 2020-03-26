package team.easytravel.logic.parser.packinglist;

import team.easytravel.commons.core.Messages;
import team.easytravel.commons.core.index.Index;
import team.easytravel.logic.commands.packinglist.UncheckItemCommand;
import team.easytravel.logic.parser.Parser;
import team.easytravel.logic.parser.ParserUtil;
import team.easytravel.logic.parser.exceptions.ParseException;

/**
 * The type Uncheck item parser.
 */
public class UncheckItemCommandParser implements Parser<UncheckItemCommand> {
    @Override
    public UncheckItemCommand parse(String userInput) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(userInput);
            UncheckItemCommand.UncheckItemDescriptor uncheckItemDescriptor =
                    new UncheckItemCommand.UncheckItemDescriptor();
            return new UncheckItemCommand(index, uncheckItemDescriptor);
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    UncheckItemCommand.MESSAGE_USAGE), pe);
        }
    }
}
