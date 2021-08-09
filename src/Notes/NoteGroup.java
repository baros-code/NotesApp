package Notes;
import java.util.List;
import java.util.ArrayList;

//Composite
public class NoteGroup implements INote{
    private List<INote> subNotes;
    private int id;
    private String title;
    private NoteGroup parent;

    public NoteGroup(int id, String title){
        subNotes = new ArrayList<INote>();
        this.id = id;
        this.title = title;
        this.parent = null;
    }
 
    public void add(INote note){
        if(note != null)
            note.setParent(this);
            subNotes.add(note);
    }


    public boolean isNote() {
        return false;
    }


    public String toString(){
    	String returnString = header() + "\n      ";
	     for(INote currentNote : subNotes) {
	         returnString += currentNote.header()+ "\n      ";
	     }
	     return returnString;
    	
    }
    
    public String header() {
    	return "("+getId()+")" +" "+this.getTitle()+" ["+getType()+"]";
     }
    
    public List<INote> getSubNotes() {
        return subNotes;
    }

    public int getId(){ return id; }
    public String getTitle(){ return title; }
    

    public String getType(){
        return this.getClass().getSimpleName();
    }

    /**
     * Method that returns INote object in subNotes with given id.
     * @param int
     * @return INote
     */
    public INote findNoteById(int id) { // note groups and notes
        INote foundNote = null;
        if(id > 0) {
            for(INote n : subNotes) {
                if(n.getId()==id)
                    foundNote = n;
                else if(!n.isNote()) // if it is noteGroup
                    ((NoteGroup)n).findNoteById(id);      
            }
        }
        return foundNote; 
   }

   public boolean hasNote(int id) {
        return findNoteById(id) != null;
   }
   
    public void setParent(NoteGroup n){this.parent = n;}
    public NoteGroup getParent(){return this.parent;}

}