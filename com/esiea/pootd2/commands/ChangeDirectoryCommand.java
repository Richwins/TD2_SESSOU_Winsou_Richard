package com.esiea.pootd2.commands;

//Change le dossier courant avec le chemin passé en argument
public class ChangeDirectoryCommand extends Command {
    private String path;
    
    public ChangeDirectoryCommand(String path){
        this.path = path;
    }
    
    public String getPath() {
        return path;
    }
}
