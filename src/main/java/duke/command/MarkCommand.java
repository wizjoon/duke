package duke.command;

import duke.Tasklist.Task;
import duke.UI.UI;
import duke.Storage.fileaccess;

import java.util.ArrayList;

public class MarkCommand extends Command {

    protected int numbertomark;

    public MarkCommand(String passed, int number) {
        super(passed);
        this.numbertomark = number;
    }

    @Override


    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isSort() {
        return false;
    }

    public void execute(ArrayList<Task> tasklist, UI ui, fileaccess f) {
        if (type.equals("mark")) {
            tasklist.get(numbertomark - 1).setDone();
            UI.showMark();
            System.out.println(tasklist.get(numbertomark-1).getStatus());
        }
        if (type.equals("unmark")) {
            tasklist.get(numbertomark - 1).set_unDone();
            UI.showUnmark();
            System.out.println(tasklist.get(numbertomark-1).getStatus());
        }
    }
}
