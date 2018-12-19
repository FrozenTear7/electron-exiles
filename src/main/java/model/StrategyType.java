package model;

public enum StrategyType {
    STRATEGY_AND, STRATEGY_OR;

    public String toString() {
        if(this.equals(STRATEGY_AND)) {
            return "AND";
        } else if (this.equals(STRATEGY_OR)) {
            return "OR";
        }
        else {
            return "null";
        }
    }
}
