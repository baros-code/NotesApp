package States;

public class Completed implements State{
    
    public State change() { // change operation is not allowed
        System.out.println("Completed State can not be changed!");
        System.out.println();
        return this;        
    }

    public State change(int choice) {
        System.out.println("Completed State can not be changed!");
        System.out.println();
        return this;
    }
    public String toString(){
        return this.getClass().getSimpleName();
    }
}