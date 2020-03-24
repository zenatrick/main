package seedu.address.model.trip;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.core.time.Date;
import seedu.address.model.util.attributes.Title;


/**
 * Represents a Trip in the EasyTravel
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Trip {

    // Trip data
    private final Title title;
    private final Date startDate;
    private final Date endDate;
    private final int numDays;
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

    @Override
    public String toString() {
        return "Trip - Title: " + title
                + " Start date: " + startDate
                + " End date: " + endDate
                + " Duration: " + numDays + " days"
                + " Budget: " + budget;
    }

}
