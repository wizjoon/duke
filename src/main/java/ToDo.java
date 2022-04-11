public class ToDo extends Task{

    public ToDo(String description){
        super(description);
    }

    public String getTaskStatus(){
        return ("[T]"  + super.getTaskStatus());
    }
}
