package com.fruitday.boot.domain.error;

public class BusinessException extends  Exception {

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }
}
