package States;

public class Permanent implements State{

    public State change() {

        return new Cancelled(); // from permanent state  to cancelled state
    }
    
    public State change(int choice) {
        return this;
    }
    public String toString(){
        return this.getClass().getSimpleName();
    }
    
} 