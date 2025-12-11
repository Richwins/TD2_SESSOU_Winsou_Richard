package com.esiea.pootd2.commands;

//Gérer les erreurs de commands
public class ErrorCommand extends Command{
    private String errorMessage;
    
    public ErrorCommand(String errorMessage){
        this.errorMessage = errorMessage;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
}
