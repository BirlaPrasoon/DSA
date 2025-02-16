package algoexpert.stacks;

import java.util.Stack;

public class CollidingAsteroids {

    public int[] collidingAsteroids(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for(int a: asteroids) {
            if(stack.isEmpty() || a >0) {
                stack.push(a);
            } else {
                while(!stack.isEmpty() && stack.peek() >0 && stack.peek() < Math.abs(a)) stack.pop();
                if(!stack.isEmpty() && stack.peek()== Math.abs(a)) stack.pop();
                else if(stack.isEmpty() || stack.peek() < Math.abs(a)) stack.push(a);
            }
        }
        int[] ans = new int[stack.size()];
        int i = 0;
        for(int a: stack) {
            ans[i++] = a;
        }
        return ans;
    }
    public static void main(String[] args) {

    }
}
