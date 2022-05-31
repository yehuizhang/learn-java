package com.yehuizhang.design_patterns.solid.liskovsubstitution;

/**
 * Liskov Substitution principle (LSP)
 * Functions that use pointers or references to base classes must be able to use objects of derived classes without knowing it.
 */
public class LiskovSubstitution {

    static void updateRectangleHeight(Rectangle r) {
        int width = r.getWidth();
        r.setHeight(10);
        System.out.println("Expected area of " + (width * 10) + ", got " + r.getArea());
    }


    public static void main(String[] args) {
        Rectangle rc = RectangleFactory.newRectangle(2, 3);
        updateRectangleHeight(rc);

        Rectangle sq = RectangleFactory.newSquare(5);
        updateRectangleHeight(sq);
    }

}

class RectangleFactory {
    public static Rectangle newSquare(int side) {
        return new Rectangle(side, side);
    }

    public static Rectangle newRectangle(int width, int height) {
        return new Rectangle(width, height);
    }
}