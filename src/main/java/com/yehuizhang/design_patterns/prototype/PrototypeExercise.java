package com.yehuizhang.design_patterns.prototype;

class Point {
    public int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point copy) {
        this.x = copy.x;
        this.y = copy.y;
    }
}

class Line {
    public Point start, end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Line deepCopy() {
        return new Line(new Point(start), new Point(end));
    }
}


public class PrototypeExercise {
}
