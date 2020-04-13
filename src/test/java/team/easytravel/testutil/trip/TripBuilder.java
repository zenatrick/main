package team.easytravel.testutil.trip;

import team.easytravel.commons.core.time.Date;
import team.easytravel.model.trip.Budget;
import team.easytravel.model.trip.ExchangeRate;
import team.easytravel.model.trip.Trip;
import team.easytravel.model.util.attributes.Title;

/**
 * A utility class to help with building Person objects.
 */
public class TripBuilder {

    public static final String DEFAULT_TITLE = "CHEESELAND OWO";
    public static final Integer DEFAULT_BUDGET = 2000;
    public static final Double DEFAULT_EXCHANGE_RATE = 1.03;
    public static final String DEFAULT_START = "28-09-2020";
    public static final String DEFAULT_END = "5-10-2020";

    private Title title;
    private Date start;
    private Date end;
    private Budget budget;
    private ExchangeRate exchangeRate;

    public TripBuilder() {
        title = new Title(DEFAULT_TITLE);
        start = Date.fromString(DEFAULT_START);
        end = Date.fromString(DEFAULT_END);
        budget = new Budget(DEFAULT_BUDGET);
        exchangeRate = new ExchangeRate(DEFAULT_EXCHANGE_RATE);
    }

    /**
     * Initializes the TripBuilder with the data of {@code trip}.
     */
    public TripBuilder(Trip trip) {
        title = trip.getTitle();
        start = trip.getStartDate();
        end = trip.getEndDate();
        budget = trip.getBudget();
        exchangeRate = trip.getExchangeRate();
    }


    public Trip build() {
        return new Trip(title, start, end, budget, exchangeRate);
    }

}
