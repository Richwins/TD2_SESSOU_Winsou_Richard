package com.esiea.pootd2.models;

public abstract class Inode {
    private final String name;
    protected FolderInode parent;

    public Inode(String name) {
        this.name = name;
    }

    public FolderInode getParent() {
        return parent;
    }
    
    public void setParent(FolderInode parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public int getSize(){
        return 0;
    }

}
