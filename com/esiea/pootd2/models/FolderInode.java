package com.esiea.pootd2.models;

import java.util.ArrayList;
import java.util.List;

public class FolderInode extends Inode {
    List<Inode> children;

    //Le constructeur permet d'initialiser la taille d'un dossier ainsi que la liste des ses enfants
    public FolderInode(String name) {
        super(name);
        this.children = new ArrayList<>();
    }

    //Cette fonction permet d'ajouter un fichier ou dossier à la liste des enfants du dossier courent
    public void addInode(Inode inode){
        children.add(inode);

        System.out.println(" Liste des enfant après l'ajout du fichier/dossier dans " + getName());
        for (Inode child : children) {
            System.out.println(child.getName());
        }
        System.out.println('\n');
    }

    @Override
    public int getSize(){
        int taille = 0;
        for (Inode child : children) {
            taille += child.getSize();
        }
        return taille;
    }

}
