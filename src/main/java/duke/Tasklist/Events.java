package duke.Tasklist;

import duke.Duke;
import duke.Exception.DukeException;
import duke.Exception.timeparseException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Events extends Task {

    protected String details;
    public LocalDate first_occur;

    /**
     * Events(activity or event, details of activity or event on which date)
     * @param description Pass the String of description of task. Eg attend seminar
     * @param details Pass the String of task details/event details with its date. Date here always follow "on".
     *                Eg: at NUS with Mr Tan on 2022-10-01
     * @throws DateTimeParseException is throwable when LocalDate.parse and LocalDate.parse got error.
     * @throws DukeException self-created exception, is throwable when there is any error in input by user.
     */
    public Events(String description, String details) throws DateTimeParseException, DukeException {
        super(description);
        try {
            String[] splited_details = details.split(" "); // at woodland on YYYY-MM-DD
            Integer on_index = -1;
            String _temp ="";
            for (Integer i = 0; (i < splited_details.length && on_index == -1); i++) {
                if (splited_details[i].trim().equals("on")) {
                    on_index = i;
                }
                if (!splited_details[i].trim().equals("on") && i == splited_details.length-1) {
                    throw new DukeException();
                }
                if (!splited_details[i].trim().equals("on")) {
                    if(_temp != "") {
                        _temp += " "+ splited_details[i].trim();
                    }
                    else {
                        _temp += splited_details[i].trim();
                    }
                }
            }
            this.details = _temp;
            this.first_occur = LocalDate.parse(splited_details[on_index + 1]); //YYYY-MM-DD
        } catch (DateTimeParseException e) {
            System.out.println("DateTimePraseException: Date format should be YYYY-MM-DD");
            throw new DukeException();
        }
    }
    /**
     *
     * @return String of status of task including whether is it done and its details and date
     */
    public String getStatus() {
        return (isDone ? "[E][X] " + this.description + " (" + details + " on " + first_occur.format(DateTimeFormatter.ofPattern("MMM d YYYY")) + ")" : "[E][ ] " + this.description + " (" + details + " on " + first_occur.format(DateTimeFormatter.ofPattern("MMM d YYYY")) + ")");
    }

    /**
     *
     * @return String of details of task including date.
     */
    public String getDetails() {
        return details + " on " + first_occur.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
    }
    /**
     *
     * @return LocalDate of date of task
     */
    public LocalDate getDate() {
        return first_occur;
    }
    /**
     *
     * @param passed Pass in the getStatus() method of Events class
     * @return Return a String in JUnit testing.
     */
    public static String testgetStatus(String passed) {
        return (passed);
    }
}