package com.cursedbackend.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CursedLogger {

    private static final Logger log = LoggerFactory.getLogger("CursedLogger");

    private CursedLogger() {
        // prevent instantiation
    }

    /* ================= INFO ================= */

    public static void info(String message) {
        log.info("\n" + message);
    }

    public static void info(String message, Object... args) {
        log.info("\n" + message, args);
    }

    /* ================= DEBUG ================= */

    public static void debug(String message) {
        log.debug("\n" + message);
    }

    public static void debug(String message, Object... args) {
        log.debug("\n" + message, args);
    }

    /* ================= WARN ================= */

    public static void warn(String message) {
        log.warn("\n" + message);
    }

    public static void warn(String message, Object... args) {
        log.warn("\n" + message, args);
    }

    /* ================= ERROR ================= */

    public static void error(String message) {
        log.error("\n" + message);
    }

    public static void error(String message, Throwable throwable) {
        log.error("\n" + message, throwable);
    }

    public static void error(String message, Object... args) {
        log.error("\n" + message, args);
    }
}
