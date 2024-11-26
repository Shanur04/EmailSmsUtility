package gov.cdac.Threads;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

class NamedThreadFactory implements ThreadFactory {
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private static final String NAME_PREFIX = "EmailSenderThread-";

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, NAME_PREFIX + threadNumber.getAndIncrement());
        return t;
    }
}