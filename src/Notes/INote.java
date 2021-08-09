package Notes;
//Component
public interface INote {

    /**
     * Method that returns whether the object is Note or not.
     * @return boolean
     */
    public boolean isNote();
    /**
     * Method that returns header text of object.
     * @return String
     */
    public String header();
    public int getId();
    public String getTitle();
    public String getType();
    public void setParent(NoteGroup parent);
    public NoteGroup getParent();

 
}