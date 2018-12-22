package model;

import java.util.List;

public class RuleAnd implements IRule {
    private List<IRule> subRules;

    public RuleAnd(List<IRule> subRules) {
        this.subRules = subRules;
    }

    public boolean predicate(List<DataRow> data, int index) {
        for (IRule rule : subRules) {
            if (!rule.predicate(data, index)) {
                return false;
            }
        }
        return true;
    }

    public void addRule(IRule rule) {
        subRules.add(rule);
    }

    public List<IRule> getRules() {
        return subRules;
    }

    @Override
    public List<MarketAction> evaluate(List<DataRow> data) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (IRule rule : subRules) {
            result.append("(").append(rule.toString()).append(") AND ");
        }
        return result.substring(0, result.length() - 5);
    }
}
