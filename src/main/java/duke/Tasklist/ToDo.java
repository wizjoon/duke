package duke.Tasklist;

public class ToDo extends Task {
    protected String by;
    /**
     *
     * @param description keep the String of description of ToDo task
     */
    public ToDo(String description)  {
        super(description);
    }

    /**
     *
     * @return String of status of task and its description
     */
    public String getStatus() {
        return (isDone ? "[T][X] " + this.description : "[T][ ] " + this.description);
    }

    /**
     *
     * @param passed Pass in the getStatus() method of ToDo class
     * @return Return a String in JUnit testing.
     */
    public static String testgetStatus(String passed) {
        return (passed);
    }

}
