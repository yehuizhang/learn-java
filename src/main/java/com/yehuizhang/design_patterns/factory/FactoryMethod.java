package com.yehuizhang.design_patterns.factory;

class Point {
    private final double x;
    private final double y;

    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static class Factory {
        // factory method
        public static Point newCartesianPoint(double x, double y) {
            return new Point(x, y);
        }

        // factory method
        public static Point newPolarPoint(double rho, double theta) {
            return new Point(rho * Math.cos(theta), rho * Math.sin(theta));
        }
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

public class FactoryMethod {
    public static void main(String[] args) {
        Point cartesian = Point.Factory.newCartesianPoint(10, 20);
        System.out.println("cartesian point: " + cartesian);

        Point polar = Point.Factory.newPolarPoint(10 ,20);
        System.out.println("Polar point: " + polar);

    }
}
