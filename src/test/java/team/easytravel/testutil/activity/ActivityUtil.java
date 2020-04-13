package team.easytravel.testutil.activity;

import static team.easytravel.logic.parser.CliSyntax.PREFIX_ACTIVITY_DURATION;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_ACTIVITY_LOCATION;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_ACTIVITY_TAG;
import static team.easytravel.logic.parser.CliSyntax.PREFIX_ACTIVITY_TITLE;

import java.util.Set;

import team.easytravel.logic.commands.activity.AddActivityCommand;
import team.easytravel.logic.commands.activity.EditActivityCommand.EditActivityDescriptor;
import team.easytravel.model.listmanagers.activity.Activity;
import team.easytravel.model.listmanagers.activity.Tag;

/**
 * A utility class for activity.
 */
public class ActivityUtil {

    /**
     * Returns an add command string for adding the {@code activity}.
     */
    public static String getAddCommand(Activity activity) {
        return AddActivityCommand.COMMAND_WORD + " " + getActivityDetails(activity);
    }

    /**
     * Returns the part of command string for the given {@code activity}'s details.
     */
    public static String getActivityDetails(Activity activity) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_ACTIVITY_TITLE + activity.getTitle().value + " ");
        sb.append(PREFIX_ACTIVITY_LOCATION + activity.getLocation().value + " ");
        sb.append(PREFIX_ACTIVITY_DURATION + activity.getDuration().value.toString() + " ");
        activity.getTags().stream().forEach(s -> sb.append(PREFIX_ACTIVITY_TAG + s.tagName + " "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditActivityDescriptor}'s details.
     */
    public static String getEditActivityDescriptorDetails(EditActivityDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getTitle().ifPresent(name -> sb.append(PREFIX_ACTIVITY_TITLE).append(name.value).append(" "));
        descriptor.getLocation().ifPresent(location -> sb.append(PREFIX_ACTIVITY_LOCATION).append(location.value)
                .append(" "));
        descriptor.getDuration().ifPresent(duration -> sb.append(PREFIX_ACTIVITY_DURATION).append(duration.value)
                .append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_ACTIVITY_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_ACTIVITY_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
