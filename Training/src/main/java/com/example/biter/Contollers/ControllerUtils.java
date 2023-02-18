package com.example.biter.Contollers;

import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class ControllerUtils {

    static public Map<String, String> getErrors(BindingResult bindingResult){
        Map<String, String> errors = new HashMap<>();

        bindingResult.getFieldErrors().forEach(error ->
                errors.put(error.getField() + "Error", error.getDefaultMessage()));

        return  errors;
    }

}
