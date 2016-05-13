/**
 * Created by asus-pc on 5/13/2016.
 */
public class Time {
    private int numberOfTurns;
    private int numberOfCycles;
    private int numberOfClashes;

    public boolean isTimePassed() {
        if ((this.numberOfTurns < 1) && (this.numberOfCycles < 1) && (this.numberOfClashes < 1))
            return true;
        return false;
    }

    public void reduceTime() {
        if (this.numberOfTurns > 0)
            this.numberOfTurns--;
        else if (this.numberOfCycles > 0)
            this.numberOfCycles--;
        else if (this.numberOfClashes > 0)
            this.numberOfClashes--;
    }
}
