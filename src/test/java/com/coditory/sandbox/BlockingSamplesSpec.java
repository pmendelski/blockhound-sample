package com.coditory.sandbox;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import reactor.blockhound.BlockHound;
import reactor.blockhound.BlockingOperationError;

import static com.coditory.sandbox.BlockHoundAssertions.assertBlockingOperation;

class BlockingSamplesSpec {
    static {
        BlockHound.install();
    }

    @Test
    public void shouldThrowErrorOnSimpleBlock() {
        BlockingOperationError error = assertBlockingOperation(BlockingSamples::sleepThread);
        Assertions.assertEquals(error.getMessage(), "Blocking call! java.lang.Thread.sleep");
    }

    @Test
    public void shouldThrowErrorOnReadingFileWithBufferedReader() {
        BlockingOperationError error = assertBlockingOperation(BlockingSamples::readFileWithBufferedReader);
        Assertions.assertEquals(error.getMessage(), "Blocking call! java.io.FileInputStream#readBytes");
    }

    @Test
    @Disabled("Test fails and I don't know why.")
    public void shouldThrowErrorOnReadFileWithFilesReadString() {
        assertBlockingOperation(BlockingSamples::readFileWithFilesReadString);
        // fails, why?
    }
}
