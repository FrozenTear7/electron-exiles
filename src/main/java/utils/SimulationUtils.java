package utils;

import model.DataRow;
import model.Strategy;

import java.util.List;

public class SimulationUtils {
    public static Object simulate(List<Strategy> strategies, List<DataRow> data) {
        float budget = 100;
        float accumulatedActions = 0;
        int length = data.size();
        float stockValue = 0;
        for(int i = 0; i < length; i++) {
            stockValue = data.get(i).getStockValue();
            float buyValue = 0;  // counts total value of stock bought in this step
            float sellValue = 0;  // counts total value of stock sold in this step, always <= 0
            for(Strategy strategy : strategies){
                if(strategy.getRule().predicate(data, i)){
                    if(strategy.getTransactionValue() > 0) {
                        buyValue += strategy.getTransactionValue();
                    }
                    else{
                        sellValue += strategy.getTransactionValue();
                    }
                }
            }
            if(buyValue + sellValue > budget) {  // can't do all actions, not enough money
                if(sellValue / stockValue >= accumulatedActions){  // checking if we have enough stock to do only sell actions
                    accumulatedActions += sellValue/stockValue;
                    budget -= sellValue;
                }
            }
            else if ((buyValue + sellValue) / stockValue <= accumulatedActions) {  // can't do all actions, not enough stock
                if(buyValue <= budget){  // checking if we have enough money to do only buy actions
                    accumulatedActions += buyValue/stockValue;
                    budget -= buyValue;
                }
            }
        }
        float profit = budget + accumulatedActions*stockValue - 100;
        return "Profit: " + profit + "%, money left: " + budget + "%";
    }
}
