package States;

public class Incomplete implements State{

    public State change() {
        return this;
    }

   public State change(int choice) { // Incomplete depends on some situations.
        if (choice == 1) {
            return new Completed();
        } else if (choice == 2) {
            return new Cancelled();
        } else {
           return this;
        }
    }
    public String toString(){
        return this.getClass().getSimpleName();
    }
    
}