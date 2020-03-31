package team.easytravel.logic.parser.packinglist;

import static team.easytravel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_ITEM_CATEGORY;

import java.util.stream.Stream;

import team.easytravel.logic.commands.packinglist.AddPresetCommand;
import team.easytravel.logic.parser.ArgumentMultimap;
import team.easytravel.logic.parser.ArgumentTokenizer;
import team.easytravel.logic.parser.Parser;
import team.easytravel.logic.parser.Prefix;
import team.easytravel.logic.parser.exceptions.ParseException;
import team.easytravel.model.listmanagers.packinglistpreset.PresetDataUtil;

/**
 * The type Add preset parser.
 */
public class AddPresetCommandParser implements Parser<AddPresetCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPresetCommand
     * and returns an AddPresetCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPresetCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ITEM_CATEGORY);

        if (!arePrefixesPresent(argMultimap, PREFIX_ITEM_CATEGORY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddPresetCommand.MESSAGE_USAGE));
        }

        String category = argMultimap.getValue(PREFIX_ITEM_CATEGORY).get();
        AddPresetCommand addPresetCommand;
        switch(category) {
        case "hiking":
            addPresetCommand = new AddPresetCommand(PresetDataUtil.hikingPreset(), category);
            break;
        case "clothes female":
            addPresetCommand = new AddPresetCommand(PresetDataUtil.clothesFemalePreset(), category);
            break;
        case "clothes male":
            addPresetCommand = new AddPresetCommand(PresetDataUtil.clothesMalePreset(), category);
            break;
        case "toiletries":
            addPresetCommand = new AddPresetCommand(PresetDataUtil.toiletriesPreset(), category);
            break;
        case "swimming":
            addPresetCommand = new AddPresetCommand(PresetDataUtil.swimmingPreset(), category);
            break;
        case "formal dinner f":
            addPresetCommand = new AddPresetCommand(PresetDataUtil.formalDinnerFemalePreset(), category);
            break;
        case "formal dinner m":
            addPresetCommand = new AddPresetCommand(PresetDataUtil.formalDinnerMalePreset(), category);
            break;
        case "beach":
            addPresetCommand = new AddPresetCommand(PresetDataUtil.beachPreset(), category);
            break;
        case "aeroplane":
            addPresetCommand = new AddPresetCommand(PresetDataUtil.aeroplanePreset(), category);
            break;
        case "car":
            addPresetCommand = new AddPresetCommand(PresetDataUtil.carPreset(), category);
            break;
        case "boat":
            addPresetCommand = new AddPresetCommand(PresetDataUtil.boatPreset(), category);
            break;
        case "train":
            addPresetCommand = new AddPresetCommand(PresetDataUtil.trainPreset(), category);
            break;
        case "work":
            addPresetCommand = new AddPresetCommand(PresetDataUtil.workPreset(), category);
            break;
        case "essentials":
            addPresetCommand = new AddPresetCommand(PresetDataUtil.essentialsPreset(), category);
            break;
        case "international":
            addPresetCommand = new AddPresetCommand(PresetDataUtil.internationalPreset(), category);
            break;
        case "camping":
            addPresetCommand = new AddPresetCommand(PresetDataUtil.campingPreset(), category);
            break;
        case "snow":
            addPresetCommand = new AddPresetCommand(PresetDataUtil.snowPreset(), category);
            break;
        case "gym":
            addPresetCommand = new AddPresetCommand(PresetDataUtil.gymPreset(), category);
            break;
        default:
            throw new ParseException(String.format("Sorry! Preset does not exist."));
        }
        return addPresetCommand;
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
