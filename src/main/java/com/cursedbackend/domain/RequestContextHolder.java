package com.cursedbackend.domain;

public class RequestContextHolder {
    private static final ThreadLocal<RequestContext> HOLDER = new ThreadLocal<>();

    private RequestContextHolder() {
    }

    public static void set(RequestContext ctx) {
        HOLDER.set(ctx);
    }

    public static RequestContext get() {
        return HOLDER.get();
    }

    public static void clear() {
        HOLDER.remove();
    }
}
