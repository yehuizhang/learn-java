package com.yehuizhang.design_patterns.solid.openclosed;

public class Product {

    public Color color;
    public Size size;
    public String name;

    public Product(String name, Color color, Size size) {
        this.color = color;
        this.size = size;
        this.name = name;
    }
}