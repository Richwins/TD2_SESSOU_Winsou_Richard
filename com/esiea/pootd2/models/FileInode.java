package com.esiea.pootd2.models;

public class FileInode extends Inode {

    /*
    Ce constructeur permet d'attribuer aléatoirement une taille compris entre 1 et 100000 à un nouveau fichier
     */
    public FileInode(String name) {
        super(name);
    }

    @Override
    public int getSize(){
        return (int) (Math.random() * 100000) + 1;
    }
}
