package com.esiea.pootd2.interfaces;

import com.esiea.pootd2.controllers.IExplorerController;

public abstract class AbstractInterface implements IUserInterface {
    protected IExplorerController controller;

    public AbstractInterface(IExplorerController controller) {
        this.controller = controller;
    }
}

