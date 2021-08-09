package Notes;
import java.time.LocalDate;
import States.*;
//LEAF
public class Note implements INote {
    private String content;
    private int id;
    private LocalDate date;
    private String title;
    private State state;
    private NoteGroup parent;

    // Full Constructor
    public Note(int id, String title, String content, LocalDate date, int stateChoice) {
        this.content = content;
        this.id = id;
        this.date = date;
        this.title = title;
        this.state = decideState(stateChoice);
        this.parent = null;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }

    public State getState() {
        return this.state;
    }
    public String toString() {
    	return header() + " Content: " + getContent() + " Status: " +getState().toString();
    }
    public String header() {
    	return "("+getId()+")" +" "+this.getTitle()+" ["+getType()+"]";
    }
    
    public void setParent(NoteGroup parent){
        this.parent = parent;
    }
    public NoteGroup getParent(){
        return this.parent;
    }

    private State decideState(int stateChoice) {
        try {
            if(stateChoice == 1)
                return new Incomplete();
            else if(stateChoice == 2)
               return new Permanent();
            else 
                throw new IllegalArgumentException();

    }catch(IllegalArgumentException e) {
        System.out.println("Invalid state number.");
        }
        return null;
    }
   
    public boolean isNote() {
        return true;
    }

    public void changeState() {
        this.state=state.change();
    }

    public void changeState(int choice) { 
        this.state=state.change(choice);
    }

}