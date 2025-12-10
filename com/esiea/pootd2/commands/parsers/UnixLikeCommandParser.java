package com.esiea.pootd2.commands.parsers;

import com.esiea.pootd2.commands.*;

import java.util.ArrayList;
import java.util.List;

public class UnixLikeCommandParser implements ICommandParser {
    
    @Override
    public Command parse(String commandStr) {
        if (commandStr == null || commandStr.trim().isEmpty()) {
            return new ErrorCommand("Empty command");
        }
        
        try {
            List<String> arguments = splitArguments(commandStr);
            return mapCommand(arguments);
        } catch (Exception e) {
            return new ErrorCommand("Parse error: " + e.getMessage());
        }
    }
    
    private List<String> splitArguments(String commandStr) {
        List<String> arguments = new ArrayList<>();
        String[] parts = commandStr.trim().split("\\s+");
        for (String part : parts) {
            if (!part.isEmpty()) {
                arguments.add(part);
            }
        }
        return arguments;
    }
    
    private Command mapCommand(List<String> arguments) {
        if (arguments.isEmpty()) {
            return new ErrorCommand("No command provided");
        }
        
        String command = arguments.get(0);
        
        switch (command) {
            case "ls":
                if (arguments.size() > 1) {
                    return new ErrorCommand("ls: too many arguments");
                }
                return new ListCommand();
                
            case "cd":
                if (arguments.size() < 2) {
                    return new ErrorCommand("cd: missing argument");
                }
                if (arguments.size() > 2) {
                    return new ErrorCommand("cd: too many arguments");
                }
                return new ChangeDirectoryCommand(arguments.get(1));
                
            case "mkdir":
                if (arguments.size() < 2) {
                    return new ErrorCommand("mkdir: missing argument");
                }
                if (arguments.size() > 2) {
                    return new ErrorCommand("mkdir: too many arguments");
                }
                return new MakeDirectoryCommand(arguments.get(1));
                
            case "touch":
                if (arguments.size() < 2) {
                    return new ErrorCommand("touch: missing argument");
                }
                if (arguments.size() > 2) {
                    return new ErrorCommand("touch: too many arguments");
                }
                return new TouchCommand(arguments.get(1));
                
            default:
                return new ErrorCommand("Unknown command: " + command);
        }
    }
}

