package team.easytravel.logic.parser.activity;

import team.easytravel.commons.core.Messages;
import team.easytravel.commons.core.index.Index;
import team.easytravel.logic.commands.activity.DeleteActivityCommand;
import team.easytravel.logic.parser.Parser;
import team.easytravel.logic.parser.ParserUtil;
import team.easytravel.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteActivityCommand.
 */
public class DeleteActivityCommandParser implements Parser<DeleteActivityCommand> {

    @Override
    public DeleteActivityCommand parse(String userInput) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(userInput);
            return new DeleteActivityCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_DISPLAYED_INDEX_FORMAT, "activity"), pe);
        }
    }
}
