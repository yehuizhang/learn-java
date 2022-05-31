package com.yehuizhang.design_patterns.bridge;

interface Renderer {
    String whatToRenderAs();
}

abstract class Shape {
    private final Renderer renderer;
    public Shape(Renderer renderer) {
        this.renderer = renderer;
    }

    public abstract String getName();

    @Override
    public String toString() {
        return String.format("Drawing %s as %s", getName(), renderer.whatToRenderAs());
    }
}

class VectorRenderer implements Renderer {

    @Override
    public String whatToRenderAs() {
        return "lines";
    }
}

class RasterRenderer implements  Renderer {

    @Override
    public String whatToRenderAs() {
        return "pixels";
    }
}

class Triangle extends Shape {

    public Triangle(Renderer renderer) {
        super(renderer);
    }

    @Override
    public String getName() {
        return "Triangle";
    }
}

class Square extends Shape {
    public Square(Renderer renderer) {
        super(renderer);
    }

    @Override
    public String getName() {
        return "Square";
    }
}

public class BridgeExercise {
    public static void main(String[] args) {
        System.out.println(new Triangle(new RasterRenderer()));
    }
}
