public class RBTree <T extends Comparable<T> > {

    static final boolean RED = false;
    static final boolean BLACK = true;
    private RBNode<T> root;
    private int numberOfNodes = 0;

    public RBTree() { }

    public RBNode<T> getRoot() {
        return root;
    }

    //-----------------------insert------------------------------------
    public boolean insert(T key) {

        if(search(key))
            return false;

        root = insertRecursively(root, key);
        fixTreeAfterInsert(search(this.root, key));
        numberOfNodes++;
        return true;
    }

    private RBNode<T> insertRecursively(RBNode<T> current, T key) {

        // Empty Tree
        if(root == null) {
            return new RBNode<>(key,null,null,null,BLACK);
        }

        if(current == null) {
            return new RBNode<>(key,null,null,null,RED);
        }

        if(key.compareTo(current.getData()) > 0) {
            current.setRight(insertRecursively(current.getRight(),key));
            current.getRight().setParent(current);

        } else if(key.compareTo(current.getData()) < 0) {
            current.setLeft(insertRecursively(current.getLeft(),key));
            current.getLeft().setParent(current);
        }

        return current;
    }

    private void fixTreeAfterInsert(RBNode<T> current) {

        if(current == this.root) {
            current.setColor(BLACK); //enforce black color for root
            return;
        }

        RBNode<T> parent = current.getParent();

        // CASE 1 - parent of the inserted node is black
        if (parent.getColor() == BLACK)
            return;

        RBNode<T> grandparent = parent.getParent();
        RBNode<T> uncle = getUncle(current);

        // CASE 2 - Uncle is black or null
        if(uncle == null || uncle.getColor() == BLACK) {

            RBNode<T> recolorNode;
            String parentToChild = whichSide(current,parent), grandToParent = whichSide(parent,grandparent);
            if(parentToChild.equals("Left") && grandToParent.equals("Left")) {
                RBNode<T> grandGParent = grandparent.getParent();
                if(grandGParent == null) {
                    this.root = singleRightRotation(parent);
                    recolorNode = this.root;
                    this.root.setParent(null);
                } else {
                    String ggParentToG = whichSide(grandparent,grandGParent);
                    recolorNode = parent;
                    if(ggParentToG.equals("Left")) {
                        grandGParent.setLeft(singleRightRotation(parent));
                        parent.setParent(grandGParent);
                    } else {
                        grandGParent.setRight(singleRightRotation(parent));
                        parent.setParent(grandGParent);
                    }
                }

            } else if(parentToChild.equals("Right") && grandToParent.equals("Right")) {
                RBNode<T> grandGParent = grandparent.getParent();
                if(grandGParent == null) {
                    this.root = singleLeftRotation(parent);
                    recolorNode = this.root;
                    this.root.setParent(null);
                } else {
                    String ggParentToG = whichSide(grandparent,grandGParent);
                    recolorNode = parent;
                    if(ggParentToG.equals("Left")) {
                        grandGParent.setLeft(singleLeftRotation(parent));
                        parent.setParent(grandGParent);
                    } else {
                        grandGParent.setRight(singleLeftRotation(parent));
                        parent.setParent(grandGParent);
                    }
                }

            } else if(parentToChild.equals("Left") && grandToParent.equals("Right")) {
                RBNode<T> grandGParent = grandparent.getParent();
                recolorNode = current;
                String ggParentToG = whichSide(grandparent,grandGParent);
                if(ggParentToG.equals("Left")) {
                    grandGParent.setLeft(RLDoubleRotation(current));
                    current.setParent(grandGParent);
                } else {
                    grandGParent.setRight(RLDoubleRotation(current));
                    current.setParent(grandGParent);
                }
            } else {
                RBNode<T> grandGParent = grandparent.getParent();
                recolorNode = current;
                String ggParentToG = whichSide(grandparent,grandGParent);
                if(ggParentToG.equals("Left")) {
                    grandGParent.setLeft(LRDoubleRotation(current));
                    current.setParent(grandGParent);
                } else {
                    grandGParent.setRight(LRDoubleRotation(current));
                    current.setParent(grandGParent);
                }
            }

            recolorNode.setColor(BLACK);
            if(recolorNode.getLeft() != null)
                recolorNode.getLeft().setColor(RED);
            if(recolorNode.getRight() != null)
                recolorNode.getRight().setColor(RED);

        }

        // CASE 3 - Uncle is red
        if(uncle != null && uncle.getColor() == RED) {
            parent.setColor(BLACK);
            uncle.setColor(BLACK);
            grandparent.setColor(RED);
            fixTreeAfterInsert(grandparent);
        }
    }
    //-----------------------insert------------------------------------


    //-----------------------Delete------------------------------------

    //-----------------------Delete------------------------------------


    //-----------------------Rotation------------------------------------
    private RBNode<T> singleRightRotation(RBNode<T> node) {
        RBNode<T> rightChild = node.getRight();
        RBNode<T> parent = node.getParent();
        node.setRight(parent);
        parent.setParent(node);
        parent.setLeft(rightChild);
        return node;
    }

    private RBNode<T> singleLeftRotation(RBNode<T> node) {
        RBNode<T> leftChild = node.getLeft();
        RBNode<T> parent = node.getParent();
        node.setLeft(parent);
        parent.setParent(node);
        parent.setRight(leftChild);
        return node;
    }

    private RBNode<T> LRDoubleRotation(RBNode<T> node) {
        RBNode<T> grandParent = node.getParent().getParent();
        grandParent.setLeft(singleLeftRotation(node));
        node.setParent(grandParent);
        return singleRightRotation(node);
    }

    private RBNode<T> RLDoubleRotation(RBNode<T> node) {
        RBNode<T> grandParent = node.getParent().getParent();
        grandParent.setRight(singleRightRotation(node));
        node.setParent(grandParent);
        return singleLeftRotation(node);
    }
    //-----------------------Rotation------------------------------------


    //-----------------------Auxiliary functions------------------------------------
    private String whichSide(RBNode<T> child, RBNode<T> parent){
        if(child.getData().compareTo(parent.getData()) > 0)
            return "Right";
        return "Left";
    }

    private RBNode<T> getUncle(RBNode<T> child){
        RBNode<T> grandParent = child.getParent().getParent();
        if(child.getData().compareTo(grandParent.getData()) > 0)
            return grandParent.getLeft();

        return grandParent.getRight();
    }
    //-----------------------Auxiliary functions------------------------------------


    //-----------------------SEARCH------------------------------------
    private RBNode < T > search(RBNode <T> current, T data) {
        if (current == null)
            return null;
        if (data.compareTo(current.getData()) > 0) { // larger than
            return search(current.getRight(), data);
        } else if (data.compareTo(current.getData()) < 0) { // less than
            return search(current.getLeft(), data);
        } else return current;
    }

    public boolean search(T data) {
        return search(this.root, data) != null;
    }
    //-----------------------SEARCH------------------------------------


    //-----------------------SIZE--------------------------------------
    public int size() {
        return this.numberOfNodes;
    }
    //-----------------------SIZE--------------------------------------


    //-----------------------height--------------------------------------
    public int height() {
        return getHeight(this.root);
    }

    private int getHeight(RBNode<T> current) {
        if(current == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(current.getLeft()),getHeight(current.getRight()));
    }
    //-----------------------height--------------------------------------


    //-----------------------traverse--------------------------------------
    public void traverseInorder(RBNode<T> current) {
        if(current == null) {
            return;
        }
        traverseInorder(current.getLeft());
        System.out.println(current.getData() + " " + (current.getColor() == RED? "R" : "B" )+ " ");
        traverseInorder(current.getRight());
    }
    //-----------------------traverse--------------------------------------

    
}
