package com.esiea.pootd2;

import com.esiea.pootd2.controllers.ExplorerController;
import com.esiea.pootd2.interfaces.TextInterface;
import com.esiea.pootd2.interfaces.HttpInterface;
import com.esiea.pootd2.interfaces.IUserInterface;

public class ExplorerApp {

    public static void main(String[] args) {
        ExplorerController controller = new ExplorerController();
        IUserInterface userInterface;
        
        System.out.println("Choisissez l'interface utilisateur :");
        System.out.println("  1. text - Interface terminal");
        System.out.println("  2. http - Interface web (http://localhost:8001)");
        System.out.print("Votre choix (text/http) : ");
        
        String choice = "";
        java.io.Console console = System.console();
        if (console != null) {
            choice = console.readLine().trim().toLowerCase();
        } else {
            // Fallback si System.console() n'est pas disponible (ex: dans certains IDE)
            // Utiliser BufferedReader qui ne ferme pas System.in
            try {
                java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(System.in));
                choice = reader.readLine().trim().toLowerCase();
            } catch (java.io.IOException e) {
                System.err.println("Erreur lors de la lecture de l'entrée.");
                choice = "text"; // Par défaut
            }
        }
        
        if ("text".equals(choice) || "1".equals(choice)) {
            userInterface = new TextInterface(controller);
        } else if ("http".equals(choice) || "2".equals(choice)) {
            userInterface = new HttpInterface(controller);
        } else {
            System.err.println("Choix invalide. Utilisation de l'interface terminal par défaut.");
            userInterface = new TextInterface(controller);
        }
        
        userInterface.run();
    }
}

