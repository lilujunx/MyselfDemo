package com.myself.library.pay;

/**
 *
 */

public class PayContext {
    private PayStrategyInterf mStrategy;

    public PayContext(PayStrategyInterf strategy) {
        mStrategy = strategy;
    }

    public void pay() {
        if (mStrategy != null) {
            mStrategy.doPay();
        }
    }
}
