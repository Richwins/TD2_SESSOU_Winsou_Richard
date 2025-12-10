package com.esiea.pootd2.controllers;

import com.esiea.pootd2.commands.*;
import com.esiea.pootd2.commands.parsers.ICommandParser;
import com.esiea.pootd2.commands.parsers.UnixLikeCommandParser;
import com.esiea.pootd2.models.FileInode;
import com.esiea.pootd2.models.FolderInode;
import com.esiea.pootd2.models.Inode;

public class ExplorerController implements IExplorerController {
    private FolderInode root;
    private FolderInode currentFolder;
    private ICommandParser parser;
    
    public ExplorerController() {
        this.root = new FolderInode("/");
        this.currentFolder = root;
        this.parser = new UnixLikeCommandParser();
    }
    
    public FolderInode getRoot() {
        return root;
    }
    
    public FolderInode getCurrentFolder() {
        return currentFolder;
    }
    
    public void setCurrentFolder(FolderInode folder) {
        this.currentFolder = folder;
    }
    
    @Override
    public String executeCommand(String commandStr) {
        Command cmd = parser.parse(commandStr);
        return doCommand(cmd);
    }
    
    private String doCommand(Command cmd) {
        if (cmd instanceof ListCommand) {
            return doCommand((ListCommand) cmd);
        } else if (cmd instanceof ChangeDirectoryCommand) {
            return doCommand((ChangeDirectoryCommand) cmd);
        } else if (cmd instanceof MakeDirectoryCommand) {
            return doCommand((MakeDirectoryCommand) cmd);
        } else if (cmd instanceof TouchCommand) {
            return doCommand((TouchCommand) cmd);
        } else if (cmd instanceof ErrorCommand) {
            return doCommand((ErrorCommand) cmd);
        }
        return "";
    }
    
    private String doCommand(ListCommand cmd) {
        StringBuilder result = new StringBuilder();
        for (Inode inode : currentFolder.children) {
            result.append(inode.getName()).append(" ").append(inode.getSize()).append("\n");
        }
        return result.toString().trim();
    }
    
    private String doCommand(ChangeDirectoryCommand cmd) {
        String path = cmd.getPath();
        
        try {
            if (path.equals("..")) {
                if (currentFolder.getParent() != null) {
                    currentFolder = currentFolder.getParent();
                }
                return "";
            } else if (path.equals("/")) {
                currentFolder = root;
                return "";
            } else {
                // Recherche dans les enfants du dossier courant
                for (Inode inode : currentFolder.children) {
                    if (inode.getName().equals(path) && inode instanceof FolderInode) {
                        currentFolder = (FolderInode) inode;
                        return "";
                    }
                }
                return "cd: " + path + ": No such file or directory";
            }
        } catch (Exception e) {
            return "cd: " + path + ": Error";
        }
    }
    
    private String doCommand(MakeDirectoryCommand cmd) {
        String name = cmd.getName();
        
        try {
            // Vérifier si le nom existe déjà
            for (Inode inode : currentFolder.children) {
                if (inode.getName().equals(name)) {
                    return "mkdir: " + name + ": File or directory already exists";
                }
            }
            
            FolderInode newFolder = new FolderInode(name);
            newFolder.setParent(currentFolder);
            currentFolder.addInode(newFolder);
            return "";
        } catch (Exception e) {
            return "mkdir: " + name + ": Error";
        }
    }
    
    private String doCommand(TouchCommand cmd) {
        String name = cmd.getName();
        
        try {
            // Vérifier si le nom existe déjà
            for (Inode inode : currentFolder.children) {
                if (inode.getName().equals(name)) {
                    return "touch: " + name + ": File already exists";
                }
            }
            
            FileInode newFile = new FileInode(name);
            newFile.setParent(currentFolder);
            currentFolder.addInode(newFile);
            return "";
        } catch (Exception e) {
            return "touch: " + name + ": Error";
        }
    }
    
    private String doCommand(ErrorCommand cmd) {
        return cmd.getErrorMessage();
    }
}
