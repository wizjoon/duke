public class Event extends Task{
    protected String details;
    public Event(String description, String details){
        super(description);
        this.details = details;
    }

    public String getDetail(){
        return this.details;
    }

    public String getTaskStatus(){
        return ("[E]"  + super.getTaskStatus() + " (at:" + getDetail() + ")");
    }
}
