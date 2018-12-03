package segmenttree;

import util.ZDaPangArrayUtil;

import java.util.*;

public class LeetCode_218_TheSkylineProblem {
    public static void main(String[] args) {
        int list[][] = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        //[2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0]
        getSkyline(list).forEach(ZDaPangArrayUtil::printArray);
    }

    private static class Point {
        int pos;
        boolean up;
        int height;

        public Point(int pos, boolean up, int height) {
            this.pos = pos;
            this.up = up;
            this.height = height;
        }
    }

    private static class MyComparator implements Comparator<Point> {

        @Override
        public int compare(Point p1, Point p2) {
            if (p1.pos != p2.pos) {
                return p1.pos - p2.pos;
            } else {
                if (p1.up && p2.up) {
                    return p2.height - p1.height;
                }
                if (!p1.up && !p2.up) {
                    return p1.height - p2.height;
                }
                return p1.up ? -1 : 1;
            }
        }

    }

    public static List<int[]> getSkyline(int[][] buildings) {
        Point[] points = new Point[buildings.length * 2];
        int idx = 0;
        for (int[] building : buildings) {
            points[idx++] = new Point(building[0], true, building[2]);
            points[idx++] = new Point(building[1], false, building[2]);
        }
        Arrays.sort(points, new MyComparator());
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 1);
        List<int[]> res = new ArrayList<>();
        int prevMax = 0;
        for (Point point : points) {
            if (point.up) {
                map.put(point.height, map.getOrDefault(point.height, 0) + 1);
            } else {
                int cnt = map.get(point.height);
                if (cnt > 1) {
                    map.put(point.height, cnt - 1);
                } else {
                    map.remove(point.height);
                }
            }
            int currMax = map.lastKey();
            if (currMax != prevMax) {
                res.add(new int[] { point.pos, currMax });
                prevMax = currMax;
            }
        }
        return res;
    }
}
