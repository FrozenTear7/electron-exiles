package model;

import java.util.List;

public interface IStrategy {
    void addRule(Rule rule);
    List<Rule> getRules();
    List<MarketAction> evaluate(List<DataRow> data);
}