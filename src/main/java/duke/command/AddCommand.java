package duke.command;

import duke.Exception.DukeException;
import duke.Tasklist.Task;
import duke.UI.UI;
import duke.Storage.fileaccess;

import java.util.ArrayList;

public class AddCommand extends Command {

    protected Task passed_task;

    public AddCommand(String passed, Task t_passed) throws DukeException {
        super(passed);
        this.passed_task = t_passed;
    }


    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isSort() {
        return false;
    }

    @Override
    public void execute(ArrayList<Task> tasklist, UI ui, fileaccess f) throws NullPointerException {
        tasklist.add(passed_task);
        UI.showGotit();
        System.out.println(passed_task.getStatus());
        UI.showAdd(tasklist.size());
    }
}
