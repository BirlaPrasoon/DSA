package leetcode.problems.google.medium.depth_first_search.postOrder;

public class CheckIfTwoBinaryTreesAreEquivalent {
/*

    A binary expression tree is a kind of binary tree used to represent arithmetic expressions.
    Each node of a binary expression tree has either zero or two children. Leaf nodes (nodes with 0 children) correspond to operands (variables),
    and internal nodes (nodes with two children) correspond to the operators. In this problem, we only consider the '+' operator (i.e. addition).

    You are given the roots of two binary expression trees, root1 and root2. Return true if the two binary expression trees are equivalent.
    Otherwise, return false.

    Two binary expression trees are equivalent if they evaluate to the same value regardless of what the variables are set to.


*/

/*
Example 1:

    Input: root1 = [x], root2 = [x]
    Output: true
    Example 2:



    Input: root1 = [+,a,+,null,null,b,c], root2 = [+,+,a,b,c]
    Output: true
    Explanation: a + (b + c) == (b + c) + a

    Example 3:



    Input: root1 = [+,a,+,null,null,b,c], root2 = [+,+,a,b,d]
    Output: false
    Explanation: a + (b + c) != (b + d) + a


    Constraints:

    The number of nodes in both trees are equal, odd and, in the range [1, 4999].
    Node.val is '+' or a lower-case English letter.
    It's guaranteed that the tree given is a valid binary expression tree.


    Follow up: What will you change in your solution if the tree also supports the '-' operator (i.e. subtraction)?

    Nothing, when I'm parent's right child and parent's sign is negative,
    I'll calculate all my subtree counting and subtract them with original counting.
    */

    class Node {
        char val;
        Node left;
        Node right;

        Node() {
            this.val = ' ';
        }

        Node(char val) {
            this.val = val;
        }

        Node(char val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    int count1[] = new int[26];
    int count2[] = new int[26];

    public boolean checkEquivalence(Node root1, Node root2) {
        dfs(root1, count1);
        dfs(root2, count2);
        for (int i = 0; i < 26; i++) {
            if (count1[i] != count2[i]) return false;
        }
        return true;
    }

    private void dfs(Node root, int counter[]) {
        if (root == null) return;
        if (Character.isAlphabetic(root.val)) {
            counter[root.val - 'a']++;
        }
        dfs(root.left, counter);
        dfs(root.right, counter);
    }


}
