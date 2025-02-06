package reivosar.common.async.event;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ErrorHandlingStrategyTest {

    @Test
    void isStopOnError() {
        assertTrue(ErrorHandlingStrategy.STOP_ON_ERROR.isStopOnError());
        assertFalse(ErrorHandlingStrategy.SKIP_AND_CONTINUE.isStopOnError());
        assertFalse(ErrorHandlingStrategy.RETRY_AND_CONTINUE.isStopOnError());
    }

    @Test
    void isContinueOnError() {
        assertFalse(ErrorHandlingStrategy.STOP_ON_ERROR.isContinueOnError());
        assertTrue(ErrorHandlingStrategy.SKIP_AND_CONTINUE.isContinueOnError());
        assertTrue(ErrorHandlingStrategy.RETRY_AND_CONTINUE.isContinueOnError());
    }

    @Test
    void isRetryOnError() {
        assertFalse(ErrorHandlingStrategy.STOP_ON_ERROR.isRetryOnError());
        assertFalse(ErrorHandlingStrategy.SKIP_AND_CONTINUE.isRetryOnError());
        assertTrue(ErrorHandlingStrategy.RETRY_AND_CONTINUE.isRetryOnError());
    }
}