package com.esiea.pootd2.commands;

//Crée le fichier avec le nom passé en argument
public class TouchCommand extends Command {
    private String name;
    
    public TouchCommand(String name){
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
