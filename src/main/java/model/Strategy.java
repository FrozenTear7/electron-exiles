package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Strategy {
    private List<IRule> ruleList = new ArrayList<>();
    private float transactionValue;

    public Strategy(float transactionValue) {
        this.transactionValue = transactionValue;
    }

    public void addRule(IRule rule) {
        ruleList.add(rule);
    }

    public List<IRule> getRules() {
        return ruleList;
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
        if(transactionValue >= 0) {
            return "Buy: " + transactionValue + "%";
        } else {
            return "Sell: " + transactionValue + "%";
        }
    }
}
