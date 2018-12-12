package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Strategy {
    private List<Rule> ruleList = new ArrayList<>();
    private StrategyType type;
    private float transactionValue;

    public Strategy() {

    }

    public void addRule(Rule rule) {
        ruleList.add(rule);
    }

    public List<String> getRules() {
        List<String> returnList = new ArrayList<>();

        for (Rule rule : ruleList) {
            returnList.add(rule.toString());
        }

        return returnList;
    }
}
