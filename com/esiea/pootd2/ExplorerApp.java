package com.esiea.pootd2;

import com.esiea.pootd2.controllers.ExplorerController;
import com.esiea.pootd2.interfaces.TextInterface;

public class ExplorerApp {

    public static void main(String[] args) {
        ExplorerController controller = new ExplorerController();
        TextInterface textInterface = new TextInterface(controller);
        textInterface.run();
    }
}

