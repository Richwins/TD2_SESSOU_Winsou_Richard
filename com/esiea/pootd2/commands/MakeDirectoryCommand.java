package com.esiea.pootd2.commands;

//Crée le dossier avec le nom passé en argument
public class MakeDirectoryCommand extends Command {
    private String name;
    
    public MakeDirectoryCommand(String name){
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
