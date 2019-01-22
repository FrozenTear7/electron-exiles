package model;

import lombok.Data;

@Data
public class Strategy {
    private IRule rule;
    private float transactionValue;

    public Strategy(float transactionValue, IRule rule) {
        this.transactionValue = transactionValue;
        this.rule = rule;
    }

    public IRule getRule() {
        return rule;
    }
    public float getTransactionValue() {
        return transactionValue;
    }

    public String toString() {
        String transactionType = transactionValue >= 0 ? "Buy" : "Sell";

        return transactionType + ": " + transactionValue + "% " + rule.toString();
    }
}
