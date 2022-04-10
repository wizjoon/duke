public class Task {
    protected String description;
    protected boolean isDone;

    public Task (String description){
        this.description = description;
        isDone = false;
    }

    public void setDone(){
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [X]" + " " + this.description);
    }

    public void setUndone(){
        this.isDone = false;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [ ]" + " " + this.description);
    }

    public String getTaskStatus(){
        return (isDone ? "[X]"  + " " + this.description: "[ ]" + " " + this.description);
    }

    public String getDescription() {
        return description;
    }
}
