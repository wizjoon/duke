package duke.command;

import duke.Storage.fileaccess;
import duke.Tasklist.RecurringTask;
import duke.Tasklist.Task;
import duke.UI.UI;

import java.util.ArrayList;


public class Add_Recur_Command extends Command {
    public ArrayList<Task> passed_task = new ArrayList<Task>();

    public Add_Recur_Command(String passed) {
        super(passed);
    }

    public void add_task(RecurringTask recur_passed) {
        passed_task.add(recur_passed);
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isSort() {
        return false;
    }

    @Override
    public void execute(ArrayList<Task> tasklist, UI ui, fileaccess f) throws NullPointerException {
        if(passed_task.size() > 1)
        {
            UI.showGotthem();
        }
        else {
            UI.showGotit();
        }
        for (Integer i = 0; i < passed_task.size(); i++) {
            tasklist.add(passed_task.get(i));
            System.out.println(passed_task.get(i).getStatus());
        }
        UI.showAdd(tasklist.size());


    }
}
