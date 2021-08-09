package States;

public interface State {

     /**
      * Method that changes the current state to the next.
      * @return State
      */
     public State  change();
     /**
      * Method that changes the current state according to given parameter.
      * @param int
      * @return State
      */
     public State change(int choice);
     public String toString();
    
}