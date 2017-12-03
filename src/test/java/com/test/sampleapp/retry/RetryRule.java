package com.test.sampleapp.retry;

/**
 * Created by MRomeh on 05/09/2017.
 */

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import com.test.sampleapp.retry.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

public final class RetryRule implements TestRule {

    @NotNull
    private Throwable[] errors = new Throwable[0];

    private int currentAttempt = 0;

    @Override
    public Statement apply(final Statement base, final Description description) {
        final Retry retryAnnotation = description.getAnnotation(Retry.class);
        if (retryAnnotation == null) {
            return base;
        }
        final int times = retryAnnotation.times();
        if (times <= 0) {
            throw new IllegalArgumentException(
                    "@" + Retry.class.getSimpleName() + " cannot be used with a \"times\" parameter less than 1"
            );
        }
        final long timeout = retryAnnotation.timeout();
        if (timeout < 0) {
            throw new IllegalArgumentException(
                    "@" + Retry.class.getSimpleName() + " cannot be used with a \"timeout\" parameter less than 0"
            );
        }

        errors = new Throwable[times];

        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                while (currentAttempt < times) {
                    try {
                        base.evaluate();
                        return;
                    } catch (Throwable t) {
                        errors[currentAttempt] = t;
                        currentAttempt++;
                        Thread.sleep(timeout);
                    }
                }
                throw RetryException.from(errors);
            }
        };
    }

    /**
     * @return an array representing the errors that have been encountered so far. {@code errors()[0]} corresponds to the
     * Throwable encountered when running the test-case for the first time, {@code errors()[1]} corresponds to the
     * Throwable encountered when running the test-case for the second time, and so on.
     */
    @NotNull
    public Throwable[] errors() {
        return Arrays.copyOfRange(errors, 0, currentAttempt);
    }

    /**
     * A convenience method to return the {@link Throwable} that was encountered on the last invocation of this test-case.
     * Returns {@code null} if this is the first invocation of the test-case.
     */
    public Throwable lastError() {
        final int currentAttempt = currentAttempt();
        final Throwable[] errors = errors();
        if (currentAttempt == 0) {
            return null;
        }
        return errors[currentAttempt - 1];
    }

    /**
     * @return the current attempt (0-indexed). 0 is the very first attempt, 1 is the next one, and so on.
     */
    public int currentAttempt() {
        return currentAttempt;
    }
}
