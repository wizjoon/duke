package duke.Tasklist;

import duke.Duke;
import duke.Exception.DukeException;
import duke.Exception.dateparseException;
import duke.Exception.timeparseException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Deadlines extends Task {

    protected String by;
    protected LocalDate deadliner;
    private LocalTime timerliner;

    /**
     * Deadline class constructor
     * @param description keep the String of task description. Eg: submit report
     * @param by keep the String of date and time of task to be done before
     * @throws DateTimeParseException is throwable when LocalDate.parse and LocalDate.parse got error.
     * @throws NullPointerException is throwable when certain information is not provided such as time or date
     * @throws dateparseException self-created exception, is throwable when receive DateTimeParseException. To indicate that date parse got error
     * @throws timeparseException self-created exception, is throwable when receive DateTimeParseException. To indicate that time parse got error
     * @throws DukeException self-created exception, is throwable when there is any error in input by user.
     */
    public Deadlines(String description, String by) throws dateparseException, DukeException, NullPointerException, timeparseException, DateTimeParseException {
        super(description);
        this.by = by;
        try {
            String[] d_t = by.split(" "); // by YYYY-MM-DD HHMM  (in 24 hours format)
            Integer by_index = -1;
            for (Integer i = 0; (i < d_t.length && by_index == -1); i++) {
                if (d_t[i].equals("by")) {
                    by_index = i;
                }
                if (!d_t[i].equals("by") && i == d_t.length - 1) {
                    throw new DukeException();
                }
            }
            this.deadliner = LocalDate.parse(d_t[by_index + 1]); //YYYY-MM-DD
            String time = tokenize_date(Integer.parseInt(d_t[by_index + 2]));
            this.timerliner = LocalTime.parse(time); //HHMM Eg 1800, 0000, 2359
        } catch (DateTimeParseException e) {
            System.out.println("DateTimeParseException: "+"Date Time parse Error");
            throw new dateparseException();
        }catch (NumberFormatException e) {
            System.out.println("NumberFormatException: "+"Weird time detected");
            throw new timeparseException();
        }
    }

    /**
     * @param passed Integer that represent the time in 24 hours format
     * @return Return String of time in HH:mm format
     * @throws NumberFormatException is throwable when time being passed in is out of range.
     */
    private static String tokenize_date(Integer passed) throws NumberFormatException{
        Integer mins = passed%100;
        Integer hours = passed/100;
        if (mins> 59 || mins < 0 || hours > 23 || hours<0)
        {
            throw new NumberFormatException();
        }
        String str_mins = String.format("%02d",mins);
        String str_hours = String.format("%02d",hours);
        return str_hours + ":"+str_mins;
    }
    /**
     * Return a String of task status
     * @return the status of task including whether is it done and its description and date time if have
     */
    public String getStatus() {
        if (timerliner == null) {
            return (isDone ? "[D][X] " + this.description + " (" + deadliner.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")" : "[D][ ] " + this.description + " (" + deadliner.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
        }
        return (isDone ? "[D][X] " + this.description + " (" + deadliner.format(DateTimeFormatter.ofPattern("MMM d yyyy ")) + timerliner.format(DateTimeFormatter.ofPattern("hh:mma")) + ")" : "[D][ ] " + this.description + " (" + deadliner.format(DateTimeFormatter.ofPattern("MMM d yyyy ")) + timerliner.format(DateTimeFormatter.ofPattern("hh:mma")) + ")");
    }
    /**
     * Return a String of deadline task with its date and time
     */
    public String getBy() {
        return by;
    }

    /**
     * Return a LocalDate of deadline
     */
    public LocalDate getDate() {
        return deadliner;
    }

    /**
     * Return a LocalTime of deadline timing
     */
    public LocalTime getTime() {
        return timerliner;
    }

    /**
     *
     * @param passed Pass in the getStatus() method of Deadlines class
     * @return Return a String in JUnit testing.
     */
    public static String testgetStatus(String passed) {
        return (passed);
    }
}
