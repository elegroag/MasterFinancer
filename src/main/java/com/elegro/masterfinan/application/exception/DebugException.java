package com.elegro.masterfinan.application.exception;

import java.util.Map;
import java.util.Optional;

public class DebugException extends Exception{

    private static Map<String, String> variables;

    public static Map<String, String> getVariables() {
        return variables;
    }

    public static void setVariables(Map<String, String> _variables) {
        variables = _variables;
    }

    public static void addVar(String key, String value){
        variables.put(key, value);
    }

    public static Optional<String> getVar(String key){
        if(variables.containsKey(key)){
            return Optional.of(variables.get(key));
        }else{
            return Optional.empty();
        }
    }
    
    public DebugException(String message) {
        super(message);
    }

    public DebugException(String message, Throwable cause) {
        super(message, cause);
    }

    public DebugException(Throwable cause) {
        super(cause);
    }
}
