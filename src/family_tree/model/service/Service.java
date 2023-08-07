package family_tree.model.service;

import family_tree.model.family_tree.FamilyTree;
import family_tree.model.human.Gender;
import family_tree.model.human.Human;
import family_tree.model.writer.FileHandler;

import java.time.LocalDate;

public class Service {
    private int idHuman;
    private FamilyTree<Human> tree;
    private String text;
    private InfoText infoText;


    public Service() {
        tree = new FamilyTree<>();
        infoText = new InfoText();
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
    public void setTree(FamilyTree<Human> tree) {
        this.tree = tree;

    }
    public FamilyTree<Human> getTree() {
        return tree;
    }
    public String getHumanInfo() {
        return tree.getInfo();
    }


    public void addHuman(String name, Gender gender, LocalDate birthData) {
        Human human = new Human(name, gender, birthData);
        tree.add(human);
        setText(name + infoText.addText);
    }

    public void wedding(long idHusband, long idWife) {
        tree.setWedding(idHusband, idWife);
        setText(infoText.spousesText+infoText.andText);
    }

    public void addChildren(long idChildren, long idFather, long idMother) {

        Human child = tree.getById(idChildren);
        Human father = tree.getById(idFather);
        Human mother = tree.getById(idMother);
        if (child != null && father != null && mother != null) {
            father.addChild(child);
            mother.addChild(child);
            child.addParent(father);
            child.addParent(mother);
            setText(infoText.childText + child.getName() + infoText.addText);
        } else {
            setText(infoText.idNotFoundText);
        }
    }

    public void addParent(long idChildren, long idFather, long idMother) {
        Human child = tree.getById(idChildren);
        Human father = tree.getById(idFather);
        Human mother = tree.getById(idMother);
        if (child != null && father != null && mother != null) {
            father.addChild(child);
            mother.addChild(child);
            child.addParent(father);
            child.addParent(mother);
            setText(infoText.parentsText+father.getName()+infoText.andText+mother.getName()+infoText.addsText);
        } else {
            setText(infoText.idNotFoundText);
        }
    }



    public void readFile() {
        FileHandler fileHandler = new FileHandler();
        FamilyTree newTree = (FamilyTree) fileHandler.read(infoText.filePathText);
        setTree(newTree);
    }

    public void saveFile() {
        FileHandler fileHandler = new FileHandler();
        fileHandler.save(tree, infoText.filePathText);
    }

}
