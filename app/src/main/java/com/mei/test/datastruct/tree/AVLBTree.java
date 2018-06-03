package com.mei.test.datastruct.tree;

public class AVLBTree<E extends Comparable<E>> {
    Node<E> root;
    int size = 0;
    private static final int LH = 1;
    private static final int RH = -1;
    private static final int EH = 0;

    public void left_rotate(Node<E> x) {
        if (x != null) {
            Node<E> y = x.right;
            // step 1
            x.right = y.left;
            if (y.left != null) {
                y.left.parent = x;
            }
            // step2
            y.parent = x.parent;
            if (x.parent == null) {
                root = y;
            } else {
                if (x.parent.left == x) {
                    x.parent.left = y;

                } else if (x.parent.right == x) {
                    x.parent.right = y;
                }
            }
            //step 3
            y.left = x;
            x.parent = y;
        }
    }

    public void right_rotate(Node<E> y) {
        if (y != null) {
            Node<E> yl = y.left;

            //step1
            y.left = yl.right;
            if (yl.right != null) {
                yl.right.parent = y;
            }

            // step2
            yl.parent = y.parent;
            if (y.parent == null) {
                root = yl;
            } else {
                if (y.parent.left == y) {
                    y.parent.left = yl;
                } else if (y.parent.right == y) {
                    y.parent.right = yl;
                }
            }
            // step3
            yl.right = y;
            y.parent = yl;
        }
    }

    public void rightBalance(Node<E> t) {
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
                    case RH:
                        t.balance = LH;
                        tr.balance = EH;
                        trl.balance = EH;
                        break;
                    case LH:
                        t.balance = EH;
                        tr.balance = RH;
                        trl.balance = EH;
                        break;
                    case EH:
                        t.balance = EH;
                        tr.balance = EH;
                        trl.balance = EH;
                        break;
                }
                right_rotate(t.right);
                left_rotate(t);
                break;
            case EH:
                break;
        }
    }

    public void leftBalance(Node<E> t) {
        Node<E> tl = t.left;
        switch (tl.balance) {
            case LH:
                right_rotate(t);
                tl.balance = EH;
                t.balance = EH;
                break;
            case RH:
                Node<E> tlr = tl.right;
                switch (tlr.balance) {
                    case LH:
                        t.balance = RH;
                        tl.balance = EH;
                        tlr.balance = EH;
                        break;
                    case RH:
                        t.balance = EH;
                        tl.balance = LH;
                        tlr.balance = EH;
                        break;
                    case EH:
                        t.balance = EH;
                        tl.balance = EH;
                        tlr.balance = EH;
                        break;

                    default:
                        break;
                }
                //旋转
                left_rotate(t.left);
                right_rotate(t);
                break;

            default:
                break;
        }
    }

//	public boolean insertElement(E element) {
//		Node<E> t = root;
//		if (t == null) {
//			root = new Node<E>(element, null);
//			size = 1;
//			root.balance = 0;
//			return true;
//		} else {
//			/**
//			 * 插入节点 开始
//			 */
//			int cmp = 0;
//			Node<E> parent;
//			Comparable<? super E> e = (Comparable<? super E>)element;
//			do {
//				parent = t;
//				cmp = e.compareTo(t.elemet);
//				if (cmp < 0) {
//					t= t.left;
//				} else if (cmp > 0) {
//					t= t.right;
//				} else {
//					return false;
//				}
//			} while (t != null);
//			Node<E> child = new Node<E>(element, parent);
//			if (cmp < 0) {
//				parent.left = child;
//			} else {
//				parent.right = child;
//			}
//			/**
//			 * 插入节点 结束
//			 */
//			//节点已经插入
//			// 检查平衡因子，回溯查找
//			while(parent != null) {
//
//			}
//
//		}
//	}

    public class Node<E extends Comparable<E>> {
        E elemet;
        int balance = 0;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E elem, Node<E> pare) {
            this.elemet = elem;
            this.parent = pare;
        }

        public E getElemet() {
            return elemet;
        }

        public void setElemet(E elemet) {
            this.elemet = elemet;
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


}
