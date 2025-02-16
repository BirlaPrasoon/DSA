package leetcode.problems.google.hard.graphs.dpOnGraph;

import java.util.*;

public class Leetcode818RaceCar {
    List<Integer[]> queue = new ArrayList<Integer[]>();
    HashSet<Integer> visited = new HashSet<Integer>();
    int moves, position, speed = 0;
    int PRIME = 101;

    public int racecar(int target) {
        queue.add(new Integer[]{0, 0, 1}); // #moves, position, speed

        while (!queue.isEmpty()) {
            moves = queue.get(0)[0];
            position = queue.get(0)[1];
            speed = queue.get(0)[2];
            queue.remove(0);

            int key = code(position, speed);
            if (position == target) {
                return moves;
            } else if (!visited.contains(key)) {
                visited.add(key);
                int newPosition = position + speed;
                queue.add(new Integer[]{moves + 1, newPosition, speed * 2});
                if ((newPosition > target && speed > 0) || (newPosition < target && speed < 0)) {
                    // Reverse direction but position remains the same.
                    queue.add(new Integer[]{moves + 1, position, speed > 0 ? -1 : 1});
                }
            }
        }
        return moves;
    }

    private int code(int i, int j) {
        return (int) (Math.pow(PRIME, 3) * i + Math.pow(PRIME, 1) * j);
    }

    public static void main(String[] args) {
        SolutionMine obj = new SolutionMine();
        System.out.println(obj.racecar(6));
    }


    static class SolutionMine {


        public int racecar(int target) {
            if (target == 0) return 0;
            int moves = 0;

            HashSet<State> visited= new HashSet<>();
            Queue<State> q = new LinkedList<>();
            State initial = new State(0, 1, 0);
            q.add(initial);
            visited.add(initial);
            while(!q.isEmpty()) {
                State cur = q.poll();

//                System.out.println("CUR: " + cur);
                if(cur.position == target) return cur.moves;
                // A
                State newPosition = new State(cur.position + cur.speed, cur.speed*2, cur.moves+1);

                if(visited.contains(newPosition)) continue;
                visited.add(newPosition);
                q.add(newPosition);
                // if we don't have this check, time goes from 4ms to 400 ms.
                if((newPosition.position > target && newPosition.speed > 0) || newPosition.position < target && newPosition.speed <0) {
                    // R
                    State newReversePosition = new State(cur.position, cur.speed > 0 ? -1:1, cur.moves+1);
                    if(visited.contains(newReversePosition)) continue;
                    visited.add(newReversePosition);
                    q.add(newReversePosition);
                }
            }

            return -1;
        }

    }
    static class State {
        int position;
        int speed;
        int moves;

        public State(int position, int speed, int moves) {
            this.position = position;
            this.speed = speed;
            this.moves = moves;
        }

        @Override
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof State state)) return false;

            return position == state.position && speed == state.speed;
        }

        @Override
        public int hashCode() {
            int result = position;
            result = 31 * result + speed;
            return result;
        }

        @Override
        public String toString() {
            return "State{" +
                    "position=" + position +
                    ", speed=" + speed +
                    ", moves=" + moves +
                    '}';
        }
    }
    class SolutionMine2 {
        public int racecar(int target) {
            Queue<State> q = new LinkedList<>();
            HashSet<State> visited = new HashSet<>();
            State initial= new State(0, 1, 0);
            visited.add(initial);
            q.add(initial);
            while(!q.isEmpty()) {
                State cur = q.poll();
                if(cur.position == target) return cur.moves;
                State nextAccelleratedPosition = new State(cur.position + cur.speed, cur.speed*2, cur.moves+1);
                if(visited.contains(nextAccelleratedPosition)) continue;
                visited.add(nextAccelleratedPosition);
                q.add(nextAccelleratedPosition);
                if((nextAccelleratedPosition.position > target && nextAccelleratedPosition.speed > 0) || (nextAccelleratedPosition.position < target && nextAccelleratedPosition.speed < 0)){
                    State nextReversePosition = new State(cur.position, cur.speed > 0 ? -1:1, cur.moves+1);
                    if(visited.contains(nextReversePosition)) continue;
                    visited.add(nextReversePosition);
                    q.add(nextReversePosition);
                }
            }
            return -1;
        }
    }
}
