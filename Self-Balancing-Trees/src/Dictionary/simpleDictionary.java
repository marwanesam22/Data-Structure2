package Dictionary;
import AVLTree.*;
import Main.*;
import RBTree.*;

public class simpleDictionary {
    IBalancedTree<String> dictionary;
    public simpleDictionary(int dictionaryType){
        if(dictionaryType == 1)dictionary = new AVL<String>();
        else dictionary = new RBTree<String>();//initialize dictionary as a RB tree
    }

    public boolean insert(String newWord){
        return dictionary.insert(newWord);
    }

    public boolean delete(String word){
        return dictionary.delete(word);
    }


    public boolean search(String word){
        return dictionary.search(word);
    }

    public int size(){
        return dictionary.size();
    }

    public int height(){
        return dictionary.height();
    }
}
