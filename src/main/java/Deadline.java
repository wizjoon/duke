public class Deadline extends Task{
    protected String by;
    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    public String getBy(){
        return this.by;
    }

    public String getTaskStatus(){
        return ("[D]"  + super.getTaskStatus() + " (by:" + getBy() + ")");
    }
}
