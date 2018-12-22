package model;

public enum MarketAction {
    BUY, SELL, UNDEF;

    public String toString() {
        if (this.equals(BUY)) {
            return "buy";
        } else if (this.equals(SELL)) {
            return "sell";
        }
        else {
            return "not defined";
        }
    }
}
