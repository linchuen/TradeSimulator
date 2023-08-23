package com.cooba.TradeSimulator.Config;

import com.cooba.TradeSimulator.Annotation.Step;
import com.cooba.TradeSimulator.Object.TradeStep;
import com.cooba.TradeSimulator.Object.Transaction;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.*;

@Configuration
public class TransactionInitConfig {
    @Autowired
    List<? extends TradeStep> tradeSteps;
    @Autowired
    private ConfigurableBeanFactory beanFactory;

    @PostConstruct
    public void init() {
        Map<String, List<TradeStep>> tradeStepMap = new HashMap<>();
        for (TradeStep tradeStep : tradeSteps) {
            Class<? extends TradeStep> clazz = tradeStep.getClass();

            if (clazz.isAnnotationPresent(Step.class)) {
                Step step = clazz.getAnnotation(Step.class);
                buildTransactionChain(tradeStepMap, tradeStep, step);
            }
        }

        tradeStepMap.forEach((name, stepList) -> {
            List<TradeStep> sortedList = sortList(name, stepList);
            int size = sortedList.size();
            if (size == 0) return;

            TradeStep firstNode = sortedList.get(0);
            for (int i = 0; i < size; i++) {
                TradeStep nextNode = (i + 1) == size ? null : sortedList.get(i + 1);
                sortedList.get(i).next(nextNode);
            }
            beanFactory.registerSingleton(name, new Transaction(firstNode));
        });
    }

    private List<TradeStep> sortList(String name, List<TradeStep> stepList) {
        return stepList.stream().sorted(Comparator.comparing(tradeStep -> {
            Class<? extends TradeStep> clazz = tradeStep.getClass();
            if (clazz.isAnnotationPresent(Step.class)) {
                return clazz.getAnnotation(Step.class).sort();
            }
            return 0;
        })).toList();
    }

    private void buildTransactionChain(Map<String, List<TradeStep>> tradeStepMap, TradeStep tradeStep, Step step) {
        String transaction = step.transaction();
        tradeStepMap.compute(transaction, (k, stepList) -> addTradeStep(stepList, tradeStep));
    }

    @NotNull
    private List<TradeStep> addTradeStep(List<TradeStep> stepList, TradeStep tradeStep) {
        if (stepList == null) {
            List<TradeStep> newList = new ArrayList<>();
            newList.add(tradeStep);
            return newList;
        }

        stepList.add(tradeStep);
        return stepList;
    }
}
