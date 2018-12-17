package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Strategy {
    private List<Rule> ruleList = new ArrayList<>();
    private StrategyType type;
    private float transactionValue;

    public Strategy(float transactionValue, boolean isChecked) {
        this.transactionValue = transactionValue;
        if(isChecked) {
            this.type = StrategyType.STRATEGY_AND;
        } else {
            this.type = StrategyType.STRATEGY_OR;
        }
    }

    public void addRule(Rule rule) {
        ruleList.add(rule);
    }

    public List<Rule> getRules() {
        return ruleList;
    }

    public String toString() {
        if(transactionValue >= 0) {
            return "Strategy: buy: " + transactionValue + "% / " + type.toString();
        } else {
            return "Strategy: sell: " + transactionValue + "% / " + type.toString();
        }
    }
}
