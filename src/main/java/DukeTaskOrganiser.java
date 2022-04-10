import java.util.ArrayList;
import java.util.*;

public class DukeTaskOrganiser {
    private static ArrayList <Task> toDoTaskList = new ArrayList();
    private static int taskCounter = 0;
    //private String [] todoList = new String[100];

    public DukeTaskOrganiser(){
    }

    public void dukeMenu() {

        Scanner readUserInput = new Scanner(System.in);
        String readUserRespond = "";

        boolean systemStatus = true;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (systemStatus) {
            readUserRespond = readUserInput.nextLine();

            if (readUserRespond.toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                systemStatus = false;
                break;
            }
            if (readUserRespond.isBlank()) {
                System.out.println("No Response Received!!");
                continue;
            } else if (readUserRespond.toLowerCase().equals("list")) {
                printTaskList();
            } else if (readUserRespond.toLowerCase().startsWith("mark") || readUserRespond.toLowerCase().startsWith("unmark")) {
                try {
                    String[] words = readUserRespond.split(" ");
                    if (words.length == 2) {
                        int choiceTask = Integer.parseInt(words[1]);
                        if (words[0].equals("mark")) {
                            toDoTaskList.get(choiceTask - 1).setDone();
                        } else if (words[0].equals("unmark")) {
                            toDoTaskList.get(choiceTask - 1).setUndone();
                        }
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Please enter a number for your choice");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Index out of bound");
                }
            } else {
                //todoList[taskCounter] = readUserRespond;
                Task newTask = new Task(readUserRespond);
                toDoTaskList.add(newTask);
                System.out.println("added: " + readUserRespond);
                taskCounter += 1;
            }
        }
    }

    public void printTaskList() {
        if (taskCounter == 0) {
            System.out.println("Your tasklist is currently empty");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCounter; i++) {
            System.out.println(i + 1 + ". " + toDoTaskList.get(i).getTaskStatus());
        }
    }
}