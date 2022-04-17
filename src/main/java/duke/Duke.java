package duke;

import duke.Exception.CannotWriteException;
import duke.Exception.DukeException;
import duke.Exception.FileLoadException;
import duke.Storage.fileaccess;
import duke.Tasklist.RecurringTask;
import duke.UI.Parser;
import duke.Tasklist.Task;
import duke.UI.UI;
import duke.command.Add_Recur_Command;
import duke.command.Command;

import java.io.IOException;
import java.lang.module.FindException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.io.File;

public class Duke {

    private fileaccess f;
    private UI user_interface;
    private static ArrayList<Task> tasklist = new ArrayList<>();
    public static String basepath = new File("").getAbsolutePath();
    private static String filepath = basepath + "/data/buffer.txt";

    public Duke(String filepath) {
        this.user_interface = new UI();
        f = new fileaccess(filepath);
        try {
            f.load(tasklist);
        } catch (FileLoadException e) {
            System.out.println("FileLoadException: "+"Error in loading file, will create new file");
        } catch (DukeException e) {
            System.out.println("Duke Exception: "+"Error in loading file");
        }catch (NullPointerException e) {
            System.out.println("NullPointerException: "+"File contains blank line");
        }

    }

    public void run() {
        user_interface.showWelcome();
        boolean isExit = false;
        boolean isSort = false;
        while (!isExit) {
            try {
                String command = user_interface.readCommand();
                user_interface.showLine();
                Command c = Parser.parsing(command);
                c.execute(tasklist, user_interface, f);
                isSort = c.isSort();
                isExit = c.isExit();
            } catch (NullPointerException e) {
                System.out.println("NullPointerException: "+"Some info is incorrect or missing");
            }catch (DukeException e) {
                System.out.println("Duke Exception: "+"Error in command");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("IndexOutOfBoundsException: "+"Index out of Bound Error");
            }catch (FindException e) {
                System.out.println("FindException: "+" Too many word");
            }catch (AssertionError e) {
                System.out.println("Assertion Error: " + "Please type only 'y','Y' for YES or 'n','N' for NO");
            }
            finally {
                try{
                    f.writetoFile(tasklist);
                    user_interface.showLine();
                } catch (CannotWriteException e) {
                    System.out.println("CannotWriteException:"+"Cannot write");
                } catch (IOException e) {
                    System.out.println("IOException: "+"Error in creating file");
                }

            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke project = new Duke(filepath);
        project.run();
    }
}
