package model;

import lombok.Data;

@Data
public class Strategy {
    private IRule rule;
    private float transactionValue;
    private Float profit;

    public Strategy(float transactionValue, IRule rule) {
        this.transactionValue = transactionValue;
        this.rule = rule;
    }

    public IRule getRule() {
        return rule;
    }

    /*public List<MarketAction> evaluate(List<DataRow> data) {
        List<MarketAction> result = new ArrayList<>();
        List<List<MarketAction>> partialEvaluations = new ArrayList<>();
        MarketAction defaultAction;
        if (type == StrategyType.STRATEGY_AND) {
            defaultAction = transactionValue > 0 ? MarketAction.BUY : MarketAction.SELL;
        }
        else {
            defaultAction = MarketAction.UNDEF;
        }

        for(IRule rule : ruleList){
            partialEvaluations.add(rule.evaluate(data));
        }

        for(int i = 0; i < data.size(); i++) {
            MarketAction tmp = defaultAction;
            for(int ruleIndex = 0; ruleIndex < ruleList.size(); ruleIndex++) {
                if(type == StrategyType.STRATEGY_AND) {
                    if(partialEvaluations.get(ruleIndex).get(i) != defaultAction) {
                        tmp = MarketAction.UNDEF;
                        break;
                    }
                }
                else {
                    if(partialEvaluations.get(ruleIndex).get(i) != MarketAction.UNDEF) {
                        tmp = defaultAction;
                        break;
                    }
                }
            }
            result.add(tmp);
        }
        return result;
    }*/

    public String toString() {
        String profitString = profit == null ? "" : profit.toString() + " | ";
        String transactionType = transactionValue >= 0 ? "Buy" : "Sell";

        return profitString + transactionType + ": " + transactionValue + "% " + rule.toString();
    }
}
