package com.test.sampleapp.retry;

/**
 * Created by MRomeh on 05/09/2017.
 */

import javax.validation.constraints.NotNull;
import java.io.PrintWriter;
import java.io.StringWriter;
import com.test.sampleapp.retry.*;

/**
 * An exception thrown to signal that a retry operation (executed via {@link RetryRule}) has retried more than the
 * allowed number of times, and has still failed.
 */
public final class RetryException extends RuntimeException {

    private RetryException(@NotNull String message) {
        super(message);
    }

    /**
     * @param errors the errors for each attempt at running this test-case
     */
    @NotNull
    public static RetryException from(@NotNull Throwable[] errors) {
        final StringBuilder msg = new StringBuilder("Invoked methods still failed after " + errors.length + " attempts.");
        for (int i = 0; i < errors.length; i++) {
            final Throwable error = errors[i];
            msg.append('\n');
            msg.append("Attempt #").append(i).append(" threw exception:");
            msg.append(stackTraceAsString(error));
        }
        return new RetryException(msg.toString());
    }

    @NotNull
    private static String stackTraceAsString(@NotNull Throwable t) {
        final StringWriter errors = new StringWriter();
        t.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }
}

