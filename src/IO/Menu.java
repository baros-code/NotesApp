package IO;

import Notes.NoteGroup;

public class Menu {


    public static void displayNoteGroupOperations(NoteGroup n) {
        System.out.println("[1] Create Note in " + n.header());
        System.out.println("[2] Create Note Group in " + n.header());
        System.out.println("[3] Open Note/Note Group in  " + n.header());
    }

    public static void displayCommonOperations() { // operations for noteGroup and note
        System.out.println("[4] Go back");
        System.out.println("[5] Go back to root directory.");
        System.out.println("[6] Export notes as JSON file this branch.");
        System.out.println("[7] Reset note program.");
        System.out.println("[8] Exit program");

    }

    public static void displayNoteOperations() {
        System.out.println("[1] Change status of the note ");
    }

    public static void displayChangeState() {
        System.out.println("Please select the new state");
        System.out.println("[1] COMPLETED");
        System.out.println("[2] CANCELLED");
    }
    
    public static void displayTitleDemandMessage(){
        System.out.print("Enter title: ");
    }
    public static void displayContentDemandMessage(){
        System.out.print("Enter content: ");
    }
    public static void displayStatusDemandMessage(){
        System.out.println("Choose status of the note:");
        System.out.println("[1] Incomplete\n[2] Permanent");
    }

    public static void displayIdDemandMessage(){ // open operation
        System.out.print("Please enter a Note Group/Note id: ");
    }


    public static void displayExitMessage(){
        System.out.println("Exiting from the program...");
    }


    public static void displayResetMessage(){
        System.out.println("All notes cleared.");
    }

}