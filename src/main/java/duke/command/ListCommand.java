package duke.command;

import duke.Tasklist.Task;
import duke.UI.UI;
import duke.Storage.fileaccess;

import java.util.ArrayList;

public class ListCommand extends Command {

    public ListCommand(String passed) {
        super(passed);
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
    public void execute(ArrayList<Task> tasklist, UI ui, fileaccess f) {
        int j = 1;
        for (Task s : tasklist) {
            System.out.println(j + "." + s.getStatus());
            j++;
        }
    }
}
