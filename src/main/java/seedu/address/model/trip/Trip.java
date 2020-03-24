package seedu.address.model.trip;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import seedu.address.commons.core.time.Date;
import seedu.address.model.util.attributes.Title;


/**
 * Represents a Trip in the EasyTravel
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Trip {

    // Needed Fields
    private final Title title;
    private final Date startDate;
    private final Date endDate;
    private final int numDays;
    private final List<DaySchedule> daySchedules;
    private final Budget budget;

    /**
     * Every field must be present and not null.
     */
    public Trip(Title title, Date start, Date end, Budget budget) {
        requireAllNonNull(title, start, end, budget);
        this.title = title;
        this.budget = budget;
        this.startDate = start;
        this.endDate = end;
        numDays = start.daysUntilInclusive(end);
        daySchedules = IntStream.range(0, numDays).mapToObj(x -> new DaySchedule()).collect(Collectors.toList());
    }

    public Title getTitle() {
        return title;
    }

    public int getNumDays() {
        return numDays;
    }

    public Budget getBudget() {
        return budget;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public DaySchedule getDaySchedule(int dayIndex) {
        return daySchedules.get(dayIndex);
    }

    @Override
    public String toString() {
        return "Trip - Title: " + title
                + " Start date: " + startDate
                + " End date: " + endDate
                + " Duration: " + numDays + " days"
                + " Budget: " + budget;
    }

}
