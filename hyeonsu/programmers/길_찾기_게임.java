package hyeonsu.programmers;

import java.util.*;

class 길_찾기_게임 {

    static List<Integer> preorderNums = new ArrayList<>();
    static List<Integer> postorderNums = new ArrayList<>();

    static Node[] nodes;
    static Integer[][] ans = new Integer[2][];

    public Integer[][] solution(int[][] nodeInfo) {

        nodes = new Node[nodeInfo.length];

        for (int i = 0; i <nodeInfo.length; i++) {
            int x = nodeInfo[i][0];
            int y = nodeInfo[i][1];

            nodes[i] = new Node(i + 1, x, y);
        }

        Arrays.sort(nodes);

        BinaryTree tree = new BinaryTree(nodes[0]);

        for (int i = 1; i < nodes.length; i++) {
            tree.insert(tree.root, nodes[i]);
        }

        tree.preorder(tree.root, preorderNums);
        tree.postorder(tree.root, postorderNums);

        ans[0] = preorderNums.toArray(new Integer[0]);
        ans[1] = postorderNums.toArray(new Integer[0]);

        return ans;
    }

    class BinaryTree {

        Node root;

        public BinaryTree(Node node) {this.root = node;}

        public void insert(Node curr, Node node) {
            if (curr.x > node.x) {
                if (curr.l == null) {
                    curr.l = node;
                } else {
                    insert(curr.l, node);
                }
            } else {
                if (curr.r == null) {
                    curr.r = node;
                } else {
                    insert(curr.r, node);
                }
            }
        }

        public void preorder(Node curr, List<Integer> nums) {
            nums.add(curr.n);
            if (curr.l != null) {
                preorder(curr.l, nums);
            }
            if (curr.r != null) {
                preorder(curr.r, nums);
            }
        }

        public void postorder(Node curr, List<Integer> nums) {
            if (curr.l != null) {
                postorder(curr.l, nums);
            }
            if (curr.r != null) {
                postorder(curr.r, nums);
            }
            nums.add(curr.n);
        }

    }

    class Node implements Comparable<Node> {
        int n;
        int x;
        int y;
        Node l;
        Node r;

        public Node(int n, int x, int y) {
            this.n = n;
            this.x = x;
            this.y = y;
            l = null;
            r = null;
        }

        @Override
        public int compareTo(Node o) {
            if (this.y == o.y) {
                return this.x - o.x;
            }
            return o.y - this.y;
        }
    }
}
