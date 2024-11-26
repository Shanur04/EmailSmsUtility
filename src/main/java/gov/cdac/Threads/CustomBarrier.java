package gov.cdac.Threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CustomBarrier {
    private final Runnable barrierAction;
    private CyclicBarrier barrier;

    public CustomBarrier(int parties, Runnable barrierAction) {
        this.barrierAction = barrierAction;
        this.barrier = new CyclicBarrier(parties, barrierAction);
    }

    public void await() throws InterruptedException, BrokenBarrierException {
        barrier.await();
    }

    public void adjustParties(int newParties) {
        if (newParties > 0) {
            barrier = new CyclicBarrier(newParties, barrierAction);
        }
    }
}
