package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Strategy {
    private List<Rule> ruleList = new ArrayList<>();
    private StrategyType type;
    private float transactionValue;

    public Strategy(float transactionValue, StrategyType strategyType) {
        this.transactionValue = transactionValue;
        this.type = strategyType;
    }

    public void addRule(Rule rule) {
        ruleList.add(rule);
    }

    public List<Rule> getRules() {
        return ruleList;
    }

    public String toString() {
        if (transactionValue >= 0) {
            return "Strategy: buy: " + transactionValue + "% / " + type.toString();
        } else {
            return "Strategy: sell: " + transactionValue + "% / " + type.toString();
        }
    }
}
