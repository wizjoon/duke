package duke.command;

import duke.Storage.fileaccess;
import duke.Tasklist.Task;
import duke.UI.UI;

import java.util.ArrayList;

public class FindCommand extends Command{
    protected String target;
    public FindCommand (String passed, String passed_target)
    {
        super(passed);
        this.target= passed_target;
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
        int count = 0;
        for (Integer i =0; i<tasklist.size(); i++)
        {
            String []content = tasklist.get(i).description.split(" ");
            int found =-1;
            for(Integer j =0; j<content.length && found == -1 ; j++)
            {
                String word = content[j].trim();
                if((target.compareToIgnoreCase(word)) ==0){
                    count++;
                    found = 0;
                    System.out.print(i+1 + ".");
                    System.out.println(tasklist.get(i).getStatus());
                }
            }
        }
        try {
            UI.showFindResult(count);
        }catch (AssertionError e) {
            System.out.println("Assertion Error: Not supposed to have negative count");
        }

    }
}
