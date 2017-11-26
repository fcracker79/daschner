package io.mirko.control;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Method;
import java.util.logging.Logger;

@Tracked()
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class TrackingInterceptor {
    @AroundInvoke
    public Object aroundInvoke(InvocationContext ctx) throws Exception {
        Tracked tracker = this.resolveAnnotation(ctx);
        final long start = System.currentTimeMillis();
        try {
            return ctx.proceed();
        } finally {
            Logger.getLogger(tracker.loggerName()).info(
                    String.format(
                            "Execution of %s took %s msecs",
                            ctx.getMethod(), System.currentTimeMillis() - start)
            );
        }
    }

    private Tracked resolveAnnotation(InvocationContext ctx) {
        Method method = ctx.getMethod();
        Tracked tracked = method.getAnnotation(Tracked.class);
        return tracked != null ? tracked : method.getDeclaringClass().getAnnotation(Tracked.class);
    }

}
