package seedu.address.model.trip;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Date;
/**
 * Represents a Trip in the EasyTravel
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Trip {

    // Needed Fields
    private final Date startDay;
    private final Date endDay;
    private final Scheduler scheduler;
    private final Budget budget;
    private final float duration;
    private final TripTitle title;


    /**
     * Every field must be present and not null.
     */
    public Trip(TripTitle title, Date start, Date end, Budget budget) {
        requireAllNonNull(title, start, end, budget);
        this.title = title;
        this.startDay = start;
        this.endDay = end;
        this.scheduler = new Scheduler(); //replaced with stubed
        this.budget = budget;
        long difference = endDay.getTime() - startDay.getTime();
        this.duration = (difference / (1000 * 60 * 60 * 24));

    }

    public TripTitle getTitle() {
        return title;
    }

    public float getDuration() {
        return duration;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public Budget getBudget() {
        return budget;
    }

    public Date getStartDay() {
        return startDay;
    }

    public Date getEndDay() {
        return endDay;
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append(" Duration: ")
                .append(getDuration())
                .append(" Start day: ")
                .append(getStartDay())
                .append(" End day: ")
                .append(getEndDay())
                .append(" Budget: ")
                .append(getBudget())
                .append(getBudget());
        return builder.toString();
    }

}
