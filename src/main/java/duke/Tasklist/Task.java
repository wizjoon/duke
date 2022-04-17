package duke.Tasklist;

import duke.Tasklist.*;

public class Task {
    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatus() {
        return (isDone ? "[X]" + " " + this.description : "[ ]" + " " + this.description);
    }

    public void setDone() {
        this.isDone = true;
    }

    public void set_unDone() {
        this.isDone = false;
    }

    public void set_category() {
        this.isDone = false;
    }
}
