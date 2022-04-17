package duke.command;

import duke.Storage.fileaccess;
import duke.Tasklist.Task;
import duke.UI.UI;

import java.util.ArrayList;

public class DeleteAllCommand extends Command{
    public DeleteAllCommand(String passed) {
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
        tasklist.removeAll(tasklist);
        UI.showDeleteAll();
    }
}
