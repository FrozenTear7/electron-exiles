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
//        List<String> returnList = new ArrayList<>();
//
//        for (Strategy strategy : strategyList) {
//            returnList.add(strategy.toString());
//        }
//
//        return returnList;

        return strategyList;
    }

    public void removeStrategy(Strategy strategy){
        strategyList.remove(strategy);
    }

}
