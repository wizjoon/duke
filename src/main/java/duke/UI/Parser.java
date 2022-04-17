package duke.UI;

import duke.Exception.DukeException;
import duke.Exception.dateparseException;
import duke.Exception.timeparseException;
import duke.Tasklist.*;
import duke.command.*;

import java.lang.module.FindException;
import java.time.format.DateTimeParseException;


public class Parser {
    /**
     *
     * @param between Pass in recurring event interval as String.
     * @return Return the recurring event interval as Integer
     * @throws NumberFormatException is throwable when parse non-integer character.
     */
    public static Integer checkRecurring_gap(String between) throws NumberFormatException {
        return Integer.parseInt(between);
    }

    /**
     *
     * @param number Pass in number of time of recurring event as String.
     * @return Return the number of time of recurring event as Integer
     * @throws NumberFormatException is throwable when parse non-integer character.
     */
    public static Integer checkRecurring_count(String number) throws NumberFormatException {
        return Integer.parseInt(number);
    }

    /**
     *
     * @param str Pass in an array of String to be combined
     * @param start Pass in the first index of String to be combined
     * @param end Pass in the last index of String to be combined
     * @return Return combined String
     */
    private static String str_concat(String[] str, int start, int end) {
        String ans = "";
        for (int i = start; i <= end; i++) {
            ans += (str[i] + " ");
        }
        return ans;
    }

    /**
     *
     * @param passed String array to be checked
     * @return Return true if passed array size is bigger than 1
     * @throws IndexOutOfBoundsException is throwable when the passed array size is 1
     */
    private static boolean check_length(String[] passed) throws IndexOutOfBoundsException {
        if (passed[1].isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return true;
    }

    /**
     *
     * @param passed_task Pass Task in to be parsed and write into file
     * @return Return String that to be written into file
     */
    public static String parsing_to_write(Task passed_task) {
        String str_1 = null;
        String str_2 = "0";
        String str_3;
        String str_4 = "";
        if (passed_task instanceof Deadlines) {
            str_1 = "D";
        }
        if (passed_task instanceof Events) {
            str_1 = "E";
        }
        if (passed_task instanceof ToDo) {
            str_1 = "T";
        }
        if (passed_task instanceof RecurringTask) {
            str_1 = "E";
        }
        if (passed_task.isDone) {
            str_2 = "1";
        }
        str_3 = passed_task.description;
        if (passed_task instanceof Deadlines) {
            str_4 = ((Deadlines) passed_task).getBy();
        }
        if (passed_task instanceof Events) {
            str_4 = ((Events) passed_task).getDetails();
        }
        if (passed_task instanceof RecurringTask) {
            str_4 = ((RecurringTask) passed_task).getDetails();
        }

        if(passed_task instanceof ToDo) {
            return (str_1 + " | " + str_2 + " | " + str_3);
        }
        else {
            return (str_1 + " | " + str_2 + " | " + str_3 + " | " + str_4);
        }
    }

    /**
     *
     * @param passed Pass the String input by user to be parsed.
     * @return Return command to be carried to be carried out in Duke
     * @throws FindException is throwable when user input "find" and the format got error such as multiple target
     *                       Eg: "find report" is correct
     *                       EgL "find submit report" is wrong because can only find 1 word at a time.
     */
    public static Command parsing(String passed) throws DukeException, FindException{
        String[] str = new String[20]; // to keep the passed sentence into string array
        try {       // parse to String array when received the passed task
            str = passed.split(" ");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException: "+"Index out of bound");
        }
        if (str.length == 2 && (str[0].equals("mark") || str[0].equals("unmark"))) //if is mark or unmark and second is numeric
        {
            int number;
            try { // if first word is "mark" or "unmark", check its second words must be an integer
                number = Integer.parseInt(str[1]);
                MarkCommand passed_command = new MarkCommand(str[0], number);
                return passed_command;
            } catch (NumberFormatException ex) {
                System.out.println("NumberFormatException: "+ " No number is detected");
            }
        }
/***********************************************************************/
        if (str.length > 1 && str[0].equals("todo")) {
            try {
                if (check_length(str)) {
                    String s1 = str_concat(str, 1, str.length - 1);
                    ToDo t_flag = new ToDo(s1);
                    AddCommand passed_command = new AddCommand(str[0], t_flag);
                    return passed_command;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("IndexOutOfBoundsException: "+"Index out of bound");
            }
        }
/***********************************************************************/
        if (str.length > 1 && str[0].equals("deadline")) {
            try {
                if (check_length(str)) {
                    String[] str2;
                    str2 = passed.split("/");
                    String descrip = str2[0].replaceAll("deadline", "");
                    String detail = str2[1];
                    Deadlines t_flag = new Deadlines(descrip, detail);
                    AddCommand passed_command = new AddCommand(str[0], t_flag);
                    return passed_command;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("ArrayIndexOutOfBoundsException: "+"Format might be wrong. It should be");
                System.out.println("Eg: deadline submit assignment 5 / by 2022-12-21 1800");
            } catch (DukeException e) {
                System.out.println("Duke Exception: " + "OOPS!!! The format might be wrong. It should be");
                System.out.println("Eg: deadline submit report / by 2022-12-31 2359");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("IndexOutOfBoundsException: "+"Index out of bound");
            } catch (NullPointerException e) {
                System.out.println("NullPointerException: "+"Should be '/by', no need space");
            } catch (timeparseException e) {
                System.out.println("timeparseException: " + "Time format error");
            } catch (dateparseException e) {
                System.out.println("dateparseException: " + "Date or Time format error");
            }


        }
/***********************************************************************/
        if (str.length > 1 && str[0].equals("event")) {
            try {
                if (check_length(str)) {
                    String[] str2;
                    str2 = passed.split("/");
                    String descrip = str2[0].replaceAll("event", "");
                    String detail = str2[1];
                    System.out.println("To set as Recurring Events? (Y/N)");
                    //Task t_flag = null;
                    UI recurr_UI = new UI();
                    String recurr_command = recurr_UI.readCommand();
                    assert (recurr_command.equals("Y") || recurr_command.equals("N") || recurr_command.equals("y") || recurr_command.equals("n"));
                    if (recurr_command.equals("Y") || recurr_command.equals("y")) {
                        System.out.println("What is the interval (day) for this Recurring Event: ");
                        String recurr_period = recurr_UI.readCommand();
                        Integer between = checkRecurring_gap(recurr_period);

                        System.out.println("How many times to be recurred: ");
                        String recurr_count = recurr_UI.readCommand();
                        Integer number = checkRecurring_count(recurr_count);
                        Add_Recur_Command passed_command = new Add_Recur_Command(str[0]);
                        for (Integer j = 0; j < number; j++) { // recurring task list is created in Add_Recur_Command, hence adding the task into this command
                            RecurringTask t_flag = new RecurringTask(descrip, detail, number, between);
                            t_flag.add_day(j);
                            passed_command.add_task(t_flag);
                        }
                        return passed_command;
                    }
                    //else
                    if(recurr_command.equals("N") || recurr_command.equals("n")){
                        Events t_flag = new Events(descrip, detail);
                        AddCommand passed_command = new AddCommand(str[0], t_flag);
                        return passed_command;
                    }
                }

            } catch (DukeException e) {
                System.out.println("Duke Exception: "+"OOPS!!! 'on' is missing in front of date or date is missing");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("IndexOutOfBoundsException: " + "Format might be wrong. It should be");
                System.out.println("Eg: event attend seminar / at NUS on 2020-08-03");
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException: " +"A non-number has been detected");
            } catch (DateTimeParseException e) {
                System.out.println("DateTimeParseException: "+"Date Format is wrong, should be in YYYY-MM-DD");
            }

        }
/***********************************************************************/
        if (str[0].equals("list")) //if list, list out the task
        {
            ListCommand l = new ListCommand(str[0]);
            return l;
        }
/***********************************************************************/
        if (str.length == 2 && str[0].equals("delete")) {
            if(str[1].trim().equals("*"))
            {
                DeleteAllCommand d = new DeleteAllCommand(str[0]);
                return d;
            }
            int number;
            try { // if first word is "delete", check its second words must be an integer
                number = Integer.parseInt(str[1]);
                DeleteCommand d = new DeleteCommand(str[0], number - 1);
                return d;
            } catch (NumberFormatException ex) {
                System.out.println("NumberFormatException: "+"Delete number is not given");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("IndexOutOfBoundsException: "+"Index out of bound or probably there is no on-hand task");
            }

        }

/***********************************************************************/
        if (str[0].equals("bye")) {
            ExitCommand passed_command = new ExitCommand(str[0]);
            return passed_command;
        }
/***********************************************************************/
        if (str[0].equals("sort")) {
            SortCommand passed_command = new SortCommand(str[0]);
            return passed_command;
        }
/***********************************************************************/
        if (str[0].equals("find")) {
            if(str.length > 2)
            {
                throw new FindException();
            }
        FindCommand passed_command = new FindCommand(str[0], str[1]);
        return passed_command;
    }
        throw new DukeException();

}
}

/***********************************************************************/
