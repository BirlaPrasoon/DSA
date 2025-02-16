package leetcode.problems.google.medium.depth_first_search;

import java.util.*;

public class OperationsOnATree {

/*
    You are given a tree with n nodes numbered from 0 to n - 1 in the form of a parent array parent where parent[i] is the parent of the ith node. The root of the tree is node 0, so parent[0] = -1 since it has no parent. You want to design a data structure that allows users to lock, unlock, and upgrade nodes in the tree.

    The data structure should support the following functions:

    Lock: Locks the given node for the given user and prevents other users from locking the same node. You may only lock a node using this function if the node is unlocked.
    Unlock: Unlocks the given node for the given user. You may only unlock a node using this function if it is currently locked by the same user.
    Upgrade: Locks the given node for the given user and unlocks all of its descendants regardless of who locked it. You may only upgrade a node if all 3 conditions are true:
    The node is unlocked,
    It has at least one locked descendant (by any user), and
    It does not have any locked ancestors.
    Implement the LockingTree class:

    LockingTree(int[] parent) initializes the data structure with the parent array.
    lock(int num, int user) returns true if it is possible for the user with id user to lock the node num, or false otherwise. If it is possible, the node num will become locked by the user with id user.
            unlock(int num, int user) returns true if it is possible for the user with id user to unlock the node num, or false otherwise. If it is possible, the node num will become unlocked.
            upgrade(int num, int user) returns true if it is possible for the user with id user to upgrade the node num, or false otherwise. If it is possible, the node num will be upgraded.
*/


    class LockingTree {
        // array for lock, hashmaps: parent and child
        int[] lock;
        Map<Integer, Integer> parent;
        Map<Integer, List<Integer>> child;

        public LockingTree(int[] parent) {
            this.lock = new int[parent.length];
            Arrays.fill(this.lock, -1);
            this.parent = new HashMap<>();
            this.child = new HashMap<>();
            for (int i = 0; i < parent.length; i++) {
                this.parent.put(i, parent[i]);
                this.child.putIfAbsent(parent[i], new ArrayList<>());
                this.child.get(parent[i]).add(i);
            }
        }

        public boolean lock(int num, int user) {
            if (this.lock[num] == -1) {
                this.lock[num] = user;
                return true;
            }
            return false;
        }

        public boolean unlock(int num, int user) {
            if (this.lock[num] == user) {
                this.lock[num] = -1;
                return true;
            }
            return false;
        }

        public boolean upgrade(int num, int user) {
            int cur = num;
            while (this.parent.containsKey(cur)) {
                if (cur != -1 && this.lock[cur] != -1)
                    return false;
                cur = this.parent.get(cur);
            }
            List<Integer> lock_child = new ArrayList<>();
            dfs(num, lock_child);
            if (lock_child.size() > 0) {
                this.lock[num] = user;
                for (int val : lock_child)
                    this.lock[val] = -1;
                return true;
            }
            return false;
        }

        void dfs(int num, List<Integer> lock_child) {
            for (int val : this.child.getOrDefault(num, List.of())) {
                if (this.lock[val] != -1)
                    lock_child.add(val);
                dfs(val, lock_child);
            }
        }
    }

}
