/**
 * Created by asus-pc on 5/13/2016.
 */
public class Time {
    private int numberOfTurns;
    private int numberOfCycles;
    private int numberOfClashes;

    Time(Time time) {
        this.setNumberOfTurns(time.getNumberOfTurns());
        this.setNumberOfCycles(time.getNumberOfCycles());
        this.setNumberOfClashes(time.getNumberOfClashes());
    }

    Time() {
        setNumberOfClashes(0);
        setNumberOfCycles(0);
        setNumberOfTurns(0);
    }

    public Time(int numberOfTurns, int numberOfCycles, int numberOfClashes) {
        this.numberOfTurns = numberOfTurns;
        this.numberOfCycles = numberOfCycles;
        this.numberOfClashes = numberOfClashes;
    }

    public boolean isTimePassed() {
        if ((this.numberOfTurns < 1) && (this.numberOfCycles < 1) && (this.numberOfClashes < 1))
            return true;
        return false;
    }

    public void reduceTime(String typeOfTime) {
        switch (typeOfTime) {
            case "NumberOfTurns":
                this.numberOfClashes--;
                break;
            case "NumberOfCycles":
                this.numberOfCycles--;
                break;
            case "NumberOfClashes":
                this.numberOfClashes--;
                break;
        }
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public int getNumberOfCycles() {
        return numberOfCycles;
    }

    public void setNumberOfCycles(int numberOfCycles) {
        this.numberOfCycles = numberOfCycles;
    }

    public int getNumberOfClashes() {
        return numberOfClashes;
    }

    public void setNumberOfClashes(int numberOfClashes) {
        this.numberOfClashes = numberOfClashes;
    }
}
