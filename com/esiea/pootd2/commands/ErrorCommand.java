package com.esiea.pootd2.commands;

public class ErrorCommand extends Command{
    private String errorMessage;
    
    public ErrorCommand(String errorMessage){
        this.errorMessage = errorMessage;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
}
