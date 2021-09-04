package com.example.demo.service;

import com.example.demo.model.RateStrategyName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class RateStrategyFactory {

    private Map<RateStrategyName, RateStrategy> strategies;

    @Autowired
    public RateStrategyFactory(Set<RateStrategy> strategySet) {
        this.strategies = new HashMap<RateStrategyName, RateStrategy>();
        strategySet.forEach(strategy -> strategies.put(strategy.getRateStrategyName(), strategy));
    }

    public RateStrategy findStrategy(RateStrategyName rateStrategyName) {
        return strategies.get(rateStrategyName);
    }
}
