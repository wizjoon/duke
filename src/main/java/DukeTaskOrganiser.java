import java.util.ArrayList;
import java.util.*;

public class DukeTaskOrganiser {
    private static ArrayList <Task> toDoTaskList = new ArrayList();
    //private String [] todoList = new String[100];

    public DukeTaskOrganiser(){
    }

    public void dukeMenu(){

        Scanner readUserInput = new Scanner(System.in);
        String readUserRespond = "";

        boolean systemStatus = true;
        int taskCounter = 0;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        while (systemStatus){
            System.out.println("What can I do for you?");
            readUserRespond =  readUserInput.nextLine();

            if(readUserRespond.toLowerCase().equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                systemStatus = false;
                break;
            }
            if(readUserRespond.isBlank()){
                System.out.println("No Resonse from you!!");
            } else if(readUserRespond.toLowerCase().equals("list")){
                if(taskCounter == 0) {
                    System.out.println("Your tasklist is currently empty");
                }
                System.out.println("Here are the tasks in your list:");
                for(int i=0; i<taskCounter; i++){
                    System.out.println(i+1 + ". " + toDoTaskList.get(i).getTaskStatus());
                }

            } else{
                //todoList[taskCounter] = readUserRespond;
                Task newTask = new Task(readUserRespond);
                toDoTaskList.add(newTask);
                System.out.println("added: " + readUserRespond);
                taskCounter += 1;
            }
        }
    }
}
