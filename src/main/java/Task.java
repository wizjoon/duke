public class Task {
    protected String description;
    protected boolean isDone;

    public Task (String description){
        this.description = description;
        isDone = false;
    }

    /*
    public void setDone(){
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [X]" + " " + getDescription());
    }

    public void setUndone(){
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  [ ]" + " " + getDescription());
    }
    */

    public void changeMarkStatus(boolean markFlag){
        isDone = markFlag;
        if (isDone){
            System.out.println("Nice! I've marked this task as done:");
        } else{
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  " + getTaskStatus());
    }

    public String getTaskStatus(){
        return (isDone ? "[X]"  + " " + getDescription(): "[ ]" + " " + getDescription());
    }

    public String getDescription() {
        return this.description;
    }
}
