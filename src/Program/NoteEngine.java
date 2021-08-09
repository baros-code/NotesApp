package Program;
import IO.JSONFileWriter;
import IO.Menu;
import Notes.INote;
import Notes.Note;
import Notes.NoteGroup;
import java.time.LocalDate;
import java.util.Scanner;

public class NoteEngine{
    private Scanner sc = new Scanner(System.in);
    private JSONFileWriter fileWriter;
    private INote currentNote;
    private int idGenerator;
    private INote root;

    /**
     * The Constructor
     */
    public NoteEngine (){
        fileWriter = new JSONFileWriter();
        currentNote = new NoteGroup(0,"Notes");       //root note group which has id = 0
        idGenerator = 1;                             //new note/note group ids start from 1
        root = currentNote;
    }

    public void start() {
        System.out.println("--Welcome to Note Application--\n");
        while (true) {
            //System.out.println();
            //System.out.println("-----------------------------------------");
            System.out.println("Current directory:\n");
            System.out.println(currentNote);
            System.out.println("-----------------------------------------");
            
            if (currentNote.isNote()) {
                noteOperations((Note) currentNote); //if  currentNote is a note
            } else {
                noteGroupOperations((NoteGroup) currentNote); // if currentNote is a noteGroup
            }
        }
    }

    /**
     * 
     *  Method that  provides opening  a NoteGroup or  a Note in a specified NoteGroup directory.
     * It takes an input  from the user as an id to find the NoteGroup or Note that will be opened.  
     * @param NoteGroup Parameter that indicates the NoteGroup directory.
     * 
     */
    private void openOperation(NoteGroup n) {
        Menu.displayIdDemandMessage();
        int choice=getInput(); // id checking
        
        if (n.hasNote(choice)) {
            currentNote=n.findNoteById(choice); 
        } else {
            System.out.println("There is no note/NoteGroup with that id.");
            System.out.println("Operation is unsuccessful.");
        }
    }
    
    /**
     * 
     * Method that provides operations for a NoteGroup
     * @param NoteGroup parameter that specifies the NoteGroup which Operations will be executed to.
     */
    private void noteGroupOperations(NoteGroup n) {
        Menu.displayNoteGroupOperations(n);
        Menu.displayCommonOperations();
        int choice=getInput();
            if (choice == 1) { // create a note
            createNote(n);
        } else if (choice == 2) { // create a noteGroup
            createNoteGroup(n);
        } else  if (choice == 3) { // open a note/NoteGroup in a current directory.
            openOperation(n);
        } else {
            commonOperations(choice); // other operations will be checked according to choice
        }
    }
    
    /**
     * 
     * Method that checks the INote object is a root or not.
     * @param INote parameter for checking.
     * @return boolean true INote object is a root, otherwise it returns false
     */
    private boolean isRoot(INote n) {
        return n.getParent() == null;
    }

    /**
     * 
     * Methat that provides operations for both Note Group and Note.
     * @param int parameter is an input of the user.
     */
    private void commonOperations(int choice) {
        switch(choice){
            case 4:
                // Go Back to previous directory(noteGroup)
                if (isRoot(currentNote))  {
                    System.out.println("NoteGroup is the root.Go Back operation is not allowed!");
                    break;
                }
                currentNote=currentNote.getParent();
                break;
            case 5: // Go Back to root directory(noteGroup)
            	currentNote = root;
            	break;
            case 6:// save as JSON file
                saveAsJSON(); 
                break;
            case 7: // reset the program
                currentNote=new NoteGroup(0, "Notes");
                root = currentNote; //update the root
                idGenerator=1; //reset the id generator
                Menu.displayResetMessage();
                break;
            case 8: // exit the program
                Menu.displayExitMessage();
                System.exit(0);
                break;
            default: //input checker
                System.out.println("Please enter a valid command.");
                break;
        }
    }
        
        /**
         * 
         * Method that asks the user to choose either completed state or cancelled state.
         * @param Note whose state will be changed.
         */
    private void askUserToChange(Note n) {
        boolean valid=false;
        int choice=0;
        
        while (!valid) {
        	Menu.displayChangeState();
            choice = getInput();
            valid=validStateChecker(choice);

        }
        n.changeState(choice);
    }

    /**
     * Method that checks whether the state of the given Note can be changed to more than one state.
     * @param Note
     * @return boolean
     */
    private boolean hasMoreStateOptions(Note n) {
        return n.getState().toString().equals("Incomplete");
    }

    /**
     * Method that manages Note specific and common operations.
     * @param Note
     */
    private void noteOperations(Note n) {
    	Menu.displayNoteOperations();
        Menu.displayCommonOperations();
       
        int choice = getInput();
                if (choice == 1) { // change status of the note
                    if (hasMoreStateOptions(n)) { // if state is an incomplete
                        askUserToChange(n);
                    }
                    else {
                        n.changeState();
                    }
                }
                else {
                    commonOperations(choice);
                }
    }

    /**
     * Method that takes input from user.
     * @return int
     */
    private int getInput() {//input checker
        String input = "";
        while(true) {
            input = sc.nextLine();
            if(isInteger(input) && Integer.parseInt(input) <= 8){
                break;
            }
            else{
                System.out.println("Please enter a valid number.");
            }
        }
        return Integer.parseInt(input);
    }

    /**
     * Saves as JSON File
     */
    private void saveAsJSON(){
    	fileWriter.saveAsJSON((NoteGroup)currentNote);
    }

    /**
     * Method that checks and returns whether given string is numeric or not.
     * @param String
     * @return boolean
     */
    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }


    /**
     * Method that creates NoteGroup object in a specified NoteGroup directory.
     * @param NoteGroup
     * @return INote
     */
    private INote createNoteGroup(NoteGroup n) {
        Menu.displayTitleDemandMessage();
        String title = sc.nextLine();

        INote newNoteGroup = new NoteGroup(idGenerator,title);
        n.add(newNoteGroup);
        idGenerator++;

        return newNoteGroup;
    }
    
    /**
     * 
     * Method that creates a Note object in a specified NoteGroup directory.
     * @param NoteGroup 
     * @return Note
     */
    private INote createNote(NoteGroup n) {
        Menu.displayTitleDemandMessage();
        String title = sc.nextLine();

        Menu.displayContentDemandMessage();
        String content = sc.nextLine();

        boolean valid=false;
        int stateChoice=0;
        
        while (!valid) {
        	Menu.displayStatusDemandMessage();
            stateChoice = getInput();
            valid=validStateChecker(stateChoice);
        }
        INote newNote = new Note(idGenerator,title,content, LocalDate.now(),stateChoice);
        n.add(newNote);
        idGenerator++;

        return newNote;
    }

    /**
     * 
     * Method that checks the input of the user for state choosing process.
     * @param int parameter that is a input of the user
     * @return true if user's input is valid, otherwise false
     * 
     */
    private boolean validStateChecker(int choice) {
        if (choice == 1 || choice == 2) {
                   return true;
        } else {
            System.out.println("Please enter the valid number");
            System.out.println();
            return false;
        }
    }

}
