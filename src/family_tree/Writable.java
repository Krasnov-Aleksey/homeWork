package family_tree;

import family_tree.family_tree.FamilyTree;

import java.io.*;

public interface Writable {
    void saveFile(FamilyTree tree,String path) throws IOException;
    //void loadFile(FamilyTree tree, String path) throws IOException, ClassNotFoundException;
}
