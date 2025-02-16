package leetcode.problems.google.medium.implementation;

import java.util.*;

public class SnakeGame {

    int size;
    int height, width;
    int[][] food;
    HashSet<Point> occupiedPositions;
    LinkedList<Point> snakePositions;
    private final HashMap<String, int[]> DIR;
    private int foodIndex;
    boolean isGameOver;

    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        this.snakePositions = new LinkedList<>();
        this.occupiedPositions = new HashSet<>();
        Point initial = new Point(0,0);
        this.snakePositions.add(initial);
        this.occupiedPositions.add(initial);
        this.foodIndex = 0;
        this.size = 0;
        this.isGameOver = false;
        DIR = new HashMap<>();
        DIR.put("L", new int[]{0,-1});
        DIR.put("U", new int[]{-1,0});
        DIR.put("D", new int[]{1,0});
        DIR.put("R", new int[]{0,+1});
    }

    public int move(String direction) {
        if(this.isGameOver) return -1;
        Point curPoint = snakePositions.peekFirst();
        Point nextPoint = getNextPoint(curPoint, direction);
        if(isOutOfBounds(nextPoint)) {
            this.isGameOver = true;
            return -1;
        }
        if(isNextPointFood(nextPoint)) {
            size++;
            snakePositions.addFirst(nextPoint);
            occupiedPositions.add(nextPoint);
            foodIndex++;
        } else {
            Point lastOccupiedPosition = snakePositions.removeLast();
            occupiedPositions.remove(lastOccupiedPosition);
            snakePositions.addFirst(nextPoint);
            if(occupiedPositions.contains(nextPoint)) {
                this.isGameOver = true;
                return -1;
            }
            occupiedPositions.add(nextPoint);
        }
        return size;
    }

    private boolean isOutOfBounds(Point cur){
        return cur.x<0 || cur.y<0 || cur.x>=height || cur.y>= width;
    }

    private boolean isNextPointFood(Point point) {
        if(foodIndex>= food.length) return false;
        return food[foodIndex][0] == point.x && food[foodIndex][1] == point.y;
    }
    private Point getNextPoint(Point cur, String dir) {
        var nextDir = DIR.get(dir);
        var nextX = cur.x + nextDir[0];
        var nextY = cur.y + nextDir[1];
        return new Point(nextX, nextY);
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Position {" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point point)) return false;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }


    public static void main(String[] args) {
        int[][] food = {{1,0}};
        SnakeGame snakeGame = new SnakeGame(1, 2, food);
//        System.out.println(snakeGame.move("R"));
        System.out.println(snakeGame.move("D"));
        System.out.println(snakeGame.move("R"));
        System.out.println(snakeGame.move("U"));
        System.out.println(snakeGame.move("L"));
        System.out.println(snakeGame.move("U"));
    }
}
