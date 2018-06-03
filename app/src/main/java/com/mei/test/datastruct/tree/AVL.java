package com.mei.test.datastruct.tree;

import java.util.LinkedList;

public class AVL<E extends Comparable<E>> {
    private Node<E> root;
    private int size = 0;
    private static final int LH = 1;
    private static final int RH = -1;
    private static final int EH = 0;

    public void midOrderDisplay(Node root) {
        if (root == null) {
            return;
        } else {
            midOrderDisplay(root.left);
            System.out.println("midOrder: " + root.element);
            midOrderDisplay(root.right);
        }
    }

    /**
     * node insert,check balance and keep balance
     *
     * @param <E>
     * @author LK
     */
    public boolean inserElement(E element) {
        Node<E> t = root;
        if (t == null) {
            root = new Node<E>(element, null);
            size = 1;
            root.balance = 0;
            return true;
        } else {
            int cmp = 0;
            Node<E> parent;
            Comparable<? super E> e = (Comparable<? super E>) element;
            do {
                parent = t;
                cmp = e.compareTo(t.element);
                if (cmp < 0) {
                    t = t.left;
                } else if (cmp > 0) {
                    t = t.right;
                } else {
                    return false;
                }
            } while (t != null);
            Node<E> child = new Node<E>(element, parent);
            if (cmp < 0) {
                parent.left = child;
            } else {
                parent.right = child;
            }
            //节点已经插入，
            // 检查平衡，回溯查找
            while (parent != null) {
                cmp = e.compareTo(parent.element);
                //元素在左边插入
                if (cmp < 0) {
                    parent.balance++;
                } else { //元素在右边插入
                    parent.balance--;
                }

                if (parent.balance == 0) {
                    break;
                }
                if (Math.abs(parent.balance) == 2) {
                    //出现平衡问题
                    fixAfterInsertion(parent);
                    break;
                } else {
                    parent = parent.parent;
                }
            }
            size++;
            return true;
        }
    }

    private void fixAfterInsertion(AVL<E>.Node<E> parent) {
        if (parent.balance == 2) {
            leftBalance(parent);
        }
        if (parent.balance == -2) {
            rightBalance(parent);
        }

    }

    private void rightBalance(AVL<E>.Node<E> t) {
        Node<E> tr = t.right;
        switch (tr.balance) {
            case RH:
                left_rotate(t);
                t.balance = EH;
                tr.balance = EH;
                break;
            case LH:
                Node<E> trl = tr.left;
                switch (trl.balance) {
                    case LH:
                        t.balance = EH;
                        tr.balance = RH;
                        break;
                    case RH:
                        t.balance = LH;
                        tr.balance = EH;
                        break;
                    case EH:
                        t.balance = EH;
                        tr.balance = EH;
                        break;

                }
                trl.balance = EH;
                right_rotate(t.right);
                left_rotate(t);
                break;

            default:
                break;
        }

    }

    private void left_rotate(AVL<E>.Node<E> t) {
        if (t != null) {
            Node tr = t.right;
            t.right = tr.left;
            if (tr.left != null) {
                tr.left.parent = t;
            }

            tr.parent = t.parent;
            if (t.parent == null) {
                root = tr;
            } else {
                if (t.parent.left == t) {
                    t.parent.left = tr;
                } else if (t.parent.right == t) {
                    t.parent.right = tr;
                }
            }
            tr.left = t;
            t.parent = tr;
        }
    }

    private void right_rotate(AVL<E>.Node<E> t) {
        if (t != null) {
            Node<E> tl = t.left;
            t.left = tl.right;
            if (tl.right != null) {
                tl.right.parent = t;
            }

            tl.parent = t.parent;
            if (t.parent == null) {
                root = tl;
            } else {
                if (t.parent.left == t) {
                    t.parent.left = tl;
                } else if (t.parent.right == t) {
                    t.parent.right = tl;
                }
            }

            tl.right = t;
            t.parent = tl;
        }
    }

    public Node<E> searchNode(E element) {
        if (root == null) {
            return null;
        } else {
            Node<E> cur = root;
            while (cur != null) {
                if (element.compareTo(cur.element) > 0) {
                    cur = cur.right;
                } else if (element.compareTo(cur.element) < 0) {
                    cur = cur.left;
                } else {
                    return cur;
                }
            }
        }
        return null;
    }


    private void leftBalance(AVL<E>.Node<E> t) {
        Node<E> tl = t.left;
        switch (tl.balance) {
            case LH: // t 的左子树的左边有问题，直接右旋
                right_rotate(t);
                tl.balance = EH;
                t.balance = EH;
                break;
            case RH:
                Node<E> tlr = tl.right;
                switch (tlr.balance) {
                    case RH:
                        t.balance = EH;
                        tlr.balance = EH;
                        tl.balance = LH;
                        break;
                    case LH:
                        t.balance = RH;
                        tl.balance = EH;
                        tlr.balance = EH;
                        break;
                    case EH:
                        t.balance = EH;
                        tl.balance = EH;
                        tlr.balance = EH;
                        break;
                    //统一旋转
                    default:
                        break;
                }
                left_rotate(t.left);
                right_rotate(t);
                break;
            default:
                break;
        }

    }

    class NodeBF {

        int level;
        Node<E> node;

        public NodeBF(Node node, int lev) {
            this.node = node;
            level = lev;
        }

    }

    class Node<E extends Comparable<E>> {
        E element; // data
        int balance = 0; // balance value
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return element + "BF: " + balance;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }


    }

    public Node<E> getRoot() {
        return root;
    }

    public void levelDisplay(Node root) {
        LinkedList<NodeBF> list = new LinkedList<>();
        NodeBF nodeBF = new NodeBF(root, 0);
        list.offer(nodeBF);
        while (!list.isEmpty()) {
            NodeBF node = list.pop();
            System.out.println(node.node.element + " level: " + node.level);
            if (node.node.left != null) {
                NodeBF nodel = new NodeBF(node.node.left, node.level + 1);
                list.offer(nodel);
            }
            if (node.node.right != null) {
                NodeBF noder = new NodeBF(node.node.right, node.level + 1);
                list.offer(noder);
            }
        }
    }

    public static void main(String[] args) {
//		Integer[] nums = {5, 8, 2, 0, 1, -2, -9, 100};
        Integer[] nums = {5, 8, 2, 0, 1, -2};
        AVL<Integer> vAvlTree = new AVL();
        for (int i = 0; i < nums.length; i++) {
            vAvlTree.inserElement(nums[i]);
        }
//		vAvlTree.midOrderDisplay(vAvlTree.root);
        vAvlTree.levelDisplay(vAvlTree.root);
        System.out.println(vAvlTree.root.element);
    }
}
