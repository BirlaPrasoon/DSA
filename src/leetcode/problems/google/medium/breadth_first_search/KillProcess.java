package leetcode.problems.google.medium.breadth_first_search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KillProcess {

/*
    You have n processes forming a rooted tree structure. You are given two integer arrays pid and ppid,
    where pid[i] is the ID of the ith process and ppid[i] is the ID of the ith process's parent process.

    Each process has only one parent process but may have multiple children processes.
    Only one process has ppid[i] = 0, which means this process has no parent process (the root of the tree).

    When a process is killed, all of its children processes will also be killed.

    Given an integer kill representing the ID of a process you want to kill, return a list of the IDs of the processes that will be killed. You may return the answer in any order.
*/

    /*
        Input: pid = [1,3,10,5], ppid = [3,0,5,3], kill = 5
        Output: [5,10]
        Explanation: The processes colored in red are the processes that should be killed.
        Example 2:

        Input: pid = [1], ppid = [0], kill = 1
        Output: [1]
    */
    public class Solution {
        class Node {
            int val;
            List<Node> children = new ArrayList<>();
        }

        public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
            HashMap<Integer, Node> map = new HashMap<>();
            for (int id : pid) {
                Node node = new Node();
                node.val = id;
                map.put(id, node);
            }
            for (int i = 0; i < ppid.size(); i++) {
                if (ppid.get(i) > 0) {
                    Node par = map.get(ppid.get(i));
                    par.children.add(map.get(pid.get(i)));
                }
            }
            List<Integer> l = new ArrayList<>();
            l.add(kill);
            getAllChildren(map.get(kill), l);
            return l;
        }

        public void getAllChildren(Node pn, List<Integer> l) {
            for (Node n : pn.children) {
                l.add(n.val);
                getAllChildren(n, l);
            }
        }
    }

}
