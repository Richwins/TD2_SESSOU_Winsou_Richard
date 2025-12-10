package com.esiea.pootd2.interfaces;
import com.esiea.pootd2.controllers.IExplorerController;

import java.util.Scanner;

public class TextInterface implements IUserInterface{

    private IExplorerController explorerController;
    
    public TextInterface(IExplorerController explorerController) {
        this.explorerController = explorerController;
    }

    @Override
    public void run() {
        try (Scanner sc = new Scanner(System.in)) {
            String input;
            
            while(true){
                System.out.print('>');
                input = sc.nextLine();
                
                if (input.equals("exit")) {
                    break;
                }
                
                String result = explorerController.executeCommand(input);
                if (!result.isEmpty()) {
                    System.out.println(result);
                }
            }
        }
    }
}
