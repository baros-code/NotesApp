package States;

public class Cancelled implements State {

    public State change() { // change operation is not allowed
        System.out.println("Cancelled State can not be changed!");
        System.out.println();
        return this;        //exception can be added
    }


    public State change(int choice) {
        System.out.println("Cancelled State can not be changed!");
        System.out.println();
        return this;
    }
    public String toString(){
        return this.getClass().getSimpleName();
    }
    
}