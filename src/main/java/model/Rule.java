package model;

import java.util.ArrayList;
import java.util.List;

public class Rule implements IStrategy {
    private int daysBack;
    private float valueChange;

    public Rule(int daysBack, float valueChange) {
//        if (daysBack <= 0) {
//            throw new LoadException("Number of days must be positive!");
//        } else if (valueChange < -100.0 || valueChange > 100.0) {
//            throw new LoadException("Change of value must be between -100 to 100!");
//        }

        this.daysBack = daysBack;
        this.valueChange = valueChange;
    }

    @Override
    public void addRule(IRule rule) {

    }

    public void addRule(Rule rule) {
    }

    public List<IRule> getRules() {
        return null;
    }

    public List<MarketAction> evaluate(List<DataRow> data) {
        List<MarketAction> result = new ArrayList<>();
        for(int i = 0; i < data.size(); i++) {
            if (i < daysBack) {
                result.add(MarketAction.UNDEF);
            }
            else {
                if (valueChange > 0) {
                    if (data.get(i).getStockValue() - data.get(i-daysBack).getStockValue() >= valueChange) {
                        result.add(MarketAction.BUY);
                    }
                    else {
                        result.add(MarketAction.UNDEF);
                    }
                }
                else {
                    if (data.get(i).getStockValue() - data.get(i-daysBack).getStockValue() <= valueChange) {
                        result.add(MarketAction.SELL);
                    }
                    else {
                        result.add(MarketAction.UNDEF);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "If stock value changed " + daysBack + " days ago, by: " + valueChange + "%";
    }
}
