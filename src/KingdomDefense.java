import java.util.*;

public class KingdomDefense {
    public static int maxMonstersEliminated(int A, int B, int[] C, int[] D, int[] E) {
        // Separate monsters by type
        PriorityQueue<Monster> pq0 = new PriorityQueue<>();
        PriorityQueue<Monster> pq1 = new PriorityQueue<>();

        for (int i = 0; i < B; i++) {
            Monster monster = new Monster(D[i], E[i]);
            if (C[i] == 0) {
                pq0.add(monster);
            } else {
                pq1.add(monster);
            }
        }

        return Math.max(maxKills(A, pq0, pq1, 0), maxKills(A, pq1, pq0, 0));
    }

    private static int maxKills(int power, PriorityQueue<Monster> firstType, PriorityQueue<Monster> secondType, int count) {
        int maxKills = count;

        // Try killing a monster from the first type
        for (int i = 0; i < firstType.size(); i++) {
            Monster monster = firstType.poll();
            if (monster.health <= power) {
                // Update power and kill this monster
                int newPower = power + monster.increment;
                maxKills = Math.max(maxKills, maxKills(newPower, secondType, firstType, count + 1));
            }
        }

        return maxKills;
    }

    // Helper class to represent a monster
    static class Monster implements Comparable<Monster>{
        int health;
        int increment;

        Monster(int health, int increment) {
            this.health = health;
            this.increment = increment;
        }

        @Override
        public int compareTo(Monster o) {
            if(this.health == o.health) {
                return o.increment - this.increment;
            }
            return this.health - o.health;
        }
    }

    public static void main(String[] args) {
        // Example 1
        int A1 = 1;
        int B1 = 3;
        int[] C1 = {1, 1, 0};
        int[] D1 = {1, 2, 0};
        int[] E1 = {1, 1, 1};
        System.out.println(maxMonstersEliminated(A1, B1, C1, D1, E1)); // Output: 3

        // Example 2
        int A2 = 1;
        int B2 = 1;
        int[] C2 = {1};
        int[] D2 = {2};
        int[] E2 = {1};
        System.out.println(maxMonstersEliminated(A2, B2, C2, D2, E2)); // Output: 0
    }
}

