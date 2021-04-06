package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс тестирования работы логирования.
 */
public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        boolean bool = false;
        byte b = 32;
        char ch = 'W';
        short sh = 3200;
        int i = 1234567;
        long l = 4223134;
        double d = 7654321;
        float fl = 8573521;
        LOG.debug(
                "Переменные:\nboolean = {},\nbyte = {},\nchar = {},\nshort = {},\nint = {},\nlong = {},\ndouble = {},\nfloat = {}",
                bool, b, ch, sh, i, l, d, fl
        );
    }
}
