package com.airtribe.meditrack.util;

import java.util.concurrent.atomic.AtomicInteger;

// Singleton with eager initialization
public class IdGenerator {
    private static final IdGenerator INSTANCE = new IdGenerator();
    private final AtomicInteger counter = new AtomicInteger(0); // Concurrency safe

    private IdGenerator() {}

    public static IdGenerator getInstance() {
        return INSTANCE;
    }

    public String generateId(String prefix) {
        return prefix + counter.incrementAndGet();
    }
}
