package model;

import java.util.List;

public interface IStrategy {
    void addRule(IRule rule);
    List<IRule> getRules();
    List<MarketAction> evaluate(List<DataRow> data);
}