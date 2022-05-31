package com.yehuizhang.design_patterns.solid.liskovsubstitution;

class Rectangle {
    protected int width, height;

    public Rectangle() {
    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }

    public boolean isSquare() {
        return width == height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}


class Square extends Rectangle {
    public Square() {
    }

    public Square(int size) {
        width = height = size;
    }

    @Override
    /*
      This override violates LSP since updateRectangleHeight which uses base case delivered unexpected result on
      derived class - Square
     */
    public void setWidth(int side) {
        super.setWidth(side);
        super.setHeight(side);
    }

    @Override
    public void setHeight(int side) {
        super.setHeight(side);
        super.setWidth(side);
    }
}

public class LspViolationExample {
    static void updateRectangleHeight(Rectangle r) {
        int width = r.getWidth();
        r.setHeight(10);
        System.out.println("Expected area of " + (width * 10) + ", got " + r.getArea());
    }

    public static void main(String[] args) {
        Rectangle rc = new Rectangle(2, 3);
        updateRectangleHeight(rc);

        Rectangle sq = new Square(5);
        updateRectangleHeight(sq);
    }
}
