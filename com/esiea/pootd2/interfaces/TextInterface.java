package com.esiea.pootd2.interfaces;
import com.esiea.pootd2.controllers.ExplorerController;
import com.esiea.pootd2.interfaces.IUserInterface;

import java.util.Scanner;

public class TextInterface implements IUserInterface{

    private ExplorerController explorerController;
    @Override
    public void run() {
        System.out.print('>');
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (input.equals("exit")) {
            return;
        }
        while(!input.equals("exit")){
            explorerController.executeCommand(input);
            System.out.print('>');
            input = sc.nextLine();
        }
        System.out.println("Session fermée !");
        System.exit(0);
    }
}
