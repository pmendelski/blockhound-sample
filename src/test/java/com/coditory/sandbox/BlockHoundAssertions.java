package com.coditory.sandbox;

import reactor.blockhound.BlockingOperationError;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BlockHoundAssertions {
    static BlockingOperationError assertBlockingOperation(Callable<?> callable) {
        BlockingOperationError error = null;
        try {
            runOnParallel(callable);
        } catch (Exception e) {
            if (e.getCause() instanceof BlockingOperationError) {
                error = (BlockingOperationError) e.getCause();
            } else {
                throw e;
            }
        }
        assertNotNull(error);
        return error;
    }

    private static <T> T runOnParallel(Callable<T> callable) {
        return Mono.fromCallable(callable)
                .subscribeOn(Schedulers.parallel())
                .block();
    }
}
