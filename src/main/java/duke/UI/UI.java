package duke.UI;

import duke.Exception.DukeException;

import java.util.*;

public class UI {


    public String readCommand() throws DukeException {
        Scanner scanInput = new Scanner(System.in);
        ;
        return scanInput.nextLine();

    }

    public static void showWelcome() {
        System.out.println("Welcome!");
        System.out.println("This is Duke");
        System.out.println("I am here to help check your on-hand task");

    }

    public static void showLine() {
        System.out.println("______________________________________________");
    }

    public static void showAdd(int num) {
        if (num < 1) {
            System.out.println("Now you have " + num + " task in the list");
        } else {
            System.out.println("Now you have " + num + " tasks in the list");
        }
    }

    public static void showGotit() {
        {
            System.out.println("Got it. I've added this task: ");
        }
    }
    public static void showGotthem() {
        {
            System.out.println("Got it. I've added these tasks: ");
        }
    }

    public static void showDelete() {
        System.out.println("Noted. I've removed this task:");
    }
    public static void showDeleteAll() {
        System.out.println("Noted. I've removed all the tasks:");
    }

    public static void showMark() {
        System.out.println("Nice! I've marked this task as done: ");
    }

    public static void showUnmark() {
        System.out.println("OK, I've marked this task as not done yet:");
    }
    public static void showFindResult(int count) throws AssertionError{
        assert (count > 0);
        if(count == 0) {
            System.out.println("No match task is found");
        }
        else if(count == 1) {
            System.out.println("OK, I found this task shown above");
        }
        else{
            System.out.println("OK, I found these tasks shown above");
        }
    }


}

