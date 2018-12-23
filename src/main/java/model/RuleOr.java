package model;

import java.util.List;

public class RuleOr implements IRule {
    private List<IRule> subRules;

    public RuleOr(List<IRule> subRules) {
        this.subRules = subRules;
    }

    public boolean predicate(List<DataRow> data, int index) {
        for (IRule rule : subRules) {
            if (rule.predicate(data, index)) {
                return true;
            }
        }
        return false;
    }

    public void addRule(IRule rule) {
        subRules.add(rule);
    }

    public List<IRule> getRules() {
        return subRules;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (IRule rule : subRules) {
            result.append("(").append(rule.toString()).append(") OR ");
        }
        return result.substring(0, result.length() - 4);
    }
}
