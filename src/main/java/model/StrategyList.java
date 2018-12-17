package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StrategyList {
    private List<Strategy> strategyList = new ArrayList<>();

    public StrategyList() {

    }

    public void addStrategy(Strategy strategy) {
        strategyList.add(strategy);
    }

    public Strategy getStrategy(int index) {
        return strategyList.get(index);
    }

    public List<Strategy> getStrategyList() {
        return strategyList;
    }

    public void removeStrategy(Strategy strategy){
        strategyList.remove(strategy);
    }

}
