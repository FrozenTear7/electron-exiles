package model;

public class Rule {
    private int daysBack;
    private float valueChange;

    public Rule(int daysBack, float valueChange) {
//        if (daysBack <= 0) {
//            throw new LoadException("Number of days must be positive!");
//        } else if (valueChange < -100.0 || valueChange > 100.0) {
//            throw new LoadException("Change of value must be between -100 to 100!");
//        }

        this.daysBack = daysBack;
        this.valueChange = valueChange;
    }

    @Override
    public String toString() {
        return "If stock value changed " + daysBack + " days ago, by: " + valueChange + "%";
    }
}
