package duke.command;

import duke.Tasklist.Task;
import duke.UI.UI;
import duke.Storage.fileaccess;

import java.util.ArrayList;

public class DeleteCommand extends Command {

    protected int numbertodelete;

    public DeleteCommand(String passed, int numberpassed) {
        super(passed);
        numbertodelete = numberpassed;
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
    public void execute(ArrayList<Task> tasklist, UI ui, fileaccess f) throws IndexOutOfBoundsException {
        System.out.println(numbertodelete+1 +"."+ tasklist.get(numbertodelete).getStatus());
        tasklist.remove(numbertodelete);
        UI.showDelete();

    }
}
