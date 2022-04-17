package duke.Tasklist;

import duke.Exception.DukeException;
/**
 * Recurring class extends Events
 * new variable : number. number of recurring task intended
 * new variable : between. the interval of recurring task
 */


public class RecurringTask extends Events {

    protected Integer number;
    protected Integer between;

    /**
     *
     * @param description Pass the String of description of task. Eg attend seminar
     * @param info Pass the String of task details/event details with its date. Date here always follow "on".
     *                      Eg: at NUS with Mr Tan on 2022-10-01
     * @param n_of_recur Pass the number of recurring of event.
     * @param gap Pass the time interval of event that recurring in unit of day.
     * @throws DukeException self-created exception, is throwable when there is any error in input by user.
     */
    public RecurringTask(String description, String info, Integer n_of_recur, Integer gap) throws DukeException {

        super(description, info);
        assert (gap > 0 && n_of_recur > 0);
        this.number = n_of_recur;
        this.between = gap;
    }
    /**
     *
     * @param multiple Pass in to calculate the next date of recurring event.
     */
    public void add_day(Integer multiple) {
        first_occur = first_occur.plusDays(between * multiple);
    }

}

