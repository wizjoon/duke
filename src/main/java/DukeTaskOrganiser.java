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
        String taskDescription = "", taskDetail = "";

        boolean systemStatus = true;

        Task newTask;
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
                    boolean markFlag = false;
                    String[] words = readUserRespond.split(" ");
                    if (words.length == 2) {
                        int choiceTask = Integer.parseInt(words[1]);
                        if (words[0].equals("mark")) {
                            markFlag = true;
                        } else if (words[0].equals("unmark")) {
                            markFlag = false;
                        }
                        toDoTaskList.get(choiceTask - 1).changeMarkStatus(markFlag);
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Please enter a number for your choice");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Index out of bound");
                }
            } else if (readUserRespond.toLowerCase().startsWith("todo") || readUserRespond.toLowerCase().startsWith("deadline") || readUserRespond.toLowerCase().startsWith("event")) {
                //todoList[taskCounter] = readUserRespond;
                if (readUserRespond.toLowerCase().startsWith("todo")) {
                    taskDescription =  readUserRespond.substring(5);
                    if (taskDescription.isBlank()) {
                        System.out.println("OOPS!!! The description of a todo cannot be empty.");
                        continue;
                    } else {
                        newTask = new ToDo(taskDescription);
                        toDoTaskList.add(newTask);
                        System.out.println("added: " + newTask.getTaskStatus());
                        taskCounter += 1;
                    }
                } else if (readUserRespond.toLowerCase().startsWith("deadline")) {
                    String[] words = readUserRespond.split("/");
                    taskDescription =  words[0].substring(9);

                    if (taskDescription.isBlank()) {
                        System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                        continue;
                    }
                    if(words[1].toLowerCase().startsWith("by")) {
                        taskDetail =  words[1].substring(3);
                        if (taskDescription.isBlank()) {
                            System.out.println("OOPS!!! The detail of a deadline cannot be empty.");
                            continue;
                        }
                    } else {
                        System.out.println("OOP!! Wrong deadline format!!");
                        continue;
                    }
                        newTask = new Deadline(taskDescription, taskDetail);
                        toDoTaskList.add(newTask);
                        System.out.println("added: " + newTask.getTaskStatus());
                        taskCounter += 1;
                    } else if (readUserRespond.toLowerCase().startsWith("event")){
                        String[] words = readUserRespond.split("/");
                        taskDescription =  words[0].substring(6);

                        if (taskDescription.isBlank()) {
                            System.out.println("OOPS!!! The description of a event cannot be empty.");
                            continue;
                        }
                        if(words[1].toLowerCase().startsWith("at")) {
                            taskDetail =  words[1].substring(3);
                            if (taskDescription.isBlank()) {
                                System.out.println("OOPS!!! The detail of a event cannot be empty.");
                                continue;
                            }
                        } else {
                            System.out.println("OOP!! Wrong event format!!");
                            continue;
                        }
                            newTask = new Event(taskDescription, taskDetail);
                            toDoTaskList.add(newTask);
                            System.out.println("added: " + newTask.getTaskStatus());
                            taskCounter += 1;
                    }
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