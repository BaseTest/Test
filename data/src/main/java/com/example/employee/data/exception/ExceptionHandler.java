package com.example.employee.data.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * Обработчик исключений
 */
@Slf4j
public class ExceptionHandler {

    public static void handle(Throwable e, String message) {
        log.error(message, e);
        e.printStackTrace();
    }

}

