package duke.Storage;

import duke.Duke;
import duke.Exception.*;
import duke.Tasklist.*;
import duke.UI.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.ArrayList;

public class fileaccess {

    private static File loadedfile;
    private static String f_p;

    public fileaccess(String filepath) {
        this.f_p = filepath;
        this.loadedfile = new File(filepath);
    }

    public static void load(ArrayList<Task> tasklist) throws FileLoadException, DukeException{
        Scanner s = null;
        try {
            s = new Scanner(loadedfile);
        } catch (FileNotFoundException e) {
            System.out.println("File is not found : "+ loadedfile);
            throw new FileLoadException();
        }
        while (s.hasNext()) {
            if(s == null)
            {
                throw new NullPointerException();
            }
            String[] str = new String[20];
            str = s.nextLine().split("[|] ", 5);
            if (str[0].trim().replace("\\s", "").equals("T")) {
                ToDo t_flag = new ToDo(str[2].trim());  // str[0] = T , str[1]= 1 or 0 , str[2] description, str[3] details
                if (str[1].trim().equals("1")) {
                    t_flag.setDone();
                }
                if (str[1].trim().equals("0")) {
                    t_flag.set_unDone();
                }
                tasklist.add(t_flag);
            } else if (str[0].trim().replace("\\s", "").equals("E")) {
                Events t_flag = new Events(str[2].trim(), str[3].trim());
                if (str[1].trim().equals("1")) {
                    t_flag.setDone();
                }
                if (str[1].trim().equals("0")) {
                    t_flag.set_unDone();
                }
                tasklist.add(t_flag);
            } else if (str[0].trim().replace("\\s", "").equals("D")) {
                try {

                    String[] datetime_trim = str[3].split(" ");
                    Deadlines t_flag = new Deadlines(str[2].trim(), str[3]);
                    if (str[1].trim().equals("1")) {
                        t_flag.setDone();
                    }
                    if (str[1].trim().equals("0")) {
                        t_flag.set_unDone();
                    }
                    tasklist.add(t_flag);
                } catch (dateparseException e) {
                    System.out.println("dateparseException: "+"Date format wrong");
                } catch (DukeException e) {
                    System.out.println("DukeException: "+"Error in Command");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("IndexOutOfBoundsException: "+"Index out of Bound Error");
                } catch (timeparseException e) {
                    System.out.println("timeparseException: "+"Time Info Error");
                }
            } else {
                continue;
            }
        }
    }

    public static void printFileContents(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void writetoFile(ArrayList<Task> tasklist) throws CannotWriteException, IOException {

        FileWriter fw = new FileWriter(f_p, false);
        for (Task s : tasklist) {
            String w = Parser.parsing_to_write(s);
            fw.write(w + "\n");

        }
        fw.close();

    }

}
