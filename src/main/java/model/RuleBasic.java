package model;

import java.util.List;

public class RuleBasic implements IRule {
    private int daysBack;
    private float valueChange;
    private boolean acceptIfGreater;

    public RuleBasic(int daysBack, float valueChange, boolean acceptIfGreater) {
        this.daysBack = daysBack;
        this.valueChange = valueChange;
        this.acceptIfGreater = acceptIfGreater;
    }

    public boolean predicate(List<DataRow> data, int index) {
        if (index < daysBack) {
            return false;
        }
        if (acceptIfGreater) {
            return data.get(index).getStockValue() - data.get(index - daysBack).getStockValue() > valueChange;
        }
        else {
            return data.get(index).getStockValue() - data.get(index - daysBack).getStockValue() < valueChange;
        }
    }

    @Override
    public List<MarketAction> evaluate(List<DataRow> data) {
        return null;
    }

    @Override
    public String toString() {
        if (acceptIfGreater) {
            return "At least " + (100+valueChange) + "% of value " + daysBack + " days ago";
        }
        else {
            return "At most " + (100+valueChange) + "% of value " + daysBack + " days ago";
        }
    }
}
