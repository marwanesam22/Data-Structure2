package RBTree;
import Main.*;
public class RBTree<T extends Comparable<T>> implements IBalancedTree<T> {

    static final boolean RED = false;
    static final boolean BLACK = true;
    private RBNode<T> root;
    private int numberOfNodes = 0;
    private String testTree = "";

    public RBTree() {
    }

    public RBNode<T> getRoot() {
        return root;
    }

    //-----------------------insert------------------------------------
    public boolean insert(T key) {
        return insertInTree(key);
    }

    private boolean insertInTree(T key) {

        RBNode<T> node = this.root, parent = null;

        while(node != null) {
            parent = node;
            if(key.compareTo(node.getData()) > 0)
                node = node.getRight();
            else if(key.compareTo(node.getData()) < 0)
                node = node.getLeft();
            else
                return false;
        }

        RBNode<T> newNode = new RBNode<T>(key,parent,null,null,RED);
        this.numberOfNodes++;
        if(parent == null)
            this.root = newNode;
        else if(key.compareTo(parent.getData()) > 0)
            parent.setRight(newNode);
        else
            parent.setLeft(newNode);

        fixTreeAfterInsert(newNode);
        return true;

    }

    private void fixTreeAfterInsert(RBNode<T> current) {

        if (current == this.root) {
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
        if (uncle == null || uncle.getColor() == BLACK) {

            RBNode<T> recolorNode;
            String parentToChild = whichSide(current, parent), grandToParent = whichSide(parent, grandparent);
            if (grandToParent.equals("Left") && parentToChild.equals("Left")) {
                RBNode<T> grandGParent = grandparent.getParent();
                if (grandGParent == null) {
                    this.root = singleRightRotation(parent);
                    recolorNode = this.root;
                    this.root.setParent(null);
                } else {
                    String ggParentToG = whichSide(grandparent, grandGParent);
                    recolorNode = parent;
                    if (ggParentToG.equals("Left")) {
                        grandGParent.setLeft(singleRightRotation(parent));
                    } else {
                        grandGParent.setRight(singleRightRotation(parent));
                    }
                }

            } else if (grandToParent.equals("Right") && parentToChild.equals("Right")) {
                RBNode<T> grandGParent = grandparent.getParent();
                if (grandGParent == null) {
                    this.root = singleLeftRotation(parent);
                    recolorNode = this.root;
                    this.root.setParent(null);
                } else {
                    String ggParentToG = whichSide(grandparent, grandGParent);
                    recolorNode = parent;
                    if (ggParentToG.equals("Left")) {
                        grandGParent.setLeft(singleLeftRotation(parent));
                    } else {
                        grandGParent.setRight(singleLeftRotation(parent));
                    }
                }

            } else if (grandToParent.equals("Right") && parentToChild.equals("Left")) {
                RBNode<T> grandGParent = grandparent.getParent();
                recolorNode = current;
                if (grandGParent == null) {
                    this.root = RLDoubleRotation(current);
                    recolorNode = this.root;
                } else {
                    String ggParentToG = whichSide(grandparent, grandGParent);//error is here
                    if (ggParentToG.equals("Left")) {
                        grandGParent.setLeft(RLDoubleRotation(current));
                    } else {
                        grandGParent.setRight(RLDoubleRotation(current));
                    }
                }
            } else {
                RBNode<T> grandGParent = grandparent.getParent();
                recolorNode = current;
                if (grandGParent == null) {
                    this.root = LRDoubleRotation(current);
                    recolorNode = this.root;
                } else {
                    String ggParentToG = whichSide(grandparent, grandGParent);
                    if (ggParentToG.equals("Left")) {
                        grandGParent.setLeft(LRDoubleRotation(current));
                    } else {
                        grandGParent.setRight(LRDoubleRotation(current));
                    }
                }
            }

            recolorNode.setColor(BLACK);
            if (recolorNode.getLeft() != null)
                recolorNode.getLeft().setColor(RED);
            if (recolorNode.getRight() != null)
                recolorNode.getRight().setColor(RED);

        }

        // CASE 3 - Uncle is red
        if (uncle != null && uncle.getColor() == RED) {
            parent.setColor(BLACK);
            uncle.setColor(BLACK);
            grandparent.setColor(RED);
            fixTreeAfterInsert(grandparent);
        }
    }
    //-----------------------END of insert------------------------------------


    //-----------------------Delete------------------------------------



    private void deleteAccordingSide(RBNode<T> child, RBNode<T> parent) {
        if (whichSide(child, child.getParent()).equals("Left"))
            child.getParent().setLeft(null);
        else child.getParent().setRight(null);
    }

    private void fixDoubleBlack(RBNode<T> doubleBlackNode) {
        //base case
        if (doubleBlackNode == this.root)
            return;

        RBNode<T> parent = doubleBlackNode.getParent(), sibling = getSibling(doubleBlackNode);

        //node black, parent (), sibling black, sibling 2 children are black
        if (doubleBlackNode.getColor() == BLACK && sibling.getColor() == BLACK && checkSiblingChildrenBothBlack(sibling)) {
            sibling.setColor(RED);
            if (parent.getColor() == RED)
                parent.setColor(BLACK);
            else
                fixDoubleBlack(parent);
        }

        //node black, parent any, at least one of the children of sibling is red
        else if (doubleBlackNode.getColor() == BLACK && sibling.getColor() == BLACK && !checkSiblingChildrenBothBlack(sibling)) {
            //100% has more than one red child
            RBNode<T> sRightChild = sibling.getRight();
            RBNode<T> sLeftChild = sibling.getLeft();

            if (whichSide(sibling, parent).equals("Right")) {

                //RR
                //one red child on the left of the sibling
                //sol: recolor left, left to double rotate
                if (sRightChild != null && sRightChild.getColor() == RED) {
                    if (parent.getColor() == BLACK) {
                        sRightChild.setColor(BLACK);
                    } else {
                        sibling.setColor(RED);
                        sRightChild.setColor(BLACK);
                        parent.setColor(BLACK);
                    }
                    singleLeftRotation(sibling);
                } else {
                    //RL
                    //(Right child is 100% black, leftChild is 100% red)
                    //left child is black, RL doubleRotate
                    if (parent.getColor() == BLACK) {
                        sLeftChild.setColor(BLACK);
                    }
                    parent.setColor(BLACK);
                    RLDoubleRotation(sLeftChild);
                }
            } else {
                if (sLeftChild != null && sLeftChild.getColor() == RED) {
                    if (parent.getColor() == BLACK) {
                        sLeftChild.setColor(BLACK);
                    } else {
                        sibling.setColor(RED);
                        sLeftChild.setColor(BLACK);
                        parent.setColor(BLACK);
                    }
                    singleRightRotation(sibling);
                } else {
                    //LR
                    if (parent.getColor() == BLACK) {
                        sRightChild.setColor(BLACK);
                    }
                    parent.setColor(BLACK);
                    LRDoubleRotation(sRightChild);
                }
            }
        } else if (doubleBlackNode.getColor() == BLACK && sibling.getColor() == RED) {
            parent.setColor(RED);
            sibling.setColor(BLACK);
            if (whichSide(sibling, parent).equals("Right"))
                singleLeftRotation(sibling);
            else
                singleRightRotation(sibling);
            fixDoubleBlack(doubleBlackNode);
        }
    }

    private void delete(RBNode<T> node) {
        RBNode<T> parent = node.getParent();
        //Case0: Delete Root
        if (parent == null) {
            this.root = null;
        } else {
            //Case1: node is leaf & RED
            //Tested and works well
            RBNode<T> sibling = getSibling(node);
            if (node.getColor() == RED) {
                deleteAccordingSide(node, parent);
            }

            //node black, parent red, sibling black, children of sibling are black
            //solution : node black, parent black, sibling red
            //Tested and works well
            //Test case used : 10 20 5 30 25 40 15 --> delete 15 40 20
            else if (node.getColor() == BLACK) {
                if (parent.getColor() == RED && sibling == null) {
                    parent.setColor(BLACK);
                    deleteAccordingSide(node, parent);
                } else {
                    fixDoubleBlack(node);
                    deleteAccordingSide(node, parent);
                }

            }

        }
        this.numberOfNodes--;
    }


    public boolean delete(T key) {
        RBNode<T> nodeToBeDeleted = search(this.root, key);
        if (nodeToBeDeleted == null)
            return false;
        delete(getNodeToBeDeletedExactly(nodeToBeDeleted));
        return true;
    }

    //-----------------------END of Delete------------------------------------

    private RBNode<T> swapData(RBNode<T> nodeToBeDeleted, RBNode<T> successor) {
        T dataOfNodeToBeDeleted = nodeToBeDeleted.getData();
        T dataOfSuccessor = successor.getData();
        nodeToBeDeleted.setData(dataOfSuccessor);
        successor.setData(dataOfNodeToBeDeleted);
        //successor has now the value to be deleted
        return successor;
    }

    //-----------------------Rotation------------------------------------
    private RBNode<T> singleRightRotation(RBNode<T> node) {
        RBNode<T> rightChild = node.getRight();
        RBNode<T> parent = node.getParent();
        RBNode<T> grandParent = parent.getParent();
        node.setRight(parent);
        parent.setParent(node);
        parent.setLeft(rightChild);
        if (rightChild != null)
            rightChild.setParent(parent);
        if (grandParent == null) {
            this.root = node;
            node.setParent(null);
        } else {
            String side = whichSide(parent, grandParent);
            if (side.equals("Left")) {
                grandParent.setLeft(node);
                node.setParent(grandParent);
            } else {
                grandParent.setRight(node);
                node.setParent(grandParent);
            }
        }
        return node;
    }

    private RBNode<T> singleLeftRotation(RBNode<T> node) { //node = parent (middle node)
        RBNode<T> leftChild = node.getLeft();
        RBNode<T> parent = node.getParent();
        RBNode<T> grandParent = parent.getParent();
        node.setLeft(parent);
        parent.setParent(node);
        parent.setRight(leftChild);
        if (leftChild != null)
            leftChild.setParent(parent);
        if (grandParent == null) {
            this.root = node;
            node.setParent(null);
        } else {
            String side = whichSide(parent, grandParent);
            if (side.equals("Left")) {
                grandParent.setLeft(node);
                node.setParent(grandParent);
            } else {
                grandParent.setRight(node);
                node.setParent(grandParent);
            }
        }
        return node;
    }

    private RBNode<T> LRDoubleRotation(RBNode<T> node) {
        RBNode<T> grandParent = node.getParent().getParent();
        grandParent.setLeft(singleLeftRotation(node));
        return singleRightRotation(node);
    }

    private RBNode<T> RLDoubleRotation(RBNode<T> node) {
        RBNode<T> grandParent = node.getParent().getParent();
        grandParent.setRight(singleRightRotation(node));
        return singleLeftRotation(node);
    }
    //-----------------------Rotation------------------------------------


    //-----------------------Auxiliary functions------------------------------------
    private String whichSide(RBNode<T> child, RBNode<T> parent) {
        if (parent.getRight() != null && parent.getRight().getData().compareTo(child.getData()) == 0) {
            return "Right";
        }
        return "Left";
    }

    private RBNode<T> getUncle(RBNode<T> child) {
        RBNode<T> grandParent = child.getParent().getParent();
        if (child.getData().compareTo(grandParent.getData()) > 0)
            return grandParent.getLeft();

        return grandParent.getRight();
    }

    private RBNode<T> getInorderSuccessor(RBNode<T> current) {
        //function to get the leftmost child of the parent of the given node
        if (current.getLeft() == null) return current;
        return getInorderSuccessor(current.getLeft());
    }

    private RBNode<T> getSibling(RBNode<T> node) {
        RBNode<T> parent = node.getParent();
        String sideOfChildToParent = whichSide(node, parent);
        if (sideOfChildToParent.equals("Left"))
            return parent.getRight();
        return parent.getLeft();
    }

    private boolean checkSiblingChildrenBothBlack(RBNode<T> sibling) {
        if (sibling == null) return true;
        if (sibling.getLeft() == null || sibling.getLeft().getColor() == BLACK) {
            return sibling.getRight() == null || sibling.getRight().getColor() == BLACK;
        }
        return false;
    }


    private RBNode<T> getOnlyChild(RBNode<T> node) {
        if (node.getLeft() == null && node.getRight() != null)
            return node.getRight();
        else if (node.getLeft() != null && node.getRight() == null) {
            return node.getLeft();
        }
        return null;
    }


    //Tested, It works well
    private RBNode<T> getNodeToBeDeletedExactly(RBNode<T> current) {
        //case1: no children
        if (current.getRight() == null && current.getLeft() == null)
            return current;
        //case2: one child
        RBNode<T> onlyChild = getOnlyChild(current);
        if (onlyChild != null) {
            return getNodeToBeDeletedExactly(swapData(current, onlyChild));
        }
        //case3: successor (100% has 2 children)
        else {
            RBNode<T> successor = getInorderSuccessor(current.getRight());
            return getNodeToBeDeletedExactly(swapData(current, successor));
        }
    }

    //-----------------------END of Auxiliary functions------------------------------------


    //-----------------------SEARCH------------------------------------
    public RBNode<T> search(RBNode<T> current, T data) {
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
    //-----------------------END of SEARCH------------------------------------


    //-----------------------SIZE--------------------------------------
    public int size() {
        return this.numberOfNodes;
    }
    //-----------------------END of SIZE--------------------------------------


    //-----------------------height--------------------------------------
    public int height() {
        return getHeight(this.root);
    }

    private int getHeight(RBNode<T> current) {
        if (current == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(current.getLeft()), getHeight(current.getRight()));
    }
    //-----------------------END of height--------------------------------------


    //-----------------------traverse--------------------------------------
    public void traverse() {
        traverseInorder(this.root);
    }

    private void traverseInorder(RBNode<T> current) {
        if (current == null) {
            return;
        }
        traverseInorder(current.getLeft());
        System.out.println(current.getData() + " " + (current.getColor() == RED ? "R" : "B") + " ");
        traverseInorder(current.getRight());
    }
    //-----------------------END of traverse--------------------------------------

    //-----------------------testing--------------------------------------
    public String test() {
        testTree = "";
        print(this.root);
        return testTree;
    }

    private void print(RBNode<T> current) {
        if (current == null) {
            return;
        }
        print(current.getLeft());
        testTree += current.getData() + " " + (current.getColor() == RED ? "R" : "B") + " ";
        print(current.getRight());
    }
    //-----------------------testing--------------------------------------

}
