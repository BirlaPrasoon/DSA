package algoexpert.graphs;

import java.util.HashSet;
import java.util.List;

public class RectangleMania {

    public static int rectangleMania(List<Integer[]> coords) {
        HashSet<Point> coordinates = new HashSet<>();
        for(Integer[] pair: coords) {
            coordinates.add(new Point(pair[0], pair[1]));
        }
        int count = 0;
        for(Point p1: coordinates) {
            for(Point p2: coordinates) {
                if(p2.x <= p1.x || p2.y <= p1.y) continue;
                else if(coordinates.contains(new Point(p1.x, p2.y)) && coordinates.contains(new Point(p2.x, p1.y))){
                    count++;
                }
            }
        }
        return count;
    }

    static class Point {
        public int x;
        public int y;


        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if(this == o) return true;
            if(!(o instanceof Point)) return false;
            Point p = (Point)o;
            return p.x == x && p.y == y;
        }

        @Override
        public int hashCode() {
            return (x + ":" + y).hashCode();
        }

    }

    public static void main(String[] args) {
        System.out.println();
    }
}
