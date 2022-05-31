package com.yehuizhang.design_patterns.adapter;

interface Rectangle {
    int getWidth();

    int getHeight();

    default int getArea() {
        return getWidth() * getHeight();
    }
}

class Square {
    public int side;

    public Square(int side) {
        this.side = side;
    }
}

class SquareToRectangleAdapter implements Rectangle {
    private final Square s;

    public SquareToRectangleAdapter(Square square) {
        this.s = square;
    }


    @Override
    public int getWidth() {
        return s.side;
    }

    @Override
    public int getHeight() {
        return s.side;
    }
}

public class AdapterExercise {
}
