package com.gmail.psyh2409;

import java.util.Objects;

public class CustomFieldException extends Exception{

    private String message;

    public CustomFieldException() {super();}

    public CustomFieldException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
